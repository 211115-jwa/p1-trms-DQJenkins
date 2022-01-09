package com.revature.data;
import org.junit.jupiter.api.Test;

import com.revature.beans.Department;
import com.revature.beans.GradingFormat;
import com.revature.data.GradingFormatDAO;
import com.revature.utils.DAOFactory;
//this imports the static methods from Assertions so that
//we can type "assertEquals" rather than "Assertions.assertEquals"
import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

public class GradingFormatDAOTest {
	private GradingFormatDAO gradingFormatDAO = DAOFactory.getGradingFormatDAO();
	//DAO Methods to Test
	//public GradingFormat getById(int id);
	@Test
	public void getByIdWhenIdExists() {
		GradingFormat result = gradingFormatDAO.getById(1);
		assertEquals(1, result.getFormatId());
	}
	
	@Test
	public void getByIdWhenIdDoesntExist() {
		GradingFormat result = gradingFormatDAO.getById(1138);
		assertNull(result);
	}
	//public Set<Object> getAll();
	@Test
	public void getAll() {
		Set<Object> result = gradingFormatDAO.getAll();
		assertNotNull(result);
		assertFalse(result.isEmpty());
	}
	//public Set<GradingFormat> getByName(String name);
	@Test
	public void getByNameWhenNameExists() {
		Set<GradingFormat> result = gradingFormatDAO.getByName("Letter Grade");
		assertNotNull(result);
		assertFalse(result.isEmpty());
	}
	
	@Test
	public void getByNameWhenNameDoesntExist() {
		Set<GradingFormat> result = gradingFormatDAO.getByName("Bribe");
		assertTrue(result.isEmpty());
	}
}
