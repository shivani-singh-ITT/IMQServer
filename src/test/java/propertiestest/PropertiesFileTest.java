package propertiestest;

import static org.junit.Assert.*;
import java.util.Properties;
import org.junit.Test;

import properties.PropertyFile;

public class PropertiesFileTest {

	PropertyFile propertyFile = new PropertyFile();

	@Test 
	public void propertiesFileTest() {
		Properties testProperties = propertyFile.loadPropertiesFile();
		assertNotEquals(testProperties, null);

		String propertyValue = propertyFile.getPropertyValue("ipName", testProperties);
		assertEquals(propertyValue, "localhost ");
	}

}
