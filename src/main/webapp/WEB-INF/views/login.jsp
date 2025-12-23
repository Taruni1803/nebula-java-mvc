<%--
  Created by IntelliJ IDEA.
  User: tarun
  Date: 23-12-2025
  Time: 12:43 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Login</title></head>
<body>

<h2>Login</h2>

<form action="${pageContext.request.contextPath}/login" method="post">

    Email: <input type="email" name="email" required /><br><br>
    Password: <input type="password" name="password" required /><br><br>

    <button type="submit">Login</button>
</form>
<p>
    New user?
    <a href="${pageContext.request.contextPath}/register">
        Register here
    </a>
</p>


</body>
</html>

