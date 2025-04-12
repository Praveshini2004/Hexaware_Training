package service;

import bean.Account;
import bean.Customer;
import exception.InsufficientFundException;
import exception.InvalidAccountException;
import exception.OverdraftLimitExceededException;
import java.util.List;

public interface IBankServiceProvider {
    
    // Method to create an account for a customer
    void addAccount(Account account);
    
    // Method to find an account by account number
    Account findAccountByAccountNumber(String accountNumber) throws InvalidAccountException;
    
    // Method to deposit an amount into an account
    void deposit(String accountNumber, double amount) throws InvalidAccountException;
    
    // Method to withdraw an amount from an account
    void withdraw(String accountNumber, double amount) throws InvalidAccountException, InsufficientFundException, OverdraftLimitExceededException;
    
    // Method to get the balance of an account
    double getAccountBalance(String accountNumber) throws InvalidAccountException;
    
    // Method to transfer money from one account to another
    void transfer(String fromAccountNumber, String toAccountNumber, double amount) throws InvalidAccountException, InsufficientFundException, OverdraftLimitExceededException;
    
    // Method to get a list of all customers
    List<Customer> getAllCustomers();
    
    // Method to get a list of all accounts
    List<Account> getAllAccounts();
    
    // Method to delete an account
    void deleteAccount(String accountNumber) throws InvalidAccountException;
}
