package com.nebula.controller;

import com.nebula.dao.UserActivityDAO;
import com.nebula.dao.UserSkillDAO;
import com.nebula.model.User;
import com.nebula.model.UserSkill;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class AddSkillServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1️⃣ Session check
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // 2️⃣ Logged-in user
        User user = (User) session.getAttribute("user");
        int userId = user.getUserId();

        // 3️⃣ Selected skills (multiple)
        String[] skillIds = request.getParameterValues("skillId");

        if (skillIds != null) {

            UserSkillDAO userSkillDAO = new UserSkillDAO();

            int addedCount = 0;
            int duplicateCount = 0;

            for (String skillIdStr : skillIds) {
                int skillId = Integer.parseInt(skillIdStr);

                // 🔐 DUPLICATE CHECK (UX IMPROVEMENT)
                if (userSkillDAO.skillAlreadyAdded(userId, skillId)) {
                    duplicateCount++;
                    continue; // skip this skill
                }

                UserSkill userSkill = new UserSkill();
                userSkill.setUserId(userId);
                userSkill.setSkillId(skillId);
                userSkill.setLevel("Beginner");
                userSkill.setStatus("Not Started");

                userSkillDAO.addUserSkill(userSkill);
                UserActivityDAO activityDAO = new UserActivityDAO();
                activityDAO.logActivity(userId, skillId);

                addedCount++;
            }

            // 4️⃣ FLASH MESSAGE (session-based UX)
            if (addedCount > 0 && duplicateCount > 0) {
                session.setAttribute("flashMessage",
                        "⚠️ Some skills were already added. New skills have been added successfully.");
            } else if (addedCount > 0) {
                session.setAttribute("flashMessage",
                        "✅ Skills added successfully.");
            } else {
                session.setAttribute("flashMessage",
                        "ℹ️ Selected skills already exist in your profile.");
            }
        }

        // 5️⃣ Redirect (Post-Redirect-Get pattern)
        response.sendRedirect(request.getContextPath() + "/home");
    }
}
