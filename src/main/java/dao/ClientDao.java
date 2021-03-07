package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbcconnection.CreateStatementObject;
import model.Client;
import services.Selector;
 
public class ClientDao {
	Selector selector = new Selector();

	public ArrayList<String> getAllClients() {
		CreateStatementObject createStatement = new CreateStatementObject();
		PreparedStatement preparedStatement = createStatement.getPreparedStatement(selector.getUsers());
		try {
			ResultSet result = preparedStatement.executeQuery();
			ArrayList<String> allUsersList = new ArrayList<String>();
			while (result.next()) {
				String username = result.getString("username");
				allUsersList.add(username);
			}
			return allUsersList;
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		return null;
	}

	public void storeClient(Client client) {
		CreateStatementObject createStatement = new CreateStatementObject();
		PreparedStatement preparedStatement = null;
		String clientName = client.getClientName();
		preparedStatement = createStatement.getPreparedStatement(selector.insertUser());
		try {
			preparedStatement.setString(1, clientName);
			preparedStatement.execute();
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
	}
}
