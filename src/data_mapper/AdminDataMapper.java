package data_mapper;

import models.*;
import java.awt.List;
import java.net.URISyntaxException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.print.attribute.HashAttributeSet;

import database.DBConnection;

public class AdminDataMapper {
	
	public static ArrayList<Admin> searchbyid(int id){
		String sql = "SELECT admin_id, username,firstname, lastname from admin_table WHERE admin_id ="+id;
		Connection connection;
		ArrayList<Admin> results = new ArrayList<Admin>();
		try {
			connection = DBConnection.getConnection();
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				int admin_id = rs.getInt(1);
				String username = rs.getString(2);
				String firstname = rs.getString(3);
				String lastname = rs.getString(4);
				Admin admin = new Admin(username, firstname, lastname);
				results.add(admin);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(results.size());
		return results;
	}

}
