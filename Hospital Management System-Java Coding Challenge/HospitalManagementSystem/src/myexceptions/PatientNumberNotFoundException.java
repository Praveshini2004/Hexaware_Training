/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myexceptions;

/**
 *
 * @author DELL
 */
public class PatientNumberNotFoundException extends Exception {
    public PatientNumberNotFoundException(String message) {
        super(message);
    }
}