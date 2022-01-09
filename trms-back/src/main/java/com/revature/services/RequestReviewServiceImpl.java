package com.revature.services;

import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Comment;
import com.revature.beans.Employee;
import com.revature.beans.Reimbursement;
import com.revature.data.EmployeeDAO;
import com.revature.data.ReimbursementDAO;
import com.revature.utils.DAOFactory;

public class RequestReviewServiceImpl implements RequestReviewService {
	EmployeeDAO empDAO = DAOFactory.getEmployeeDAO();
	ReimbursementDAO reqDAO = DAOFactory.getReimbursementDAO();
	/**
	 * Returns the Set of reimbursement requests that are
	 * currently pending approval from the specified Employee.
	 * The method must account for all of the following scenarios:
	 * - Requests that are at the "direct supervisor" approval
	 * status and the specified employee is the requestor's direct
	 * supervisor
	 * - Requests that are at the "department head" approval
	 * status and the specified employee is the head of that department
	 * - Requests that are at the "benefits coordinator" approval
	 * status and the specified employee is in the human resources
	 * department
	 * @param approver the employee who must approve the returned requests
	 * @return the Set of requests awaiting the specified employee's approval
	 */
	@Override
	public Set<Reimbursement> getPendingReimbursements(Employee approver) {
		return reqDAO.getByApprover(approver);
	}

	@Override
	public void approveRequest(Reimbursement request) {
		//if status < 4
		//status++
		//else return (request already completed)
	}

	@Override
	public void rejectRequest(Reimbursement request) {
		//if status < 4
		//status += 4
		//else return(request already completed)
	}

	@Override
	public void rejectRequest(Reimbursement request, Comment comment) {
		//if status < 4
		//status += 4, create comment
		//else return(request already completed)
	}

}
