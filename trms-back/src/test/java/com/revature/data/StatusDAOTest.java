package com.revature.data;
import org.junit.jupiter.api.Test;

import com.revature.beans.Department;
import com.revature.beans.Status;
import com.revature.data.StatusDAO;
import com.revature.utils.DAOFactory;
//this imports the static methods from Assertions so that
//we can type "assertEquals" rather than "Assertions.assertEquals"
import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

public class StatusDAOTest {
	private StatusDAO statusDAO = DAOFactory.getStatusDAO();
	
	//DAO Methods to test
	//public Status getById(int id);
	@Test
	public void getByIdWhenIdExists() {
		Status result = statusDAO.getById(1);
		assertEquals(1, result.getStatusId());
	}
	
	@Test
	public void getByIdWhenIdDoesntExist() {
		Status result = statusDAO.getById(1138);
		assertNull(result);
	}
	//public Set<Status> getAll();
	@Test
	public void getAll() {
		Set<Status> result = statusDAO.getAll();
		assertNotNull(result);
		assertFalse(result.isEmpty());
	}
	//public Set<Status> getByName(String name);
	@Test
	public void getByNameWhenNameExists() {
		Set<Status> result = statusDAO.getByName("Pending Approval");
		assertNotNull(result);
		assertFalse(result.isEmpty());
	}
	
	@Test
	public void getByNameWhenNameDoesntExist() {
		Set<Status> result = statusDAO.getByName("Never Been Looked At");
		assertTrue(result.isEmpty());
	}
}
