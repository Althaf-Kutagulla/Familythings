package com.servlets;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecalender.GoogleCalendarAPI;
import com.model.*;
import com.pojo.*;

/**
 * Servlet implementation class UpdateEvent
 */
@WebServlet("/UpdateEvent")
public class UpdateEvent extends HttpServlet {
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String eventId = request.getParameter("googleEventId");
		System.out.print("Google event id: "+eventId);
		
		CalendarEvent event = new CalendarEvent();
		event.setTitle(request.getParameter("title"));
		event.setDescription(request.getParameter("description"));
		event.setLocation(request.getParameter("location"));
		event.setStartDateTime(request.getParameter("startDateTime"));
		event.setEndDateTime(request.getParameter("endDateTime"));
		
		CalendarsDao calendar = new CalendarsDao();
		GoogleCalendarAPI googleCalendar = new GoogleCalendarAPI();
		
		try {
			String startDateTime = event.getStartDateTime()+":00-07:00";
			String endDateTime = event.getEndDateTime()+":00-07:00";
			googleCalendar.updateEvent(eventId, event.getTitle(), event.getDescription(), event.getLocation(), startDateTime, endDateTime);
		} catch (IOException | GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			calendar.updateEvent(event, eventId);
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		response.sendRedirect("todolist.jsp");
	}

}
