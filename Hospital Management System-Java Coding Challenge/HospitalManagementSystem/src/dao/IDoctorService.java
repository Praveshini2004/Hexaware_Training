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


import entity.Doctor;
import java.util.List;

public interface IDoctorService {
    boolean addDoctor(Doctor doctor);
    Doctor getDoctorById(int id);
    List<Doctor> getAllDoctors();
    boolean updateDoctor(Doctor doctor);
    boolean deleteDoctor(int id);
}
