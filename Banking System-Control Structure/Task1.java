/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banking.system.control.structures;

import java.util.Scanner;

/**
 *
 * @author DELL
 */
public class Task1 {
    public static void main(String[] args){
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

