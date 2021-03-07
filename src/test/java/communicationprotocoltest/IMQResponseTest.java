package communicationprotocoltest;
import static org.junit.Assert.*;

import org.junit.Test;

import communicationProtocol.IMQResponse;

public class IMQResponseTest {

	@Test
	public void test() {
		IMQResponse response=new IMQResponse();
		response.setResponse("message");
		assertEquals(response.getResponse(),"message");
	}

}
