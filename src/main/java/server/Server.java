package server;

import java.io.*;
import java.net.*;

public class Server { 

	public void configureNewClient() throws IOException {
		ServerSocket serverSocket = new ServerSocket(5056);
		while (true) {
			Socket socket = null;
			try {
				socket = serverSocket.accept();
				Thread thread = new ClientHandler(socket);
				thread.start();
			} catch (IOException e) {
				serverSocket.close();
				System.out.println("Error in connecting new client");
			}
		}
	}

	public static void main(String[] args) throws IOException {
		Server server = new Server();
		server.configureNewClient();
	}
}
