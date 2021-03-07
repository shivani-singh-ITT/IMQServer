package servicestest;

import static org.junit.Assert.*;

import java.io.IOException; 
import org.junit.Test;

import model.Client;
import model.IMQMessage;
import model.Topic;
import services.PublisherService;

public class PublisherServiceTest {

	Client client=new Client();
	Topic topic=new Topic();
	IMQMessage message=new IMQMessage();
	
	@Test
	public void pushMessageTest() throws IOException { 
		client.setClientName("Priya");
		topic.setTopicName("News");
		message.setData("Stock market");
	    PublisherService publisherService=new PublisherService();
	    String response=publisherService.pushMessage(client,topic,message);
	    assertEquals(response,"Message published !!");
	}

} 
 