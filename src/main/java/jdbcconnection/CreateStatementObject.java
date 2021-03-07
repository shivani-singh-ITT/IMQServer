package jdbcconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class CreateStatementObject{
public PreparedStatement getPreparedStatement(String sql){
	
	JdbcConnection connection= JdbcConnection.getInstance();
	Connection newConnection=connection.getJdbcConnection();
	PreparedStatement statement = null;
	try {
		statement = newConnection.prepareStatement(sql);
	} catch (Exception exceptionObject) {
		exceptionObject.printStackTrace();
		
	}
	return statement;
}
}
