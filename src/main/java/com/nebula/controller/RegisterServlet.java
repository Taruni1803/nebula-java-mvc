package com.nebula.controller;

import com.nebula.dao.UserDAO;
import com.nebula.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {

    // 1️⃣ Show REGISTER page
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/views/register.jsp")
                .forward(request, response);
    }

    // 2️⃣ Handle REGISTER form submission
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User user = new User();
        user.setName(request.getParameter("name"));
        user.setEmail(request.getParameter("email"));
        user.setPassword(request.getParameter("password"));
        user.setRole("student");
        user.setBranch(request.getParameter("branch"));

        String yearStr = request.getParameter("year");
        user.setYear(yearStr == null || yearStr.isEmpty() ? 0 : Integer.parseInt(yearStr));

        user.setCareerGoal(request.getParameter("careerGoal"));

        UserDAO dao = new UserDAO();
        boolean success = dao.registerUser(user);

        if (success) {
            // 🔑 REDIRECT, not forward
            response.sendRedirect(request.getContextPath() + "/login");
        } else {
            request.setAttribute("error", "Registration Failed");
            request.getRequestDispatcher("/WEB-INF/views/register.jsp")
                    .forward(request, response);
        }
    }
}
