package service;

import bean.Customer;
import java.util.List;

public interface ICustomerServiceProvider {
    
    // Method to add a customer
    void addCustomer(Customer customer);
    
    // Method to find a customer by their ID
    Customer findCustomerById(int customerId);
    
    // Method to get a list of all customers
    List<Customer> getAllCustomers();
    
    // Method to update customer details
    void updateCustomer(Customer customer);
    
    // Method to delete a customer by ID
    void deleteCustomer(int customerId);
}
