package server;

import com.google.gson.Gson;

import communicationProtocol.IMQRequest;

public class ClientRequest {
	public static IMQRequest getMessageFromClient(String request) {
		Gson gson = new Gson();
		return gson.fromJson(request, IMQRequest.class);
	}
}
 