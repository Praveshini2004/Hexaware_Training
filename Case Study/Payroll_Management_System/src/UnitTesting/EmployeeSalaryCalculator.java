package UnitTesting;


public class EmployeeSalaryCalculator {
    public double calculateGrossSalary(double basic, double hra, double da) {
        return basic + hra + da;
    }
    public double calculateNetSalary(double grossSalary, double tax, double insurance) {
        return grossSalary - tax - insurance;
    }
    public double calculateTax(double grossSalary) {
       
        if (grossSalary > 100000) {
            return grossSalary * 0.30;  
        } else if (grossSalary > 50000) {
            return grossSalary * 0.20;
        } else {
            return grossSalary * 0.10;
        }
    }
}
