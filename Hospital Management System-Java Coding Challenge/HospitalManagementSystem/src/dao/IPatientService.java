/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author DELL
 */
import entity.Patient;
import java.util.List;

public interface IPatientService {
    boolean addPatient(Patient patient);
    Patient getPatientById(int id);
    List<Patient> getAllPatients();
    boolean updatePatient(Patient patient);
    boolean deletePatient(int id);
}