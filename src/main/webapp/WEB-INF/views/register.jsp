<%--
  Created by IntelliJ IDEA.
  User: tarun
  Date: 23-12-2025
  Time: 12:04 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<h2>Student Registration</h2>

<form action="${pageContext.request.contextPath}/register" method="post">

    Name: <input type="text" name="name" required /><br><br>
    Email: <input type="email" name="email" required /><br><br>
    Password: <input type="password" name="password" required /><br><br>
    Branch: <input type="text" name="branch" /><br><br>
    Year: <input type="number" name="year" /><br><br>
    Career Goal: <input type="text" name="careerGoal" /><br><br>

    <button type="submit">Register</button>
</form>

</body>
</html>

