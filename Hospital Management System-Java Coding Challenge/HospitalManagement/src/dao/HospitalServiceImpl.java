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

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import entity.Appointment;
import myexceptions.PatientNumberNotFoundException;
import util.DBConnection;

public class HospitalServiceImpl implements IHospitalService {

    private Connection conn;

    public HospitalServiceImpl() {
        conn = DBConnection.getConnection();
    }

    @Override
    public Appointment getAppointmentById(int appointmentId) {
        Appointment appointment = null;
        String query = "SELECT * FROM appointments WHERE appointment_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, appointmentId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                appointment = extractAppointmentFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointment;
    }

    /**
     *
     * @param patientId
     * @return
     * @throws PatientNumberNotFoundException
     */
    @Override
    public List<Appointment> getAppointmentsForPatient(int patientId) throws PatientNumberNotFoundException {
    List<Appointment> list = new ArrayList<>();
    String query = "SELECT * FROM appointments WHERE patient_id = ?";
    try (PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setInt(1, patientId);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            list.add(extractAppointmentFromResultSet(rs));
        }

        if (list.isEmpty()) {
            throw new PatientNumberNotFoundException("No appointments found for patient ID: " + patientId);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return list;
}

    @Override
    public List<Appointment> getAppointmentsForDoctor(int doctorId) {
        List<Appointment> list = new ArrayList<>();
        String query = "SELECT * FROM appointments WHERE doctor_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, doctorId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(extractAppointmentFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean scheduleAppointment(Appointment appointment) {
        String query = "INSERT INTO appointments (appointment_id, patient_id, doctor_id, appointment_date, description) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, appointment.getAppointmentId());
            stmt.setInt(2, appointment.getPatientId());
            stmt.setInt(3, appointment.getDoctorId());
            stmt.setString(4, appointment.getAppointmentDate());
            stmt.setString(5, appointment.getDescription());
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateAppointment(Appointment appointment) {
        String query = "UPDATE appointments SET patient_id = ?, doctor_id = ?, appointment_date = ?, description = ? WHERE appointment_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, appointment.getPatientId());
            stmt.setInt(2, appointment.getDoctorId());
            stmt.setString(3, appointment.getAppointmentDate());
            stmt.setString(4, appointment.getDescription());
            stmt.setInt(5, appointment.getAppointmentId());
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean cancelAppointment(int appointmentId) {
        String query = "DELETE FROM appointments WHERE appointment_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, appointmentId);
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Helper method to convert ResultSet to Appointment object
    private Appointment extractAppointmentFromResultSet(ResultSet rs) throws SQLException {
        int appointmentId = rs.getInt("appointment_id");
        int patientId = rs.getInt("patient_id");
        int doctorId = rs.getInt("doctor_id");
        String date = rs.getString("appointment_date");
        String description = rs.getString("description");
        return new Appointment(appointmentId, patientId, doctorId, date, description);
    }
}
