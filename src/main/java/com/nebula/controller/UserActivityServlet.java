package com.nebula.controller;

import com.nebula.dao.UserActivityDAO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/api/activity")
public class UserActivityServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        resp.setContentType("application/json");

        String body = req.getReader().lines()
                .reduce("", (acc, line) -> acc + line);

        int userId = Integer.parseInt(
                body.replaceAll(".*\"userId\"\\s*:\\s*(\\d+).*", "$1")
        );
        int skillId = Integer.parseInt(
                body.replaceAll(".*\"skillId\"\\s*:\\s*(\\d+).*", "$1")
        );

        UserActivityDAO dao = new UserActivityDAO();
        dao.logActivity(userId, skillId);

        resp.getWriter().write(
                "{\"status\":\"success\",\"message\":\"Activity logged\"}"
        );
    }
}
