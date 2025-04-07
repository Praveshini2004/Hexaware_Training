/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

/**
 *
 * @author DELL
 */import payrollmanagementsystem.Payroll;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class PayrollDao {
    private final String URL = "jdbc:mysql://localhost:3306/payxpert";
    private final String USER = "root";
    private final String PASS = "Praveshini";
private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    // 1. INSERT
    public void addPayroll(Payroll payroll) {
        String sql = "INSERT INTO payroll (employeeid, payperiodstartdate, payperiodenddate, basicsalary, overtimepay, deductions, netsalary) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, payroll.getEmployeeId());
            stmt.setDate(2, Date.valueOf(payroll.getPayPeriodStartDate()));
            stmt.setDate(3, Date.valueOf(payroll.getPayPeriodEndDate()));
            stmt.setDouble(4, payroll.getBasicSalary());
            stmt.setDouble(5, payroll.getOvertimePay());
            stmt.setDouble(6, payroll.getDeductions());
            stmt.setDouble(7, payroll.getNetSalary());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 2. READ by ID
    public Payroll getPayrollById(int payrollId) {
        Payroll payroll = null;
        String sql = "SELECT * FROM payroll WHERE payrollid = ?";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, payrollId);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                payroll = extractPayrollFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payroll;
    }

    // 3. READ ALL
    public List<Payroll> getAllPayrolls() {
        List<Payroll> payrolls = new ArrayList<>();
        String sql = "SELECT * FROM payroll";

        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Payroll payroll = extractPayrollFromResultSet(rs);
                payrolls.add(payroll);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payrolls;
    }

    // 4. UPDATE
    public void updatePayroll(Payroll payroll) {
        String sql = "UPDATE payroll SET employeeid = ?, payperiodstartdate = ?, payperiodenddate = ?, basicsalary = ?, overtimepay = ?, deductions = ?, netsalary = ? WHERE payrollid = ?";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, payroll.getEmployeeId());
            stmt.setDate(2, Date.valueOf(payroll.getPayPeriodStartDate()));
            stmt.setDate(3, Date.valueOf(payroll.getPayPeriodEndDate()));
            stmt.setDouble(4, payroll.getBasicSalary());
            stmt.setDouble(5, payroll.getOvertimePay());
            stmt.setDouble(6, payroll.getDeductions());
            stmt.setDouble(7, payroll.getNetSalary());
            stmt.setInt(8, payroll.getPayrollId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 5. DELETE
    public void deletePayroll(int payrollId) {
        String sql = "DELETE FROM payroll WHERE payrollid = ?";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, payrollId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Helper to map ResultSet to Payroll object
    private Payroll extractPayrollFromResultSet(ResultSet rs) throws SQLException {
        return new Payroll(
                rs.getInt("payrollid"),
                rs.getInt("employeeid"),
                rs.getDate("payperiodstartdate").toLocalDate(),
                rs.getDate("payperiodenddate").toLocalDate(),
                rs.getDouble("basicsalary"),
                rs.getDouble("overtimepay"),
                rs.getDouble("deductions"),
                rs.getDouble("netsalary")
        );
    }
}
