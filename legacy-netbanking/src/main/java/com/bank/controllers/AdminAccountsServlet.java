package com.bank.controllers;

import com.bank.dao.Account;
import com.bank.dao.AccountDAO;
import com.bank.dao.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Lists all accounts for admin (GET /adminAccounts.do).
 */
@WebServlet("/adminAccounts.do")
public class AdminAccountsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        User user = (User) (session != null ? session.getAttribute("user") : null);
        if (user == null || !user.isAdmin()) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        AccountDAO accountDAO = new AccountDAO();
        List<Account> accounts = null;
        try {
            accounts = accountDAO.findAll();
        } catch (SQLException e) {
            req.setAttribute("error", "Database error.");
        }

        req.setAttribute("accounts", accounts);
        req.setAttribute("user", user);
        req.getRequestDispatcher("/admin/accounts.jsp").forward(req, resp);
    }
}
