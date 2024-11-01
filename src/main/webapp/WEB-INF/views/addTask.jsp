<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, Crudpack.Service" %>
<%@ page import="Crudpack.Task" %>
<html>
<head>
    <title>Add Task</title>
</head>
<body>

<h2>Add Task</h2>
<form action="/saveTask" method="POST">
    <!-- Hidden field to hold the user ID -->
    <input type="hidden" name="userId" value="${param.userId}"/>

    Task Title: <input type="text" name="intituleTask" required><br/><br/>
    Services:
    <select name="serviceIds" multiple>
        <% 
            List<Service> services = (List<Service>) request.getAttribute("services");
            if (services != null) {
                for (Service service : services) {
        %>
                    <option value="<%= service.getCodeservice() %>"><%= service.getIntituleService() %></option>
        <% 
                }
            }
        %>
    </select><br/><br/>
    <input type="submit" value="Submit">
    <a href="/"><button type="button">Cancel</button></a>
</form>

</body>
</html>
