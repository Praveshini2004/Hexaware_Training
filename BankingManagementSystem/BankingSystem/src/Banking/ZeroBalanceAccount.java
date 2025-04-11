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


public class ZeroBalanceAccount extends Account {
    public ZeroBalanceAccount(Customer customer) {
        super("ZeroBalance", 0, customer);
    }

    public void deposit(float amount) {
        accountBalance += amount;
    }

    public void withdraw(float amount) throws Exception {
        if (amount > accountBalance) throw new Exception("Insufficient funds!");
        accountBalance -= amount;
    }
    @Override
    public String toString() {
        return "ZeroBalanceAccount {" +
                "Account Type = '" + accountType + '\'' +
                ", Account Balance = " + accountBalance +
                ", Customer = " + customer +
                '}';
    }
}
