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



public interface ICustomerServiceProvider {
    float get_account_balance(long account_number);
    float deposit(long account_number, float amount);
    float withdraw(long account_number, float amount) throws Exception;
    boolean transfer(long from_account_number, long to_account_number, float amount) throws Exception;
    String getAccountDetails(long account_number);
}

