package bean;

import exception.InsufficientFundException;

public class ZeroBalanceAccount extends Account {

    // Constructor
    public ZeroBalanceAccount(String accountNumber, Customer customer, double balance) {
        super(accountNumber, customer, balance, "ZeroBalance");
    }

    @Override
    public void withdraw(double amount) throws InsufficientFundException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be greater than zero.");
        }

        if (amount > getBalance()) {
            throw new InsufficientFundException("Insufficient balance. Cannot withdraw beyond zero.");
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
        return "ZeroBalanceAccount [AccountNumber=" + getAccountNumber() +
               ", Customer=" + getCustomer().getFirstName() + " " + getCustomer().getLastName() +
               ", Balance=" + getBalance() + "]";
    }
}
