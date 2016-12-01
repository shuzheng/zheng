package com.zheng.cms.jms;

import com.zheng.cms.dao.model.User;
import com.zheng.cms.service.UserService;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * MQ消费者
 * Created by ZhangShuzheng on 2016/11/24.
 */
public class defaultQueueMessageListener implements MessageListener {

    private static Logger _log = LoggerFactory.getLogger(defaultQueueMessageListener.class);

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
                    User user = (User) JSONObject.toBean(json, User.class);
                    userService.getMapper().insertSelective(user);
                    _log.info("zheng-cms-mq接收到：{}", text);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
