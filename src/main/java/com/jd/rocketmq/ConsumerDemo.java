package com.jd.rocketmq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.util.List;

/**
 * @author lk
 * @version 1.0
 * @date 2020/8/23 17:55
 */
public class ConsumerDemo {
	public static void main(String[] args) throws Exception {
		DefaultMQPushConsumer myConsumer = new DefaultMQPushConsumer("MyConsumer");
		myConsumer.setNamesrvAddr("111.231.107.218：9876");

		myConsumer.subscribe("MyTopic", "*");

		myConsumer.setMessageListener(new MessageListenerConcurrently() {
			@Override
			public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list,
																											ConsumeConcurrentlyContext consumeConcurrentlyContext) {
				for (MessageExt msg : list) {
					System.out.println(new String(msg.getBody()));
				}
				// 默认情况下 这条消息只会被 一个consumer 消费到 点对点
				// message 状态修改
				// ack
				return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
				//消费失败
//				return ConsumeConcurrentlyStatus.RECONSUME_LATER;
			}
		});
		myConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_TIMESTAMP);
		myConsumer.setConsumeTimeout(1000L);
		// 讲道理 冲突，首先，别这么干
		myConsumer.setMessageModel(MessageModel.CLUSTERING);
		//广播机制
//		myConsumer.setMessageModel(MessageModel.BROADCASTING);
		myConsumer.start();
	}
}
