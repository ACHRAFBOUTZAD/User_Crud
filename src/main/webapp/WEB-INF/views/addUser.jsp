<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, Crudpack.Service" %>
<%@ page import="Crudpack.Task" %>
<html>
<head>
    <title>Add User</title>
</head>
<body>

<h2>User Registration</h2>
<form action="/saveUser" method="POST">
    Username: <input type="text" name="username" required><br/><br/>
    Password: <input type="password" name="password" required><br/><br/>
    Student: <input type="checkbox" name="student"><br/><br/>
    Country: <input type="text" name="country" required><br/><br/>
    <input type="submit" value="Submit">
    <a href="/"><button type="button">Cancel</button></a>
</form>

</body>
</html>
