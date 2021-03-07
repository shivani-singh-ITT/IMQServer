package services;

import java.io.IOException;
import java.util.LinkedList;

import dao.MessageDao;
import dao.TopicDao;
import model.Client;
import model.IMQMessage;
import model.Topic;
import server.QueueHandler;

public class PublisherService {
	TopicDao topicData = new TopicDao();
	MessageDao messageData = new MessageDao();

	QueueHandler queueHandler = QueueHandler.getInstance();
	LinkedList<IMQMessage> messageQueue = queueHandler.getQueue(); 

	public String pushMessage(Client client, Topic topic, IMQMessage message) throws IOException {
		 int topicCode = topicData.isTopicConnected(client, topic);
			if (topicCode == 11) {
				String response = messageData.pushMessage(message, topic, client);
				queueHandler.addMessageInQueue(message, messageQueue);
				return response;
			} else if (topicCode == 10) {
				return "Please connect to Topic first";
			} else {
				if (topicData.isTopicExists(topic)) {
					return "Please connect to Topic first";
				} else {
					return "Incorrect Topic Name " + topic.getTopicName();
				}
			}
			
		}
}
