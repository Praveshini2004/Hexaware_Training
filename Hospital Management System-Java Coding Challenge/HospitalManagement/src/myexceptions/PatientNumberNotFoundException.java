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

    public PatientNumberNotFoundException() {
        super("Patient number not found in the database.");
    }

    public PatientNumberNotFoundException(String message) {
        super(message);
    }

    public String getMessage() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
