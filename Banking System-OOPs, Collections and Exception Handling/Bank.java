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
import java.util.*;
public class Bank {
    public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);
        Account account = null;

        System.out.println("Select Account Type:");
        System.out.println("1. Savings Account");
        System.out.println("2. Current Account");
        int choice = scanner.nextInt();

        System.out.print("Enter Account Number: ");
        int accountNumber = scanner.nextInt();
        System.out.print("Enter Initial Balance: ");
        double balance = scanner.nextDouble();

        switch (choice) {
            case 1:
                account = new SavingsAccount(accountNumber, balance);
                break;
            case 2:
                account = new CurrentAccount(accountNumber, balance);
                break;
            default:
                System.out.println("Invalid choice!");
                return;
        }

        account.printAccountInfo();

        System.out.print("Enter Deposit Amount: ");
        double depositAmount = scanner.nextDouble();
        account.deposit(depositAmount);

        System.out.print("Enter Withdraw Amount: ");
        double withdrawAmount = scanner.nextDouble();
        account.withdraw(withdrawAmount);

        if (account instanceof SavingsAccount) {
            ((SavingsAccount) account).calculateInterest();
        }

        account.printAccountInfo();
        scanner.close();
    }
}
