package communicationprotocoltest;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import communicationProtocol.IMQProtocol;

public class IMQProtocolTest {

	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void test() {
		IMQProtocol protocol=new IMQProtocol(); 
		protocol.setDataFormat("JSON");
		protocol.setDestinationUrl("localhost");
		protocol.setVersion("1.0"); 
		assertEquals(protocol.getDataFormat(),"JSON");
		assertEquals(protocol.getDestinationUrl() ,"localhost");
		assertEquals(protocol.getVersion(),"1.0" );
		
	 }

}
