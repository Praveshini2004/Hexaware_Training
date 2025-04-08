/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Task10;

/**
 *
 * @author DELL
 */
import java.util.HashMap;
import java.util.Map;

public class Bank {
    private Map<Long, Account> accounts = new HashMap<>();
    private long nextAccountNumber = 1001;

    public long createAccount(Customer customer, String accType, float balance) {
        long accNo = nextAccountNumber++;
        Account account = new Account(accNo, accType, balance, customer);
        accounts.put(accNo, account);
        System.out.println("Account created successfully! Account Number: " + accNo);
        return accNo;
    }

    public float getAccountBalance(long accNo) {
        Account acc = accounts.get(accNo);
        if (acc != null) return acc.getAccountBalance();
        else throw new IllegalArgumentException("Account not found.");
    }

    public float deposit(long accNo, float amount) {
        Account acc = accounts.get(accNo);
        if (acc != null) {
            acc.setAccountBalance(acc.getAccountBalance() + amount);
            return acc.getAccountBalance();
        } else throw new IllegalArgumentException("Account not found.");
    }

    public float withdraw(long accNo, float amount) {
        Account acc = accounts.get(accNo);
        if (acc != null) {
            if (acc.getAccountBalance() >= amount) {
                acc.setAccountBalance(acc.getAccountBalance() - amount);
                return acc.getAccountBalance();
            } else throw new IllegalArgumentException("Insufficient balance.");
        } else throw new IllegalArgumentException("Account not found.");
    }

    public void transfer(long fromAcc, long toAcc, float amount) {
        withdraw(fromAcc, amount);
        deposit(toAcc, amount);
        System.out.println("Transfer successful from " + fromAcc + " to " + toAcc);
    }

    public void getAccountDetails(long accNo) {
        Account acc = accounts.get(accNo);
        if (acc != null) acc.printAccountInfo();
        else throw new IllegalArgumentException("Account not found.");
    }
}

