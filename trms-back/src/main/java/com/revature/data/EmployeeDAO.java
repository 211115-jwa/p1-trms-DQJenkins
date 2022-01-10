package com.revature.data;

import com.revature.beans.Employee;
import com.revature.beans.Reimbursement;

public interface EmployeeDAO extends GenericDAO<Employee> {
	public Employee getByUsername(String username);
	public Employee getApproverByRequest(Reimbursement request);
}
