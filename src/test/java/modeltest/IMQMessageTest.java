package modeltest;

import static org.junit.Assert.*;
 
import org.junit.Test;

import model.IMQMessage;

public class IMQMessageTest {
 

	@Test
	public void test() {
		IMQMessage message=new IMQMessage();
		message.setData("new message");
		assertEquals(message.getData(),"new message");
	}

}
