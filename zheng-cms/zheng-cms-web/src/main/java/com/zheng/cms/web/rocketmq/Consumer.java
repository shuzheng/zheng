package com.zheng.cms.web.rocketmq;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * Created by ZhangShuzheng on 2016/12/8.
 */
public class Consumer {

	public static void main(String[] args){
		DefaultMQPushConsumer consumer =
				new DefaultMQPushConsumer("PushConsumer");
		consumer.setNamesrvAddr("127.0.0.1:9876");
		try {
			//订阅PushTopic下Tag为push的消息
			consumer.subscribe("PushTopic", "push");
			//程序第一次启动从消息队列头取数据
			consumer.setConsumeFromWhere(
					ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
			consumer.registerMessageListener(
					new MessageListenerConcurrently() {
						public ConsumeConcurrentlyStatus consumeMessage(
								List<MessageExt> list,
								ConsumeConcurrentlyContext Context) {
							Message msg = list.get(0);
							System.out.println(msg.toString());
							return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
						}
					}
			);
			consumer.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
