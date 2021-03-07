package properties;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyFile {
	public Properties loadPropertiesFile() {
		Properties properties=null;
		try {
			properties=new Properties();
			FileInputStream propertiesFile= new FileInputStream("config.properties");
			properties.load(propertiesFile); 
			return properties;
		 } catch (FileNotFoundException exception) {
			System.out.println("Properties File Not Found");
		} catch (IOException ioException) { 
			System.out.println("Properties File Can't be Opened");
		}
		return properties;
	}
	public String getPropertyValue(String key,Properties properties) {
		return properties.getProperty(key);
	}
}
