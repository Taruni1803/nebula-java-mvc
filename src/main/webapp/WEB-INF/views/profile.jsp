<%--
  Created by IntelliJ IDEA.
  User: tarun
  Date: 06-01-2026
  Time: 12:22 pm
  To change this template use File | Settings | File Templates.
--%>


<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.nebula.model.User" %>


<%
    User user = (User) request.getAttribute("user");
%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.Map" %>
<%
    Map<String, Integer> stats = (Map<String, Integer>) request.getAttribute("stats");
%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet"
            href="${pageContext.request.contextPath}/assets/css/style2.css">
            <link rel="stylesheet"
                  href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
</head>
<body class="app">

<%@ include file="/WEB-INF/views/components/app-header.jsp" %>

<div class="page-wrapper">
<%
                String success = (String) session.getAttribute("success");
                if (success != null) {
            %>
            <script>
                window.onload = function () {
                    showPopup();
                }
            </script>
            <%
                    session.removeAttribute("success");
                }
            %>

    <div class="profile-card">

        <!-- Banner -->
        <div class="profile-banner"></div>

        <!-- Profile header -->
        <div class="profile-header">
            <div class="profile-avatar-icon">
                <i class="fa-solid fa-user"></i>
            </div>


            <h2><%= user.getName() %></h2>
            <p class="profile-meta"><%= user.getEmail() %></p>
        </div>

        <!-- Stats -->
        <div class="profile-stats">
            <div class="stat-box">
                <h3><%= stats.get("total") %></h3>
                <span>Skills</span>
            </div>
            <div class="stat-box">
                <h3><%= stats.get("completed") %></h3>
                <span>Completed</span>
            </div>
            <div class="stat-box">
                <h3><%= stats.get("progress") %></h3>
                <span>In Progress</span>
            </div>
        </div>


        <!-- Profile form -->
        <form class="profile-form"
              method="post"
              action="${pageContext.request.contextPath}/profile">

            <label>Branch</label>
            <input type="text" name="branch"
                   value="<%= user.getBranch() %>" required>

            <label>Year</label>
            <input type="number" name="year"
                   value="<%= user.getYear() %>" required>

            <label>Career Goal</label>
            <input type="text" name="careerGoal"
                   value="<%= user.getCareerGoal() %>" required>

            <button type="submit" class="btn-primary" >
                Update Profile
            </button>


        </form>

    </div>

</div>
<!-- Popup -->
<div id="popup" class="popup-overlay">
    <div class="popup-box">
        <h3>Success</h3>
        <p>Profile successfully updated</p>
        <button onclick="closePopup()">OK</button>
    </div>
</div>

<script>
    function showPopup() {
        document.getElementById("popup").style.display = "flex";
    }

    function closePopup() {
        document.getElementById("popup").style.display = "none";
    }
</script>

</body>


</body>
</html>
