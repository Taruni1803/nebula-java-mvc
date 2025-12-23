<%--
  Created by IntelliJ IDEA.
  User: tarun
  Date: 23-12-2025
  Time: 12:44 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.nebula.model.User" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<html>
<body>
<h2>Welcome, <%= user.getName() %></h2>
<p>You are logged in as <%= user.getRole() %></p>
<a href="${pageContext.request.contextPath}/logout">Logout</a>


</body>
</html>

