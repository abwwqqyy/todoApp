<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2023/6/25 0025
  Time: 22:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
  <header>
    <nav class="navbar navbar-expand-md navbar-dark" style="background-color: lightblue">
      <div>
        <a href="/hello-servlet" class="navbar-brand">Todo App</a>
      </div>
      <ul class="navbar-nav navbar-collapse justify-content-end">
        <li><a href="<%= request.getContextPath() %>/login" class="nav-link">Login</a></li>
        <li><a href="<%= request.getContextPath() %>/register" class="nav-link">Signup</a></li>
      </ul>
    </nav>
  </header>
</body>
</html>
