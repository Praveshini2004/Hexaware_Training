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
public class CurrentAccount extends Account{
    
    private static final double OVERDRAFT_LIMIT = 5000; 

    public CurrentAccount(int accountNumber, double balance) {
        super(accountNumber, "Current", balance);
    }

    @Override
    public void withdraw(double amount) {
        if (amount > 0 && (getBalance() + OVERDRAFT_LIMIT) >= amount) {
            setBalance(getBalance() - amount);
            System.out.println("Withdrawn (Current Account): " + amount);
        } else {
            System.out.println("Overdraft limit exceeded!");
        }
    }
}

