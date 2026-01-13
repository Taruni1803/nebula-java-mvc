package com.nebula.controller;

import com.nebula.dao.UserActivityDAO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;


public class StreakServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        resp.setContentType("application/json");

        int userId = Integer.parseInt(req.getParameter("userId"));

        UserActivityDAO dao = new UserActivityDAO();
        int streak = dao.getCurrentStreak(userId);

        String json = "{ \"streak\": " + streak + " }";
        resp.getWriter().write(json);
    }
}
