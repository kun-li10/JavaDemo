package com.jd.rocketmq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.util.List;


/**
 * 发送消息时指定固定的Queue发送消息
 * 顺序消费
 * @author lk
 * @version 1.0
 * @date 2020/8/23 18:20
 */
public class TopicQueue {
	public static void main(String[] args) throws Exception{
		DefaultMQProducer producer = new DefaultMQProducer("myProducer");
		producer.setNamesrvAddr("111.231.107.218:9876");
		producer.setRetryTimesWhenSendFailed(3);
		producer.setSendMsgTimeout(1000);

		Message message = new Message("myTopic", "MyQueue".getBytes());

		producer.send(message, new MessageQueueSelector() {
			//指定固定的Queue发送消息
			@Override
			public MessageQueue select(List<MessageQueue> list, Message message, Object arg) {
				System.out.println(arg);
				return list.get(Integer.parseInt(String.valueOf(arg)));
			}
		},1,2000);

		producer.start();



		DefaultMQPushConsumer myConsumer = new DefaultMQPushConsumer("myConsumer");
		myConsumer.setNamesrvAddr("111.231.107.218:9876");
		myConsumer.setConsumeTimeout(5000);
		//默认也是集群
		myConsumer.setMessageModel(MessageModel.CLUSTERING);

		myConsumer.setMessageListener(new MessageListenerOrderly() {
			@Override
			public ConsumeOrderlyStatus consumeMessage(List<MessageExt> list, ConsumeOrderlyContext consumeOrderlyContext) {
				for (MessageExt msg : list) {
					System.out.println(new String(msg.getBody()));
				}
				// ack
				return ConsumeOrderlyStatus.SUCCESS;
			}
		});

		myConsumer.start();
	}
}
