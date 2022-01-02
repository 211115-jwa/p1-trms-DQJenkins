package com.revature.data;

import com.revature.beans.Comment;
import java.util.Set;

public interface CommentDAO {
	public int create(Comment dataToAdd);
	public Comment getById(int id);
	public Set<Comment> getAll();
	public Set<Comment> getByRequestId(int reqId);
}
