package com.servlets;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecalender.GoogleCalendarAPI;
import com.model.CalendarsDao;

/**
 * Servlet implementation class DeleteEvent
 */
@WebServlet("/DeleteEvent")
public class DeleteEvent extends HttpServlet {
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String eventId =(String) request.getParameter("eventId");
		System.out.println(eventId);
		System.out.println("Delete event Servlet Called");
		GoogleCalendarAPI calendar = new GoogleCalendarAPI();
		try {
			calendar.deleteEvent(eventId);
		} catch (IOException | GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		CalendarsDao calendarevent = new CalendarsDao();
		try {
			calendarevent.deleteEvent(eventId);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		response.sendRedirect("todolist.jsp");
	}

}
