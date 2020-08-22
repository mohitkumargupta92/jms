package com.jms.application;

import com.jms.receiver.MessageTopicOne;
import com.jms.receiver.MessageTopicTwo;
import com.jms.sender.MessageSender;

public class Main {

	public static void main(String[] args) throws Exception {
		MessageSender sender = new MessageSender();

		MessageTopicOne receiverOne = new MessageTopicOne();
		receiverOne.startListening();

		MessageTopicTwo receiverTwo = new MessageTopicTwo();
		receiverTwo.startListening();

		for (int i = 1; i <= 10; i++) {
			String m = "Hello world! " + i;
			sender.sendMessage(m);
			Thread.sleep(300);
		}

//		MessageQueueOne queueOne = new MessageQueueOne();
//		queueOne.startListening();

		sender.destroy();
		receiverOne.destroy();
		receiverTwo.destroy();
//		queueOne.destroy();
	}
}