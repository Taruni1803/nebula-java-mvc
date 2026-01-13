<%--
  Created by IntelliJ IDEA.
  User: tarun
  Date: 05-01-2026
  Time: 04:02 pm
  To change this template use File | Settings | File Templates.
--%>




<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.nebula.model.Skill" %>

<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/css/style1.css">
</head>
<body class="public">
<%@ include file="/WEB-INF/views/components/header.jsp" %>
<h2 style="text-align:center; color:white;">Select Skills to Learn</h2>

<div class="container">
    <div class="card skill-card">

        <form method="post" action="${pageContext.request.contextPath}/add-skill">

            <table class="skill-table">
                <thead>
                <tr>
                    <th>Select</th>
                    <th>Skill</th>
                    <th>Category</th>
                    <th>Description</th>
                </tr>
                </thead>

                <tbody>
                <%
                    List<Skill> skills = (List<Skill>) request.getAttribute("skills");
                    if (skills != null) {
                        for (Skill skill : skills) {
                %>
                <tr>
                    <td>
                        <input type="checkbox" name="skillId" value="<%= skill.getSkillId() %>">
                    </td>
                    <td><%= skill.getSkillName() %></td>
                    <td><%= skill.getCategory() %></td>
                    <td><%= skill.getDescription() %></td>
                </tr>
                <%
                        }
                    }
                %>
                </tbody>
            </table>

            <div class="action-bar">
                <button type="submit" class="primary-btn">
                    ➕ Add Selected Skills
                </button>
            </div>

        </form>

    </div>
</div>


</body>
</html>
