package com.zheng.upms.server.shiro;

import com.zheng.common.util.RedisUtil;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.io.*;
import java.util.List;
import java.util.Set;

/**
 * 基于redis的sessionDao，缓存共享session
 * Created by shuzheng on 2017/2/23.
 */
public class UpmsSessionDao extends EnterpriseCacheSessionDAO {

    private static Logger _log = LoggerFactory.getLogger(UpmsSessionDao.class);
    // 全局会话cookie的key
    private final static String ZHENG_UPMS_SERVER_SESSION_ID = "zheng-upms-server-session-id";
    // 全局会话redis的key
    private final static String ZHENG_UPMS_SHIRO_SESSION_ID = "zheng-upms-shiro-session-id";
    // 全局会话redis的id列表key
    private final static String ZHENG_UPMS_SHIRO_SESSION_IDS = "zheng-upms-shiro-session-ids";
    // token key
    private final static String ZHENG_UPMS_SERVER_TOKEN = "zheng-upms-server-token";
    // 局部会话key
    private final static String ZHENG_UPMS_CLIENT_SESSION_ID = "zheng-upms-client-session-id";
    // 单点同一个token所有局部会话key
    private final static String ZHENG_UPMS_CLIENT_SESSION_IDS = "zheng-upms-client-session-ids";

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = super.doCreate(session);
        RedisUtil.set(sessionToByte(ZHENG_UPMS_SHIRO_SESSION_ID + "_" + sessionId), sessionToByte(session));
        _log.debug("[UpmsSessionDao]创建session: sessionId={}", session.getId());
        // 维护会话id列表，提供会话分页管理
        Jedis jedis = RedisUtil.getJedis();
        jedis.lpush(ZHENG_UPMS_SHIRO_SESSION_IDS, sessionId.toString());
        jedis.close();
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        // 先从缓存中获取session，如果没有再去redis中获取
        Session session = super.doReadSession(sessionId);
        if(session == null){
            byte[] bytes = RedisUtil.get(sessionId.toString().getBytes());
            if(null != bytes && bytes.length > 0){
                session = (Session) byteToSession(bytes);
                _log.debug("[UpmsSessionDao]redis中获取session: sessionId={}", session.getId());
            }
        }
        return session;
    }

    @Override
    protected void doUpdate(Session session) {
        // 更新session的最后一次访问时间
        super.doUpdate(session);
        RedisUtil.set(session.getId().toString().getBytes(), sessionToByte(session));
        _log.debug("[UpmsSessionDao]redis中更新session: sessionId={}", session.getId());
    }

    @Override
    protected void doDelete(Session session) {
        // 删除session前，清空所有注册的局部会话
        String serverSessionId = session.getId().toString();
        // 当前全局会话token
        String token = RedisUtil.get(ZHENG_UPMS_SERVER_SESSION_ID + "_" + serverSessionId);
        // 清除全局会话
        RedisUtil.remove(ZHENG_UPMS_SERVER_SESSION_ID + "_" + serverSessionId);
        // 清除token校验值
        RedisUtil.remove(ZHENG_UPMS_SERVER_TOKEN + "_" + token);
        // 清除所有局部会话
        Jedis jedis = RedisUtil.getJedis();
        Set<String> clientSessionIds = jedis.smembers(ZHENG_UPMS_CLIENT_SESSION_IDS + "_" + token);
        for (String clientSessionId : clientSessionIds) {
            jedis.del(ZHENG_UPMS_CLIENT_SESSION_ID + "_" + clientSessionId);
            jedis.srem(ZHENG_UPMS_CLIENT_SESSION_IDS + "_" + token, clientSessionId);
        }
        _log.debug("当前token={}，对应的注册系统个数：{}个", token, jedis.scard(ZHENG_UPMS_CLIENT_SESSION_IDS + "_" + token));
        jedis.close();

        // 删除session
        super.doDelete(session);
        RedisUtil.remove(sessionToByte(ZHENG_UPMS_SHIRO_SESSION_ID + "_" + serverSessionId));
        _log.debug("[UpmsSessionDao]redis中删除session: sessionId={}", session.getId());

        // 维护会话id列表，提供会话分页管理
        jedis = RedisUtil.getJedis();
        jedis.lrem(ZHENG_UPMS_SHIRO_SESSION_IDS, 1, serverSessionId);
        jedis.close();
    }

    /**
     * 获取会话列表
     * @param page
     * @param size
     * @return
     */
    List<Session> getActiveSessions(int page, int size) {
        // TODO
        return null;
    }

    // 把Object对象转化为byte保存到redis中
    public byte[] sessionToByte(Object session) {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        byte[] bytes = null;
        try {
            ObjectOutputStream oo = new ObjectOutputStream(bo);
            oo.writeObject(session);
            bytes = bo.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    // 把byte还原为Object
    public Object byteToSession(byte[] bytes) {
        ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
        ObjectInputStream in;
        SimpleSession session = null;
        try {
            in = new ObjectInputStream(bi);
            session = (SimpleSession) in.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return session;
    }

}
