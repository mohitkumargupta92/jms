package com.jms.receiver;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import com.jms.provider.JMSProvider;

public class MessageQueueOne {

	private Connection connection;

	public void startListening() throws JMSException {
		ConnectionFactory factory = JMSProvider.getConnectionFactory();
		this.connection = factory.createConnection();
		connection.start();

		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

		Queue queue = session.createQueue("example.queue");
		MessageConsumer consumer = session.createConsumer(queue);

		Message message = consumer.receive();

		if (message instanceof TextMessage) {
			TextMessage textMessage = (TextMessage) message;
			System.out.println(textMessage.getText());
		}

	}

	public void destroy() throws JMSException {
		connection.close();
	}
}