package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbcconnection.CreateStatementObject;
import model.Client;
import model.IMQMessage;
import model.Topic;
import services.Selector;

public class MessageDao {
	Selector selector = new Selector();

	public String pushMessage(IMQMessage message, Topic topic, Client client) {
		CreateStatementObject createStatement = new CreateStatementObject();
		PreparedStatement preparedStatement = null;
		preparedStatement = createStatement.getPreparedStatement(selector.insertMessage());
		try {
			preparedStatement.setString(1, message.getData());
			preparedStatement.setString(2, topic.getTopicName());
			preparedStatement.setString(3, client.getClientName());
			preparedStatement.execute();
			return "Message published !!";
		} catch (SQLException exception) {
			return "SQL Error while publishing message ";
		}
	}

	public ArrayList<IMQMessage> getMessages(Topic topic, Client client) {
		CreateStatementObject createStatement = new CreateStatementObject();
		PreparedStatement preparedStatement = null;
		preparedStatement = createStatement.getPreparedStatement(selector.getMessage());
		try {
			preparedStatement.setString(1, topic.getTopicName());
			preparedStatement.setString(2, client.getClientName());
			ResultSet result = preparedStatement.executeQuery();
			ArrayList<IMQMessage> messagesList = new ArrayList<IMQMessage>();
			while (result.next()) {
				IMQMessage message = new IMQMessage();
				message.setData(result.getString("data"));
				message.setId(result.getInt("messageid"));
				messagesList.add(message);
			}
			storeReceivedMessages(client, messagesList);
			return messagesList;

		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		return null;
	}

	private void storeReceivedMessages(Client client, ArrayList<IMQMessage> messagesList) {
		CreateStatementObject createStatement = new CreateStatementObject();
		PreparedStatement preparedStatement = null;
		preparedStatement = createStatement.getPreparedStatement(selector.receiveMessage());
		for (IMQMessage message : messagesList) {
			try {
				preparedStatement.setString(1,client.getClientName());
				preparedStatement.setInt(2, message.getId());
				preparedStatement.execute();
			} catch (SQLException exception) {
				exception.printStackTrace();
			}
		}
	}
}
