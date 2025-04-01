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
public class Task2 {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter current balance:");
        double balance=sc.nextDouble();
        System.out.println("\n ATM MENU:");
        System.out.println("1.Check Balance");
        System.out.println("2.Withdraw");
        System.out.println("3.Deposit");
        System.out.print("Enter your choice:");
        int choice=sc.nextInt();
        
        if(choice==1){
            System.out.println("Current Balance: "+balance);
            
        }
        else if(choice==2){
            System.out.print("Enter withdraw amount-Multiples of 100 or 500: ");
            double withdraw=sc.nextDouble();
            if(withdraw>balance){
                System.out.println("Insufficient Balance");
            }
            else if(withdraw%100==0 || withdraw%500==0){
                balance-=withdraw;
                System.out.println("Withdraw successful \n New balance:"+balance);
            }
            else{
                System.out.println("Invalid amount");
            }
            
        }
        else if(choice==3){
            System.out.print("Enter deposit amount: ");
            double deposit=sc.nextDouble();
            
            if(deposit>0){
                balance+=deposit;
                System.out.println("Deposit successful \n New Balance: "+balance);
            }
            else{
                System.out.println("Invalid Deposit amount");
            }
        }
    }
    
}
