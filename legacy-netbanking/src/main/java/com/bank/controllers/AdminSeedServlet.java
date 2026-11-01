package com.bank.controllers;

import com.bank.dao.User;
import com.bank.util.DatabaseInitializer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Re-runs database seed (admin only). GET shows current status; POST triggers seed.
 * GET /adminSeed.do, POST /adminSeed.do
 */
@WebServlet("/adminSeed.do")
public class AdminSeedServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        User user = (User) (session != null ? session.getAttribute("user") : null);
        if (user == null || !user.isAdmin()) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }
        req.setAttribute("user", user);
        req.getRequestDispatcher("/admin/seed.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        User user = (User) (session != null ? session.getAttribute("user") : null);
        if (user == null || !user.isAdmin()) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        String message = "Seed data already exists. Re-run init to reset.";
        try {
            DatabaseInitializer.initialize();
            message = "Database schema and seed data initialized successfully.";
        } catch (Exception e) {
            message = "Error: " + e.getMessage();
        }
        req.setAttribute("seedMessage", message);
        req.setAttribute("user", user);
        req.getRequestDispatcher("/admin/seed.jsp").forward(req, resp);
    }
}
