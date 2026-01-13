package com.nebula.controller;

import com.nebula.dao.UserDAO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

    @WebServlet("/api/user/goal")
    public class SetUserGoalServlet extends HttpServlet {

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp)
                throws IOException {

            resp.setContentType("application/json");

            // Read JSON body
            String body = req.getReader().lines()
                    .reduce("", (acc, line) -> acc + line);

            // VERY SIMPLE parsing (for now)
            int userId = Integer.parseInt(
                    body.replaceAll(".*\"userId\"\\s*:\\s*(\\d+).*", "$1")
            );
            int roleId = Integer.parseInt(
                    body.replaceAll(".*\"roleId\"\\s*:\\s*(\\d+).*", "$1")
            );

            UserDAO dao = new UserDAO();
            dao.updateUserRole(userId, roleId);

            resp.getWriter().write(
                    "{\"status\":\"success\",\"message\":\"Career goal set successfully\"}"
            );
        }
    }


