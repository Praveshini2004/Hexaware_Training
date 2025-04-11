/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author DELL
 */
import Banking.Account;
import service.IBankServiceProvider;
import service.IBankRepository;
import Banking.Customer;
import java.util.List;

public class BankServiceProviderImpl implements IBankServiceProvider {

    private final IBankRepository bankRepo = new BankRepositoryImpl();

    @Override
    public void create_account(Customer customer, String accType, float balance) {
        // Auto-generate account number starting from lastAccNo
        long accNo = Account.lastAccNo + 1;
        Account.lastAccNo = accNo; // update static counter to prevent duplicates

        // Business rule: Savings account must have min ₹500
        if (accType.equalsIgnoreCase("Savings") && balance < 500) {
            System.out.println("Savings Account must be opened with a minimum balance of ₹500.");
            return;
        }

        // Proceed to create the account via repository
        bankRepo.createAccount(customer, accNo, accType, balance);
    }

    @Override
    public void listAccounts() {
        List<Account> accounts = bankRepo.listAccounts();
        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
        } else {
            System.out.println("List of Accounts:");
            for (Account acc : accounts) {
                System.out.println(acc);
            }
        }
    }

    @Override
    public void calculateInterest() {
        float totalInterest = bankRepo.calculateInterest();
        System.out.println("Total interest calculated on all savings accounts: ₹" + totalInterest);
    }
}
