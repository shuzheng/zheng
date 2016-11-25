package com.zheng.cms.controller;

import com.zheng.common.util.JmsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jms.Destination;

/**
 * 消息队列controller
 * @author shuzheng
 * @date 2016年11月24日
 */
@Controller
@RequestMapping("/activemq")
public class ActiveMQController extends BaseController {

	private static Logger _log = LoggerFactory.getLogger(ActiveMQController.class);

	@Autowired
	JmsTemplate jmsQueueTemplate;

	@Autowired
	Destination defaultQueueDestination;

	@Autowired
	ThreadPoolTaskExecutor threadPoolTaskExecutor;

	@RequestMapping("/send")
	@ResponseBody
	public Object send() {
		long start = System.currentTimeMillis();
		for (int i = 0; i < 100; i ++) {
			_log.info("发送消息" + (i + 1));
			final long time = System.currentTimeMillis();
			threadPoolTaskExecutor.execute(new Runnable() {
				public void run() {
					JmsUtil.sendMessage(jmsQueueTemplate, defaultQueueDestination, "消息" + time);
				}
			});
		}
		_log.info("发送消息消耗时间" + (System.currentTimeMillis() - start));
		return "success";
	}

}