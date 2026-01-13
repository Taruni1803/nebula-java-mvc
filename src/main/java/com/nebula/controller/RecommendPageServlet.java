package com.nebula.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;


public class RecommendPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        try {
            req.getRequestDispatcher("/WEB-INF/views/recommend.jsp")
                    .forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
