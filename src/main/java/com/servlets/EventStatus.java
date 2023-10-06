package com.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.CalendarsDao;

/**
 * Servlet implementation class EventStatus
 */
@WebServlet("/EventStatus")
public class EventStatus extends HttpServlet {
	



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id =Integer.parseInt( request.getParameter("Id"));
		System.out.println(id);
		
		CalendarsDao calendar = new CalendarsDao();
		try {
			calendar.changeEventStatus(id);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.sendRedirect("todolist.jsp");
	}

}
