<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2023/6/26 0026
  Time: 18:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Todo App - Login</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

</head>
<body>
  <jsp:include page="../common/header.jsp"></jsp:include>
  <div class="container col-md-8 col-md-offset-3" style="overflow: auto">
    <h1>Login Form</h1>
    <form action="<%=request.getContextPath()%>/login" method="post">

      <div class="form-group">
        <label for="username">Username:</label>
        <input type="text" class="form-control" id="username" placeholder="Username" name="username" required>
      </div>

      <div class="form-group">
        <label for="password">Password:</label>
        <input type="password" class="form-control" id="password" placeholder="Password" name="password" required>
      </div>


      <button type="submit" class="btn btn-primary">Submit</button>
    </form>
  </div>
  <jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>
