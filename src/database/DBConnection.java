package database;

import java.io.Console;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.LookAndFeel;

public class DBConnection {
	
	private static final String DB_CONNECTION = "jdbc:postgresql://ec2-107-22-160-185.compute-1.amazonaws.com:5432/d5jrjtoi3jojjg";//?sslmode=require";
	private static final String DB_User = "vvweskpjovgfiy";
	private static final String DB_Password = "5e4e5b2e57eac91647625358c8798c3a4edd5de7d5709134aa3bba178c3ee3c1";
	
//	private static final String DB_CONNECTION = "jdbc:postgresql://localhost:5432/hrm";
//	private static final String DB_User = "postgres";
//	private static final String DB_Password = "admin";
	
	public static Connection connection = null;
	static PreparedStatement ps = null;
	
	public static Connection getConnection() throws SQLException, URISyntaxException{
		try {
			DriverManager.registerDriver(new org.postgresql.Driver());
			connection = DriverManager.getConnection(DB_CONNECTION, DB_User, DB_Password);
			connection.setAutoCommit(true);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		//return ps;
		return connection;
	}

}
