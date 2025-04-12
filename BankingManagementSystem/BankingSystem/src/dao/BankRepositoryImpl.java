package dao;

import bean.*;
import exception.*;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BankRepositoryImpl implements IBankRepository {

    @Override
    public void addAccount(Account account) {
        String query = "INSERT INTO accounts (accountNumber, customerId, balance, accountType) VALUES (?, ?, ?, ?)";
        try (Connection connection = DBUtil.getDBConn();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, account.getAccountNumber());
            statement.setInt(2, account.getCustomer().getCustomerId());
            statement.setDouble(3, account.getBalance());
            statement.setString(4, account.getAccountType());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Account findAccountByAccountNumber(String accountNumber) throws InvalidAccountException {
        String query = "SELECT * FROM accounts WHERE accountNumber = ?";
        try (Connection connection = DBUtil.getDBConn();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, accountNumber);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int customerId = resultSet.getInt("customerId");
                double balance = resultSet.getDouble("balance");
                String accountType = resultSet.getString("accountType");
                Customer customer = findCustomerById(customerId);
                switch (accountType) {
                    case "Current":
                        return new CurrentAccount(accountNumber, customer, balance);
                    case "Savings":
                        return new SavingsAccount(accountNumber, customer, balance);
                    case "ZeroBalance":
                        return new ZeroBalanceAccount(accountNumber, customer, balance);
                    default:
                        throw new InvalidAccountException("Invalid account type for account number: " + accountNumber);
                }
            } else {
                throw new InvalidAccountException("Account not found with account number: " + accountNumber);
            }
        } catch (Exception e) {
            throw new InvalidAccountException("Error retrieving account: " + e.getMessage());
        }
    }

    @Override
    public void deposit(String accountNumber, double amount) throws InvalidAccountException {
        Account account = findAccountByAccountNumber(accountNumber);
        double newBalance = account.getBalance() + amount;
        String query = "UPDATE accounts SET balance = ? WHERE accountNumber = ?";
        try (Connection connection = DBUtil.getDBConn();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDouble(1, newBalance);
            statement.setString(2, accountNumber);
            statement.executeUpdate();

            // Log transaction
            addTransaction(new Transaction(accountNumber, "Deposit", amount, new Timestamp(System.currentTimeMillis())));
        } catch (Exception e) {
            throw new InvalidAccountException("Error depositing money: " + e.getMessage());
        }
    }

    @Override
    public void withdraw(String accountNumber, double amount) throws InvalidAccountException, InsufficientFundException, OverdraftLimitExceededException {
        Account account = findAccountByAccountNumber(accountNumber);
        account.withdraw(amount);

        String query = "UPDATE accounts SET balance = ? WHERE accountNumber = ?";
        try (Connection connection = DBUtil.getDBConn();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDouble(1, account.getBalance());
            statement.setString(2, accountNumber);
            statement.executeUpdate();

            // Log transaction
            addTransaction(new Transaction(accountNumber, "Withdraw", amount, new Timestamp(System.currentTimeMillis())));
        } catch (Exception e) {
            throw new InvalidAccountException("Error withdrawing money: " + e.getMessage());
        }
    }

    @Override
    public void transfer(String fromAccountNumber, String toAccountNumber, double amount) throws InvalidAccountException, InsufficientFundException, OverdraftLimitExceededException {
        withdraw(fromAccountNumber, amount);
        deposit(toAccountNumber, amount);

        // Log transfer for source account
        addTransaction(new Transaction(fromAccountNumber, "Transfer Out", amount, new Timestamp(System.currentTimeMillis())));
        // Log transfer for destination account
        addTransaction(new Transaction(toAccountNumber, "Transfer In", amount, new Timestamp(System.currentTimeMillis())));
    }

    @Override
    public double getAccountBalance(String accountNumber) throws InvalidAccountException {
        Account account = findAccountByAccountNumber(accountNumber);
        return account.getBalance();
    }

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        String query = "SELECT * FROM customers";
        try (Connection connection = DBUtil.getDBConn();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                customers.add(new Customer(
                        resultSet.getInt("customerId"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("email"),
                        resultSet.getString("phoneNumber"),
                        resultSet.getString("address")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customers;
    }

    @Override
    public List<Account> getAllAccounts() {
        List<Account> accounts = new ArrayList<>();
        String query = "SELECT * FROM accounts";
        try (Connection connection = DBUtil.getDBConn();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                String accountNumber = resultSet.getString("accountNumber");
                int customerId = resultSet.getInt("customerId");
                double balance = resultSet.getDouble("balance");
                String accountType = resultSet.getString("accountType");
                Customer customer = findCustomerById(customerId);
                Account account = null;
                switch (accountType) {
                    case "Current":
                        account = new CurrentAccount(accountNumber, customer, balance);
                        break;
                    case "Savings":
                        account = new SavingsAccount(accountNumber, customer, balance);
                        break;
                    case "ZeroBalance":
                        account = new ZeroBalanceAccount(accountNumber, customer, balance);
                        break;
                }
                accounts.add(account);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accounts;
    }

    @Override
    public void deleteAccount(String accountNumber) throws InvalidAccountException {
        String query = "DELETE FROM accounts WHERE accountNumber = ?";
        try (Connection connection = DBUtil.getDBConn();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, accountNumber);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new InvalidAccountException("No account found with account number: " + accountNumber);
            }
        } catch (Exception e) {
            throw new InvalidAccountException("Error deleting account: " + e.getMessage());
        }
    }

    @Override
    public void addCustomer(Customer customer) {
        String query = "INSERT INTO customers (customerId, firstName, lastName, email, phoneNumber, address) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = DBUtil.getDBConn();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, customer.getCustomerId());
            statement.setString(2, customer.getFirstName());
            statement.setString(3, customer.getLastName());
            statement.setString(4, customer.getEmail());
            statement.setString(5, customer.getPhoneNumber());
            statement.setString(6, customer.getAddress());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Customer findCustomerById(int customerId) {
        String query = "SELECT * FROM customers WHERE customerId = ?";
        try (Connection connection = DBUtil.getDBConn();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, customerId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Customer(
                        customerId,
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("email"),
                        resultSet.getString("phoneNumber"),
                        resultSet.getString("address")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateCustomer(Customer customer) {
        String query = "UPDATE customers SET firstName = ?, lastName = ?, email = ?, phoneNumber = ?, address = ? WHERE customerId = ?";
        try (Connection connection = DBUtil.getDBConn();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, customer.getFirstName());
            statement.setString(2, customer.getLastName());
            statement.setString(3, customer.getEmail());
            statement.setString(4, customer.getPhoneNumber());
            statement.setString(5, customer.getAddress());
            statement.setInt(6, customer.getCustomerId());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCustomer(int customerId) {
        String query = "DELETE FROM customers WHERE customerId = ?";
        try (Connection connection = DBUtil.getDBConn();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, customerId);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // NEW METHOD TO ADD TRANSACTIONS
    public void addTransaction(Transaction transaction) {
        String query = "INSERT INTO transactions (accountNumber, transactionType, amount, transactionDate) VALUES (?, ?, ?, ?)";
        try (Connection connection = DBUtil.getDBConn();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, transaction.getAccountNumber());
            statement.setString(2, transaction.getTransactionType());
            statement.setDouble(3, transaction.getAmount());
            statement.setTimestamp(4, transaction.getTransactionDate());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
