package com.revature.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.beans.Comment;
import com.revature.beans.Employee;
import com.revature.beans.Reimbursement;
import com.revature.data.CommentDAO;
import com.revature.data.DepartmentDAO;
import com.revature.data.EmployeeDAO;
import com.revature.data.EventTypeDAO;
import com.revature.data.GradingFormatDAO;
import com.revature.data.ReimbursementDAO;
import com.revature.data.StatusDAO;
import com.revature.utils.DAOFactory;


public class EmployeeServiceTest {
	
	@Mock
	private CommentDAO commentDAO;// = DAOFactory.getCommentDAO();
	@Mock
	private DepartmentDAO deptDAO;// = DAOFactory.getDepartmentDAO();
	@Mock
	private EmployeeDAO empDAO;// = DAOFactory.getEmployeeDAO();
	@Mock
	private EventTypeDAO eventDAO;// = DAOFactory.getEventTypeDAO();
	@Mock
	private GradingFormatDAO gradingDAO;// = DAOFactory.getGradingFormatDAO();
	@Mock
	private ReimbursementDAO reqDAO;// = DAOFactory.getReimbursementDAO();
	@Mock
	private StatusDAO statusDAO;// = DAOFactory.getStatusDAO();
	@InjectMocks
	private EmployeeService empService = new EmployeeServiceImpl();
	
	
	
	//Service methods to test
	//public Map<String, Set<Object>> getRequestOptions();
	 
	//public int submitReimbursementRequest(Reimbursement request);
	 
	//public Set<Reimbursement> getReimbursementRequests(Employee requestor);
	 
	//public Set<Comment> getComments(Reimbursement request);
	 
	//public int addComment(Comment comment);

	//public Employee getEmployeeById(int empId);
	@Test
	public void getEmployeeByIdExists() {
		Employee emp = new Employee();
		emp.setEmpId(1);
		
		when(empDAO.getById(1)).thenReturn(emp);
		
		Employee actualEmp = empService.getEmployeeById(1);
		assertEquals(emp, actualEmp);
	}
	
	@Test
	public void getEmployeeByIdDoesNotExist() {
		when(empDAO.getById(5364)).thenReturn(null);
		
		Employee actualEmp = empService.getEmployeeById(5364);
		assertNull(actualEmp);
	}
	
}
