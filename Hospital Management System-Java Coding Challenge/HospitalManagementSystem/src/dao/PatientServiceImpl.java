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
import util.DBConnUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientServiceImpl implements IPatientService {
    private final Connection conn = DBConnUtil.getConnection();

    @Override
    public boolean addPatient(Patient p) {
        String query = "INSERT INTO patient VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, p.getPatientId());
            stmt.setString(2, p.getFirstName());
            stmt.setString(3, p.getLastName());
            stmt.setString(4, p.getDateOfBirth());
            stmt.setString(5, p.getGender());
            stmt.setString(6, p.getContactNumber());
            stmt.setString(7, p.getAddress());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Patient getPatientById(int id) {
        String query = "SELECT * FROM patient WHERE patientId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Patient(
                        rs.getInt("patientId"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("dateOfBirth"),
                        rs.getString("gender"),
                        rs.getString("contactNumber"),
                        rs.getString("address")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Patient> getAllPatients() {
        List<Patient> list = new ArrayList<>();
        String query = "SELECT * FROM patient";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                list.add(new Patient(
                        rs.getInt("patientId"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("dateOfBirth"),
                        rs.getString("gender"),
                        rs.getString("contactNumber"),
                        rs.getString("address")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean updatePatient(Patient p) {
        String query = "UPDATE patient SET firstName=?, lastName=?, dateOfBirth=?, gender=?, contactNumber=?, address=? WHERE patientId=?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, p.getFirstName());
            stmt.setString(2, p.getLastName());
            stmt.setString(3, p.getDateOfBirth());
            stmt.setString(4, p.getGender());
            stmt.setString(5, p.getContactNumber());
            stmt.setString(6, p.getAddress());
            stmt.setInt(7, p.getPatientId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deletePatient(int id) {
        String query = "DELETE FROM patient WHERE patientId=?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
