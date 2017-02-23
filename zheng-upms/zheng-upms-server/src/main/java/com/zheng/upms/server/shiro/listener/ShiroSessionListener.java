package com.zheng.upms.server.shiro.listener;

import com.zheng.upms.server.controller.manage.UpmsOrganizationController;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by shuzheng on 2017/2/12.
 */
public class ShiroSessionListener implements SessionListener {

    private static Logger _log = LoggerFactory.getLogger(ShiroSessionListener.class);

    @Override
    public void onStart(Session session) {
        _log.debug("会话创建：" + session.getId());
    }

    @Override
    public void onStop(Session session) {
        _log.debug("会话停止：" + session.getId());
    }

    @Override
    public void onExpiration(Session session) {
        _log.debug("会话过期：" + session.getId());
    }

}
