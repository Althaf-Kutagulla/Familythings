<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.pojo.*,com.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Event</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap/dist/css/bootstrap.min.css">
</head>
<body>

<%

	int id =Integer.parseInt(request.getParameter("eventId"));
	CalendarEvent event = new CalendarEvent();
	CalendarsDao calendar = new CalendarsDao();
	event = calendar.getCalendarEvent(id);
	
	
	session.setAttribute("event", event);
	


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
                        <a class="nav-link" href="Logout">Logout</a>
                    </li>
                </ul>
            </div>
            
            <span class="navbar-text">
                <c:out value="${email}"></c:out>
            </span>
        </div>
    </nav>

<div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-6">
            	<h1 class="text-center">Edit Event</h1>
                <form action="UpdateEvent" method="post">
                    <div class="mb-3">
                        <label for="title" class="form-label">Title:</label>
                        <input type="text" class="form-control" id="title" name="title" required value="${event.getTitle()}">
                    </div>
                    <div class="mb-3">
                        <label for="description" class="form-label">Description:</label>
                        <input type="text" class="form-control" id="description" name="description" required value="${event.getDescription()}">
                    </div>
                    <div class="mb-3">
                        <label for="location" class="form-label">Location:</label>
                        <input type="text" class="form-control" id="location" name="location" required value="${event.getLocation()}">
                    </div>
                    <div class="mb-3">
                        <label for="startDateTime" class="form-label">Start Date:</label>
                        <input type="datetime-local" class="form-control" id="startDateTime" name="startDateTime" pattern="\d{4}-\d{2}-\d{2}" required value="${event.getStartDateTime()}">
                    </div>
                    <div class="mb-3">
                        <label for="endDateTime" class="form-label">End Date:</label>
                        <input type="datetime-local" class="form-control" id="endDateTime" name="endDateTime" pattern="\d{4}-\d{2}-\d{2}" required value="${event.getEndDateTime()}">
                    </div>
                    <div class="mb-3">
                        <label for="endDateTimee" class="form-label">End Datee:</label>
                        <input type="text" class="form-control" id="googleEventId" name="googleEventId"  required value="${event.getEventId()}">
                    </div>
                    
          
                    <button type="submit" class="btn btn-primary">Update Event</button>
                </form>
            </div>
        </div>
    </div>
    
    
	<script src="https://cdn.jsdelivr.net/npm/bootstrap/dist/js/bootstrap.min.js"></script>
</body>
</html>