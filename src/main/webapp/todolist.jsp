<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  import="com.model.*,com.pojo.*,java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>To do List</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>

<%
response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
if(session.getAttribute("email")==null){
	response.sendRedirect("login.jsp");
}

CalendarsDao calendar = new CalendarsDao();
List<CalendarEvent> eventList = calendar.getCalenderEvents();
session.setAttribute("eventList",eventList);

%>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container">
            <a class="navbar-brand" href="#">Family Things</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ml-auto">
                   
                    <li class="nav-item">
                        <a class="nav-link" href="todolist.jsp">Todo List</a>
                    </li>
                    <li class="nav-item">
                    	<form action="Logout" method="post"><input type="submit" class="nav-link" value="Logout" ></form>
                        
                    </li>
                </ul>
            </div>
            
            <span class="navbar-text">
                <c:out value="${email}"></c:out>
            </span>
        </div>
    </nav>



<div class="container mt-5">
        <div class="row mb-3">
            <div class="col">
                <button class="btn btn-primary"><a href="todolistform.jsp" style="text-decoration: none; color: white;">Create Event</a></button>
            </div>
        </div>
        <table class="table table-hover table-bordered">
            <thead class="thead-dark">
                <tr>
                    <th>Title</th>
                    <th>Description</th>
                    <th>Location</th>
                    <th>Start Date</th>
                    <th>End Date</th>
                    <th>Status</th>
                    <th>Change status</th>
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${eventList}" var="event">
                    <tr>
                        <td><c:out value="${event.getTitle()}"></c:out></td>
                        <td><c:out value="${event.getDescription()}"></c:out></td>
                        <td><c:out value="${event.getLocation()}"></c:out></td>
                        <td><c:out value="${event.getStartDateTime()}"></c:out></td>
                        <td><c:out value="${event.getEndDateTime()}"></c:out></td>
                        <td><c:out value="${event.getStatus()}"></c:out></td>
                        
                         <td>
                            <form action="EventStatus" method="post">
                                <input type="hidden" name="Id" value="${event.getId()}">
                                <button type="submit" class="btn btn-success">Mark Done</button>
                            </form>
                        </td>
                        <td>
                            <form action="todolisteditform.jsp" method="post">
                                <input type="hidden" name="eventId" value="${event.getId()}">
                                <button type="submit" class="btn btn-primary">Edit</button>
                            </form>
                        </td>
                        <td>
                            <form action="DeleteEvent" method="post">
                                <input type="hidden" name="eventId" value="${event.getEventId()}">
                                <button type="submit" class="btn btn-danger">Delete</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

<!--  
<button ><a href="todolistform.jsp">Create Event</a></button>
<table class="table table-hover container border border-dark">
	<tr>
		<th>Title</th>
		<th>Description</th>
		<th>Location</th>
		<th>StartDate</th>
		<th>EndDate</th>
		<th>Event ID</th>
		<th>Actions</th>
	</tr>
	
	<c:forEach  items="${eventList}" var="event">
	
		<tr>
			<td><c:out value="${event.getTitle()}"></c:out></td>
			<td><c:out value="${event.getDescription()}"></c:out></td>
			<td><c:out value="${event.getLocation()}"></c:out></td>
			<td><c:out value="${event.getEndDateTime()}"></c:out></td>
			<td><c:out value="${event.getStartDateTime()}"></c:out></td>
			<td><c:out value="${event.getEventId()}"></c:out></td>
			<td>
			
			
				<form action="DeleteEvent" method="post">
					<input type="hidden" name="eventId" value="${event.getEventId()}" style="display:none;">
					<input type="submit" value="delete"> 
			
				</form>
			</td>
		</tr>
		
	</c:forEach>
	
	
</table>

-->

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</body>
</html>