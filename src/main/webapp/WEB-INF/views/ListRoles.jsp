<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="Crudpack.Role" %>
<html>
<head>
    <title>Roles Page</title>
    <style>
        .button {
            display: inline-block;
            padding: 10px 20px;
            font-size: 14px;
            cursor: pointer;
            text-align: center;
            text-decoration: none;
            outline: none;
            color: #fff;
            background-color: #4CAF50;
            border: none;
            border-radius: 15px;
            box-shadow: 0 9px #999;
        }

        .button:hover { background-color: #3e8e41 }

        .button:active {
            background-color: #3e8e41;
            box-shadow: 0 5px #666;
            transform: translateY(4px);
        }
    </style>
</head>
<body>

<h2>List of Roles</h2>
<ul>
    <%
        List<Role> roles = (List<Role>) request.getAttribute("roles");
        if (roles != null) {
            for (Role role : roles) {
    %>
                <li><%= role.getIntituleRole() %></li>
    <%
            }
        }
    %>
</ul>

<!-- Form to add a new role -->
<form action="/addNewRole" method="POST">
    <label for="roleName">Role Name:</label>
    <input type="text" id="roleName" name="roleName" required>
    <button type="submit" class="button">Add New Role</button>
    <a href="/"><button type="button">Cancel</button></a>
</form>

</body>
</html>
