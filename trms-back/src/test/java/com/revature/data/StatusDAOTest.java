package com.revature.data;
import org.junit.jupiter.api.Test;
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
	//public Set<Status> getAll();
	//public Set<Status> getByName(String name);
}
