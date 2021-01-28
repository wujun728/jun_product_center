package org.myframework.quartz.scheduler.job;

import java.io.Serializable;

/**
 * 
 * <ol>
 * <li>JMS是Java的消息服务，JMS的客户端之间可以通过JMS服务进行异步的消息传输。</li>
 * <li>JMS支持两种消息模型：Point-to-Point（P2P）和Publish/Subscribe（Pub/Sub），即点对点和发布订阅模型。
 * </li>
 * <li>即点对点和发布订阅模型。</li>
 * </ol>
 * 
 * @see
 * @author Wujun
 * @since 1.0
 * @2016年3月15日
 *
 */
public interface MsgSender {

	/**
	 * 
	 * 在P2P模型中，有下列概念：消息队列(Queue)、发送者(Sender)、接收者(Receiver)。
	 * 每个消息都被发送到一个特定的队列，接收者从队列中获取消息。队列保留着消息，直到它们被消费或超时。 l
	 * 每个消息只有一个消费者(Consumer)（即一旦被消费，消息就不再在消息队列中） l
	 * 发送者和接收者之间在时间上没有依赖性，也就是说当发送者发送了消息之后，不管接收者有没有正在运行， 它不会影响到消息被发送到队列。 l
	 * 接收者在成功接收消息之后需向队列应答成功 如果你希望发送的每个消息都应该被成功处理的话，那么你需要P2P模型。
	 * 
	 * @param queue
	 * @param message
	 */
	void sendMessageToQueue(String queue, Serializable message);

	/**
	 * 
	 * 在Pub/Sub模型中，有下列概念： 主题（Topic）、发布者（Publisher）、订阅者（Subscriber）。
	 * 客户端将消息发送到主题。多个发布者将消息发送到Topic，系统将这些消息传递给多个订阅者。 l 每个消息可以有多个消费者 l
	 * 发布者和订阅者之间有时间上的依赖性。针对某个主题(Topic)的订阅者，它必须创建一个订阅之后，才能消费发布者的消息，
	 * 而且为了消费消息，订阅者必须保持运行的状态。
	 * 当然，为了缓和这种严格的时间相关性，JMS允许订阅者创建一个可持久化的订阅。这样，即使订阅者没有被激活（运行），
	 * 它也能接收到发布者的消息。
	 * 如果你希望发送的消息可以不被做任何处理、或者被一个消费者处理、或者可以被多个消费者处理的话，那么可以采用Pub/Sub模型。
	 * 
	 * @param topic
	 * @param message
	 */
	void sendMessageToTopic(String topic, Serializable message);
}
