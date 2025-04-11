/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author DELL
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import service.ICustomerServiceProvider;
import service.IBankRepository;

import Banking.Account;
import Exception.InvalidAccountException;

public class CustomerServiceProviderImpl implements ICustomerServiceProvider {

    protected IBankRepository bankRepo = new BankRepositoryImpl();  // protected so subclass can reuse

    @Override
    public float get_account_balance(long account_number) {
        float balance = bankRepo.getAccountBalance(account_number);
        if (balance == -1) {
            System.out.println("Invalid account number.");
        } else {
            System.out.println("Current balance for account " + account_number + " is ₹" + balance);
        }
        return balance;
    }

    @Override
    public float deposit(long account_number, float amount) {
        float newBalance = bankRepo.deposit(account_number, amount);
        if (newBalance != -1) {
            System.out.println("₹" + amount + " deposited successfully. New balance: ₹" + newBalance);
        } else {
            System.out.println("Deposit failed.");
        }
        return newBalance;
    }

    @Override
    public float withdraw(long account_number, float amount) throws Exception {
        float newBalance = bankRepo.withdraw(account_number, amount);
        if (newBalance != -1) {
            System.out.println("₹" + amount + " withdrawn successfully. New balance: ₹" + newBalance);
        } else {
            System.out.println("Withdrawal failed.");
        }
        return newBalance;
    }

    @Override
    public boolean transfer(long from_account_number, long to_account_number, float amount) throws Exception {
        boolean status = bankRepo.transfer(from_account_number, to_account_number, amount);
        if (status) {
            System.out.println("₹" + amount + " transferred from Account " + from_account_number + " to Account " + to_account_number);
        } else {
            throw new InvalidAccountException("Transfer failed. Check account numbers or balance.");
        }
        return status;
    }

    @Override
    public String getAccountDetails(long account_number) {
        Account acc = bankRepo.getAccountDetails(account_number);
        if (acc != null) {
            System.out.println("Account Details:");
            System.out.println(acc);
            return acc.toString();
        } else {
            System.out.println("Account not found.");
            return "Account not found.";
        }
    }
}
