package UnitTesting;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class EmployeeSalaryCalculatorTest {

    static EmployeeSalaryCalculator calculator;

    @BeforeAll
    public static void setup() {
        System.out.println("Object creation");
        calculator = new EmployeeSalaryCalculator();
    }

    @Test
    public void testCalculateGrossSalaryForEmployee() {
        double result = calculator.calculateGrossSalary(30000, 10000, 5000);
        assertEquals(45000.0, result, 0.001);
    }

    @Test
    public void testCalculateNetSalaryAfterDeductions() {
        double grossSalary = 60000;
        double tax = 8000;
        double insurance = 2000;
        double result = calculator.calculateNetSalary(grossSalary, tax, insurance);
        assertEquals(50000.0, result, 0.001);
    }

    @Test
    public void testVerifyTaxCalculationForHighIncomeEmployee() {
        double grossSalary = 150000;
        double expectedTax = 45000.0;
        double result = calculator.calculateTax(grossSalary);
        assertEquals(expectedTax, result, 0.001);
    }

    @AfterAll
    public static void destroy() {
        System.out.println("Completed");
        calculator = null;
    }
}
