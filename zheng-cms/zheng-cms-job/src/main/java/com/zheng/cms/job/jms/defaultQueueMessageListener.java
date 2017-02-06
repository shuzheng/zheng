package com.zheng.cms.job.jms;

import com.zheng.cms.dao.model.CmsUser;
import com.zheng.cms.rpc.api.UserService;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * MQ消费者
 * Created by ZhangShuzheng on 2016/11/24.
 */
public class DefaultQueueMessageListener implements MessageListener {

    private static Logger _log = LoggerFactory.getLogger(DefaultQueueMessageListener.class);

    @Autowired
    ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Autowired
    UserService userService;

    public void onMessage(final Message message) {
        // 使用线程池多线程处理
        threadPoolTaskExecutor.execute(new Runnable() {
            public void run() {
                TextMessage textMessage = (TextMessage) message;
                try {
                    String text = textMessage.getText();
                    JSONObject json = JSONObject.fromObject(text);
                    CmsUser user = (CmsUser) JSONObject.toBean(json, CmsUser.class);
                    if (user.getUsername().equals("1")) {
                        _log.info("消费开始时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis())));
                    }
                    if (user.getUsername().equals("1000")) {
                        _log.info("消费结束时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis())));
                    }
                    userService.insertSelective(user);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
