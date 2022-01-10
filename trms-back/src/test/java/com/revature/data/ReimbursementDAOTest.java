package com.revature.data;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.revature.beans.Employee;
import com.revature.beans.Reimbursement;
import com.revature.beans.Status;

import com.revature.utils.DAOFactory;
//this imports the static methods from Assertions so that
//we can type "assertEquals" rather than "Assertions.assertEquals"
import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

@TestMethodOrder(OrderAnnotation.class)
public class ReimbursementDAOTest {
	private ReimbursementDAO reimbursementDAO = DAOFactory.getReimbursementDAO();
	static int generatedId;
	//DAO methods to test
	//public Set<Reimbursement> getByRequestor(Employee requestor);
	@Test
	public void getByRequestorWhenRequestorExists() {
		Employee requestor = DAOFactory.getEmployeeDAO().getById(5);
		Set<Reimbursement> request = reimbursementDAO.getByRequestor(requestor);
		assertFalse(request.isEmpty());
	}
	@Test
	public void getByRequestorWhenRequestorDoesntExist() {
		Set<Reimbursement> request = reimbursementDAO.getByRequestor(new Employee());
		assertTrue(request.isEmpty());
	}
	//public Set<Reimbursement> getByStatus(Status status);
	@Test
	public void getByStatusWhenStatusExists() {
		Status status = DAOFactory.getStatusDAO().getById(1);
		Set<Reimbursement> request = reimbursementDAO.getByStatus(status);
		assertFalse(request.isEmpty());
	}
	@Test
	public void getByStatusWhenStatusDoesntExist() {
		//Test data does not contain any requests with status id 7 - "Rejected" - "Benefits Coordinator"
		Status status = DAOFactory.getStatusDAO().getById(7);
		Set<Reimbursement> request = reimbursementDAO.getByStatus(status);
		assertTrue(request.isEmpty());
	}
	//public int create(Reimbursement dataToAdd);
	@Test
	@Order(value = 0)
	public void create() {
		Reimbursement req = new Reimbursement();
		generatedId = reimbursementDAO.create(req);
		assertNotEquals(0, generatedId);
	}
	//public Reimbursement getById(int id);
	@Test
	public void getByIdWhenIdExists() {
		Reimbursement result = reimbursementDAO.getById(1);
		assertEquals(1, result.getReqId());
	}
	
	@Test
	public void getByIdWhenIdDoesntExist() {
		Reimbursement result = reimbursementDAO.getById(1138);
		assertNull(result);
	}
	//public Set<Reimbursement> getAll();
	@Test
	public void getAll() {
		Set<Reimbursement> result = reimbursementDAO.getAll();
		assertNotNull(result);
		assertFalse(result.isEmpty());
	}
	//public void update(Reimbursement dataToUpdate);
	@Test
	@Order(value = 1)
	public void update() {
		Reimbursement req = reimbursementDAO.getById(generatedId);
		req.setLocation("The Moon");
		reimbursementDAO.update(req);
		assertEquals("The Moon", reimbursementDAO.getById(generatedId).getLocation());
	}
	//public void delete(Reimbursement dataToDelete);
	@Test
	@Order(value = 100)
	public void delete() {
		int currentSize = reimbursementDAO.getAll().size();
		Reimbursement req = reimbursementDAO.getById(generatedId);
		reimbursementDAO.delete(req);
		int newSize = reimbursementDAO.getAll().size();
		assertEquals(currentSize-1, newSize);
	}
	
	//public Set<Reimbursement> getByApprover(Employee approver);
	@Test
	public void getByApproverWhenApproverIsBenefitsCoordinator() {
		//Test data employee 4 is a Benefits Coordinator
		Employee approver = DAOFactory.getEmployeeDAO().getById(4);
		Set<Reimbursement> request = reimbursementDAO.getByApprover(approver);
		assertFalse(request.isEmpty());
		for(Reimbursement req: request) {
			assertEquals("Benefits Coordinator", req.getStatus().getApprover());
		}
	}
	@Test
	public void getByApproverWhenApproverIsDepartmentHead() {
		//Test data employee 1 is a Department Head for Department 1
		Employee approver = DAOFactory.getEmployeeDAO().getById(1);
		Set<Reimbursement> request = reimbursementDAO.getByApprover(approver);
		assertFalse(request.isEmpty());
		for(Reimbursement req: request) {
			assertEquals(1, req.getRequestor().getDepartment().getDeptId());
			assertEquals("Department Head", req.getStatus().getApprover());
		}
	}
	@Test
	public void getByApproverWhenApproverIsSupervisor() {
		//Test data employee 6 is a Supervisor to employee 9
		Employee approver = DAOFactory.getEmployeeDAO().getById(6);
		Set<Reimbursement> request = reimbursementDAO.getByApprover(approver);
		assertFalse(request.isEmpty());
		for(Reimbursement req: request) {
			assertEquals(approver, req.getRequestor().getSupervisor());
		}
	}
	@Test
	public void getByApproverWhenApproverIsEmployee() {
		//Test data employee 10 is a standard employee and would have no pending requests to approve
		Employee approver = DAOFactory.getEmployeeDAO().getById(10);
		Set<Reimbursement> request = reimbursementDAO.getByApprover(approver);
		assertTrue(request.isEmpty());
	}
}
