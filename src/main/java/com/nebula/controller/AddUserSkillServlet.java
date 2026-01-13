package com.nebula.controller;

import com.nebula.dao.UserActivityDAO;
import com.nebula.dao.UserSkillDAO;
import com.nebula.model.UserSkill;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/api/user/skills")
public class AddUserSkillServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        resp.setContentType("application/json");

        // Read request body
        String body = req.getReader().lines()
                .reduce("", (acc, line) -> acc + line);

        int userId = Integer.parseInt(
                body.replaceAll(".*\"userId\"\\s*:\\s*(\\d+).*", "$1")
        );
        int skillId = Integer.parseInt(
                body.replaceAll(".*\"skillId\"\\s*:\\s*(\\d+).*", "$1")
        );

        // Create UserSkill object
        UserSkill userSkill = new UserSkill();
        userSkill.setUserId(userId);
        userSkill.setSkillId(skillId);
        userSkill.setLevel("Beginner");
        userSkill.setStatus("Not Started");
        userSkill.setTargetCompletionDate(null);

        UserSkillDAO dao = new UserSkillDAO();
        boolean added = dao.addUserSkill(userSkill);
        UserActivityDAO activityDAO = new UserActivityDAO();
        activityDAO.logActivity(userId, skillId);


        if (added) {
            resp.getWriter().write(
                    "{\"status\":\"success\",\"message\":\"Skill added to your Dashboard\"}"
            );
        } else {
            resp.getWriter().write(
                    "{\"status\":\"exists\",\"message\":\"Skill already added\"}"
            );
        }
    }
}
