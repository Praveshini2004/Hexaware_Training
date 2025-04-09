/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author DELL
 */import java.util.List;
import entity.Appointment;
import myexceptions.PatientNumberNotFoundException;

public interface IHospitalService {

    Appointment getAppointmentById(int appointmentId);

    List<Appointment> getAppointmentsForPatient(int patientId) throws PatientNumberNotFoundException;

    List<Appointment> getAppointmentsForDoctor(int doctorId);

    boolean scheduleAppointment(Appointment appointment);

    boolean updateAppointment(Appointment appointment);

    boolean cancelAppointment(int appointmentId);
}
