package com.jd.rocketmq;

import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * @author lk
 * @version 1.0
 * @date 2020/8/23 17:39
 */
public class TrancationProducerDemo {

	public static void main(String[] args) throws Exception{
		TransactionMQProducer transactionMQProducer = new TransactionMQProducer("myProducer");
		transactionMQProducer.setNamespace("111.231.107.218:9876");
    transactionMQProducer.setTransactionListener(new TransactionListener() {
			@Override
			public LocalTransactionState executeLocalTransaction(Message message, Object o) {
				try {

				}catch (Exception e){
//					return LocalTransactionState.UNKNOW;
					//回滚
					return  LocalTransactionState.ROLLBACK_MESSAGE;
				}
				return LocalTransactionState.COMMIT_MESSAGE;
			}

			@Override
			public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
				// Broker 端 回调 ，检查事务
				System.out.println("=====checkLocalTransaction");
				System.out.println("msg:" + new String(messageExt.getBody()));
				System.out.println("msg:" + messageExt.getTransactionId());

				// 事务执行成功
				return LocalTransactionState.COMMIT_MESSAGE;
			}
		});

		Message message = new Message("myTopic", "mykey", "myMessage1............".getBytes());

		transactionMQProducer.start();

		transactionMQProducer.send(message,1000L);

		//失败后,重试发送
		transactionMQProducer.setRetryTimesWhenSendFailed(2);
		transactionMQProducer.send(message);

		// 是否向其他broker发送请求 默认false
		transactionMQProducer.setRetryAnotherBrokerWhenNotStoreOK(true);

	}
}
