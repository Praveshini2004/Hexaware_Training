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
public class Account {  
    private int accountNumber;
    private String accountType;
    private double balance;

    public Account() {}

    public Account(int accountNumber, String accountType, double balance) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.balance = balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(int accountNumber) { 
        this.accountNumber = accountNumber; 
    }
    
    public String getAccountType() {
        return accountType; 
    }
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
    
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }

  public void deposit(float amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited (float): " + amount);
        }
    }

    public void deposit(int amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited (int): " + amount);
        }
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited (double): " + amount);
        }
    }

    public void withdraw(float amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            System.out.println("Withdrawn (float): " + amount);
        } else {
            System.out.println("Insufficient balance!");
        }
    }

    public void withdraw(int amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            System.out.println("Withdrawn (int): " + amount);
        } else {
            System.out.println("Insufficient balance!");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            System.out.println("Withdrawn (double): " + amount);
        } else {
            System.out.println("Insufficient balance!");
        }
    }

    public void printAccountInfo() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Type: " + accountType);
        System.out.println("Balance: " + balance);
    }




    
}
