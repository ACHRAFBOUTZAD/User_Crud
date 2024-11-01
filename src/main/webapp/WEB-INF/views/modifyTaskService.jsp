<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, Crudpack.Service" %>
<%@ page import="Crudpack.User" %>
<%@ page import="Crudpack.Task" %>
<!DOCTYPE html>
<html>
<head>
<title>Update Task and Service</title>
</head>
<body>
<form action="/updateTaskAndServices" method="POST">
<input type="hidden" name="taskId" value="${param.taskId}"/>
    <h2>Update Information</h2>
    Task Title: <input type="text" name="taskTitle" value="${task.intituleTask}" required><br/>
    <%
        List<Service> services = (List<Service>) pageContext.findAttribute("services");
        if (services != null) {
            for (int i = 0; i < services.size(); i++) {
                Service service = services.get(i);
    %>
                <input type="hidden" name="serviceIds" value="<%= service.getCodeservice() %>" />
                Service Title:
                <input type="text" name="serviceTitles" value="<%= service.getIntituleService() %>" required><br/>
    <%
            }
        }
    %>
    <input type="submit" value="Update">
    <a href="/"><button type="button">Cancel</button></a>
</form>

</body>
</html>