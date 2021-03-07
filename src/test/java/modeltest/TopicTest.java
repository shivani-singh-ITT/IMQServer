package modeltest;

import static org.junit.Assert.*;
import org.junit.Test;
import model.Topic;

public class TopicTest {
	@Test
	public void topicTest() {
		Topic topic = new Topic();
		topic.setTopicName("Business");
		assertEquals(topic.getTopicName(), "Business");
	}
}
