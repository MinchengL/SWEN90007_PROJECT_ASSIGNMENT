package database;

import java.net.URISyntaxException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
	
	private static int userCount = 0;
	
	private static final String sql = "select * from \"Users\"";
	
	private static ResultSet rs = null;
	
	public static int getUserCount() throws URISyntaxException {
	try {
		PreparedStatement statement = DBConnection.prepare(sql);
		rs = statement.executeQuery();
		System.out.println(rs.getRow());
		while(rs.next()) {
			userCount++;
		}
	}catch(SQLException e) {
		e.printStackTrace();
	}
	return userCount;
	}
}
