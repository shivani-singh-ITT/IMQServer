package IMQ.IMQClient;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import clientTest.ClientServiceTest;
import communicationprotocoltest.IMQProtocolTest;
import communicationprotocoltest.IMQRequestTest;
import communicationprotocoltest.IMQResponseTest;
import modeltest.ClientTest;
import modeltest.JSONMessageTest;
import parsertest.CommandParserTest;
import propertiestest.PropertiesFileTest;

@RunWith(Suite.class)


@Suite.SuiteClasses({ 
   IMQProtocolTest.class,
   IMQRequestTest.class,
   IMQResponseTest.class,
   ClientServiceTest.class, 
   ClientTest.class,
   JSONMessageTest.class,
   CommandParserTest.class,
   PropertiesFileTest.class
})

public class JunitTestSuite {
 }

 


 