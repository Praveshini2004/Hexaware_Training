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

public abstract class Account {
    public static long lastAccNo = 1000;
    public long accountNumber;
    protected String accountType;
    protected float accountBalance;
    protected Customer customer;

    public Account() {}

    public Account(String accountType, float accountBalance, Customer customer) {
        this.accountNumber = ++lastAccNo;
        this.accountType = accountType;
        this.accountBalance = accountBalance;
        this.customer = customer;
    }

    public long getAccountNumber() { return accountNumber; }
    public String getAccountType() { return accountType; }
    public float getAccountBalance() { return accountBalance; }
    public Customer getCustomer() { return customer; }

    public abstract void deposit(float amount);
    public abstract void withdraw(float amount) throws Exception;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Account account = (Account) obj;
        return accountNumber == account.accountNumber;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(accountNumber);
    }

    @Override
    public String toString() {
        return "Account [Number=" + accountNumber +
               ", Type=" + accountType +
               ", Balance=" + accountBalance +
               ", Customer=" + customer.getFirstName() + " " + customer.getLastName() + "]";
    }

    public void setAccountBalance(float balance) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setAccountNumber(long aLong) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
