package com.model;
import java.sql.Statement;

import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.pojo.*;

public class CalendarsDao {
	
//	public Connection connectDB() throws SQLException {
//		Connection connection=null;
//		connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/calendarevents","postgres","althaf");
//		return connection;
//	}
	
	public void createEventInDatabase(CalendarEvent event) throws SQLException, ClassNotFoundException {
		
		Class.forName("org.postgresql.Driver");
		
		Connection connection =null;
		connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/calendarevents","postgres","althaf");
		PreparedStatement prepared = connection.prepareStatement("INSERT INTO calendarevents (eventid,title,description,location,startdatetime,enddatetime) VALUES (?,?,?,?,?,?)");
		prepared.setString(1,event.getEventId());
		prepared.setString(2,event.getTitle());
		prepared.setString(3,event.getDescription());
		prepared.setString(4, event.getLocation());
		prepared.setString(5, event.getStartDateTime());
		prepared.setString(6,event.getEndDateTime());
		prepared.execute();
	}
	
	
	public List<CalendarEvent> getCalenderEvents() throws ClassNotFoundException, SQLException {
		Class.forName("org.postgresql.Driver");
		Connection connection =null;
		connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/calendarevents","postgres","althaf");
		Statement statement = connection.createStatement();
		ResultSet resultset = statement.executeQuery("SELECT * FROM calendarevents");
		
		
		List<CalendarEvent> list = new ArrayList<>();
		
		while(resultset.next()) {
			CalendarEvent event = new CalendarEvent();
			event.setEventId(resultset.getString(2));
			event.setTitle(resultset.getString(3));
			event.setDescription(resultset.getString(4));
			event.setLocation(resultset.getString(5));
			event.setStartDateTime(resultset.getString(6));
			event.setEndDateTime(resultset.getString(7));
			
			list.add(event);
		}
		return list;	
	}
	
	
	public void deleteEvent(String eventId) throws ClassNotFoundException, SQLException {
		
		Class.forName("org.postgresql.Driver");
		Connection connection =null;
		connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/calendarevents","postgres","althaf");
		PreparedStatement prepare = connection.prepareStatement("DELETE FROM calendarevents WHERE eventid=?");
		
		prepare.setString(1, eventId);
		prepare.execute();
		System.out.println(eventId);
		System.out.println("Event deleted from database.");
		
	}
	
	
	public static void main(String args[]) throws ClassNotFoundException, SQLException {
		CalendarEvent event = new CalendarEvent();
		event.setEventId("jdjdkls");
		
		event.setTitle("Family");
		event.setDescription("Time");
		event.setStartDateTime("jhd");
		event.setEndDateTime("jsi");
		event.setLocation("Banglore");
		CalendarsDao cal = new CalendarsDao();
		cal.createEventInDatabase(event);
	}
	
	
	
	

}
