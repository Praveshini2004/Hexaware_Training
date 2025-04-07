/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.*;
import java.sql.Date;
import java.util.*;
import payrollmanagementsystem.Employee;

/**
 *
 * @author DELL
 */
public class EmployeeDao {
    private final String jdbcURL = "jdbc:mysql://localhost:3306/payxpert";
    private final String jdbcUsername = "root";
    private final String jdbcPassword = "Praveshini";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }

    // Insert Employee
    public void insertEmployee(Employee emp) {
        String sql = "INSERT INTO employee (employeeid, firstname, lastname, dateofbirth, gender, email, phonenumber, address, position, joiningdate, terminationdate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, emp.getEmployeeId());
            stmt.setString(2, emp.getFirstName());
            stmt.setString(3, emp.getLastName());
            stmt.setDate(4, Date.valueOf(emp.getDateOfBirth()));
            stmt.setString(5, emp.getGender());
            stmt.setString(6, emp.getEmail());
            stmt.setString(7, emp.getPhoneNumber());
            stmt.setString(8, emp.getAddress());
            stmt.setString(9, emp.getPosition());
            stmt.setDate(10, Date.valueOf(emp.getJoiningDate()));
            stmt.setDate(11, emp.getTerminationDate() != null ? Date.valueOf(emp.getTerminationDate()) : null);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get All Employees
    public List<Employee> getAllEmployees() {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM employee";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Employee emp = new Employee();
                emp.setEmployeeId(rs.getInt("employeeid"));
                emp.setFirstName(rs.getString("firstname"));
                emp.setLastName(rs.getString("lastname"));
                emp.setDateOfBirth(rs.getDate("dateofbirth").toLocalDate());
                emp.setGender(rs.getString("gender"));
                emp.setEmail(rs.getString("email"));
                emp.setPhoneNumber(rs.getString("phonenumber"));
                emp.setAddress(rs.getString("address"));
                emp.setPosition(rs.getString("position"));
                emp.setJoiningDate(rs.getDate("joiningdate").toLocalDate());
                Date termDate = rs.getDate("terminationdate");
                emp.setTerminationDate(termDate != null ? termDate.toLocalDate() : null);
                list.add(emp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Update Employee
    public void updateEmployee(Employee emp) {
        String sql = "UPDATE employee SET firstname=?, lastname=?, dateofbirth=?, gender=?, email=?, phonenumber=?, address=?, position=?, joiningdate=?, terminationdate=? WHERE employeeid=?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, emp.getFirstName());
            stmt.setString(2, emp.getLastName());
            stmt.setDate(3, Date.valueOf(emp.getDateOfBirth()));
            stmt.setString(4, emp.getGender());
            stmt.setString(5, emp.getEmail());
            stmt.setString(6, emp.getPhoneNumber());
            stmt.setString(7, emp.getAddress());
            stmt.setString(8, emp.getPosition());
            stmt.setDate(9, Date.valueOf(emp.getJoiningDate()));
            stmt.setDate(10, emp.getTerminationDate() != null ? Date.valueOf(emp.getTerminationDate()) : null);
            stmt.setInt(11, emp.getEmployeeId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete Employee
    public void deleteEmployee(int employeeId) {
        String sql = "DELETE FROM employee WHERE employeeid=?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, employeeId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get Employee by ID
    public Employee getEmployeeById(int id) {
        String sql = "SELECT * FROM employee WHERE employeeid=?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Employee emp = new Employee();
                    emp.setEmployeeId(rs.getInt("employeeid"));
                    emp.setFirstName(rs.getString("firstname"));
                    emp.setLastName(rs.getString("lastname"));
                    emp.setDateOfBirth(rs.getDate("dateofbirth").toLocalDate());
                    emp.setGender(rs.getString("gender"));
                    emp.setEmail(rs.getString("email"));
                    emp.setPhoneNumber(rs.getString("phonenumber"));
                    emp.setAddress(rs.getString("address"));
                    emp.setPosition(rs.getString("position"));
                    emp.setJoiningDate(rs.getDate("joiningdate").toLocalDate());
                    Date termDate = rs.getDate("terminationdate");
                    emp.setTerminationDate(termDate != null ? termDate.toLocalDate() : null);
                    return emp;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}