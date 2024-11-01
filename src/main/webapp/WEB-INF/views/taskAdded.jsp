<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, Crudpack.Service" %>
<%@ page import="Crudpack.Task" %>
<%@ page import="Crudpack.User" %>
<html>
<head>
    <title>Task Added</title>
</head>
<body>

<h2>Task successfully added!</h2>
<p>The task has been successfully added.</p>

<!-- Display the userId, if needed -->
<p>User ID: ${userId}</p>

<a href="/addTask?userId=${userId}">Add another task for this user</a> | <a href="/">Home</a>


</body>
</html>
