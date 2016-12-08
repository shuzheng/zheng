package com.zheng.cms.web.jms;

import com.zheng.cms.dao.model.User;
import com.zheng.cms.service.UserService;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.jms.Message;
import javax.jms.TextMessage;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ZhangShuzheng on 2016/12/8.
 */
@Component
public class MessageListener extends MessageListenerAdapter {

	private static Logger _log = LoggerFactory.getLogger(defaultQueueMessageListener.class);

	@Autowired
	ThreadPoolTaskExecutor threadPoolTaskExecutor;

	@Autowired
	UserService userService;

	@JmsListener(containerFactory = "connectionFactory", destination = "defaultQueueDestination")
	public void processOrder(final Message message) {
		// 使用线程池多线程处理
		threadPoolTaskExecutor.execute(new Runnable() {
			public void run() {
				TextMessage textMessage = (TextMessage) message;
				try {
					String text = textMessage.getText();
					JSONObject json = JSONObject.fromObject(text);
					User user = (User) JSONObject.toBean(json, User.class);
					if (user.getUsername().equals("1")) {
						_log.info("消费开始时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis())));
					}
					if (user.getUsername().equals("1000")) {
						_log.info("消费结束时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis())));
					}
					userService.getMapper().insertSelective(user);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
