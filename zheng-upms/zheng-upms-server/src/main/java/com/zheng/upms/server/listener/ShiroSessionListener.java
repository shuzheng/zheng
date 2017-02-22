package com.zheng.upms.server.listener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

/**
 * Created by shuzheng on 2017/2/12.
 */
public class ShiroSessionListener implements SessionListener {

    @Override
    public void onStart(Session session) {
        System.out.println(session.toString());
        System.out.println("会话创建：" + session.getId());
    }

    @Override
    public void onStop(Session session) {
        System.out.println(session.toString());
        System.out.println("会话停止：" + session.getId());
    }

    @Override
    public void onExpiration(Session session) {
        System.out.println(session.toString());
        System.out.println("会话过期：" + session.getId());
    }

}
