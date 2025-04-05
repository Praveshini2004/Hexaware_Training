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

class SavingsAccount extends BankAccount {
    private float interestRate;

    public SavingsAccount(String accountNumber, String customerName, float balance, float interestRate) {
        super(accountNumber, customerName, balance);
        this.interestRate = interestRate;
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
        if (amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount);
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    @Override
    public void calculateInterest() {
        float interest = balance * interestRate / 100;
        balance += interest;
        System.out.println("Interest added: " + interest);
    }
}
