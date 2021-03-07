package daotest; 

import static org.junit.Assert.assertEquals;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.Test;
import dao.ClientDao;
import jdbcconnection.CreateStatementObject;
import model.Client;

public class ClientDaoTest { 

	ClientDao clientDao=new ClientDao();
	Client client=new Client();
	ArrayList<String> allUsersList = new ArrayList<String>();
	
	@Test
	public void getAllClientsTest() { 
		allUsersList=clientDao.getAllClients();
		assertEquals(allUsersList.size(),6);
	}

	@Test
	public void storeClientTest() {
		client.setClientName("testuser");
		clientDao.storeClient(client);
	}
	
	@AfterClass
    public static void tearDown() {  
        CreateStatementObject createStatement = new CreateStatementObject();
		PreparedStatement preparedStatement = null;
		preparedStatement = createStatement.getPreparedStatement("delete from user where username=?");
		try { 
			preparedStatement.setString(1, "testuser");
			preparedStatement.executeUpdate(); 
		} catch (SQLException exception) {  
			exception.getMessage();
		}
}}