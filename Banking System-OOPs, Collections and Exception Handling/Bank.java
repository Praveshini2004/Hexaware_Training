/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankingmanagementsystem;

/**
 *
 * @author DELL
 */
import java.util.Scanner;

public class Bank {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BankAccount account = null;

        System.out.println("==== Welcome to HexaBank ====");
        System.out.println("1. Create Savings Account");
        System.out.println("2. Create Current Account");
        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();
        sc.nextLine();  // Consume newline

        // Common input
        System.out.print("Enter Account Number: ");
        String accNo = sc.nextLine();
        System.out.print("Enter Customer Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Initial Balance: ");
        float balance = sc.nextFloat();

        switch (choice) {
            case 1:
                System.out.print("Enter Interest Rate (%): ");
                float rate = sc.nextFloat();
                account = new SavingsAccount(accNo, name, balance, rate);
                break;
            case 2:
                account = new CurrentAccount(accNo, name, balance);
                break;
            default:
                System.out.println("Invalid option!");
                System.exit(0);
        }

        int option;
        do {
            System.out.println("\n--- Operations Menu ---");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Calculate Interest");
            System.out.println("4. Show Account Details");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");
            option = sc.nextInt();

            switch (option) {
                case 1:
                    System.out.print("Enter amount to deposit: ");
                    float dep = sc.nextFloat();
                    account.deposit(dep);
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    float with = sc.nextFloat();
                    account.withdraw(with);
                    break;
                case 3:
                    account.calculateInterest();
                    break;
                case 4:
                    account.printAccountDetails();
                    break;
                case 5:
                    System.out.println("Thank you for banking with us!");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }

        } while (option != 5);

        sc.close();
    }
}
