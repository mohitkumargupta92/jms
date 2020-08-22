package com.jms.provider;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.ConnectionFactory;

public class JMSProvider {

	public static ConnectionFactory getConnectionFactory() {
		/*
		 * The VM transport allows clients to connect to each other inside the VM
		 * without the overhead of the network communication.
		 */
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost");
		return connectionFactory;
	}
}