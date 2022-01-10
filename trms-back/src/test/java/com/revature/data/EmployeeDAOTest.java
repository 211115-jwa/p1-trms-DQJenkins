package com.revature.data;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import com.revature.beans.Employee;
import com.revature.beans.Reimbursement;
import com.revature.utils.DAOFactory;
//this imports the static methods from Assertions so that
//we can type "assertEquals" rather than "Assertions.assertEquals"
import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

@TestMethodOrder(OrderAnnotation.class)
public class EmployeeDAOTest {
	private EmployeeDAO employeeDAO = DAOFactory.getEmployeeDAO();
	static int generatedId;
	//public int create(Employee dataToAdd);
	@Test
	@Order(value = 0)
	public void create() {
		Employee emp = new Employee();
		generatedId = employeeDAO.create(emp);
		assertNotEquals(0, generatedId);
	}
	//DAO methods to test
	//public Employee getByUsername(String username);
	@Test
	@Order(value = 1)
	public void getByUsernameWhenNameExists() {
		Employee result = employeeDAO.getByUsername("csmith26");
		assertEquals("csmith26", result.getUsername());
	}
	
	@Test
	@Order(value = 1)
	public void getByUsernameWhenNameDoesntExist() {
		Employee result = employeeDAO.getByUsername("bob");
		assertNull(result);
	}

	//public Employee getById(int id);
	@Test
	@Order(value = 1)
	public void getByIdWhenIdExists() {
		Employee result = employeeDAO.getById(1);
		assertEquals(1, result.getEmpId());
	}
	
	@Test
	@Order(value = 1)
	public void getByIdWhenIdDoesntExist() {
		Employee result = employeeDAO.getById(1138);
		assertNull(result);
	}
	//public Set<Employee> getAll();
	@Test
	@Order(value = 1)
	public void getAll() {
		Set<Employee> result = employeeDAO.getAll();
		assertNotNull(result);
		assertFalse(result.isEmpty());
	}
	//public void update(Employee dataToUpdate);
	@Test
	@Order(value = 1)
	public void update() {
		Employee emp = employeeDAO.getById(generatedId);
		emp.setUsername("dana1234");
		employeeDAO.update(emp);
		assertEquals("dana1234", employeeDAO.getById(generatedId).getUsername());
	}
	//public void delete(Employee dataToDelete);
	@Test
	@Order(value = 100)
	public void delete() {
		int currentSize = employeeDAO.getAll().size();
		Employee emp = employeeDAO.getById(generatedId);
		employeeDAO.delete(emp);
		int newSize = employeeDAO.getAll().size();
		assertEquals(currentSize-1, newSize);
	}
	
	//public Employee getApproverByRequest(Reimbursement request) 
	@Test
	public void getApproverByRequestSupervisor() {
		//Test data request ID 5 is to be approved by a supervisor
		Reimbursement request = DAOFactory.getReimbursementDAO().getById(5);
		Employee result = employeeDAO.getApproverByRequest(request);
		//Test data employee 6 is the supervisor of the employee
		assertEquals(6, result.getEmpId());
	}
	
	@Test
	public void getApproverByRequestDepartmentHead() {
		//Test data request ID 4 is to be approved by the Department Head of Dept 1
		Reimbursement request = DAOFactory.getReimbursementDAO().getById(4);
		Employee result = employeeDAO.getApproverByRequest(request);
		//Test data employee 1 is the Department Head fof Dept 1
		assertEquals(1, result.getEmpId());
	}
	
	@Test
	public void getApproverByRequestBenCo() {
		//Test data request ID 1 is to be approved by a BenCo
		Reimbursement request = DAOFactory.getReimbursementDAO().getById(1);
		Employee result = employeeDAO.getApproverByRequest(request);
		//Test data employee 4 is the BenCo
		assertEquals(4, result.getEmpId());
	}
}
