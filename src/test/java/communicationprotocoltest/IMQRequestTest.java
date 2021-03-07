package communicationprotocoltest;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import communicationProtocol.IMQRequest; 

public class IMQRequestTest{

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		 IMQRequest request=new IMQRequest();
		 String[] userCommand=new String[] {"CONFIG"};
		 request.setUserCommand(userCommand);
		 assertEquals(request.getUserCommand()[0],"CONFIG");
	}

}
