package com.bank.controllers;

import com.bank.dao.User;
import com.bank.dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Creates a new user (admin only). GET shows form; POST processes submission.
 * GET /adminCreateUser.do, POST /adminCreateUser.do
 */
@WebServlet("/adminCreateUser.do")
public class AdminCreateUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        User user = (User) (session != null ? session.getAttribute("user") : null);
        if (user == null || !user.isAdmin()) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }
        req.getRequestDispatcher("/admin/createUser.jsp").forward(req, resp);
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

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String role     = req.getParameter("role");

        if (username == null || username.trim().isEmpty() ||
                password == null || password.trim().isEmpty()) {
            req.setAttribute("error", "Username and password are required.");
            req.getRequestDispatcher("/admin/createUser.jsp").forward(req, resp);
            return;
        }

        // Constrain role to known values
        if (!"ADMIN".equals(role)) {
            role = "USER";
        }

        UserDAO userDAO = new UserDAO();
        try {
            userDAO.create(username.trim(), password, role);
        } catch (SQLException e) {
            req.setAttribute("error", "Failed to create user: " + e.getMessage());
            req.getRequestDispatcher("/admin/createUser.jsp").forward(req, resp);
            return;
        }

        resp.sendRedirect(req.getContextPath() + "/adminUsers.do");
    }
}
