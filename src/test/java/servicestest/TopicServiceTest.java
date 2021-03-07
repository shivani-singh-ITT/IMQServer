package servicestest;

import static org.junit.Assert.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.AfterClass;
import org.junit.Test;

import jdbcconnection.CreateStatementObject;
import model.Client;
import model.Topic;
import services.TopicService;

public class TopicServiceTest {
	Client client = new Client();
	Topic topic = new Topic();

	 @Test
	public void connecttedTopicTest() {
		client.setClientName("shivani");
		topic.setTopicName("Business");
		TopicService topicService = new TopicService();
		String response = topicService.connectTopic(client, topic);
		assertEquals("Topic Business is already connected", response);
	}
	 
	@Test 
	public void connectTopicTest() {
		client.setClientName("shivani");
		topic.setTopicName("IT");
		TopicService topicService = new TopicService();
		String response = topicService.connectTopic(client, topic);
		assertEquals("connected to topic IT" , response);
	}
	
	@Test
	public void connectNewTopicTest() {
		client.setClientName("shivani");
		topic.setTopicName("Entertainment");
		TopicService topicService = new TopicService();
		String response = topicService.connectTopic(client, topic);
		assertEquals("Topic Entertainment is now connected!!", response);
	}
    
	@Test
	public void incorrectTopicTest() {
		client.setClientName("shivani");
		topic.setTopicName("XYZ");
		TopicService topicService = new TopicService();
		String response = topicService.connectTopic(client, topic);
		assertEquals("Incorrect topic name", response);
	}
	
	
	@Test
	public void disconnectConnectedTopicTest() {
		client.setClientName("shivani");
		topic.setTopicName("IT");
		TopicService topicService = new TopicService();
		String response = topicService.disconnectTopic(client, topic);
		assertEquals("Topic IT is disconnected",response);
	}
	
	@Test
	public void disconnectTopicTest() {
		client.setClientName("shivanisingh");
		topic.setTopicName("Sports");
		TopicService topicService = new TopicService();
		String response = topicService.disconnectTopic(client, topic);
		assertEquals("This topic is already disconnected",response);
	}
	
	@Test
	public void incorrectTopicDisconnectTest() {
		client.setClientName("shivani");
		topic.setTopicName("XYZ");
		TopicService topicService = new TopicService();
		String response = topicService.disconnectTopic(client, topic);
		assertEquals("Incorrect topic name", response);
	}
	
	@Test
	public void disconnectnewTopicTest() {
		client.setClientName("Shubhi");
		topic.setTopicName("IT");
		TopicService topicService = new TopicService();
		String response = topicService.disconnectTopic(client, topic);
		assertEquals("The topic IT is not connected",response);
	}
	
	
	@AfterClass
    public static void tearDown() {  
        CreateStatementObject createStatement = new CreateStatementObject();
		PreparedStatement preparedStatement = null;
		preparedStatement = createStatement.getPreparedStatement("delete from usertopicmapping where topicname=? and username=?");
		try {
			preparedStatement.setString(1, "Entertainment");
			preparedStatement.setString(2, "shivani");
			preparedStatement.executeUpdate(); 
		} catch (SQLException exception) {  
			exception.getMessage();
		}
		
		preparedStatement = createStatement.getPreparedStatement("update usertopicmapping set connected=false where username=? and topicname=?");
		try {
			preparedStatement.setString(1, "shivani");
			preparedStatement.setString(2, "IT");
			preparedStatement.executeUpdate(); 
		} catch (SQLException exception) {  
			exception.getMessage();
		}
		
    }
	
}
