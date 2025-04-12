package bean;

import exception.InsufficientFundException;
import exception.OverdraftLimitExceededException;

public abstract class Account {
    private String accountNumber;
    private Customer customer;
    private double balance;
    private String accountType;

    // Constructor
    public Account(String accountNumber, Customer customer, double balance, String accountType) {
        this.accountNumber = accountNumber;
        this.customer = customer;
        this.balance = balance;
        this.accountType = accountType;
    }

    // Getters and Setters
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    // Abstract Methods
    public abstract void withdraw(double amount) throws InsufficientFundException, OverdraftLimitExceededException;

    public abstract void deposit(double amount);

    // Deposit method
    public void depositAmount(double amount) {
        if (amount > 0) {
            this.balance += amount;
            System.out.println("Deposit successful. New Balance: " + balance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    // Display account information
    public void printAccountInfo() {
        System.out.println("Account Number : " + accountNumber);
        System.out.println("Account Type   : " + accountType);
        System.out.println("Balance        : " + balance);
        if (customer != null) {
            System.out.println("--- Customer Details ---");
            customer.printCustomerInfo();
        }
    }

    @Override
    public String toString() {
        return "Account [accountNumber=" + accountNumber + ", accountType=" + accountType +
                ", balance=" + balance + ", customer=" + (customer != null ? customer.getFirstName() + " " + customer.getLastName() : "None") + "]";
    }
}
