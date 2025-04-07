/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

/**
 *
 * @author DELL
 */
import java.sql.*;
import java.util.*;
import java.sql.Date;
import payrollmanagementsystem.FinancialRecord;
public class FinancialRecordDAO {

    private final String jdbcURL = "jdbc:mysql://localhost:3306/payxpert";
    private final String jdbcUsername = "root";
    private final String jdbcPassword = "Praveshini"; // Replace with your actual DB password

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }

    // 1. Add a Financial Record
    public void addFinancialRecord(FinancialRecord record) {
        String sql = "INSERT INTO financialrecord (employeeid, recorddate, description, amount, recordtype) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, record.getEmployeeId());
            stmt.setDate(2, Date.valueOf(record.getRecordDate()));
            stmt.setString(3, record.getDescription());
            stmt.setDouble(4, record.getAmount());
            stmt.setString(5, record.getRecordType());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 2. Get a Financial Record by ID
    public FinancialRecord getFinancialRecordById(int recordId) {
        FinancialRecord record = null;
        String sql = "SELECT * FROM financialrecord WHERE recordid = ?";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, recordId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                record = extractRecordFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return record;
    }

    // 3. Get all Financial Records
    public List<FinancialRecord> getAllFinancialRecords() {
        List<FinancialRecord> records = new ArrayList<>();
        String sql = "SELECT * FROM financialrecord";

        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                records.add(extractRecordFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return records;
    }

    // 4. Update Financial Record
    public void updateFinancialRecord(FinancialRecord record) {
        String sql = "UPDATE financialrecord SET employeeid = ?, recorddate = ?, description = ?, amount = ?, recordtype = ? WHERE recordid = ?";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, record.getEmployeeId());
            stmt.setDate(2, Date.valueOf(record.getRecordDate()));
            stmt.setString(3, record.getDescription());
            stmt.setDouble(4, record.getAmount());
            stmt.setString(5, record.getRecordType());
            stmt.setInt(6, record.getRecordId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 5. Delete Financial Record
    public void deleteFinancialRecord(int recordId) {
        String sql = "DELETE FROM financialrecord WHERE recordid = ?";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, recordId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Helper method to convert ResultSet into FinancialRecord object
    private FinancialRecord extractRecordFromResultSet(ResultSet rs) throws SQLException {
        return new FinancialRecord(
            rs.getInt("recordid"),
            rs.getInt("employeeid"),
            rs.getDate("recorddate").toLocalDate(),
            rs.getString("description"),
            rs.getDouble("amount"),
            rs.getString("recordtype")
        );
    }
}
