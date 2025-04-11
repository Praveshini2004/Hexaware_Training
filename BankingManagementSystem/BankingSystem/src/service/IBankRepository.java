/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

/**
 *
 * @author DELL
 */

import Banking.*;
import java.util.*;
import java.util.Date;

public interface IBankRepository {
    void createAccount(Customer customer, long accNo, String accType, float balance);
    List<Account> listAccounts();
    float calculateInterest();
    float getAccountBalance(long accNo);
    float deposit(long accNo, float amount);
    float withdraw(long accNo, float amount);
    boolean transfer(long fromAcc, long toAcc, float amount);
    Account getAccountDetails(long accNo);
    List<Transaction> getTransactions(long accNo, Date fromDate, Date toDate);
}
