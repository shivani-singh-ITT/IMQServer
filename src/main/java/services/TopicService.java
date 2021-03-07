package services;

import java.util.List;

import dao.TopicDao;
import model.Client;
import model.Topic;

public class TopicService {
	TopicDao topicDb = new TopicDao();

	public String connectTopic(Client client, Topic topic) {
			int topicCode = topicDb.isTopicConnected(client, topic);
			if (topicCode == 11) {
				return "Topic " + topic.getTopicName() + " is already connected";
			} else if (topicCode == 10) {
				String response = topicDb.connectTopic(client, topic);
				return response;
			} else {
				if (topicDb.isTopicExists(topic)) {
					String response = topicDb.connectNewTopic(client, topic);
					return response;
				} else {
					return "Incorrect topic name";
				}
			}
	}

	public String disconnectTopic(Client client, Topic topic) { 
			int topicCode = topicDb.isTopicConnected(client, topic);
			if (topicCode == 11) {
				topicDb.disconnectTopic(client, topic);
				return "Topic " + topic.getTopicName() + " is disconnected";
			} else if (topicCode == 10) {
				return "This topic is already disconnected";
			} else {
				if (topicDb.isTopicExists(topic)) {
					return "The topic " + topic.getTopicName() + " is not connected";
				} else {
					return "Incorrect topic name";
				}
			}
		}
		 

	public String showTopics() {
		StringBuilder stringBuilder = new StringBuilder();
		List<String> topicsList = topicDb.getTopics();
		for (String topic : topicsList) {
			stringBuilder.append(topic);
			stringBuilder.append("-n");
		}
		return stringBuilder.toString();
	}
}
