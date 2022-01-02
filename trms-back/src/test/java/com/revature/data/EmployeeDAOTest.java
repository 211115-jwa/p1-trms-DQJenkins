package com.revature.data;
import org.junit.jupiter.api.Test;
import com.revature.beans.Employee;
import com.revature.data.EmployeeDAO;
import com.revature.utils.DAOFactory;
//this imports the static methods from Assertions so that
//we can type "assertEquals" rather than "Assertions.assertEquals"
import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

public class EmployeeDAOTest {
	private EmployeeDAO employeeDAO = DAOFactory.getEmployeeDAO();
	
	//DAO methods to test
	//public Employee getByUsername(String username);
	//public int create(Employee dataToAdd);
	//public Employee getById(int id);
	//public Set<Employee> getAll();
	//public void update(Employee dataToUpdate);
	//public void delete(Employee dataToDelete);
}
