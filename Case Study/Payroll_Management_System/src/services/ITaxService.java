package services;
import Exceptions.TaxCalculationException;
import java.util.*;
import payrollmanagementsystem.Tax;


public interface ITaxService {
    double calculateTax(double taxableIncome) throws TaxCalculationException;
    Tax getTaxById(int taxId);
    List<Tax> getTaxesForEmployee(int employeeId);
    List<Tax> getTaxesForYear(int taxYear);
}