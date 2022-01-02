package com.revature.data;
import org.junit.jupiter.api.Test;
import com.revature.beans.Comment;
import com.revature.data.CommentDAO;
import com.revature.utils.DAOFactory;
//this imports the static methods from Assertions so that
//we can type "assertEquals" rather than "Assertions.assertEquals"
import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

public class CommentDAOTest {
	private CommentDAO commentDAO = DAOFactory.getCommentDAO();
	//DAO Methods to test
	//public int create(Comment dataToAdd);
	//public Comment getById(int id);
	//public Set<Comment> getAll();
	//public Set<Comment> getByRequestId(int reqId);
}
