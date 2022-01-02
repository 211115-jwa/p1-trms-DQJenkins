package com.revature.data;
import org.junit.jupiter.api.Test;

import com.revature.beans.Employee;
import com.revature.beans.Reimbursement;
import com.revature.beans.Status;
import com.revature.data.ReimbursementDAO;
import com.revature.utils.DAOFactory;
//this imports the static methods from Assertions so that
//we can type "assertEquals" rather than "Assertions.assertEquals"
import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

public class ReimbursementDAOTest {
	private ReimbursementDAO reimbursementDAO = DAOFactory.getReimbursementDAO();
	
	//DAO methods to test
	//public Set<Reimbursement> getByRequestor(Employee requestor);
	//public Set<Reimbursement> getByStatus(Status status);
	//public int create(Reimbursement dataToAdd);
	//public Reimbursement getById(int id);
	//public Set<Reimbursement> getAll();
	//public void update(ReimbursementdataToUpdate);
	//public void delete(Reimbursement dataToDelete);
}
