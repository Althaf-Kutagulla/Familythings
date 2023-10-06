package com.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.*;

/**
 * Servlet implementation class Registration
 */
@WebServlet("/Registration")
public class Registration extends HttpServlet {
	
   
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		System.out.println(email);
		System.out.println(password);
		
		
	
		AuthenticationDao auth1 = new AuthenticationDao();
		try {
			if(!auth1.isValidUser(email, password)) {
				System.out.println("User do not have account");
			boolean accountCreated =	auth1.createNewAccount(email, password);
			if(accountCreated) {
				response.sendRedirect("login.jsp");
			}
				
			}else {
				System.out.println("User have account");
				response.sendRedirect("login.jsp");
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
