package IMQ.IMQServer; 
import org.junit.runner.RunWith;
import org.junit.runners.Suite; 
import communicationprotocoltest.IMQProtocolTest;
import communicationprotocoltest.IMQRequestTest;
import communicationprotocoltest.IMQResponseTest;
import daotest.ClientDaoTest;
import modeltest.ClientTest;
import modeltest.IMQMessageTest;
import modeltest.TopicTest;
import propertiestest.PropertiesFileTest;
import serverTest.ClientRequestTest;
import serverTest.ServerResponseTest;
import serverTest.ServerTest; 
import servicestest.PublisherServiceTest;
import servicestest.SubscriberServiceTest;
import servicestest.TopicServiceTest;

@RunWith(Suite.class)


@Suite.SuiteClasses({
   PublisherServiceTest.class,
   SubscriberServiceTest.class,
   ClientRequestTest.class,
   TopicServiceTest.class,
   ServerResponseTest.class,
   ServerTest.class, 
   ClientTest.class,
   IMQMessageTest.class,
   TopicTest.class,
   IMQProtocolTest.class,
   IMQRequestTest.class,
   IMQResponseTest.class,
   PropertiesFileTest.class,
   ClientDaoTest.class
})

public class JunitTestSuite {
 }

 


 