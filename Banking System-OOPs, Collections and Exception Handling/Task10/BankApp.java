/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Task10;

/**
 *
 * @author DELL
 */
import java.util.Scanner;

public class BankApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Bank bank = new Bank();

        while (true) {
            System.out.println("\n------ BANK SYSTEM MENU ------");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Get Balance");
            System.out.println("5. Transfer");
            System.out.println("6. Get Account Details");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            int ch = sc.nextInt();
            sc.nextLine(); // Consume newline

            try {
                switch (ch) {
                    case 1:
                        System.out.print("Enter Customer ID: ");
                        String cid = sc.nextLine();
                        System.out.print("First Name: ");
                        String fname = sc.nextLine();
                        System.out.print("Last Name: ");
                        String lname = sc.nextLine();
                        System.out.print("Email: ");
                        String email = sc.nextLine();
                        System.out.print("Phone: ");
                        String phone = sc.nextLine();
                        System.out.print("Address: ");
                        String address = sc.nextLine();

                        Customer customer = new Customer(cid, fname, lname, email, phone, address);

                        System.out.println("Choose Account Type:");
                        System.out.println("1. Savings");
                        System.out.println("2. Current");
                        String accType = sc.nextInt() == 1 ? "Savings" : "Current";

                        System.out.print("Initial Balance: ");
                        float balance = sc.nextFloat();

                        bank.createAccount(customer, accType, balance);
                        break;

                    case 2:
                        System.out.print("Account Number: ");
                        long accNo = sc.nextLong();
                        System.out.print("Amount to deposit: ");
                        float dep = sc.nextFloat();
                        float newBalance = bank.deposit(accNo, dep);
                        System.out.println("New Balance: ₹" + newBalance);
                        break;

                    case 3:
                        System.out.print("Account Number: ");
                        long wacc = sc.nextLong();
                        System.out.print("Amount to withdraw: ");
                        float with = sc.nextFloat();
                        float wbalance = bank.withdraw(wacc, with);
                        System.out.println("New Balance: ₹" + wbalance);
                        break;

                    case 4:
                        System.out.print("Account Number: ");
                        long gbacc = sc.nextLong();
                        float gbalance = bank.getAccountBalance(gbacc);
                        System.out.println("Balance: ₹" + gbalance);
                        break;

                    case 5:
                        System.out.print("From Account Number: ");
                        long from = sc.nextLong();
                        System.out.print("To Account Number: ");
                        long to = sc.nextLong();
                        System.out.print("Amount to transfer: ");
                        float amt = sc.nextFloat();
                        bank.transfer(from, to, amt);
                        break;

                    case 6:
                        System.out.print("Account Number: ");
                        long dacc = sc.nextLong();
                        bank.getAccountDetails(dacc);
                        break;

                    case 7:
                        System.out.println("Exiting... Thank you!");
                        return;

                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
