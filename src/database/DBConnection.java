package database;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;


public class DBConnection {
	
	private static final String DB_CONNECTION = "jdbc:postgres://ec2-107-22-160-185.compute-1.amazonaws.com:5432/d5jrjtoi3jojjg";
	private static final String DB_User = "vvweskpjovgfiy";
	private static final String DB_Password = "5e4e5b2e57eac91647625358c8798c3a4edd5de7d5709134aa3bba178c3ee3c1";
	
	static Connection connection = null;
	static PreparedStatement ps = null;
	
	public static PreparedStatement prepare(String statement) throws SQLException, URISyntaxException{
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(DB_CONNECTION, DB_User, DB_Password);
			connection.setAutoCommit(false);
			ps = connection.prepareStatement(statement);
		}catch(SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ps;
	}

}
