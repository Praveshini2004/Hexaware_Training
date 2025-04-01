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
public class Task5 {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter Password");
        String password=sc.nextLine();
        if(valid(password)){
            System.out.println("Password is valid");
        }
        else{
            System.out.println("Invalid password");
        }
    }
    public static boolean valid(String s){
        if(s.length()<8){
            return false;
        }
        boolean upper=false;
        boolean digit=false;
        for(int i=0;i<s.length();i++){
            char ch=s.charAt(i);
            if(Character.isUpperCase(ch)){
                upper=true;
            }
            if(Character.isDigit(ch)){
                digit=true;
            }
        }
        return upper && digit;
    }
    
}
