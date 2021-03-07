package services;

import java.util.ArrayList;

import dao.MessageDao;
import dao.TopicDao;
import model.Client;
import model.IMQMessage;
import model.Topic;

public class SubscriberService {
	TopicDao topicData = new TopicDao();
	MessageDao messageData = new MessageDao();

	public String getMessage(Client client, Topic topic) { 
			int topicCode = topicData.isTopicConnected(client, topic);
			StringBuilder stringBuilder = new StringBuilder();
			if (topicCode == 11) {
				ArrayList<IMQMessage> messagesList = messageData.getMessages(topic, client);
				for (IMQMessage singleMessage : messagesList) {
					stringBuilder.append(singleMessage.getData());
					stringBuilder.append("-n");
				}
				if (stringBuilder.length() > 0)
					return stringBuilder.toString();
				else
					return "You have checked all messages";

			} else if (topicCode == 10) {
				return "Please connect to Topic";
			} else {
				if (topicData.isTopicExists(topic)) {
					return "Please connect to Topic";
				} else {
					return "Incorrect Topic Name " + topic.getTopicName();
				}
			}
		}

}