package com.zheng.cms.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 消息队列controller
 * @author shuzheng
 * @date 2016年11月24日
 */
@Controller
@RequestMapping("/activemq")
public class ActiveMQController extends BaseController {

	private static Logger _log = LoggerFactory.getLogger(ActiveMQController.class);

//	@Autowired
//	JmsTemplate jmsTemplate;
//
//	@Autowired
//	Destination defaultQueueDestination;
//
//	@RequestMapping("/send")
//	@ResponseBody
//	public Object send() {
//		for (int i = 0; i < 1000; i ++) {
//			JmsUtil.sendMessage(jmsTemplate, defaultQueueDestination, "消息" + i);
//		}
//		return "success";
//	}

}