package com.bank.controllers;

import com.bank.dao.AccountDAO;
import com.bank.dao.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

/**
 * Creates a new account (admin only). GET shows form; POST processes submission.
 * GET /adminCreateAccount.do, POST /adminCreateAccount.do
 */
@WebServlet("/adminCreateAccount.do")
public class AdminCreateAccountServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        User user = (User) (session != null ? session.getAttribute("user") : null);
        if (user == null || !user.isAdmin()) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }
        req.getRequestDispatcher("/admin/createAccount.jsp").forward(req, resp);
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

        String userIdStr         = req.getParameter("userId");
        String accountNumber     = req.getParameter("accountNumber");
        String initialBalanceStr = req.getParameter("initialBalance");

        if (userIdStr == null || userIdStr.trim().isEmpty() ||
                accountNumber == null || accountNumber.trim().isEmpty()) {
            req.setAttribute("error", "User ID and account number are required.");
            req.getRequestDispatcher("/admin/createAccount.jsp").forward(req, resp);
            return;
        }

        int userId;
        try {
            userId = Integer.parseInt(userIdStr.trim());
        } catch (NumberFormatException e) {
            req.setAttribute("error", "Invalid user ID.");
            req.getRequestDispatcher("/admin/createAccount.jsp").forward(req, resp);
            return;
        }

        BigDecimal balance = BigDecimal.ZERO;
        if (initialBalanceStr != null && !initialBalanceStr.trim().isEmpty()) {
            try {
                balance = new BigDecimal(initialBalanceStr.trim());
            } catch (NumberFormatException e) {
                req.setAttribute("error", "Invalid balance.");
                req.getRequestDispatcher("/admin/createAccount.jsp").forward(req, resp);
                return;
            }
        }

        AccountDAO accountDAO = new AccountDAO();
        try {
            accountDAO.create(userId, accountNumber.trim(), balance);
        } catch (SQLException e) {
            req.setAttribute("error", "Failed to create account: " + e.getMessage());
            req.getRequestDispatcher("/admin/createAccount.jsp").forward(req, resp);
            return;
        }

        resp.sendRedirect(req.getContextPath() + "/adminAccounts.do");
    }
}
