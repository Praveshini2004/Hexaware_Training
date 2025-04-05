/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankingmanagementsystem;

/**
 *
 * @author DELL
 */class CurrentAccount extends BankAccount {
    private static final float OVERDRAFT_LIMIT = 5000.0f;

    public CurrentAccount(String accountNumber, String customerName, float balance) {
        super(accountNumber, customerName, balance);
    }

    @Override
    public void deposit(float amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: " + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    @Override
    public void withdraw(float amount) {
        if (amount <= (balance + OVERDRAFT_LIMIT)) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount);
        } else {
            System.out.println("Overdraft limit exceeded.");
        }
    }

    @Override
    public void calculateInterest() {
        System.out.println("No interest for current accounts.");
    }
}
