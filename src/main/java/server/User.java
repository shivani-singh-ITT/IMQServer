package server;

import java.io.IOException;
import java.util.ArrayList;
import dao.ClientDao;
import model.Client;

public class User {
	public String login(Client client) throws IOException {
		String clientName = client.getClientName();
		if (clientName.isBlank()) {
			return "Please enter a username, press imq help for help";
		} else {
			Boolean isClientExist = isClientExists(clientName);
			if (isClientExist) {
				return "Welcome Back " + clientName + "!! You have logged in sucessfully";
			} else {
				ClientDao newUser = new ClientDao();
				newUser.storeClient(client);
				return "Welcome " + clientName + "!! You have signed up sucessfully";
			}
		}
     }

	private Boolean isClientExists(String clientName) {
		ClientDao clients = new ClientDao();
		ArrayList<String> allClients = clients.getAllClients();
		if (allClients.contains(clientName)) {
			return true;
		}
		return false;
	}
}
