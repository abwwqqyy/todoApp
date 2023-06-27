<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2023/6/27 0027
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Todo App - Todo List</title>
    <link rel="stylesheet"
        href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
        crossorigin="anonymous">
</head>
<body>
    <jsp:include page="../common/headerLogin.jsp"></jsp:include>
    <div class="row">
        <div class="container">
            <h3 class="text-center">List of Todos</h3>
            <hr>
            <div class="container text-left">
                <a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add Todo</a>
            </div>
            <br>
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>Title</th>
                    <th>Target Date</th>
                    <th>Todo Status</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                    <c:forEach var="todo" items="${listTodo}">
                        <tr>
                            <td><c:out value="${todo.title}" /></td>
                            <td><c:out value="${todo.targetDate}" /></td>
                            <td><c:out value="${todo.status}" /></td>

                            <td>
                                <a href="edit?id=<c:out value='${todo.id}' />">Edit</a>
                                &nbsp;&nbsp;&nbsp;&nbsp;
                                <a href="delete?id=<c:out value='${todo.id}' />">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <jsp:include page="../common/footer.jsp"></jsp:include>


</body>
</html>
