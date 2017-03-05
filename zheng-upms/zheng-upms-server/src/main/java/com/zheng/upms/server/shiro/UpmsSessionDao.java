package com.zheng.upms.server.shiro;

import com.zheng.common.util.RedisUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.io.*;
import java.util.*;

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
        RedisUtil.set((ZHENG_UPMS_SHIRO_SESSION_ID + "_" + sessionId).getBytes(), sessionToByte(session), (int) session.getTimeout() / 1000);
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
            byte[] bytes = RedisUtil.get((ZHENG_UPMS_SHIRO_SESSION_ID + "_" + sessionId).getBytes());
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
        UpmsSession upmsSession = (UpmsSession) session;
        UpmsSession cacheUpmsSession = (UpmsSession) doReadSession(session.getId());
        upmsSession.setStatus(cacheUpmsSession.getStatus());
        upmsSession.setAttribute("FORCE_LOGOUT", cacheUpmsSession.getAttribute("FORCE_LOGOUT"));
        super.doUpdate(session);
        RedisUtil.set((ZHENG_UPMS_SHIRO_SESSION_ID + "_" + session.getId()).getBytes(), sessionToByte(session), (int) session.getTimeout() / 1000);
        _log.debug("[UpmsSessionDao]redis中更新session: sessionId={}, seconds={}", session.getId(), (int) session.getTimeout() / 1000);
    }

    @Override
    protected void doDelete(Session session) {
        // 删除session前，清空所有注册的局部会话
        String serverSessionId = session.getId().toString();
        // 当前全局会话token
        String token = RedisUtil.get(ZHENG_UPMS_SERVER_SESSION_ID + "_" + serverSessionId);
        // 清除全局会话
        // RedisUtil.remove(ZHENG_UPMS_SERVER_SESSION_ID + "_" + serverSessionId);
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
        RedisUtil.remove((ZHENG_UPMS_SHIRO_SESSION_ID + "_" + serverSessionId).getBytes());
        _log.debug("[UpmsSessionDao]redis中删除session: sessionId={}", session.getId());

        // 维护会话id列表，提供会话分页管理
        jedis = RedisUtil.getJedis();
        jedis.lrem(ZHENG_UPMS_SHIRO_SESSION_IDS, 1, serverSessionId);
        jedis.close();
    }

    /**
     * 获取会话列表
     * @param offset
     * @param limit
     * @return
     */
    public Map getActiveSessions(int offset, int limit) {
        Map sessions = new HashMap();
        Jedis jedis = RedisUtil.getJedis();
        // 获取在线会话总数
        long total = jedis.llen(ZHENG_UPMS_SHIRO_SESSION_IDS);
        // 获取当前页会话详情
        List<String> ids = jedis.lrange(ZHENG_UPMS_SHIRO_SESSION_IDS, offset, (offset + limit - 1));
        List<Session> rows = new ArrayList<>();
        for (String id : ids) {
            byte[] bytes = RedisUtil.get((ZHENG_UPMS_SHIRO_SESSION_ID + "_" + id).getBytes());
            if(null != bytes && bytes.length > 0){
                Session session = (Session) byteToSession(bytes);
                rows.add(session);
            }
        }
        jedis.close();
        sessions.put("total", total);
        sessions.put("rows", rows);
        return sessions;
    }

    /**
     * 强制退出
     * @param ids
     * @return
     */
    public int forceout(String ids) {
        String[] sessionIds = ids.split(",");
        for (String serverSessionId : sessionIds) {
            // 清空所有注册的局部会话和token
            // 当前全局会话token
            String token = RedisUtil.get(ZHENG_UPMS_SERVER_SESSION_ID + "_" + serverSessionId);
            // 清除全局会话
            // RedisUtil.remove(ZHENG_UPMS_SERVER_SESSION_ID + "_" + serverSessionId);
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

            // 会话增加强制退出属性标识，当此会话访问系统时，判断有该标识，则退出登录
            byte[] bytes = RedisUtil.get((ZHENG_UPMS_SHIRO_SESSION_ID + "_" + serverSessionId).getBytes());
            if(null != bytes && bytes.length > 0){
                UpmsSession session = (UpmsSession) byteToSession(bytes);
                session.setStatus(UpmsSession.OnlineStatus.force_logout);
                session.setAttribute("FORCE_LOGOUT", "FORCE_LOGOUT");
                super.doUpdate(session);
                RedisUtil.set((ZHENG_UPMS_SHIRO_SESSION_ID + "_" + session.getId()).getBytes(), sessionToByte(session), (int) session.getTimeout() / 1000);
            }
        }
        return sessionIds.length;
    }

    /**
     * 更改在线状态
     * @param sessionId
     * @param onlineStatus
     */
    public void updateStatus(Serializable sessionId, UpmsSession.OnlineStatus onlineStatus) {
        UpmsSession session = (UpmsSession) doReadSession(sessionId);
        session.setStatus(onlineStatus);
        super.doUpdate(session);
        RedisUtil.set((ZHENG_UPMS_SHIRO_SESSION_ID + "_" + session.getId()).getBytes(), sessionToByte(session), (int) session.getTimeout() / 1000);
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
