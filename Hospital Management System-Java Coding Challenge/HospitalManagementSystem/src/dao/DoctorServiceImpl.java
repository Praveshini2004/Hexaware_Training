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
import util.DBConnUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorServiceImpl implements IDoctorService {
    private final Connection conn = DBConnUtil.getConnection();

    @Override
    public boolean addDoctor(Doctor d) {
        String query = "INSERT INTO doctor VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, d.getDoctorId());
            stmt.setString(2, d.getFirstName());
            stmt.setString(3, d.getLastName());
            stmt.setString(4, d.getSpecialization());
            stmt.setString(5, d.getContactNumber());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Doctor getDoctorById(int id) {
        String query = "SELECT * FROM doctor WHERE doctorId=?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Doctor(
                        rs.getInt("doctorId"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("specialization"),
                        rs.getString("contactNumber")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Doctor> getAllDoctors() {
        List<Doctor> list = new ArrayList<>();
        String query = "SELECT * FROM doctor";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                list.add(new Doctor(
                        rs.getInt("doctorId"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("specialization"),
                        rs.getString("contactNumber")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean updateDoctor(Doctor d) {
        String query = "UPDATE doctor SET firstName=?, lastName=?, specialization=?, contactNumber=? WHERE doctorId=?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, d.getFirstName());
            stmt.setString(2, d.getLastName());
            stmt.setString(3, d.getSpecialization());
            stmt.setString(4, d.getContactNumber());
            stmt.setInt(5, d.getDoctorId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteDoctor(int id) {
        String query = "DELETE FROM doctor WHERE doctorId=?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
