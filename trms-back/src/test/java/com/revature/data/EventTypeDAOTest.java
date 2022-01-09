package com.revature.data;
import org.junit.jupiter.api.Test;

import com.revature.beans.Department;
import com.revature.beans.EventType;
import com.revature.data.EventTypeDAO;
import com.revature.utils.DAOFactory;
//this imports the static methods from Assertions so that
//we can type "assertEquals" rather than "Assertions.assertEquals"
import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

public class EventTypeDAOTest {
	private EventTypeDAO eventTypeDAO = DAOFactory.getEventTypeDAO();
	//DAO Methods to test
	//public EventType getById(int id);
	@Test
	public void getByIdWhenIdExists() {
		EventType result = eventTypeDAO.getById(1);
		assertEquals(1, result.getEventId());
	}
	
	@Test
	public void getByIdWhenIdDoesntExist() {
		EventType result = eventTypeDAO.getById(1138);
		assertNull(result);
	}
	
	//public Set<Object> getAll();
	@Test
	public void getAll() {
		Set<Object> result = eventTypeDAO.getAll();
		assertNotNull(result);
		assertFalse(result.isEmpty());
	}
	//public Set<EventType> getByName(String name);
	@Test
	public void getByNameWhenNameExists() {
		Set<EventType> result = eventTypeDAO.getByName("Seminar");
		assertNotNull(result);
		assertFalse(result.isEmpty());
	}
	
	@Test
	public void getByNameWhenNameDoesntExist() {
		Set<EventType> result = eventTypeDAO.getByName("Epic Party");
		assertTrue(result.isEmpty());
	}
}
