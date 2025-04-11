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

import Banking.Customer;

public interface IBankServiceProvider {
    void create_account(Customer customer, String accType, float balance);
    void listAccounts();
    void calculateInterest();
}

