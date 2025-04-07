/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.*;
import java.util.*;
import java.sql.Date;
import payrollmanagementsystem.Tax;
/**
 *
 * @author DELL
 */public class TaxDao {
    private final String jdbcURL = "jdbc:mysql://localhost:3306/payxpert";
    private final String jdbcUsername = "root";
    private final String jdbcPassword = "Praveshini";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }

    public void addTax(Tax tax) {
        String sql = "INSERT INTO tax (employeeid, taxyear, taxableincome, taxamount) VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, tax.getEmployeeId());
            stmt.setInt(2, tax.getTaxYear());
            stmt.setDouble(3, tax.getTaxableIncome());
            stmt.setDouble(4, tax.getTaxAmount());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Tax getTaxById(int taxId) {
        Tax tax = null;
        String sql = "SELECT * FROM tax WHERE taxid = ?";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, taxId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                tax = extractTaxFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tax;
    }

    public List<Tax> getAllTaxes() {
        List<Tax> taxList = new ArrayList<>();
        String sql = "SELECT * FROM tax";

        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Tax tax = extractTaxFromResultSet(rs);
                taxList.add(tax);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return taxList;
    }

    public void updateTax(Tax tax) {
        String sql = "UPDATE tax SET employeeid = ?, taxyear = ?, taxableincome = ?, taxamount = ? WHERE taxid = ?";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, tax.getEmployeeId());
            stmt.setInt(2, tax.getTaxYear());
            stmt.setDouble(3, tax.getTaxableIncome());
            stmt.setDouble(4, tax.getTaxAmount());
            stmt.setInt(5, tax.getTaxId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTax(int taxId) {
        String sql = "DELETE FROM tax WHERE taxid = ?";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, taxId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Tax extractTaxFromResultSet(ResultSet rs) throws SQLException {
        return new Tax(
                rs.getInt("taxid"),
                rs.getInt("employeeid"),
                rs.getInt("taxyear"),
                rs.getDouble("taxableincome"),
                rs.getDouble("taxamount")
        );
    }
}