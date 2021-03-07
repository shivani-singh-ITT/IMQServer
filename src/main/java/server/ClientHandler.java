package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Logger;

import communicationProtocol.IMQRequest;
import communicationProtocol.IMQResponse;
import model.Client;
import model.IMQMessage;
import model.Topic;
import services.PublisherService;
import services.SubscriberService;
import services.TopicService;

public class ClientHandler extends Thread {
	private String bufferInput = null;
	Socket socket;

	BufferedReader bufferReader = null;
	PrintWriter printWriter = null;
	final Logger logger = Logger.getLogger(ClientHandler.class.getName());
	TopicService topicService = new TopicService();
	PublisherService publisherService = new PublisherService();
	SubscriberService subscriberService = new SubscriberService();

	public ClientHandler(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		String responseForClient;
		Client newClient = new Client();
		User user = new User(); 
	
		try {
			bufferReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			printWriter = new PrintWriter(socket.getOutputStream());
			bufferInput = bufferReader.readLine(); 
			while (true) {
				IMQRequest request = ClientRequest.getMessageFromClient(bufferInput);
				Topic topic = new Topic();
				IMQMessage message = new IMQMessage();
				String command = request.getUserCommand()[0].toUpperCase(); 
				switch (command) {
				case "LOGIN":
                    newClient.setClientName(request.getUserCommand()[1]);
					responseForClient = user.login(newClient);
					break;  
				case "CONNECT":
					topic.setTopicName(request.getUserCommand()[1]);
					responseForClient = topicService.connectTopic(newClient, topic);
					break;
				case "PUBLISH":
					topic.setTopicName(request.getUserCommand()[1]);
					message.setData(request.getUserCommand()[2]);
					responseForClient = publisherService.pushMessage(newClient, topic, message);
					break;
				case "CONFIG":
					topic.setTopicName(request.getUserCommand()[1]);
					responseForClient = subscriberService.getMessage(newClient, topic);
					break;
				case "DISCONNECT":
					topic.setTopicName(request.getUserCommand()[1]);
					responseForClient = topicService.disconnectTopic(newClient, topic);
					break;
				case "SHOWTOPICS":
					responseForClient = topicService.showTopics();
					break; 
				case "LOGOUT": 
					 responseForClient="Logged Out Successfully!!";
					 break;
				default:
					responseForClient = "Invalid Command";
					break;
				}

				IMQResponse response = ServerResponse.generateResponse(responseForClient);
				printWriter.println(ServerResponse.getServerResponse(response));
				printWriter.flush();
				if(!request.getUserCommand()[0].equals("logout")) {
			 	bufferInput = bufferReader.readLine();
			 	request = ClientRequest.getMessageFromClient(bufferInput);
				}
			} 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				System.out.println("Connection Closing..");
				if (bufferReader != null) {
					bufferReader.close();
				}
				if (printWriter != null) {
					printWriter.close();
				}
				if (socket != null) {
					socket.close();
				}
			} catch (IOException ioException) {
				System.out.println("Unable to close socket");
			}
		}
	}
}
