package com.nebula.controller;

import com.nebula.dao.UserDAO;
import com.nebula.dao.UserSkillDAO;
import com.nebula.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Map;

public class ProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        User user = (User) session.getAttribute("user");

        // 🔹 fetch skill stats
        UserSkillDAO skillDao = new UserSkillDAO();
        Map<String, Integer> stats =
                skillDao.getSkillStats(user.getUserId());

        request.setAttribute("user", user);
        request.setAttribute("stats", stats);

        request.getRequestDispatcher("/WEB-INF/views/profile.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        User user = (User) session.getAttribute("user");

        // basic validation
        if (request.getParameter("branch") == null ||
                request.getParameter("year") == null ||
                request.getParameter("careerGoal") == null) {

            session.setAttribute("error", "Invalid data");
            response.sendRedirect(request.getContextPath() + "/profile");
            return;
        }

        // update user
        user.setBranch(request.getParameter("branch"));
        user.setYear(Integer.parseInt(request.getParameter("year")));
        user.setCareerGoal(request.getParameter("careerGoal"));

        // update DB
        UserDAO dao = new UserDAO();
        dao.updateUserProfile(user);

        // refresh session
        session.setAttribute("user", user);

        // success message
        session.setAttribute("success", "Profile successfully updated");

        // redirect back to profile
        response.sendRedirect(request.getContextPath() + "/profile");
    }
}



