package com.bank.controllers;

import com.bank.dao.Account;
import com.bank.dao.AccountDAO;
import com.bank.dao.Transaction;
import com.bank.dao.TransactionDAO;
import com.bank.dao.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Displays transaction history for the logged-in user (GET /transactionHistory.do).
 */
@WebServlet("/transactionHistory.do")
public class TransactionHistoryServlet extends HttpServlet {

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
        TransactionDAO transactionDAO = new TransactionDAO();
        List<Account> accounts = null;
        List<Transaction> transactions = new ArrayList<>();

        try {
            accounts = accountDAO.findByUserId(user.getId());
            String accountIdStr = req.getParameter("accountId");
            if (accountIdStr != null && !accountIdStr.isEmpty()) {
                int accountId = Integer.parseInt(accountIdStr);
                transactions = transactionDAO.findByAccountId(accountId);
                req.setAttribute("selectedAccountId", accountId);
            }
        } catch (NumberFormatException e) {
            req.setAttribute("error", "Invalid account ID.");
        } catch (SQLException e) {
            req.setAttribute("error", "Database error.");
        }

        req.setAttribute("accounts", accounts);
        req.setAttribute("transactions", transactions);
        req.setAttribute("user", user);
        req.getRequestDispatcher("/transactionHistory.jsp").forward(req, resp);
    }
}
