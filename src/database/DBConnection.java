package database;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;


public class DBConnection {
	
	private static final String DB_CONNECTION = "jdbc:postgres://ckqxwwkldfexal:ee20e3e7c214ad851f1573af0f8f9035ce73ffcf2c1cf8c173aa84ad26aef8d7@ec2-54-83-33-14.compute-1.amazonaws.com:5432/dba3bnrqvjmifs";
	private static final String DB_User = "ckqxwwkldfexal";
	private static final String DB_Password = "ee20e3e7c214ad851f1573af0f8f9035ce73ffcf2c1cf8c173aa84ad26aef8d7";
	
	static Connection connection = null;
	static PreparedStatement ps = null;
	
	public static PreparedStatement prepare(String statement) throws SQLException, URISyntaxException{
		try {
			Class.forName("org.postgresql.Driver");
			URI dbURI = new URI(System.getenv("DATABASE_URL"));
			//URI dbURI = new URI("postgres://ckqxwwkldfexal:ee20e3e7c214ad851f1573af0f8f9035ce73ffcf2c1cf8c173aa84ad26aef8d7@ec2-54-83-33-14.compute-1.amazonaws.com:5432/dba3bnrqvjmifs");
			String username = dbURI.getUserInfo().split(":")[0];
			String pwd = dbURI.getUserInfo().split(":")[1];
			String url = "jdbc:postgresql://"+dbURI.getHost()+":"+dbURI.getPort()+dbURI.getPath();
			
			connection = DriverManager.getConnection(url, username, pwd);
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
