<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="Crudpack.Task, Crudpack.User, Crudpack.Service, Crudpack.UserRole" %>
<html>
<head>
    <title>Index Page</title>
    <style>
        .button-container {
            display: flex;
            flex-direction: row;
        }

        .button {
            display: inline-block;
            padding: 10px 20px;
            margin-right: 10px;
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

<!-- Button to Add User -->
<a href="/addUser" class="button">Add User</a>
<!-- Button to List Roles -->
<form action="/Listroles" method="GET">
       <button type="submit" name="ListRoles" class="button">List Roles</button>
</form>

<h2>Users | Roles |Tasks | Service</h2>
<%
    List<User> users = (List<User>) request.getAttribute("users");
    if (users != null) {
        for (User user : users) {
%>
            <p>User: <%= user.getUsername() %></p>
            <ul>
                <%
                    List<UserRole> roles = user.getRoles();
                    if (roles != null) {
                        for (UserRole userRole : roles) {
                %>
                            <li>Role : <%= userRole.getRole().getIntituleRole() %></li>
                            <form action="/modifyRole" method="GET">
							    <input type="hidden" name="roleId" value="<%= userRole.getRole().getIdRole() %>" />
							    <input type="hidden" name="userId" value="<%= userRole.getUser().getId() %>" />
							    
							    <button type="submit" class="button">Modify Role</button>
							</form>
                            
                            <!-- Delete Role Button -->
                                <form action="/deleteUserRole" method="POST">
                                    <input type="hidden" name="userId" value="<%= user.getId() %>" />
                                    <input type="hidden" name="roleId" value="<%= userRole.getRole().getIdRole() %>" />
                                    <button type="submit" class="button">Delete Role</button>
                                </form>
                <%
                        }
                    }
                %>
            </ul>
            <!-- Add Role Button -->
            <div class="button-container">
                <form action="/addUserRole" method="GET">
                    <button type="submit" name="userId" value="<%= user.getId() %>" class="button">Add Role</button>
                </form> 
                <!-- Modify User Button -->
                <form action="/modifyUser" method="GET">
                    <button type="submit" name="userId" value="<%= user.getId() %>" class="button">Modify User</button>
                </form> 
                <!-- Delete User Button -->
                <form action="/deleteUser" method="POST">
                    <input type="hidden" name="userId" value="<%= user.getId() %>" />
                    <button type="submit" class="button">Delete User</button>
                </form>
            </div>
            <ul>
<%
                List<Task> tasks = user.getTasks();
                if (tasks != null) {
                    for (Task task : tasks) {
%>
                        <li>
                            <%= task.getIntituleTask() %>
                            <ul>
<%
                                List<Service> services = task.getServices();
                                if (services != null) {
                                    for (Service service : services) {
%>
                                        <li><%= service.getIntituleService() %></li>
<%
                                    }
                                }
%>
                            </ul>
                            <!-- Add Modify Task Button -->
                            <form action="/modifyTaskService" method="GET">
                                <button type="submit" name="taskId" value="<%= task.getCodetask() %>" class="button">Modify Task</button>
                            </form>
                            <!-- Delete Task Button -->
                            <form action="/deleteTask" method="POST">
                                <input type="hidden" name="taskId" value="<%= task.getCodetask() %>" />
                                <button type="submit" class="button">Delete Task</button>
                            </form>
                        </li>
<%
                    }
                }
%>
            </ul>
            <form action="/addTask" method="GET">
                <button type="submit" name="userId" value="<%= user.getId() %>" class="button">Add Task</button>
            </form>
            
                      
            <hr/>
<%
        }
    }
%>

</body>
</html>
