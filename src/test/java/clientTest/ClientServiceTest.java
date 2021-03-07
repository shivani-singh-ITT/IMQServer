package clientTest;

import static org.junit.Assert.*;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.junit.Before;
import org.junit.Test;

import client.ClientService;
import communicationProtocol.IMQRequest; 

public class ClientServiceTest {

	@Before
	public void setUp() throws Exception {  
	 }

	@Test
	public void getRequestTest() throws UnknownHostException { 
		ClientService service=new ClientService();
		String[] userCommand=new String[] {"CONNECT"};
	  InetAddress inetAddress = InetAddress.getByName("localhost");
	  String ip=inetAddress.toString();
	IMQRequest request=ClientService.getRequest(ip,userCommand); 
	assertEquals(request.getUserCommand()[0],"CONNECT");
	assertEquals(request.getDataFormat() ,"JSON");
	assertEquals(request.getDestinationUrl(),"localhost");
	assertEquals(request.getVersion(),"1.0");
	assertEquals(request.getSourceUrl(),ClientService.getSourceUrl(ip));
	}
	

}
