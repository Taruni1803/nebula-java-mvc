<%--
  Created by IntelliJ IDEA.
  User: tarun
  Date: 06-01-2026
  Time: 02:31 pm
  To change this template use File | Settings | File Templates.
--%>


<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.nebula.model.User" %>


<%

    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect(request.getContextPath() + "/login");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Recommended Skills</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/css/style2.css">
</head>

<body class="app">

<%@ include file="/WEB-INF/views/components/app-header.jsp" %>

<div class="container page-wrapper">
    <div class="card skill-card">

        <h2>Recommended Skills for You</h2>

        <!-- 🔹 API will render content here -->
        <div id="recommendations">
            Loading recommendations...
        </div>

        <div class="back-link">
            <a href="${pageContext.request.contextPath}/home">← Back to Dashboard</a>
        </div>

    </div>
</div>

<script>
    // ✅ userId from session (NO hardcoding)
    const userId = <%= user.getUserId() %>;

    function loadRecommendations() {
      fetch('<%= request.getContextPath() %>/api/recommendations?userId=' + userId)

            .then(response => response.json())
            .then(data => {
            console.log(data.recommendedSkills);

                const container = document.getElementById('recommendations');
                container.innerHTML = '';

                if (!data.recommendedSkills || data.recommendedSkills.length === 0) {
                    container.innerHTML =
                        '<p class="empty">No recommendations available 🎉</p>';
                    return;
                }

                data.recommendedSkills.forEach(skill => {


                    const row = document.createElement("div");
                    row.className = "skill-row";
                    if (skill.priority === 1) {
                                     row.style.border = "2px solid #2563eb";   // essential
                                       } else {
                                    row.style.border = "1px dashed #94a3b8";  // optional
                                                               }
                    row.style.background = "#ffffff";
                    row.style.color = "#000000";
                    row.style.padding = "12px";
                    row.style.marginBottom = "10px";
                    row.style.borderRadius = "8px";
                    row.style.display = "flex";
                    row.style.justifyContent = "space-between";

                    const textDiv = document.createElement("div");
                    textDiv.textContent = skill.skillName + " (" + skill.category + ")";

                    const btn = document.createElement("button");
                    btn.textContent = "Add";
                    btn.className = "btn-primary";
                    btn.onclick = function () {
                        addSkill(skill.skillId);

                    };

                    row.appendChild(textDiv);
                    row.appendChild(btn);
                    container.appendChild(row);
                });

            })
            .catch(err => {
                document.getElementById('recommendations')
                    .innerHTML = 'Error loading recommendations';
                console.error(err);
            });
    }

    function addSkill(skillId) {
       fetch('<%= request.getContextPath() %>/api/user/skills', {

            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({
                userId: userId,
                skillId: skillId
            })
        })
        .then(response => response.json())
        .then(result => {
            alert(result.message);
            loadRecommendations(); // refresh list
        })
        .catch(err => {
            alert('Error adding skill');
            console.error(err);
        });
    }

    // Load recommendations on page load
    loadRecommendations();
</script>

</body>
</html>
