package client;

import communicationProtocol.IMQRequest;
 
public class ClientService {
	public static IMQRequest getRequest(String inetAddress, String[] userCommand) {
		IMQRequest clientRequest = new IMQRequest();
		clientRequest.setDataFormat("JSON");
		clientRequest.setDestinationUrl("localhost");
		clientRequest.setSourceUrl(getSourceUrl(inetAddress));
		clientRequest.setVersion("1.0");
		clientRequest.setUserCommand(userCommand);
		return clientRequest;
	} 

	public static String getSourceUrl(String inetAddress) {
		String[] splittedUrlArray = inetAddress.split("/", 2);
		return splittedUrlArray[1];
	}

}
