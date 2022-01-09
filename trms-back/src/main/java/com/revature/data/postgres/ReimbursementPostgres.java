package com.revature.data.postgres;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Role;
import com.revature.beans.Department;
import com.revature.beans.Employee;
import com.revature.beans.EventType;
import com.revature.beans.GradingFormat;
import com.revature.beans.Reimbursement;
import com.revature.beans.Status;
import com.revature.data.ReimbursementDAO;
import com.revature.utils.ConnectionUtil;
import com.revature.utils.DAOFactory;

public class ReimbursementPostgres implements ReimbursementDAO {
	private ConnectionUtil connUtil = ConnectionUtil.getConnectionUtil();

	@Override
	public int create(Reimbursement dataToAdd) {
		int generatedId = 0;
		try (Connection conn = connUtil.getConnection()) {
			conn.setAutoCommit(false);
			String[] keys = { "req_id" };
			String sql = "insert into reimbursement" + " (emp_id," + " event_date," + " event_time," + " location,"
					+ " description," + " cost," + " grading_format_id," + " event_type_id," + " status_id,"
					+ " submitted_at)" + " values (?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement pStmt = conn.prepareStatement(sql, keys);
			if (dataToAdd.getRequestor() != null) {
				pStmt.setInt(1, dataToAdd.getRequestor().getEmpId());
			} else {
				pStmt.setNull(1, java.sql.Types.NULL);
			}
			if (dataToAdd.getEventDate() != null) {
				pStmt.setDate(2, Date.valueOf(dataToAdd.getEventDate()));
			} else {
				pStmt.setDate(2, Date.valueOf("2100-12-31"));
			}
			if (dataToAdd.getEventTime() != null) {
				pStmt.setTime(3, Time.valueOf(dataToAdd.getEventTime()));
			} else {
				pStmt.setTime(3, Time.valueOf("00:00:00"));
			}
			pStmt.setString(4, dataToAdd.getLocation());
			pStmt.setString(5, dataToAdd.getDescription());
			pStmt.setDouble(6, dataToAdd.getCost());
			if (dataToAdd.getGradingFormat() != null) {
				pStmt.setInt(7, dataToAdd.getGradingFormat().getFormatId());
			} else {
				// Defaults to "Pass/No Pass"
				pStmt.setInt(7, 2);
			}
			if (dataToAdd.getEventType() != null) {
				pStmt.setInt(8, dataToAdd.getEventType().getEventId());
			} else {
				// Defaults to "Other"
				pStmt.setInt(8, 6);
			}
			if (dataToAdd.getStatus() != null) {
				pStmt.setInt(9, dataToAdd.getStatus().getStatusId());
			} else {
				// Defaults to "Pending Approval" - "Direct Supervisor"
				pStmt.setInt(9, 1);
			}
			pStmt.setTimestamp(10, Timestamp.valueOf(dataToAdd.getSubmittedAt()));

			pStmt.executeUpdate();
			ResultSet generatedKeys = pStmt.getGeneratedKeys();
			if (generatedKeys.next()) {
				generatedId = generatedKeys.getInt(1);
				conn.commit();
			} else {
				conn.rollback();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return generatedId;
	}

	@Override
	public Reimbursement getById(int id) {
		Reimbursement request = null;
		try (Connection conn = connUtil.getConnection()) {
			String sql = "select" + " req_id," + " emp_id," + " event_date," + " event_time," + " location,"
					+ " description," + " cost," + " grading_format_id," + " format_name,"
					+ " example as format_example," + " event_type_id," + " type_name," + " percent_covered,"
					+ " r.status_id," + " status_name," + " approver," + " submitted_at " + " from reimbursement r"
					+ " join grading_format gf on r.grading_format_id=gf.format_id"
					+ " join event_type et on r.event_type_id=et.type_id" + " join status s on r.status_id=s.status_id"
					+ " where req_id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, id);

			ResultSet resultSet = pStmt.executeQuery();
			while (resultSet.next()) {
				request = new Reimbursement();
				request.setReqId(resultSet.getInt("req_id"));

				// request.getRequestor().setEmpId(resultSet.getInt("emp_id"));
				request.setRequestor(DAOFactory.getEmployeeDAO().getById(resultSet.getInt("emp_id")));
				request.setEventDate(resultSet.getDate("event_date").toLocalDate());
				request.setEventTime(resultSet.getTime("event_time").toLocalTime());
				request.setLocation(resultSet.getString("location"));
				request.setDescription(resultSet.getString("description"));
				request.setCost(resultSet.getDouble("cost"));

				GradingFormat gf = new GradingFormat();
				gf.setFormatId(resultSet.getInt("grading_format_id"));
				gf.setName(resultSet.getString("format_name"));
				gf.setExample(resultSet.getString("format_example"));
				request.setGradingFormat(gf);

				EventType et = new EventType();
				et.setEventId(resultSet.getInt("event_type_id"));
				et.setName(resultSet.getString("type_name"));
				et.setPercentCovered(resultSet.getDouble("percent_covered"));
				request.setEventType(et);

				Status s = new Status();
				s.setStatusId(resultSet.getInt("status_id"));
				s.setName(resultSet.getString("status_name"));
				s.setApprover(resultSet.getString("approver"));
				request.setStatus(s);

				request.setSubmittedAt(resultSet.getTimestamp("submitted_at").toLocalDateTime());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return request;
	}

	@Override
	public Set<Reimbursement> getAll() {
		Set<Reimbursement> requests = new HashSet<>();
		try (Connection conn = connUtil.getConnection()) {
			String sql = "select" + " req_id," + " emp_id," + " event_date," + " event_time," + " location,"
					+ " description," + " cost," + " grading_format_id," + " format_name,"
					+ " example as format_example," + " event_type_id," + " type_name," + " percent_covered,"
					+ " r.status_id," + " status_name," + " approver," + " submitted_at " + " from reimbursement r"
					+ " join grading_format gf on r.grading_format_id=gf.format_id"
					+ " join event_type et on r.event_type_id=et.type_id" + " join status s on r.status_id=s.status_id";
			Statement stmt = conn.createStatement();

			ResultSet resultSet = stmt.executeQuery(sql);
			while (resultSet.next()) {
				Reimbursement request = new Reimbursement();
				request.setReqId(resultSet.getInt("req_id"));
				// request.getRequestor().setEmpId(resultSet.getInt("emp_id"));
				request.setRequestor(DAOFactory.getEmployeeDAO().getById(resultSet.getInt("emp_id")));
				request.setEventDate(resultSet.getDate("event_date").toLocalDate());
				request.setEventTime(resultSet.getTime("event_time").toLocalTime());
				request.setLocation(resultSet.getString("location"));
				request.setDescription(resultSet.getString("description"));
				request.setCost(resultSet.getDouble("cost"));

				GradingFormat gf = new GradingFormat();
				gf.setFormatId(resultSet.getInt("grading_format_id"));
				gf.setName(resultSet.getString("format_name"));
				gf.setExample(resultSet.getString("format_example"));
				request.setGradingFormat(gf);

				EventType et = new EventType();
				et.setEventId(resultSet.getInt("event_type_id"));
				et.setName(resultSet.getString("type_name"));
				et.setPercentCovered(resultSet.getDouble("percent_covered"));
				request.setEventType(et);

				Status s = new Status();
				s.setStatusId(resultSet.getInt("status_id"));
				s.setName(resultSet.getString("status_name"));
				s.setApprover(resultSet.getString("approver"));
				request.setStatus(s);

				request.setSubmittedAt(resultSet.getTimestamp("submitted_at").toLocalDateTime());

				requests.add(request);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return requests;
	}

	@Override
	public void update(Reimbursement dataToUpdate) {
		try (Connection conn = connUtil.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "update reimbursement set" + " emp_id=?," + " event_date=?," + " event_time=?,"
					+ " location=?," + " description=?," + " cost=?," + " grading_format_id=?," + " event_type_id=?,"
					+ " status_id=?," + " submitted_at=?" + " where req_id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			if (dataToUpdate.getRequestor() != null) {
				pStmt.setInt(1, dataToUpdate.getRequestor().getEmpId());
			} else {
				pStmt.setNull(1, java.sql.Types.NULL);
			}
			if (dataToUpdate.getEventDate() != null) {
				pStmt.setDate(2, Date.valueOf(dataToUpdate.getEventDate()));
			} else {
				pStmt.setNull(2, java.sql.Types.NULL);
			}
			if (dataToUpdate.getEventTime() != null) {
				pStmt.setTime(3, Time.valueOf(dataToUpdate.getEventTime()));
			} else {
				pStmt.setNull(3, java.sql.Types.NULL);
			}
			pStmt.setString(4, dataToUpdate.getLocation());
			pStmt.setString(5, dataToUpdate.getDescription());
			pStmt.setDouble(6, dataToUpdate.getCost());
			pStmt.setInt(7, dataToUpdate.getGradingFormat().getFormatId());
			pStmt.setInt(8, dataToUpdate.getEventType().getEventId());
			pStmt.setInt(9, dataToUpdate.getStatus().getStatusId());
			pStmt.setTimestamp(10, Timestamp.valueOf(dataToUpdate.getSubmittedAt()));
			pStmt.setInt(11, dataToUpdate.getReqId());

			int rowsAffected = pStmt.executeUpdate();
			if (rowsAffected <= 1) {
				conn.commit();
			} else {
				conn.rollback();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Reimbursement dataToDelete) {
		try (Connection conn = connUtil.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "delete from reimbursement" + " where req_id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, dataToDelete.getReqId());

			int rowsAffected = pStmt.executeUpdate();
			if (rowsAffected <= 1) {
				conn.commit();
			} else {
				conn.rollback();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Set<Reimbursement> getByRequestor(Employee requestor) {
		Set<Reimbursement> requests = new HashSet<>();
		try (Connection conn = connUtil.getConnection()) {
			String sql = "select" + " req_id," + " emp_id," + " event_date," + " event_time," + " location,"
					+ " description," + " cost," + " grading_format_id," + " format_name,"
					+ " example as format_example," + " event_type_id," + " type_name," + " percent_covered,"
					+ " r.status_id," + " status_name," + " approver," + " submitted_at " + " from reimbursement r"
					+ " join grading_format gf on r.grading_format_id=gf.format_id"
					+ " join event_type et on r.event_type_id=et.type_id" + " join status s on r.status_id=s.status_id"
					+ " where emp_id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, requestor.getEmpId());

			ResultSet resultSet = pStmt.executeQuery();
			while (resultSet.next()) {
				Reimbursement request = new Reimbursement();
				request.setReqId(resultSet.getInt("req_id"));
				// request.getRequestor().setEmpId(resultSet.getInt("emp_id"));
				request.setRequestor(DAOFactory.getEmployeeDAO().getById(resultSet.getInt("emp_id")));
				request.setEventDate(resultSet.getDate("event_date").toLocalDate());
				request.setEventTime(resultSet.getTime("event_time").toLocalTime());
				request.setLocation(resultSet.getString("location"));
				request.setDescription(resultSet.getString("description"));
				request.setCost(resultSet.getDouble("cost"));

				GradingFormat gf = new GradingFormat();
				gf.setFormatId(resultSet.getInt("grading_format_id"));
				gf.setName(resultSet.getString("format_name"));
				gf.setExample(resultSet.getString("format_example"));
				request.setGradingFormat(gf);

				EventType et = new EventType();
				et.setEventId(resultSet.getInt("event_type_id"));
				et.setName(resultSet.getString("type_name"));
				et.setPercentCovered(resultSet.getDouble("percent_covered"));
				request.setEventType(et);

				Status s = new Status();
				s.setStatusId(resultSet.getInt("status_id"));
				s.setName(resultSet.getString("status_name"));
				s.setApprover(resultSet.getString("approver"));
				request.setStatus(s);

				request.setSubmittedAt(resultSet.getTimestamp("submitted_at").toLocalDateTime());

				requests.add(request);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return requests;
	}

	@Override
	public Set<Reimbursement> getByStatus(Status status) {
		Set<Reimbursement> requests = new HashSet<>();
		try (Connection conn = connUtil.getConnection()) {
			String sql = "select" + " req_id," + " emp_id," + " event_date," + " event_time," + " location,"
					+ " description," + " cost," + " grading_format_id," + " format_name,"
					+ " example as format_example," + " event_type_id," + " type_name," + " percent_covered,"
					+ " r.status_id," + " status_name," + " approver," + " submitted_at " + " from reimbursement r"
					+ " join grading_format gf on r.grading_format_id=gf.format_id"
					+ " join event_type et on r.event_type_id=et.type_id" + " join status s on r.status_id=s.status_id"
					+ " where r.status_id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, status.getStatusId());

			ResultSet resultSet = pStmt.executeQuery();
			while (resultSet.next()) {
				Reimbursement request = new Reimbursement();
				request.setReqId(resultSet.getInt("req_id"));
				// request.getRequestor().setEmpId(resultSet.getInt("emp_id"));
				request.setRequestor(DAOFactory.getEmployeeDAO().getById(resultSet.getInt("emp_id")));
				request.setEventDate(resultSet.getDate("event_date").toLocalDate());
				request.setEventTime(resultSet.getTime("event_time").toLocalTime());
				request.setLocation(resultSet.getString("location"));
				request.setDescription(resultSet.getString("description"));
				request.setCost(resultSet.getDouble("cost"));
				GradingFormat gf = new GradingFormat();
				gf.setFormatId(resultSet.getInt("grading_format_id"));
				gf.setName(resultSet.getString("format_name"));
				gf.setExample(resultSet.getString("format_example"));
				request.setGradingFormat(gf);
				EventType et = new EventType();
				et.setEventId(resultSet.getInt("event_type_id"));
				et.setName(resultSet.getString("type_name"));
				et.setPercentCovered(resultSet.getDouble("percent_covered"));
				request.setEventType(et);
				Status s = new Status();
				s.setStatusId(resultSet.getInt("status_id"));
				s.setName(resultSet.getString("status_name"));
				s.setApprover(resultSet.getString("approver"));
				request.setStatus(s);
				request.setSubmittedAt(resultSet.getTimestamp("submitted_at").toLocalDateTime());

				requests.add(request);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return requests;
	}

	@Override
	public Set<Reimbursement> getByApprover(Employee approver) {
		Set<Reimbursement> requests = new HashSet<>();
		// try (Connection conn = connUtil.getConnection()) {
		Role approver_role = approver.getRole();
		Department approver_dep = approver.getDepartment();
		// int approver_id = approver.getEmpId();

		// String sql = "";
		if (approver_role.getName() == "Employee") {
			// Return an empty set if user role is Employee,
			// they have no requests to approve
			return requests;
		} else {
			Set<Reimbursement> allRequests = getAll();
			for (Reimbursement req : allRequests) {
				//System.out.println("req status: " + req.getStatus().getName());
				//System.out.println("req approver: " + req.getStatus().getApprover());
				//System.out.println("approver_role: " + approver_role.getName());
				//System.out.println(req.getStatus().getName().equals("Pending Approval"));
				//System.out.println(req.getStatus().getApprover().equals(approver_role.getName()));
				if (req.getStatus().getName().equals("Pending Approval")
					&& req.getStatus().getApprover().equals(approver_role.getName())) 
				{
					//System.out.println("Inside first if");
					if (req.getStatus().getApprover().equals("Benefits Coordinator")) 
					{
						//System.out.println("Inside BenCo if");
						requests.add(req);
					} else if (req.getStatus().getApprover().equals("Department Head")
							&& req.getRequestor().getDepartment().equals(approver_dep)) 
					{
						//System.out.println("Inside DepHead if");
						requests.add(req);
					} else if (req.getStatus().getApprover().equals("Supervisor")
							&& req.getRequestor().getSupervisor().equals(approver)) 
					{
						//System.out.println("Inside Supervisor if");
						requests.add(req);
					}
				}
			}

		}
		/*
		 * if (role == "Benefits Coordinator") { sql="select" +
		 * " req_id, r.emp_id, event_date, event_time," +
		 * " location, description, cost," +
		 * " grading_format_id, format_name, example as format_example," +
		 * " event_type_id, type_name, percent_covered," +
		 * " r.status_id, status_name, approver," + " submitted_at " +
		 * " from reimbursement r" +
		 * " join grading_format gf on r.grading_format_id=gf.format_id" +
		 * " join event_type et on r.event_type_id=et.type_id" +
		 * " join status s ON r.status_id = s.status_id" +
		 * " join user_role ur on role_name = approver" +
		 * " JOIN employee e ON e.role_id = ur.role_id" + " where approver = ?" +
		 * " and status_name = 'Pending Approval';"; } if (role == "Department Head") {
		 * 
		 * }
		 * 
		 * if (role == "Supervisor") {
		 * 
		 * }
		 * 
		 * PreparedStatement pStmt = conn.prepareStatement(sql); pStmt.setString(1,
		 * role); ResultSet resultSet = pStmt.executeQuery(); while (resultSet.next()) {
		 * Reimbursement request = new Reimbursement();
		 * request.setReqId(resultSet.getInt("req_id"));
		 * //request.getRequestor().setEmpId(resultSet.getInt("emp_id"));
		 * request.setRequestor(DAOFactory.getEmployeeDAO().getById(resultSet.getInt(
		 * "emp_id")));
		 * request.setEventDate(resultSet.getDate("event_date").toLocalDate());
		 * request.setEventTime(resultSet.getTime("event_time").toLocalTime());
		 * request.setLocation(resultSet.getString("location"));
		 * request.setDescription(resultSet.getString("description"));
		 * request.setCost(resultSet.getDouble("cost")); GradingFormat gf = new
		 * GradingFormat(); gf.setFormatId(resultSet.getInt("grading_format_id"));
		 * gf.setName(resultSet.getString("format_name"));
		 * gf.setExample(resultSet.getString("format_example"));
		 * request.setGradingFormat(gf); EventType et = new EventType();
		 * et.setEventId(resultSet.getInt("event_type_id"));
		 * et.setName(resultSet.getString("type_name"));
		 * et.setPercentCovered(resultSet.getDouble("percent_covered"));
		 * request.setEventType(et); Status s = new Status();
		 * s.setStatusId(resultSet.getInt("status_id"));
		 * s.setName(resultSet.getString("status_name"));
		 * s.setApprover(resultSet.getString("approver")); request.setStatus(s);
		 * request.setSubmittedAt(resultSet.getTimestamp("submitted_at").toLocalDateTime
		 * ());
		 * 
		 * requests.add(request); } } catch (SQLException e) { e.printStackTrace(); }
		 */
		return requests;
	}
}
