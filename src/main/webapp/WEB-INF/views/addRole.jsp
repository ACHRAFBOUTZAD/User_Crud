<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="Crudpack.Role" %>
<html>
<head>
    <title>Add Roles</title>
</head>
<body>

<h2>Add Roles for User</h2>

<form action="/addUserRoles" method="POST">
    <input type="hidden" name="userId" value="<%= request.getParameter("userId") %>" />
    <label for="roleIds">Select Roles:</label>
    <select name="roleIds" id="roleIds" multiple>
        <% List<Role> roles = (List<Role>) request.getAttribute("roles");
        if (roles != null) {
            for (Role role : roles) {
        %>
                <option value="<%= role.getIdRole() %>"><%= role.getIntituleRole() %></option>
        <%     }
            }
        %>
    </select>
    <button type="submit">Add Roles</button>
    <a href="/"><button type="button">Cancel</button></a>
</form>


</body>
</html>
