package com.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthenticationDao {
	
	public Connection connectDB() throws ClassNotFoundException, SQLException {
		
		Class.forName("org.postgresql.Driver");
		
		Connection connection =null;
		connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/calendarevents","postgres","althaf");
		
		return connection;
	}
	
	public boolean isValidUser(String email,String password) throws ClassNotFoundException, SQLException {
		
		Connection connection = connectDB();
		
		PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE email=? AND password=?");
		statement.setString(1, email);
		statement.setString(2, password);
		
		
		ResultSet results = statement.executeQuery();
		
		if(results.next()) {
			return true;	
		};
		
		return false;
		
	}
	
	public boolean createNewAccount(String email,String Password) throws ClassNotFoundException, SQLException {
		
		Connection connection = connectDB();
		PreparedStatement statement = connection.prepareStatement("INSERT INTO users (email,password) VALUES (?,?)");
		statement.setString(1, email);
		statement.setString(2, Password);
		
		int result =statement.executeUpdate();
		System.out.println(result);
		if(result == 1) {
			return true;
		}
		
		return false;
		
	}
	
	
//	public static void main(String args[]) throws ClassNotFoundException, SQLException {
//		AuthenticationDao auth1 = new AuthenticationDao();
//		//boolean result =auth1.isValidUser("althaff@gmail.com", "althaf123");
//		
//		auth1.createNewAccount("kiran@gmail.com", "kiran123");
//		
//	}

}
