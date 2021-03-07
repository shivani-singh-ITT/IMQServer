package clientTest;

import java.io.IOException; 
import org.junit.Test;

import client.ClientMain;

public class ClientMainTest { 

	@Test
	public void client() throws IOException { 
		ClientMain client = new ClientMain();
		client.connectToServer();  
		}
} 
