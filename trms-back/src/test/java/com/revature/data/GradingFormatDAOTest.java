package com.revature.data;
import org.junit.jupiter.api.Test;
import com.revature.beans.GradingFormat;
import com.revature.data.GradingFormatDAO;
import com.revature.utils.DAOFactory;
//this imports the static methods from Assertions so that
//we can type "assertEquals" rather than "Assertions.assertEquals"
import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

public class GradingFormatDAOTest {
	private GradingFormatDAO gradingFormatDAO = DAOFactory.getGradingFormatDAO();
	//DAO Methods to Test
	//public GradingFormat getById(int id);
	//public Set<Object> getAll();
	//public Set<GradingFormat> getByName(String name);
}
