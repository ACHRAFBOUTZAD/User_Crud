<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, Crudpack.Service" %>
<%@ page import="Crudpack.User" %>
<html>
<head>
    <title>Update User</title>
</head>
<body>

<h2>Update Information</h2>
<form action="/saveModifyUser" method="POST">
<input type="hidden" name="userId" value="${param.userId}"/>
    <!-- User Information -->
    <fieldset>
    <legend>User Info:</legend>
    Username: <input type="text" name="username" value="${user.username}" required><br/>
    Password: <input type="password" name="password" value="${user.password}" required><br/>
    Student: <input type="checkbox" name="student" ${user.student ? 'checked' : ''}><br/>
    Country: <input type="text" name="country" value="${user.country}" required><br/>
</fieldset>
    
    <input type="submit" value="Update">
    <a href="/"><button type="button">Cancel</button></a>
</form>

</body>
</html>
