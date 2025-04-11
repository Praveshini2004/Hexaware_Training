/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

/**
 *
 * @author DELL
 */
import Banking.Customer;
import dao.BankServiceProviderImpl;
import dao.CustomerServiceProviderImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        BankServiceProviderImpl bankService = new BankServiceProviderImpl();
        CustomerServiceProviderImpl customerService = new CustomerServiceProviderImpl();

        boolean running = true;

        System.out.println("=== Welcome to HMBank System ===");

        while (running) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Get Account Balance");
            System.out.println("6. Get Account Details");
            System.out.println("7. List All Accounts");
            System.out.println("8. Calculate Interest");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            try {
                switch (choice) {
                    case 1:
                       
                        System.out.println("Enter Customer ID: ");
                        long custId = scanner.nextLong();
                        scanner.nextLine();
                        System.out.println("Enter First Name: ");
                        String firstName = scanner.nextLine();
                        System.out.println("Enter Last Name: ");
                        String lastName = scanner.nextLine();
                        System.out.println("Enter Email: ");
                        String email = scanner.nextLine();
                        System.out.println("Enter Phone: ");
                        String phone = scanner.nextLine();
                        System.out.println("Enter Address: ");
                        String address = scanner.nextLine();

                        System.out.println("Choose Account Type (Savings / Current / ZeroBalance): ");
                        String accType = scanner.nextLine();
                        System.out.println("Enter Initial Balance: ");
                        float balance = scanner.nextFloat();

                        Customer customer = new Customer(custId, firstName, lastName, email, phone, address);
                        bankService.create_account(customer, accType, balance);
                        break;

                    case 2:
                       
                        System.out.println("Enter Account Number: ");
                        long accNo = scanner.nextLong();
                        System.out.println("Enter Amount to Deposit: ");
                        float depositAmt = scanner.nextFloat();
                        customerService.deposit(accNo, depositAmt);
                        break;

                    case 3:
                       
                        System.out.println("Enter Account Number: ");
                        accNo = scanner.nextLong();
                        System.out.println("Enter Amount to Withdraw: ");
                        float withdrawAmt = scanner.nextFloat();
                        customerService.withdraw(accNo, withdrawAmt);
                        break;

                    case 4:
                        
                        System.out.println("Enter From Account Number: ");
                        long fromAcc = scanner.nextLong();
                        System.out.println("Enter To Account Number: ");
                        long toAcc = scanner.nextLong();
                        System.out.println("Enter Amount to Transfer: ");
                        float transferAmt = scanner.nextFloat();
                        customerService.transfer(fromAcc, toAcc, transferAmt);
                        break;

                    case 5:
                       
                        System.out.println("Enter Account Number: ");
                        accNo = scanner.nextLong();
                        customerService.get_account_balance(accNo);
                        break;

                    case 6:
                       
                        System.out.println("Enter Account Number: ");
                        accNo = scanner.nextLong();
                        customerService.getAccountDetails(accNo);
                        break;

                    case 7:
                        
                        bankService.listAccounts();
                        break;

                    case 8:
                       
                        bankService.calculateInterest();
                        break;

                    case 9:
                        running = false;
                        System.out.println("Thank you for using HMBank. Goodbye!");
                        break;

                    default:
                        System.out.println("Invalid choice! Please select from the menu.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
