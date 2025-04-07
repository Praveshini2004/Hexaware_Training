/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import java.time.LocalDate;
import java.util.*;
import payrollmanagementsystem.Payroll;
/**
 *
 * @author DELL
 */public class PayrollService implements IPayrollService {
    private List<Payroll> payrollList = new ArrayList<>();
    private int nextPayrollId = 1;

    @Override
    public Payroll generatePayroll(int employeeId, LocalDate startDate, LocalDate endDate, double basicSalary, double overtime, double deductions) {
        double netSalary = basicSalary + overtime - deductions;
        Payroll payroll = new Payroll(nextPayrollId++, employeeId, startDate, endDate, basicSalary, overtime, deductions, netSalary);
        payrollList.add(payroll);
        return payroll;
    }

    @Override
    public Payroll getPayrollById(int payrollId) {
        for (Payroll p : payrollList) {
            if (p.getPayrollId() == payrollId) {
                return p;
            }
        }
        return null;
    }

    @Override
    public List<Payroll> getPayrollsForEmployee(int employeeId) {
        List<Payroll> result = new ArrayList<>();
        for (Payroll p : payrollList) {
            if (p.getEmployeeId() == employeeId) {
                result.add(p);
            }
        }
        return result;
    }

    @Override
    public List<Payroll> getPayrollsForPeriod(LocalDate startDate, LocalDate endDate) {
        List<Payroll> result = new ArrayList<>();
        for (Payroll p : payrollList) {
            if (!p.getPayPeriodStartDate().isAfter(endDate) && !p.getPayPeriodEndDate().isBefore(startDate)) {
                result.add(p);
            }
        }
        return result;
    }
}