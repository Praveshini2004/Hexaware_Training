package services;
import Exceptions.InvalidInputException;
import Exceptions.PayrollGenerationException;
import java.time.LocalDate;
import java.util.*;
import payrollmanagementsystem.Payroll;

public class PayrollService implements IPayrollService {
    private List<Payroll> payrollList = new ArrayList<>();
    private int nextPayrollId = 1;

    @Override
   public Payroll generatePayroll(int employeeId, LocalDate startDate, LocalDate endDate,
                                double basicSalary, double overtime, double deductions)
        throws PayrollGenerationException, InvalidInputException {

    // Input validations (InvalidInputException)
    if (employeeId <= 0) {
        throw new InvalidInputException("Employee ID must be a positive integer.");
    }
    if (startDate == null || endDate == null) {
        throw new InvalidInputException("Start date or end date cannot be null.");
    }
    if (startDate.isAfter(endDate)) {
        throw new InvalidInputException("Start date cannot be after end date.");
    }
    if (basicSalary < 0 || overtime < 0 || deductions < 0) {
        throw new InvalidInputException("Salary components cannot be negative.");
    }

    double netSalary = basicSalary + overtime - deductions;

    // Logic-level validation (PayrollGenerationException)
    if (netSalary < 0) {
        throw new PayrollGenerationException("Net salary cannot be negative.");
    }

    // Business logic
    try {
        Payroll payroll = new Payroll(nextPayrollId++, employeeId, startDate, endDate,
                                      basicSalary, overtime, deductions, netSalary);
        payrollList.add(payroll);
        return payroll;
    } catch (Exception e) {
        throw new PayrollGenerationException("Failed to generate payroll: " + e.getMessage());
    }
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