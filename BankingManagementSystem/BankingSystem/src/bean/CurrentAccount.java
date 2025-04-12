package bean;

import exception.InsufficientFundException;
import exception.OverdraftLimitExceededException;

public class CurrentAccount extends Account {
    
    private static final double OVERDRAFT_LIMIT = 5000.0;

    // Constructor
    public CurrentAccount(String accountNumber, Customer customer, double balance) {
        super(accountNumber, customer, balance, "Current");
    }

    @Override
    public void withdraw(double amount) throws InsufficientFundException, OverdraftLimitExceededException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be greater than zero.");
        }

        double availableFunds = getBalance() + OVERDRAFT_LIMIT;

        if (amount > availableFunds) {
            throw new OverdraftLimitExceededException("Withdrawal amount exceeds overdraft limit.");
        }

        setBalance(getBalance() - amount);
        System.out.println("Withdrawal successful. Remaining balance: " + getBalance());
    }

    @Override
    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid deposit amount.");
        } else {
            setBalance(getBalance() + amount);
            System.out.println("Deposit successful. New balance: " + getBalance());
        }
    }

    @Override
    public String toString() {
        return "CurrentAccount [AccountNumber=" + getAccountNumber() +
               ", Customer=" + getCustomer().getFirstName() + " " + getCustomer().getLastName() +
               ", Balance=" + getBalance() + "]";
    }
}
