package com.revature.data;
import org.junit.jupiter.api.Test;
import com.revature.beans.Department;
import com.revature.data.DepartmentDAO;
import com.revature.data.postgres.DepartmentPostgres;
import com.revature.utils.DAOFactory;
//this imports the static methods from Assertions so that
//we can type "assertEquals" rather than "Assertions.assertEquals"
import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

public class DepartmentDAOTest {
	private DepartmentDAO departmentDAO = DAOFactory.getDepartmentDAO();
	//DAO Methods to test
	//public Department getById(int id);
	@Test
	public void getByIdWhenIdExists() {
		Department result = departmentDAO.getById(1);
		assertEquals(1, result.getDeptId());
	}
	
	@Test
	public void getByIdWhenIdDoesntExist() {
		Department result = departmentDAO.getById(1138);
		assertNull(result);
	}
	//public Set<Department> getAll();
	@Test
	public void getAll() {
		Set<Department> result = departmentDAO.getAll();
		assertNotNull(result);
		assertFalse(result.isEmpty());
	}
	//public Set<Department> getByName(String name);
	@Test
	public void getByNameWhenNameExists() {
		Set<Department> result = departmentDAO.getByName("HR");
		assertNotNull(result);
		assertFalse(result.isEmpty());
	}
	
	@Test
	public void getByNameWhenNameDoesntExist() {
		Set<Department> result = departmentDAO.getByName("Best Employees");
		assertTrue(result.isEmpty());
	}
}