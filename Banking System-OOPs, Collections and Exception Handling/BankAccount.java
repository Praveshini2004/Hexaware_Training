/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankingmanagementsystem;

/**
 *
 * @author DELL
 */
abstract class BankAccount {
    protected String accountNumber;
    protected String customerName;
    protected float balance;

    // Default constructor
    public BankAccount() {
        this.accountNumber = "";
        this.customerName = "";
        this.balance = 0.0f;
    }

    // Parameterized constructor
    public BankAccount(String accountNumber, String customerName, float balance) {
        this.accountNumber = accountNumber;
        this.customerName = customerName;
        this.balance = balance;
    }

    // Getters and Setters
    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public float getBalance() { return balance; }
    public void setBalance(float balance) { this.balance = balance; }

    public void printAccountDetails() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Customer Name: " + customerName);
        System.out.println("Balance: " + balance);
    }

    // Abstract methods
    public abstract void deposit(float amount);
    public abstract void withdraw(float amount);
    public abstract void calculateInterest();
}