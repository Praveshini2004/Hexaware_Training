package services;
import Exceptions.InvalidInputException;
import Exceptions.PayrollGenerationException;
import java.time.LocalDate;
import java.util.*;
import payrollmanagementsystem.Payroll;


public interface IPayrollService {
    Payroll generatePayroll(int employeeId, LocalDate startDate, LocalDate endDate,
                            double basicSalary, double overtime, double deductions)
            throws PayrollGenerationException, InvalidInputException;

    Payroll getPayrollById(int payrollId);
    List<Payroll> getPayrollsForEmployee(int employeeId);
    List<Payroll> getPayrollsForPeriod(LocalDate startDate, LocalDate endDate);
}