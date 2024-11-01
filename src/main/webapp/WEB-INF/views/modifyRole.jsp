<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="Crudpack.Role" %>
<html>
<head>
    <title>Modify Role</title>
    <style>
        /* Your CSS styles here */
    </style>
</head>
<body>
    <h2>Modify Role</h2>
    <form action="/modifyRole" method="POST">
        <input type="hidden" name="userId" value="<%= request.getParameter("userId") %>" />
        <input type="hidden" name="roleId" value="<%= request.getParameter("roleId") %>" />
        <select name="roleId">
            <%
                List<Role> roles = (List<Role>) request.getAttribute("roles");
                if (roles != null) {
                    for (Role role : roles) {
            %>
                        <option value="<%= role.getIdRole() %>"><%= role.getIntituleRole() %></option>
            <%
                    }
                }
            %>
        </select>
        <button type="submit" class="button">Modify Role</button>
    </form>
</body>
</html>
