package com.nebula.controller;

import com.nebula.dao.SkillDAO;
import com.nebula.model.Skill;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;


public class RecommendationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        resp.setContentType("application/json");

        String userIdParam = req.getParameter("userId");

        // 🔐 Defensive check
        if (userIdParam == null || userIdParam.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write(
                    "{\"error\":\"userId parameter is required\"}"
            );
            return;
        }

        int userId = Integer.parseInt(userIdParam);

        SkillDAO dao = new SkillDAO();
        List<Skill> skills = dao.getRecommendations(userId);

        StringBuilder json = new StringBuilder();
        json.append("{\"recommendedSkills\":[");

        for (int i = 0; i < skills.size(); i++) {
            Skill s = skills.get(i);
            json.append("{")
                    .append("\"skillId\":").append(s.getSkillId()).append(",")
                    .append("\"skillName\":\"").append(s.getSkillName()).append("\",")
                    .append("\"category\":\"").append(s.getCategory()).append("\",")
                    .append("\"priority\":").append(s.getPriority())
                    .append("}");
            if (i < skills.size() - 1) json.append(",");
        }

        json.append("]}");

        resp.getWriter().write(json.toString());
    }

}
