package com.revature.data;
import org.junit.jupiter.api.Test;
import com.revature.beans.Department;
import com.revature.data.DepartmentDAO;
import com.revature.utils.DAOFactory;
//this imports the static methods from Assertions so that
//we can type "assertEquals" rather than "Assertions.assertEquals"
import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

public class DepartmentDAOTest {
	private DepartmentDAO departmentDAO = DAOFactory.getDepartmentDAO();
	//DAO Methods to test
	//public Department getById(int id);
	//public Set<Department> getAll();
	//public Set<Department> getByName(String name);
}