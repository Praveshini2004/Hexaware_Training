package dao;

import bean.Account;
import bean.Customer;
import exception.InvalidAccountException;
import exception.InsufficientFundException;
import exception.OverdraftLimitExceededException;

import java.util.List;

public interface IBankRepository {

    // Method to add a new account
    void addAccount(Account account);

    // Method to find an account by its account number
    Account findAccountByAccountNumber(String accountNumber) throws InvalidAccountException;

    // Method to deposit money into an account
    void deposit(String accountNumber, double amount) throws InvalidAccountException;

    // Method to withdraw money from an account
    void withdraw(String accountNumber, double amount) throws InvalidAccountException, InsufficientFundException, OverdraftLimitExceededException;

    // Method to get the balance of an account
    double getAccountBalance(String accountNumber) throws InvalidAccountException;

    // Method to transfer money between two accounts
    void transfer(String fromAccountNumber, String toAccountNumber, double amount) throws InvalidAccountException, InsufficientFundException, OverdraftLimitExceededException;

    // Method to get the list of all customers
    List<Customer> getAllCustomers();

    // Method to get the list of all accounts
    List<Account> getAllAccounts();

    // Method to delete an account by account number
    void deleteAccount(String accountNumber) throws InvalidAccountException;

    // Method to add a new customer
    void addCustomer(Customer customer);

    // Method to find a customer by their ID
    Customer findCustomerById(int customerId);

    // Method to update customer details
    void updateCustomer(Customer customer);

    // Method to delete a customer by ID
    void deleteCustomer(int customerId);
}
