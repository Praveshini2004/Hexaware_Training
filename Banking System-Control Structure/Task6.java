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
public class Task6 {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        List<String> list=new ArrayList<>();
        double balance=0;
        while(true){
            System.out.println("Choose a transaction");
            System.out.println("1.Deposit");
            System.out.println("2.Withdraw");
            System.out.println("3.Exit");
            System.out.println("Enter your choice:");
            int choice=sc.nextInt();
            if(choice==1){
                System.out.println("Enter deposit amount:");
                double amt=sc.nextDouble();
                if(amt>0){
                    balance+=amt;
                    list.add("Deposit: "+amt);
                    System.out.println("Deposit successful");
                }
                else{
                    System.out.println("Invalid amount");
                }
            }
            else if(choice==2){
                System.out.println("Enter withdrawal amount:");
                double amt=sc.nextDouble();
                if(amt>0 && amt<=balance){
                    balance-=amt;
                    list.add("Withdraw: "+amt);
                    System.out.println("Withdraw successful");
                }
                else{
                    System.out.println("Invalid amount");
                }
            }
            else if(choice==3){
                System.out.println("\nTransaction History:");
                if(list.isEmpty()){
                    System.out.println("No transactions made");
                }
                else{
                    for(String t:list){
                        System.out.println(t);
                    }
                    System.out.println("Balance amount: "+balance);
                }
                break;
            }
            else{
                System.out.println("Invalid choice");
            }
            
        }
        
    }
    
}
