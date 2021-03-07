package serverTest;

import static org.junit.Assert.*;

import org.junit.Test;  
import communicationProtocol.IMQRequest;
import server.ClientRequest;

public class ClientRequestTest {

	@Test
	public void getMessageFromClientTest() {
		ClientRequest clientRequest=new ClientRequest();
		 String request="{ response : message , dataFormat : JSON , version : 1.0 , destinationUrl : localhost }";
		 request=request.replace(' ', '"');
		 IMQRequest imqRequest=ClientRequest.getMessageFromClient(request);
	     assertEquals(imqRequest.getDataFormat(), "JSON");	  
	 }
}
  