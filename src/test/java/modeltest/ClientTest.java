package modeltest;

import static org.junit.Assert.*; 
import org.junit.Test;

import model.Client;

public class ClientTest {
	@Test
	public void clientTest() {
		Client client = new Client();
		client.setClientName("Shivani");
		client.setId("101");
		assertEquals(client.getClientName(), "Shivani");
		assertEquals(client.getId(), "101");
	}
}
