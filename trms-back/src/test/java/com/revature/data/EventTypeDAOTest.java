package com.revature.data;
import org.junit.jupiter.api.Test;
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
	//public Set<Object> getAll();
	//public Set<EventType> getByName(String name);
}
