<%--
  Created by IntelliJ IDEA.
  User: tarun
  Date: 23-12-2025
  Time: 12:43 pm
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head><title>Login</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/css/style1.css"></head>
<body class="public">
<%@ include file="/WEB-INF/views/components/public-header.jsp" %>

<div class="container login-wrapper">

    <div class="login-card">
        <h2>Login</h2>

        <form action="${pageContext.request.contextPath}/login" method="post">

            <label>Email</label>
            <input type="email" name="email" required>

            <label>Password</label>
            <input type="password" name="password" required>

            <button type="submit">Login</button>
        </form>

        <p class="register-link">
            New user?
            <a href="${pageContext.request.contextPath}/register">Register here</a>
        </p>
    </div>

</div>
</body>
</html>

