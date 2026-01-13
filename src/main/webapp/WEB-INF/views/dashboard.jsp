<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.nebula.model.User" %>
<%@ page import="com.nebula.model.UserSkill" %>
<%@ page import="java.util.List" %>

<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect(request.getContextPath() + "/login");
        return;
    }

    List<UserSkill> mySkills =
            (List<UserSkill>) request.getAttribute("mySkills");

    int total = mySkills != null ? mySkills.size() : 0;
    int inProgress = 0;
    int completed = 0;

    if (mySkills != null) {
        for (UserSkill us : mySkills) {
            if ("In Progress".equals(us.getStatus())) inProgress++;
            if ("Completed".equals(us.getStatus())) completed++;
        }
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Nebula | Dashboard</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/css/style.css">
</head>

<body class="app">

<div class="container">

    <!-- ✅ SIDEBAR -->
    <nav class="sidebar">
        <div class="logo">
            <img src="${pageContext.request.contextPath}/assets/images/logo1.png" alt="Nebula Logo">

        </div>

        <ul>
            <li class="active">
                <a href="${pageContext.request.contextPath}/home">Dashboard</a>
            </li>

            <li>
                <a href="${pageContext.request.contextPath}/recommend">Recommendations</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/profile">Profile</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/logout">Logout</a>
            </li>
        </ul>
    </nav>

    <!-- ✅ MAIN CONTENT -->
    <main class="main-content">

        <header>
            <h1>Dashboard</h1>
            <p>You are logged in as <%= user.getRole() %></p>
        </header>
<%
    String flash = (String) session.getAttribute("flashMessage");
    if (flash != null) {
%>
    <div style="
        background:#fff3cd;
        color:#856404;
        padding:12px;
        margin:15px 0;
        border:1px solid #ffeeba;
        border-radius:6px;
        font-weight:500;">
        <%= flash %>
    </div>
<%
        session.removeAttribute("flashMessage"); // 🔥 important
    }
%>

        <!-- ✅ STATS CARDS -->
        <div class="stats-container">
            <div class="card">
                <h3>Total Skills</h3>
                <div class="number"><%= total %></div>
            </div>

            <div class="card">
                <h3>In Progress</h3>
                <div class="number"><%= inProgress %></div>
            </div>

            <div class="card">
                <h3>Completed</h3>
                <div class="number"><%= completed %></div>
            </div>
            <!-- ✅ SKILL GAP CARD -->
                   <div class="card" style="none;">
                       <h3>Skill Gap</h3>
                       <div id="skill-gap-content" style="color:black;font-size:16px;">
                           Loading...
                       </div>
                   </div>
             <div class="card">
                 <h3>🔥Learning Streak</h3>
                 <div id="streak-count" class="number">0</div>
             </div>


        </div>



        <!-- ✅ SKILLS TABLE -->
        <section class="skills-section">
            <h2>My Skills</h2>

            <div class="table-container">
                <table>
                    <thead>
                    <tr>
                        <th>Skill</th>
                        <th>Category</th>
                        <th>Level</th>
                        <th>Status</th>
                        <th>Actions</th>
                    </tr>
                    </thead>

                    <tbody>
                    <%
                        if (mySkills != null && !mySkills.isEmpty()) {
                            for (UserSkill us : mySkills) {
                    %>
                    <tr>
                        <td><%= us.getSkillName() %></td>
                        <td><%= us.getCategory() %></td>
                        <td><%= us.getLevel() %></td>
                        <td>
                            <span class="status-badge
                                <%= "In Progress".equals(us.getStatus()) ? "progress" : "not-started" %>">
                                <%= us.getStatus() %>
                            </span>
                        </td>
                        <td>
                            <form method="post"
                                  action="${pageContext.request.contextPath}/update-skill-status"
                                  style="display:inline">
                                <input type="hidden" name="userSkillId" value="<%= us.getUserSkillId() %>">
                                <input type="hidden" name="status" value="In Progress">
                                <button class="btn start" type="submit">Start</button>
                            </form>

                            <form method="post"
                                  action="${pageContext.request.contextPath}/update-skill-status"
                                  style="display:inline">
                                <input type="hidden" name="userSkillId" value="<%= us.getUserSkillId() %>">
                                <input type="hidden" name="status" value="Completed">
                                <button class="btn complete" type="submit">Complete</button>
                            </form>

                            <form method="post"
                                  action="${pageContext.request.contextPath}/remove-skill"
                                  style="display:inline">
                                <input type="hidden" name="userSkillId" value="<%= us.getUserSkillId() %>">
                                <button class="btn remove" type="submit"
                                        onclick="return confirm('Remove this skill?');">
                                    Remove
                                </button>
                            </form>
                        </td>
                    </tr>
                    <%
                        }
                    } else {
                    %>
                    <tr>
                        <td colspan="5">No skills selected yet</td>
                    </tr>
                    <%
                        }
                    %>
                    </tbody>
                </table>


            </div>
              <br>
                                <a href="${pageContext.request.contextPath}/select-skills">Go to Skill Selection</a>
        </section>

    </main>

</div>
<script>
    const userId = <%= user.getUserId() %>;

  function loadSkillGap() {
      console.log("Calling skill gap API...");

      fetch('<%= request.getContextPath() %>/api/skill-gap?userId=' + userId)
          .then(res => {
              console.log("Response status:", res.status);
              return res.json();
          })
          .then(data => {
              console.log("Skill gap data:", data);

              const box = document.getElementById("skill-gap-content");
              box.innerHTML =
                  '<div class="skill-gap-row">' +
                      '<span>Required Skills:</span>' +
                      '<strong>' + data.totalRequired + '</strong>' +
                  '</div>' +
                  '<div class="skill-gap-row">' +
                      '<span>Added Skills:</span>' +
                      '<strong>' + data.skillsAdded + '</strong>' +
                  '</div>' +
                  '<div class="skill-gap-row">' +
                      '<span>Missing Skills:</span>' +
                      '<strong>' + data.skillsMissing + '</strong>' +
                  '</div>';

          })
          .catch(err => {
              console.error("Skill gap fetch failed:", err);
              document.getElementById("skill-gap-content").innerText =
                  "Error loading skill gap";
          });
  }


    loadSkillGap();

</script>
<script>
function loadStreak() {
    fetch('<%= request.getContextPath() %>/api/streak?userId=' + userId)
        .then(res => res.json())
        .then(data => {
            document.getElementById("streak-count").innerText =
                data.streak + " days";
        })
        .catch(err => {
            console.error("Streak load failed", err);
            document.getElementById("streak-count").innerText = "0 days";
        });
}

loadStreak();
</script>

</body>
</html>
