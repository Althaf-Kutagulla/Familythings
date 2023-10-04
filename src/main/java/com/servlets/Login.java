package com.servlets;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.AuthenticationDao;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	
   

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		System.out.println(email);
		System.out.println(password);
		
		
		AuthenticationDao auth1 = new AuthenticationDao();
		boolean isValidUser=false;
		try {
			 isValidUser =auth1.isValidUser(email, password);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(isValidUser) {
			HttpSession session = request.getSession();
			session.setAttribute("email", email);
			session.setAttribute("password", password);
			response.sendRedirect("todolist.jsp");
			System.out.println("Valid user");
		}else {
			System.out.println("Not Valid User create an account");
			response.sendRedirect("registration.jsp");
		}
		
	}

	
}
