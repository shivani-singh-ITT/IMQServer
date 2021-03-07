package serverTest;

import static org.junit.Assert.*;

import org.junit.Test;
 
import communicationProtocol.IMQResponse; 
import server.ServerResponse;

public class ServerResponseTest {

   public IMQResponse getIMQResponse() {
	   IMQResponse response=new IMQResponse(); 
	   response.setDataFormat("JSON");
	   response.setDestinationUrl("localhost"); 
	   response.setResponse("message");
	   response.setVersion("1.0");
	   return response;
   }
    
	@Test
	public void generateResponseTest() {  
		ServerResponse response=new ServerResponse(); 
		IMQResponse imqResponse=ServerResponse.generateResponse("Message");
		assertEquals(imqResponse.getResponse(),"Message");
	}
	 
	@Test
	public void getServerResponseTest() {  
		IMQResponse imqResponse=getIMQResponse();
		String response= ServerResponse.getServerResponse(imqResponse);
		response=response.replace('"',' ');
	assertEquals(response ,"{ response : message , dataFormat : JSON , version : 1.0 , destinationUrl : localhost }");
	}

}
