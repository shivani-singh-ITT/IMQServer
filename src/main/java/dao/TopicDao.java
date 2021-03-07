package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import jdbcconnection.CreateStatementObject;
import model.Client;
import model.Topic;
import services.Selector;

public class TopicDao {
	Selector selector = new Selector();

	public String connectNewTopic(Client client, Topic topic) {
		String topicName = topic.getTopicName();
		String userName = client.getClientName();
		CreateStatementObject createStatement = new CreateStatementObject();
		PreparedStatement preparedStatement = null;
		preparedStatement = createStatement.getPreparedStatement(selector.connectNewTopic());
		try {
			preparedStatement.setString(1, topicName);
			preparedStatement.setString(2, userName);
			preparedStatement.executeUpdate();
			return "Topic " + topicName + " is now connected!!";
		} catch (SQLException exception) {
			return " SQL Error occured while connecting to new topic";
		}
	}

	public Boolean isTopicExists(Topic topic) {
		String topicName = topic.getTopicName();
		ArrayList<String> allTopicsList = getTopics();
		if (allTopicsList.contains(topicName)) {
			return true;
		}
		return false;
	}

	public ArrayList<String> getTopics() {
		CreateStatementObject createStatement = new CreateStatementObject();
		String topicName;
		PreparedStatement preparedStatement = createStatement.getPreparedStatement(selector.getTopics());
		try {
			ResultSet result = preparedStatement.executeQuery();
			ArrayList<String> topicsList = new ArrayList<String>();
			while (result.next()) {
				topicName = result.getString("topicname");
				topicsList.add(topicName);
			}
			return topicsList;
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		return null;
	}

	public ArrayList<String> getConnectedTopics(String userName) {
		CreateStatementObject createStatement = new CreateStatementObject();
		String topicName;
		PreparedStatement preparedStatement = createStatement.getPreparedStatement(selector.getTopicsWithUsername());
		try {
			preparedStatement.setString(1, userName);
			ResultSet result = preparedStatement.executeQuery();
			ArrayList<String> topicsList = new ArrayList<String>();
			while (result.next()) {
				topicName = result.getString("topicname");
				topicsList.add(topicName);
			}
			return topicsList;
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		return null;
	}

	public void disconnectTopic(Client client, Topic topic) {
		String topicName = topic.getTopicName();
		String userName = client.getClientName();
		CreateStatementObject createStatement = new CreateStatementObject();
		PreparedStatement preparedStatement = null;
		preparedStatement = createStatement.getPreparedStatement(selector.disconnectTopic());
		try {
			preparedStatement.setString(1, topicName);
			preparedStatement.setString(2, userName);
			int rowsUpdated = preparedStatement.executeUpdate();
			if (rowsUpdated != 0) {
				System.out.println("disconnected");
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
	}

	public int isTopicConnected(Client client, Topic topic) {
		String topicName = topic.getTopicName();
		String userName = client.getClientName();
		if (!topicName.isBlank()) {
			CreateStatementObject createStatement = new CreateStatementObject();
			PreparedStatement preparedStatement = createStatement.getPreparedStatement(selector.getConnectedTopic());
			try {
				preparedStatement.setString(1, topicName);
				preparedStatement.setString(2, userName);
				ResultSet result = preparedStatement.executeQuery();
				result.last();
				int row = result.getRow();
				if (row == 0) {
					return 0;
				} else if (row == 1) {
					Boolean isConnected = result.getBoolean("connected");
					if (isConnected) {
						return 11;
					} else {
						return 10;
					}
				}
			} catch (SQLException exception) {
				exception.printStackTrace();
			}
		}

		return 0;
	}

	public String connectTopic(Client client, Topic topic) {
		String topicName = topic.getTopicName();
		String userName = client.getClientName();
		CreateStatementObject createStatement = new CreateStatementObject();
		PreparedStatement preparedStatement = null;
		preparedStatement = createStatement.getPreparedStatement(selector.connectTopic());
		try {
			preparedStatement.setString(1, topicName);
			preparedStatement.setString(2, userName);
			preparedStatement.executeUpdate();
			return "connected to topic " + topicName;
		} catch (SQLException exception) {
			return "SQL Error while connecting to topic";
		}

	}
}
