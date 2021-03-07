package jdbcconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import properties.PropertyFile;

public class JdbcConnection {

	PropertyFile propertyFile = new PropertyFile();
	Properties properties = propertyFile.loadPropertiesFile();
	private static Connection connection = null;
	private static JdbcConnection jdbcConnection= null;
	
	private JdbcConnection() {
	}

	public static JdbcConnection getInstance() {
		if (jdbcConnection == null)
			jdbcConnection = new JdbcConnection();

		return jdbcConnection;
	}

	public Connection getJdbcConnection() {
		if(connection == null) {
		try {
			Class.forName(propertyFile.getPropertyValue("className", properties));
			String url = propertyFile.getPropertyValue("url", properties);
			String username = propertyFile.getPropertyValue("username", properties);
			String password = propertyFile.getPropertyValue("password", properties);
			connection = DriverManager.getConnection(url, username, password);
		} catch (Exception exceptionObject) {
			exceptionObject.printStackTrace();
		}
		}
		return connection;
	}
}
