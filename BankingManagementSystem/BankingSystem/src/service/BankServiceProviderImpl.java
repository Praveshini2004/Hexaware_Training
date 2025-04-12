package service;

import bean.Account;
import bean.Customer;
import dao.BankRepositoryImpl;
import exception.InsufficientFundException;
import exception.InvalidAccountException;
import exception.OverdraftLimitExceededException;

import java.util.List;

public class BankServiceProviderImpl implements IBankServiceProvider {

    private BankRepositoryImpl bankRepository;

    public BankServiceProviderImpl() {
        this.bankRepository = new BankRepositoryImpl();
    }

    @Override
    public void addAccount(Account account) {
        bankRepository.addAccount(account);
    }

    @Override
    public Account findAccountByAccountNumber(String accountNumber) throws InvalidAccountException {
        Account account = bankRepository.findAccountByAccountNumber(accountNumber);
        if (account == null) {
            throw new InvalidAccountException("Account not found.");
        }
        return account;
    }

    @Override
    public void deposit(String accountNumber, double amount) throws InvalidAccountException {
        Account account = findAccountByAccountNumber(accountNumber);
        if (account == null) {
            throw new InvalidAccountException("Account not found.");
        }
        bankRepository.deposit(accountNumber, amount);
    }

    @Override
    public void withdraw(String accountNumber, double amount) throws InvalidAccountException, InsufficientFundException, OverdraftLimitExceededException {
        Account account = findAccountByAccountNumber(accountNumber);
        if (account == null) {
            throw new InvalidAccountException("Account not found.");
        }
        bankRepository.withdraw(accountNumber, amount);
    }

    @Override
    public double getAccountBalance(String accountNumber) throws InvalidAccountException {
        Account account = findAccountByAccountNumber(accountNumber);
        if (account == null) {
            throw new InvalidAccountException("Account not found.");
        }
        return bankRepository.getAccountBalance(accountNumber);
    }

    @Override
    public void transfer(String fromAccountNumber, String toAccountNumber, double amount) throws InvalidAccountException, InsufficientFundException, OverdraftLimitExceededException {
        Account fromAccount = findAccountByAccountNumber(fromAccountNumber);
        if (fromAccount == null) {
            throw new InvalidAccountException("From Account not found.");
        }
        
        Account toAccount = findAccountByAccountNumber(toAccountNumber);
        if (toAccount == null) {
            throw new InvalidAccountException("To Account not found.");
        }
        
        bankRepository.transfer(fromAccountNumber, toAccountNumber, amount);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return bankRepository.getAllCustomers();
    }

    @Override
    public List<Account> getAllAccounts() {
        return bankRepository.getAllAccounts();
    }

    @Override
    public void deleteAccount(String accountNumber) throws InvalidAccountException {
        Account account = findAccountByAccountNumber(accountNumber);
        if (account == null) {
            throw new InvalidAccountException("Account not found.");
        }
        bankRepository.deleteAccount(accountNumber);
    }
}
