<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration Page</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap/dist/css/bootstrap.min.css">
</head>
<body>

<%

%>

 <div class="container-fluid ">
        <div class="row justify-content-center align-items-center " style="height: 100vh;">
            <div class="col-md-4 border border-dark">
            	<h1 class="text-center">Registration</h1>
                <form action="Registration" method="post">
                    <div class="mb-3">
                        <label for="email" class="form-label">Email address</label>
                        <input type="email" class="form-control" id="email" placeholder="Enter email" required name="email">
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label">Password</label>
                        <input type="password" class="form-control" id="password" placeholder="Password" required name="password">
                    </div>
                    <button type="submit" class="btn btn-primary">Register</button>
                </form>
                <div class="text-center mt-3">
                    <p>Already have an account? <a href="login.jsp">Login</a></p>
                </div>
            </div>
        </div>
    </div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap/dist/js/bootstrap.min.js"></script>
</body>
</html>