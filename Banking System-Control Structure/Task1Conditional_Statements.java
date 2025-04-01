/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task1.conditional_statements;

/**
 *
 * @author DELL
 */
import java.util.*;
public class Task1Conditional_Statements {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter Credit score:");
        int score=sc.nextInt();
        System.out.println("Enter Annual Income:");
        double income=sc.nextDouble();
        if(score>700 && income>=50000){
            System.out.println("You are eligible for the loan");
        }
        else{
            System.out.println("You are not eligible for the loan");
        }
        
    }
    
}
