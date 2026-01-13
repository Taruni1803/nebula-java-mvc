package com.nebula.controller;

import com.nebula.dao.SkillDAO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Map;


public class SkillGapServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {


        resp.setContentType("application/json");

        int userId = Integer.parseInt(req.getParameter("userId"));

        SkillDAO dao = new SkillDAO();
        Map<String, Object> gap = dao.getSkillGap(userId);

        String json =
                "{"
                        + "\"totalRequired\":" + gap.get("totalRequired") + ","
                        + "\"skillsAdded\":" + gap.get("skillsAdded") + ","
                        + "\"skillsMissing\":" + gap.get("skillsMissing")
                        + "}";

        resp.getWriter().write(json);
    }
}
