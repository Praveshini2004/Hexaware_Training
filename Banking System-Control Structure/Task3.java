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
public class Task3 {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter number of Customers");
        int n=sc.nextInt();
        for(int i=1;i<=n;i++){
            System.out.println("Customer "+i+":");
            System.out.println("Enter initial balance");
            double balance=sc.nextDouble();
            System.out.println("Enter Interest Rate: ");
            double rate=sc.nextDouble();
            System.out.println("Enter Number of years");
            int year=sc.nextInt();
            double amt=balance*Math.pow((1+rate/100),year);
            System.out.printf("Future balance: %.2f",amt);
            System.out.println();
            
         
        }
        
        
    }
    
}
