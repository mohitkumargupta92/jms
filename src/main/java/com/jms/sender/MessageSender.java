package com.jms.sender;

import javax.jms.*;

import com.jms.provider.JMSProvider;

public class MessageSender {

	private MessageProducer producer;
	private Session session;
	private final Connection connection;

	public MessageSender() throws JMSException {
		ConnectionFactory factory = JMSProvider.getConnectionFactory();
		this.connection = factory.createConnection();
		connection.start();

//		registerQueue();

		registerTopic();
	}

	public void registerQueue() throws JMSException {
		this.session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Queue queue = session.createQueue("example.queue");
		this.producer = session.createProducer(queue);
	}

	public void registerTopic() throws JMSException {
		this.session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Topic topic = session.createTopic("example.topic");
		this.producer = session.createProducer(topic);

	}

	public void sendMessage(String message) throws JMSException {
		System.out.printf("Sending message: %s, Thread:%s%n", message, Thread.currentThread().getName());
		TextMessage textMessage = session.createTextMessage(message);
		producer.send(textMessage);
	}

	public void destroy() throws JMSException {
		connection.close();
	}
}