/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banking.system.control.structures;

/**
 *
 * @author DELL
 */
import java.util.*;
public class Task4 {
    public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter number of customers:");
        int n = sc.nextInt();
        sc.nextLine();
        
        String[] accountNumbers = new String[n];
        double[] balances = new double[n];
        
        // Storing account details
        for(int i = 0; i < n; i++){
            System.out.print("Enter account number " + (i + 1) + ": ");
            accountNumbers[i] = sc.nextLine();
            System.out.print("Enter balance amount: ");
            balances[i] = sc.nextDouble();
            sc.nextLine();
        }
        
        // Checking account balance
        while(true){
            System.out.println("Enter account number to check balance:");
            String accountNum = sc.nextLine();
            
            boolean found = false;
            for(int i = 0; i < n; i++){
                if(accountNumbers[i].equals(accountNum)){
                    System.out.printf("Account Balance: %.2f \n", balances[i]);
                    found = true;
                    break;
                }
            }
            
            if(found) break;
            else System.out.println("Invalid account number");
        }
    }
}