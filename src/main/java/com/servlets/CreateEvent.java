package com.servlets;

import javax.servlet.annotation.WebServlet;
import com.pojo.*;
import com.model.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.SQLException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecalender.GoogleCalendarAPI;
import com.googlecalender.GoogleCalenderAPI;

/**
 * Servlet implementation class CreateEvent
 */
@WebServlet("/CreateEvent")
public class CreateEvent extends HttpServlet {
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String title = (String)request.getParameter("title");
		String description = (String)request.getParameter("description");
		String location =(String) request.getParameter("location");
		String startDateTime = (String)request.getParameter("startDateTime");
		String endDateTime = (String) request.getParameter("endDateTime");
		System.out.println(title);
		System.out.println(description);
		System.out.println(location);
		System.out.println(startDateTime);
		System.out.println(endDateTime);
		
		GoogleCalendarAPI calender = new GoogleCalendarAPI();
		String eventId="";
		try {
			eventId =calender.setNewEvent(title, location, description,startDateTime,endDateTime);
			//eventId =calender.setNewEvent("Family time", "Banglore", "dinner","2023-10-30T10:00","2023-10-30T11:00");
		} catch (IOException | GeneralSecurityException e) {
			
		e.printStackTrace();
		}
		
		CalendarEvent event = new CalendarEvent();
		event.setEventId(eventId);
		event.setTitle(title);
		event.setDescription(description);
		event.setLocation(location);
		event.setStartDateTime(startDateTime);
		event.setEndDateTime(endDateTime);
		CalendarsDao calendar = new CalendarsDao();
		try {
			calendar.createEventInDatabase(event);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		response.sendRedirect("todolist.jsp");
		
		
		
		
		
	}

}
