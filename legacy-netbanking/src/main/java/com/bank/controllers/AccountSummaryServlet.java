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
 * Displays account summary for the logged-in user (GET /accountSummary.do).
 */
@WebServlet("/accountSummary.do")
public class AccountSummaryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        User user = (User) (session != null ? session.getAttribute("user") : null);
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        AccountDAO accountDAO = new AccountDAO();
        List<Account> accounts = null;
        try {
            accounts = accountDAO.findByUserId(user.getId());
        } catch (SQLException e) {
            req.setAttribute("error", "Database error.");
        }

        req.setAttribute("accounts", accounts);
        req.setAttribute("user", user);
        req.getRequestDispatcher("/accountSummary.jsp").forward(req, resp);
    }
}
