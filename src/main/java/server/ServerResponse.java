package server;

import com.google.gson.Gson;
 
import communicationProtocol.IMQResponse;
 
public class ServerResponse {
	public static String getServerResponse(IMQResponse response) {
		Gson gson = new Gson();
		return gson.toJson(response);
	}
	
	public static IMQResponse generateResponse(String message) {
		IMQResponse response = new IMQResponse(); 
		response.setResponse(message); 
		return response;
	} 	
}