<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2023/6/25 0025
  Time: 22:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
  <style>
    .footer {
      position: fixed;
      bottom: 0;
      width:100%;
      height: 40px;
      background-color: lightblue;
    }

  </style>
</head>
<body>
  <footer class="footer font-small black">
    <div class="footer-copyright text-center py-3" style="color: white"> 2023 Copyright:
      <a href="<%= request.getContextPath() %>/hello-servlet"><strong> QW </strong></a>
    </div>
  </footer>
</body>
</html>
