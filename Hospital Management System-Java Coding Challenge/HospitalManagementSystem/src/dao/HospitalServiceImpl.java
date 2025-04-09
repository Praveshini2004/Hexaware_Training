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
import entity.Appointment;
import myexceptions.PatientNumberNotFoundException;
import util.PropertyUtil;
import util.DBConnUtil;
import java.sql.*;
import java.util.*;

public class HospitalServiceImpl implements IHospitalService {
    private Connection connection;

    public HospitalServiceImpl() {
        try {
            this.connection = DBConnUtil.getConnection(); // ✅ Correct

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Appointment getAppointmentById(int id) {
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM appointment WHERE appointmentId = ?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Appointment(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Appointment> getAppointmentsForPatient(int patientId) throws PatientNumberNotFoundException {
        List<Appointment> list = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM appointment WHERE patientId = ?")) {
            ps.setInt(1, patientId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Appointment(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5)));
            }
            if (list.isEmpty()) throw new PatientNumberNotFoundException("No appointments for patientId: " + patientId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Appointment> getAppointmentsForDoctor(int doctorId) {
        List<Appointment> list = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM appointment WHERE doctorId = ?")) {
            ps.setInt(1, doctorId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Appointment(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean scheduleAppointment(Appointment appt) {
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO appointment VALUES (?, ?, ?, ?, ?)")) {
            ps.setInt(1, appt.getAppointmentId());
            ps.setInt(2, appt.getPatientId());
            ps.setInt(3, appt.getDoctorId());
            ps.setString(4, appt.getAppointmentDate());
            ps.setString(5, appt.getDescription());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateAppointment(Appointment appt) {
        try (PreparedStatement ps = connection.prepareStatement("UPDATE appointment SET patientId=?, doctorId=?, appointmentDate=?, description=? WHERE appointmentId=?")) {
            ps.setInt(1, appt.getPatientId());
            ps.setInt(2, appt.getDoctorId());
            ps.setString(3, appt.getAppointmentDate());
            ps.setString(4, appt.getDescription());
            ps.setInt(5, appt.getAppointmentId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean cancelAppointment(int appointmentId) throws PatientNumberNotFoundException {
        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM appointment WHERE appointmentId = ?")) {
            ps.setInt(1, appointmentId);
            int rows = ps.executeUpdate();
            if (rows == 0) throw new PatientNumberNotFoundException("Appointment not found with ID: " + appointmentId);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}