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

import Exception.OverDraftLimitExceededException;

public class CurrentAccount extends Account {
    private float overdraftLimit;

    public CurrentAccount(float accountBalance, Customer customer, float overdraftLimit) {
        super("Current", accountBalance, customer);
        this.overdraftLimit = overdraftLimit;
    }

    public void deposit(float amount) {
        accountBalance += amount;
    }

    @Override
    public void withdraw(float amount) throws OverDraftLimitExceededException {
        if (amount > accountBalance + overdraftLimit) {
            throw new OverDraftLimitExceededException("Withdrawal exceeds overdraft limit.");
        }
        accountBalance -= amount;
    }

    @Override
    public String toString() {
        return "CurrentAccount {" +
                "Account Type = '" + accountType + '\'' +
                ", Account Balance = " + accountBalance +
                ", Overdraft Limit = " + overdraftLimit +
                ", Customer = " + customer +
                '}';
    }
}
