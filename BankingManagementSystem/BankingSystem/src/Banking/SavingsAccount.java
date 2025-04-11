/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Banking;

/**
 *
 * @author DELL
 */


import Exception.InsufficientFundException;

public class SavingsAccount extends Account {
    private float interestRate;

    public SavingsAccount(float accountBalance, Customer customer, float interestRate) {
        super("Savings", Math.max(accountBalance, 500), customer);
        this.interestRate = interestRate;
    }

    public void deposit(float amount) {
        accountBalance += amount;
    }

    @Override
    public void withdraw(float amount) throws InsufficientFundException {
        if (accountBalance - amount < 500) {
            throw new InsufficientFundException("Savings Account must maintain minimum balance of ₹500.");
        }
        accountBalance -= amount;
    }

    public float calculateInterest() {
        return accountBalance * interestRate / 100;
    }
    @Override
    public String toString() {
        return "SavingsAccount [AccountNumber=" + accountNumber + 
               ", Balance=" + accountBalance + 
               ", InterestRate=" + interestRate +
               ", Customer=" + customer + "]";
    }

}

