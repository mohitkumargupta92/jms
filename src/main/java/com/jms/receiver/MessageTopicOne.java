package com.jms.receiver;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import com.jms.provider.JMSProvider;

public class MessageTopicOne implements MessageListener {

	private Connection connection;

	public void startListening() throws JMSException {
		ConnectionFactory factory = JMSProvider.getConnectionFactory();
		this.connection = factory.createConnection();
		connection.start();

		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

		Topic topic = session.createTopic("example.topic");
		MessageConsumer consumer = session.createConsumer(topic);
		consumer.setMessageListener(this);
	}

	@Override
	public void onMessage(Message message) {
		if (message instanceof TextMessage) {
			TextMessage tm = (TextMessage) message;
			try {
				System.out.printf("MessageReceiverOne: %s, Thread: %s%n", tm.getText(), Thread.currentThread().getName());
			} catch (JMSException e) {
				throw new RuntimeException(e);
			}
		}
	}

	public void destroy() throws JMSException {
		connection.close();
	}
}