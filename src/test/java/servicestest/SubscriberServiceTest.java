package servicestest;
import org.junit.AfterClass;
import org.junit.Test;

import model.Client; 
import model.Topic;
import services.SubscriberService;

public class SubscriberServiceTest {

	Client client=new Client();
	Topic topic=new Topic(); 
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void getMessageTest() {
		client.setClientName("shivani");
		topic.setTopicName("Sports");
		SubscriberService subscriberService=new SubscriberService();
		String messages=subscriberService.getMessage(client, topic);
	}

}
