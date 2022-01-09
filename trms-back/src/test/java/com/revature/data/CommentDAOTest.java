package com.revature.data;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import com.revature.beans.Comment;
import com.revature.beans.Reimbursement;
import com.revature.data.CommentDAO;
import com.revature.utils.DAOFactory;
//this imports the static methods from Assertions so that
//we can type "assertEquals" rather than "Assertions.assertEquals"
import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

public class CommentDAOTest {
	private CommentDAO commentDAO = DAOFactory.getCommentDAO();
	static int generatedId;
	//DAO Methods to test
	//public int create(Comment dataToAdd);
	@Test
	public void create() {
		Comment com = new Comment();
		generatedId = commentDAO.create(com);
		assertNotEquals(0, generatedId);
	}
	//public Comment getById(int id);
	@Test
	public void getByIdWhenIdExists() {
		Comment result = commentDAO.getById(1);
		assertEquals(1, result.getCommentId());
	}
	
	@Test
	public void getByIdWhenIdDoesntExist() {
		Comment result = commentDAO.getById(1138);
		assertNull(result);
	}
	//public Set<Comment> getAll();
	@Test
	public void getAll() {
		Set<Comment> result = commentDAO.getAll();
		assertNotNull(result);
		assertFalse(result.isEmpty());
	}
	//public Set<Comment> getByRequestId(int reqId);
	@Test
	public void getByRequestIdWhenIdExists() {
		Set<Comment> result = commentDAO.getByRequestId(1);
		assertFalse(result.isEmpty());
	}
	
	@Test
	public void getByRequestIdWhenIdDoesntExist() {
		Set<Comment> result = commentDAO.getByRequestId(1138);
		assertTrue(result.isEmpty());
	}
}
