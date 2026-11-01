package com.bank.controllers;

import com.bank.dao.Account;
import com.bank.dao.AccountDAO;
import com.bank.dao.TransactionDAO;
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
import java.util.List;

/**
 * Processes fund transfer submissions (POST /transfer.do).
 * GET /transfer.do delegates to TransferFormServlet behaviour.
 */
@WebServlet("/transfer.do")
public class TransferServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() + "/transferForm.do");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        User user = (User) (session != null ? session.getAttribute("user") : null);
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        String fromAccountIdStr = req.getParameter("fromAccountId");
        String toAccountNumber  = req.getParameter("toAccountNumber");
        String amountStr        = req.getParameter("amount");
        String description      = req.getParameter("description");

        if (fromAccountIdStr == null || fromAccountIdStr.trim().isEmpty() ||
                toAccountNumber == null || toAccountNumber.trim().isEmpty() ||
                amountStr == null || amountStr.trim().isEmpty()) {
            req.setAttribute("error", "All fields are required.");
            forwardToForm(req, resp, user);
            return;
        }

        // Validate amount
        BigDecimal amount;
        try {
            amount = new BigDecimal(amountStr.trim());
            if (amount.compareTo(BigDecimal.ZERO) <= 0) {
                req.setAttribute("error", "Amount must be positive.");
                forwardToForm(req, resp, user);
                return;
            }
        } catch (NumberFormatException e) {
            req.setAttribute("error", "Invalid amount.");
            forwardToForm(req, resp, user);
            return;
        }

        // Validate fromAccountId is numeric
        int fromAccountId;
        try {
            fromAccountId = Integer.parseInt(fromAccountIdStr.trim());
        } catch (NumberFormatException e) {
            req.setAttribute("error", "Invalid source account.");
            forwardToForm(req, resp, user);
            return;
        }

        AccountDAO accountDAO = new AccountDAO();
        TransactionDAO transactionDAO = new TransactionDAO();

        Account fromAccount;
        Account toAccount;
        try {
            fromAccount = accountDAO.findById(fromAccountId);
            toAccount   = accountDAO.findByAccountNumber(toAccountNumber.trim());
        } catch (SQLException e) {
            req.setAttribute("error", "Database error.");
            forwardToForm(req, resp, user);
            return;
        }

        if (fromAccount == null || fromAccount.getUserId() != user.getId()) {
            req.setAttribute("error", "Invalid source account.");
            forwardToForm(req, resp, user);
            return;
        }
        if (toAccount == null) {
            req.setAttribute("error", "Destination account not found.");
            forwardToForm(req, resp, user);
            return;
        }
        if (fromAccount.getId() == toAccount.getId()) {
            req.setAttribute("error", "Cannot transfer to same account.");
            forwardToForm(req, resp, user);
            return;
        }
        if (fromAccount.getBalance().compareTo(amount) < 0) {
            req.setAttribute("error", "Insufficient balance.");
            forwardToForm(req, resp, user);
            return;
        }

        try {
            BigDecimal newFromBalance = fromAccount.getBalance().subtract(amount);
            BigDecimal newToBalance   = toAccount.getBalance().add(amount);
            accountDAO.updateBalance(fromAccount.getId(), newFromBalance);
            accountDAO.updateBalance(toAccount.getId(), newToBalance);
            transactionDAO.create(fromAccount.getId(), toAccount.getId(), amount,
                    description != null ? description : "Fund transfer");
        } catch (SQLException e) {
            req.setAttribute("error", "Transfer failed. Please try again.");
            forwardToForm(req, resp, user);
            return;
        }

        req.setAttribute("amount", amount);
        req.setAttribute("toAccountNumber", toAccountNumber);
        req.getRequestDispatcher("/transferSuccess.jsp").forward(req, resp);
    }

    private void forwardToForm(HttpServletRequest req, HttpServletResponse resp, User user)
            throws ServletException, IOException {
        AccountDAO accountDAO = new AccountDAO();
        try {
            List<Account> accounts = accountDAO.findByUserId(user.getId());
            req.setAttribute("accounts", accounts);
        } catch (SQLException e) {
            // error already set; proceed without account list
        }
        req.setAttribute("user", user);
        req.getRequestDispatcher("/transfer.jsp").forward(req, resp);
    }
}
