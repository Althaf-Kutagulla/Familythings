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
		PreparedStatement prepared = connection.prepareStatement("INSERT INTO calendarevents (eventid,title,description,location,startdatetime,enddatetime,status) VALUES (?,?,?,?,?,?,?)");
		prepared.setString(1,event.getEventId());
		prepared.setString(2,event.getTitle());
		prepared.setString(3,event.getDescription());
		prepared.setString(4, event.getLocation());
		prepared.setString(5, event.getStartDateTime());
		prepared.setString(6,event.getEndDateTime());
		prepared.setString(7,"not done");
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
			event.setStatus(resultset.getString("status"));
			event.setId(resultset.getInt(1));
			
			list.add(event);
		}
		return list;	
	}
	
	public CalendarEvent getCalendarEvent(int id) throws ClassNotFoundException, SQLException {
		CalendarEvent event = new CalendarEvent();
		Class.forName("org.postgresql.Driver");
		Connection connection =null;
		connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/calendarevents","postgres","althaf");
		PreparedStatement prepare = connection.prepareStatement("SELECT * FROM calendarevents WHERE id=?");
		prepare.setInt(1, id);
		ResultSet result =prepare.executeQuery();
		
		while(result.next()){
			event.setId(result.getInt("id"));
			event.setEventId(result.getString("eventid"));
			event.setTitle(result.getString("title"));
			event.setDescription(result.getString("description"));
			event.setLocation(result.getString("location"));
			event.setStartDateTime(result.getString("startdatetime"));
			event.setEndDateTime(result.getString("enddatetime"));
			event.setStatus(result.getString("status"));		
		}
		
		return event;
	}
	
	public void updateEvent(CalendarEvent event,String id) throws ClassNotFoundException, SQLException {
		System.out.println("worked");
		Class.forName("org.postgresql.Driver");
		Connection connection =null;
		connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/calendarevents","postgres","althaf");
		PreparedStatement prepare = connection.prepareStatement("UPDATE calendarevents SET title=?,description=?,location=?,startdatetime=?,enddatetime=? WHERE eventid=?");
		prepare.setString(1, event.getTitle());
		prepare.setString(2,event.getDescription());
		prepare.setString(3,event.getLocation());
		prepare.setString(4,event.getStartDateTime());
		prepare.setString(5, event.getEndDateTime());
		prepare.setString(6,id);
		
		prepare.execute();
		
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
	
	public void changeEventStatus(int id) throws ClassNotFoundException, SQLException {
		Class.forName("org.postgresql.Driver");
		Connection connection =null;
		connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/calendarevents","postgres","althaf");
		PreparedStatement prepare = connection.prepareStatement("UPDATE calendarevents SET status=? WHERE id=?");
		prepare.setString(1,"done");
		prepare.setInt(2,id);
		prepare.execute();
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
