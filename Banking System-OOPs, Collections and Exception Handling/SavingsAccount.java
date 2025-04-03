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
public class SavingsAccount extends Account {
    private double interestRate = 0.045; 

    public SavingsAccount(int accountNumber, double balance) {
        super(accountNumber, "Savings", balance);
    }


    public void calculateInterest() {
        double interest = getBalance() * interestRate;
        setBalance(getBalance() + interest);
        System.out.println("Interest added: " + interest);
    }
}
