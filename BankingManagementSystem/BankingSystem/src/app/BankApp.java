package app;

import bean.*;
import dao.BankRepositoryImpl;
import exception.InsufficientFundException;
import exception.InvalidAccountException;
import exception.OverdraftLimitExceededException;

import java.util.Scanner;

public class BankApp {

    static Scanner scanner = new Scanner(System.in);
    static BankRepositoryImpl bank = new BankRepositoryImpl();

    public static void main(String[] args) {

        boolean exit = false;
        while (!exit) {
            printMainMenu();
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1: createCustomer(); break;
                    case 2: createAccount(); break;
                    case 3: deposit(); break;
                    case 4: withdraw(); break;
                    case 5: transfer(); break;
                    case 6: checkBalance(); break;
                    case 7: viewAccountInfo(); break;
                    case 8: deleteAccount(); break;
                    case 9: deleteCustomer(); break;
                    case 10: listAllCustomers(); break;
                    case 11: listAllAccounts(); break;
                    case 12: exit = true; System.out.println("Exiting. Thank you!"); break;
                    default: System.out.println("Invalid choice.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        scanner.close();
    }

    private static void printMainMenu() {
        System.out.println("\n--- Banking System ---");
        System.out.println("1. Add Customer");
        System.out.println("2. Add Account");
        System.out.println("3. Deposit");
        System.out.println("4. Withdraw");
        System.out.println("5. Transfer");
        System.out.println("6. Check Balance");
        System.out.println("7. View Account Info");
        System.out.println("8. Delete Account");
        System.out.println("9. Delete Customer");
        System.out.println("10. List All Customers");
        System.out.println("11. List All Accounts");
        System.out.println("12. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void createCustomer() {
        System.out.print("Enter Customer ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter First Name: ");
        String fname = scanner.nextLine();
        System.out.print("Enter Last Name: ");
        String lname = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Phone Number: ");
        String phone = scanner.nextLine();
        System.out.print("Enter Address: ");
        String address = scanner.nextLine();

        Customer customer = new Customer(id, fname, lname, email, phone, address);
        bank.addCustomer(customer);
        System.out.println("Customer added successfully.");
    }

    private static void createAccount() {
        System.out.print("Enter Account Number: ");
        String accNo = scanner.nextLine();

        System.out.print("Enter Customer ID: ");
        int custId = Integer.parseInt(scanner.nextLine());

        Customer customer = bank.findCustomerById(custId);
        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }

       
        System.out.print("Enter Account Type (savings / current / zerobalance): ");
        String type = scanner.nextLine().toLowerCase();

        if (!type.equals("savings") && !type.equals("current") && !type.equals("zerobalance")) {
            System.out.println("Invalid account type. Please enter 'savings', 'current', or 'zerobalance'.");
            return;
        }

        double balance = 0;
        if (type.equals("savings") || type.equals("current")) {
            System.out.print("Do you want to create a Zero Balance Account? (yes/no): ");
            String zeroBalanceChoice = scanner.nextLine().toLowerCase();

            if (zeroBalanceChoice.equals("yes")) {
                balance = 0;
            } else if (zeroBalanceChoice.equals("no")) {
                System.out.print("Enter Initial Balance: ");
                balance = Double.parseDouble(scanner.nextLine());
            } else {
                System.out.println("Invalid input for Zero Balance Account choice.");
                return;
            }
        }

        
        Account account;
        if (type.equals("savings")) {
            account = new SavingsAccount(accNo, customer, balance);
        } else if (type.equals("current")) {
            if (balance == 0) {
                System.out.println("Current Account must have a minimum balance. Zero balance not allowed.");
                return;
            }
            account = new CurrentAccount(accNo, customer, balance);
        } else if (type.equals("zerobalance")) {
            account = new ZeroBalanceAccount(accNo, customer,balance);  
        } else {
            System.out.println("Invalid account type. Please enter 'savings', 'current', or 'zerobalance'.");
            return;
        }

        bank.addAccount(account);
        System.out.println("Account created successfully.");
    }


    private static void deposit() throws InvalidAccountException {
        System.out.print("Enter Account Number: ");
        String accNo = scanner.nextLine();
        System.out.print("Enter Amount to Deposit: ");
        double amount = Double.parseDouble(scanner.nextLine());
        bank.deposit(accNo, amount);
        System.out.println("Deposit successful.");
    }

    private static void withdraw() throws InvalidAccountException, InsufficientFundException, OverdraftLimitExceededException {
        System.out.print("Enter Account Number: ");
        String accNo = scanner.nextLine();
        System.out.print("Enter Amount to Withdraw: ");
        double amount = Double.parseDouble(scanner.nextLine());
        bank.withdraw(accNo, amount);
        System.out.println("Withdrawal successful.");
    }

    private static void transfer() throws InvalidAccountException, InsufficientFundException, OverdraftLimitExceededException {
        System.out.print("From Account Number: ");
        String from = scanner.nextLine();
        System.out.print("To Account Number: ");
        String to = scanner.nextLine();
        System.out.print("Enter Amount to Transfer: ");
        double amount = Double.parseDouble(scanner.nextLine());
        bank.transfer(from, to, amount);
        System.out.println("Transfer successful.");
    }

    private static void checkBalance() throws InvalidAccountException {
        System.out.print("Enter Account Number: ");
        String accNo = scanner.nextLine();
        double balance = bank.getAccountBalance(accNo);
        System.out.println("Current Balance: " + balance);
    }

    private static void viewAccountInfo() throws InvalidAccountException {
        System.out.print("Enter Account Number: ");
        String accNo = scanner.nextLine();
        Account acc = bank.findAccountByAccountNumber(accNo);
        acc.printAccountInfo();
    }

    private static void deleteAccount() throws InvalidAccountException {
        System.out.print("Enter Account Number: ");
        String accNo = scanner.nextLine();
        bank.deleteAccount(accNo);
        System.out.println("Account deleted.");
    }

    private static void deleteCustomer() {
        System.out.print("Enter Customer ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        bank.deleteCustomer(id);
        System.out.println("Customer deleted.");
    }

    private static void listAllCustomers() {
        System.out.println("--- All Customers ---");
        for (Customer c : bank.getAllCustomers()) {
            c.printCustomerInfo();
        }
    }

    private static void listAllAccounts() {
        System.out.println("--- All Accounts ---");
        for (Account a : bank.getAllAccounts()) {
            System.out.println(a);
        }
    }
}
