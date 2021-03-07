package modeltest;

import static org.junit.Assert.*;

import org.junit.Test;

import model.JSONMessage;

public class JSONMessageTest {

	@Test
	public void test() {
		JSONMessage message=new JSONMessage();
		message.setMessage("my test");
		assertEquals(message.getMessage(), "my test");
	}

}
