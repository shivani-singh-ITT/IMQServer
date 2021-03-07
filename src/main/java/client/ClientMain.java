package client;

import java.io.*; 
import java.net.*;
import java.util.Properties;

import com.google.gson.Gson;

import communicationProtocol.IMQRequest;
import parser.CommandParser;
import properties.PropertyFile;

public class ClientMain {
	private String bufferInput = null; 
	private BufferedReader bufferReader = null;
	private BufferedReader socketReader = null; 
	private PrintWriter printWriter = null;

	static Socket socket = null;

	public static void main(String[] args) throws IOException {
		ClientMain client = new ClientMain();
		client.connectToServer();
		client.readCommand();
	}

	private InetAddress getIP(String ipName) {
		InetAddress ip = null;
		try {
			ip = InetAddress.getByName(ipName);
			return ip;
		} catch (UnknownHostException exception) {
			exception.printStackTrace();
			System.out.println("Address not found");
		}
		return ip;
	}

	public void connectToServer() throws IOException {
		PropertyFile propertyFile = new PropertyFile();
		Properties properties = propertyFile.loadPropertiesFile();
		ClientMain client = new ClientMain();
		InetAddress ip = client.getIP(propertyFile.getPropertyValue("ipName", properties));
		try {
			socket = new Socket(ip, Integer.parseInt(propertyFile.getPropertyValue("port", properties)));
			bufferReader = new BufferedReader(new InputStreamReader(System.in));
			socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			printWriter = new PrintWriter(socket.getOutputStream());
            System.out.println("LOGIN");
		} catch (IOException exception) {
           exception.getMessage();
		}
	}

	private void readCommand() throws IOException {
		CommandParser parser = new CommandParser();
		String response;
		boolean takeInput=true;
		Gson gson = new Gson();
		try {
			while (takeInput) {
				System.out.print(">>"); 
				bufferInput = bufferReader.readLine();
				String[] parsedCommand = parser.parse(bufferInput);
				IMQRequest clientRequest = ClientService.getRequest(socket.getInetAddress().toString(), parsedCommand);
			    if (!parsedCommand[0].equals("restart")) {
			    	if(parsedCommand[0].equals("logout"))
				    	takeInput=false;
				    String clientRequestString = gson.toJson(clientRequest);
					printWriter.println(clientRequestString);
					printWriter.flush();
					response = socketReader.readLine();
					response = response.replace('"', ' ');
					response = response.replace("response", "");
					response = response.replaceAll("[:{}]", "");
					String[] responseArray = response.split("-n");
					if (responseArray.length > 1) {
						for (String message : responseArray) {
							System.out.println(message);
						}
					} else
						System.out.println(response);
				} else
					readCommand();
			}
		} catch (IOException exception) {
			exception.printStackTrace();
			System.out.println("Sending request to server failed!!");
		} finally {
			socketReader.close();
			printWriter.close();
			bufferReader.close();
			socket.close();
		}
	}
}
