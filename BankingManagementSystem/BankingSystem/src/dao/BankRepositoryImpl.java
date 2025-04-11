/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author DELL
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author DELL
 */
import java.util.Date;

import service.IBankRepository;
import Banking.*;
import Exception.*;
import util.DBUtil;

import java.sql.*;
import java.util.*;
import Banking.Account;
import Banking.Customer;
import java.util.logging.Level;
import java.util.logging.Logger;
public class BankRepositoryImpl implements IBankRepository {

    @Override
    public void createAccount(Customer customer, long accNo, String accType, float balance) {
        try (Connection conn = DBUtil.getDBConn()) {

           
            String checkCustomer = "SELECT customerid FROM Customer WHERE customerid = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkCustomer);
            checkStmt.setLong(1, customer.getCustomerId());
            ResultSet rs = checkStmt.executeQuery();

            if (!rs.next()) {
                String insertCustomer = "INSERT INTO Customer (customerid, firstname, lastname, email, phonenumber, address) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement custStmt = conn.prepareStatement(insertCustomer);
                custStmt.setLong(1, customer.getCustomerId());
                custStmt.setString(2, customer.getFirstName());
                custStmt.setString(3, customer.getLastName());
                custStmt.setString(4, customer.getEmail());
                custStmt.setString(5, customer.getPhoneNumber());
                custStmt.setString(6, customer.getAddress());
                custStmt.executeUpdate();
            }

            String insertAccount = "INSERT INTO Account (accountid, customerid, accounttype, balance) VALUES (?, ?, ?, ?)";
            PreparedStatement accStmt = conn.prepareStatement(insertAccount);
            accStmt.setLong(1, accNo);
            accStmt.setLong(2, customer.getCustomerId());
            accStmt.setString(3, accType.toLowerCase());
            accStmt.setFloat(4, balance);
            accStmt.executeUpdate();

            System.out.println("Account created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Account> listAccounts() {
        List<Account> accounts = new ArrayList<>();
        try (Connection conn = DBUtil.getDBConn()) {
            String query = "SELECT a.*, c.* FROM Account a JOIN Customer c ON a.customerid = c.customerid";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Customer customer = new Customer(
                        rs.getLong("customerid"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getString("email"),
                        rs.getString("phonenumber"),
                        rs.getString("address")
                );
                String type = rs.getString("accounttype");
                float balance = rs.getFloat("balance");
                Account account;

                switch (type.toLowerCase()) {
                    case "savings":
                        account = new SavingsAccount(balance, customer, 4.0f);
                        break;
                    case "current":
                        account = new CurrentAccount(balance, customer, 10000);
                        break;
                    case "zerobalance":
                        account = new ZeroBalanceAccount(customer);
                        break;
                    default:
                        continue;
                }

                account.accountNumber = rs.getLong("accountid");
                accounts.add(account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    @Override
    public float calculateInterest() {
        float totalInterest = 0;
        for (Account acc : listAccounts()) {
            if (acc instanceof SavingsAccount) {
                totalInterest += ((SavingsAccount) acc).calculateInterest();
            }
        }
        return totalInterest;
    }

    @Override
    public float getAccountBalance(long accNo) {
        try (Connection conn = DBUtil.getDBConn()) {
            String query = "SELECT balance FROM Account WHERE accountid = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setLong(1, accNo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getFloat("balance");
            } else {
                throw new InvalidAccountException("Account number not found.");
            }
        } catch (SQLException | InvalidAccountException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public float deposit(long accNo, float amount) {
        try (Connection conn = DBUtil.getDBConn()) {
            float balance = getAccountBalance(accNo);
            float newBalance = balance + amount;

            String update = "UPDATE Account SET balance = ? WHERE accountid = ?";
            PreparedStatement stmt = conn.prepareStatement(update);
            stmt.setFloat(1, newBalance);
            stmt.setLong(2, accNo);
            stmt.executeUpdate();

            insertTransaction(accNo, "Deposit", "Deposit of ₹" + amount, amount);
            return newBalance;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public float withdraw(long accNo, float amount) {
        try (Connection conn = DBUtil.getDBConn()) {
            float balance = getAccountBalance(accNo);

            String query = "SELECT accounttype FROM Account WHERE accountid = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setLong(1, accNo);
            ResultSet rs = stmt.executeQuery();

            if (!rs.next()) throw new InvalidAccountException("Account not found.");

            String type = rs.getString("accounttype");

            if (type.equalsIgnoreCase("savings") && balance - amount < 500) {
                throw new InsufficientFundException("Minimum balance of ₹500 required.");
            } else if (type.equalsIgnoreCase("current") && amount > balance + 10000) {
                throw new OverDraftLimitExceededException("Overdraft limit exceeded.");
            } else if (amount > balance && !type.equalsIgnoreCase("current")) {
                throw new InsufficientFundException("Insufficient funds.");
            }

            float newBalance = balance - amount;

            String update = "UPDATE Account SET balance = ? WHERE accountid = ?";
            PreparedStatement updateStmt = conn.prepareStatement(update);
            updateStmt.setFloat(1, newBalance);
            updateStmt.setLong(2, accNo);
            updateStmt.executeUpdate();

            insertTransaction(accNo, "Withdraw", "Withdrawal of ₹" + amount, amount);
            return newBalance;
        } catch (SQLException | InvalidAccountException | InsufficientFundException | OverDraftLimitExceededException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public boolean transfer(long fromAcc, long toAcc, float amount) {
        float fromBalance = withdraw(fromAcc, amount);
        if (fromBalance >= 0) {
            float toBalance = deposit(toAcc, amount);
            try {
                insertTransaction(fromAcc, "Transfer", "Transferred ₹" + amount + " to Account " + toAcc, amount);
                insertTransaction(toAcc, "Transfer", "Received ₹" + amount + " from Account " + fromAcc, amount);
            } catch (SQLException ex) {
                Logger.getLogger(BankRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return true;
        }
        return false;
    }

    @Override
    public Account getAccountDetails(long accNo) {
        for (Account acc : listAccounts()) {
            if (acc.getAccountNumber() == accNo) {
                return acc;
            }
        }
        return null;
    }

    @Override
    public List<Transaction> getTransactions(long accNo, Date fromDate, Date toDate) {
        List<Transaction> trans = new ArrayList<>();
        try (Connection conn = DBUtil.getDBConn()) {
            String query = "SELECT * FROM Transactions WHERE accountid = ? AND transactiondate BETWEEN ? AND ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setLong(1, accNo);
            stmt.setDate(2, new java.sql.Date(fromDate.getTime()));
            stmt.setDate(3, new java.sql.Date(toDate.getTime()));
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Transaction transaction = new Transaction(
                        rs.getLong("transactionId"),
                        rs.getLong("accountid"),
                        rs.getString("transactiontype"),
                        rs.getString("description"),
                        rs.getFloat("amount"),
                        rs.getTimestamp("transactiondate").toLocalDateTime()
                );
                trans.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trans;
    }

    private void insertTransaction(long accNo, String type, String desc, float amount) throws SQLException {
        try (Connection conn = DBUtil.getDBConn()) {
            String insert = "INSERT INTO Transactions (accountid, transactiontype, description, amount, transactiondate) VALUES (?, ?, ?, ?, NOW())";
            PreparedStatement stmt = conn.prepareStatement(insert);
            stmt.setLong(1, accNo);
            stmt.setString(2, type);
            stmt.setString(3, desc);
            stmt.setFloat(4, amount);
            stmt.executeUpdate();
        }
    }
}
