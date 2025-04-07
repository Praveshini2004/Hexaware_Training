/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package payrollmanagementsystem;

/**
 *
 * @author DELL
 */
import Database.EmployeeDao;
import Database.PayrollDao;
import Database.TaxDao;
import Database.FinancialRecordDAO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import services.TaxService;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    private static final EmployeeDao employeeDAO = new EmployeeDao();
    private static final PayrollDao payrollDAO = new PayrollDao();
    private static final TaxDao taxDao = new TaxDao();
    private static final FinancialRecordDAO financialRecordDAO = new FinancialRecordDAO();
    private static final TaxService taxService = new TaxService();
    

    public static void main(String[] args) throws SQLException {
        mainmodule();
    }

    private static void mainmodule() throws SQLException {
        while (true) {
            System.out.println("\n========== Payroll Management System ==========");
            System.out.println("1. Employee Operations");
            System.out.println("2. Payroll Operations");
            System.out.println("3. Tax Operations");
            System.out.println("4. Financial Record Operations");
            System.out.println("5.Report generation");
            System.out.println("6. Exit");
            System.out.print("Choose a module: ");

            int module = Integer.parseInt(scanner.nextLine());

            switch (module) {
                case 1:
                    employeeMenu();
                    break;
                case 2:
                    payrollMenu();
                    break;
                case 3:
                    taxMenu();
                    break;
                case 4:
                    financialRecordMenu();
                    break;
                case 5:
                    generateFullReport();
                    break;
                case 6:
                    System.out.println("Exiting the system...");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    // ==================== Employee Menu ====================
    private static void employeeMenu() throws SQLException {
        while (true) {
            System.out.println("\n=== Employee Management ===");
            System.out.println("1. Add Employee");
            System.out.println("2. View All Employees");
            System.out.println("3. View Employee By ID");
            System.out.println("4. Update Employee");
            System.out.println("5. Delete Employee");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    Employee newEmp = new Employee();
                    System.out.print("Employee ID: ");
                    newEmp.setEmployeeId(Integer.parseInt(scanner.nextLine()));
                    System.out.print("First Name: ");
                    newEmp.setFirstName(scanner.nextLine());
                    System.out.print("Last Name: ");
                    newEmp.setLastName(scanner.nextLine());
                    System.out.print("Date of Birth (yyyy-mm-dd): ");
                    newEmp.setDateOfBirth(LocalDate.parse(scanner.nextLine()));
                    System.out.print("Gender: ");
                    newEmp.setGender(scanner.nextLine());
                    System.out.print("Email: ");
                    newEmp.setEmail(scanner.nextLine());
                    System.out.print("Phone Number: ");
                    newEmp.setPhoneNumber(scanner.nextLine());
                    System.out.print("Address: ");
                    newEmp.setAddress(scanner.nextLine());
                    System.out.print("Position: ");
                    newEmp.setPosition(scanner.nextLine());
                    System.out.print("Joining Date (yyyy-mm-dd): ");
                    newEmp.setJoiningDate(LocalDate.parse(scanner.nextLine()));
                    System.out.print("Termination Date (yyyy-mm-dd or leave blank): ");
                    String termInput = scanner.nextLine();
                    newEmp.setTerminationDate(termInput.isEmpty() ? null : LocalDate.parse(termInput));
                    employeeDAO.insertEmployee(newEmp);
                    System.out.println("Employee added successfully.");
                    break;

                case 2:
                    List<Employee> allEmployees = employeeDAO.getAllEmployees();
                    for (Employee emp : allEmployees) {
                        System.out.println(emp);
                    }
                    break;

                case 3:
                    System.out.print("Enter Employee ID: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    Employee emp = employeeDAO.getEmployeeById(id);
                    if (emp != null) {
                        System.out.println(emp);
                    } else {
                        System.out.println("Employee not found.");
                    }
                    break;

                case 4:
                    Employee updateEmp = new Employee();
                    System.out.print("Enter Employee ID to update: ");
                    updateEmp.setEmployeeId(Integer.parseInt(scanner.nextLine()));
                    System.out.print("First Name: ");
                    updateEmp.setFirstName(scanner.nextLine());
                    System.out.print("Last Name: ");
                    updateEmp.setLastName(scanner.nextLine());
                    System.out.print("Date of Birth (yyyy-mm-dd): ");
                    updateEmp.setDateOfBirth(LocalDate.parse(scanner.nextLine()));
                    System.out.print("Gender: ");
                    updateEmp.setGender(scanner.nextLine());
                    System.out.print("Email: ");
                    updateEmp.setEmail(scanner.nextLine());
                    System.out.print("Phone Number: ");
                    updateEmp.setPhoneNumber(scanner.nextLine());
                    System.out.print("Address: ");
                    updateEmp.setAddress(scanner.nextLine());
                    System.out.print("Position: ");
                    updateEmp.setPosition(scanner.nextLine());
                    System.out.print("Joining Date (yyyy-mm-dd): ");
                    updateEmp.setJoiningDate(LocalDate.parse(scanner.nextLine()));
                    System.out.print("Termination Date (yyyy-mm-dd or leave blank): ");
                    String terminationInput = scanner.nextLine();
                    updateEmp.setTerminationDate(terminationInput.isEmpty() ? null : LocalDate.parse(terminationInput));
                    employeeDAO.updateEmployee(updateEmp);
                    System.out.println("Employee updated successfully.");
                    break;

                case 5:
                    System.out.print("Enter Employee ID to delete: ");
                    int deleteId = Integer.parseInt(scanner.nextLine());
                    employeeDAO.deleteEmployee(deleteId);
                    System.out.println("Employee deleted successfully.");
                    break;

                case 6:
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    // ==================== Payroll Menu ====================
    private static void payrollMenu() {
        while (true) {
            System.out.println("\n--- Payroll Operations ---");
            System.out.println("1. Add Payroll");
            System.out.println("2. View Payroll by ID");
            System.out.println("3. Update Payroll");
            System.out.println("4. Delete Payroll");
            System.out.println("5. List All Payrolls");
            System.out.println("6. Back");
            System.out.print("Choose: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    System.out.print("Employee ID: ");
                    int empId = Integer.parseInt(scanner.nextLine());
                    System.out.print("Start Date (yyyy-mm-dd): ");
                    LocalDate start = LocalDate.parse(scanner.nextLine());
                    System.out.print("End Date (yyyy-mm-dd): ");
                    LocalDate end = LocalDate.parse(scanner.nextLine());
                    System.out.print("Basic Salary: ");
                    double basic = Double.parseDouble(scanner.nextLine());
                    System.out.print("Overtime Pay: ");
                    double overtime = Double.parseDouble(scanner.nextLine());
                    System.out.print("Deductions: ");
                    double deductions = Double.parseDouble(scanner.nextLine());
                    double net = basic + overtime - deductions;

                    Payroll payroll = new Payroll(0, empId, start, end, basic, overtime, deductions, net);
                    payrollDAO.addPayroll(payroll);
                    System.out.println("Payroll added.");
                    break;

                case 2:
                    System.out.print("Enter Payroll ID: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    Payroll p = payrollDAO.getPayrollById(id);
                    if (p != null) {
                        System.out.println("Payroll ID: " + p.getPayrollId() + ", Net Salary: " + p.getNetSalary());
                    } else System.out.println("Not found!");
                    break;

                case 3:
                    System.out.print("Payroll ID to update: ");
                    int pid = Integer.parseInt(scanner.nextLine());
                    Payroll existing = payrollDAO.getPayrollById(pid);
                    if (existing == null) {
                        System.out.println("Not found!");
                        break;
                    }

                    System.out.print("Employee ID: ");
                    int empId2 = Integer.parseInt(scanner.nextLine());
                    System.out.print("Start Date (yyyy-mm-dd): ");
                    LocalDate start2 = LocalDate.parse(scanner.nextLine());
                    System.out.print("End Date (yyyy-mm-dd): ");
                    LocalDate end2 = LocalDate.parse(scanner.nextLine());
                    System.out.print("Basic Salary: ");
                    double basic2 = Double.parseDouble(scanner.nextLine());
                    System.out.print("Overtime Pay: ");
                    double overtime2 = Double.parseDouble(scanner.nextLine());
                    System.out.print("Deductions: ");
                    double deductions2 = Double.parseDouble(scanner.nextLine());
                    double net2 = basic2 + overtime2 - deductions2;

                    Payroll updated = new Payroll(pid, empId2, start2, end2, basic2, overtime2, deductions2, net2);
                    payrollDAO.updatePayroll(updated);
                    System.out.println("Updated!");
                    break;

                case 4:
                    System.out.print("Enter Payroll ID: ");
                    int delId = Integer.parseInt(scanner.nextLine());
                    payrollDAO.deletePayroll(delId);
                    System.out.println("Deleted!");
                    break;

                case 5:
                    List<Payroll> all = payrollDAO.getAllPayrolls();
                    for (Payroll pay : all) {
                        System.out.println("ID: " + pay.getPayrollId() + ", Net Salary: " + pay.getNetSalary());
                    }
                    break;

                case 6:
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // ==================== Tax Menu ====================
    private static void taxMenu() {
        while (true) {
            System.out.println("\n=== Tax Management System ===");
            System.out.println("1. Add Tax Record");
            System.out.println("2. View Tax by ID");
            System.out.println("3. View All Taxes");
            System.out.println("4. Update Tax");
            System.out.println("5. Delete Tax");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    System.out.print("Enter Employee ID: ");
                    int empId = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter Tax Year: ");
                    int year = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter Taxable Income: ");
                    double income = Double.parseDouble(scanner.nextLine());

                    double amount = taxService.calculateTax(income);
                    Tax newTax = new Tax(0, empId, year, income, amount);
                    taxDao.addTax(newTax);
                    System.out.println("Tax record added successfully.");
                    break;

                case 2:
                    System.out.print("Enter Tax ID: ");
                    int taxId = Integer.parseInt(scanner.nextLine());
                    Tax tax = taxDao.getTaxById(taxId);
                    if (tax != null) {
                        System.out.println("Tax ID: " + tax.getTaxId());
                        System.out.println("Employee ID: " + tax.getEmployeeId());
                        System.out.println("Tax Year: " + tax.getTaxYear());
                        System.out.println("Taxable Income: " + tax.getTaxableIncome());
                        System.out.println("Tax Amount: " + tax.getTaxAmount());
                    } else {
                        System.out.println("Tax record not found.");
                    }
                    break;

                case 3:
                    List<Tax> taxes = taxDao.getAllTaxes();
                    for (Tax t : taxes) {
                        System.out.println("---------------");
                        System.out.println("Tax ID: " + t.getTaxId());
                        System.out.println("Employee ID: " + t.getEmployeeId());
                        System.out.println("Tax Year: " + t.getTaxYear());
                        System.out.println("Taxable Income: " + t.getTaxableIncome());
                        System.out.println("Tax Amount: " + t.getTaxAmount());
                    }
                    break;

                case 4:
                    System.out.print("Enter Tax ID to update: ");
                    int updateId = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter Employee ID: ");
                    int eId = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter Tax Year: ");
                    int tYear = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter Taxable Income: ");
                    double tIncome = Double.parseDouble(scanner.nextLine());

                    double newTaxAmount = taxService.calculateTax(tIncome);
                    Tax updatedTax = new Tax(updateId, eId, tYear, tIncome, newTaxAmount);
                    taxDao.updateTax(updatedTax);
                    System.out.println("Tax updated.");
                    break;

                case 5:
                    System.out.print("Enter Tax ID to delete: ");
                    int delTaxId = Integer.parseInt(scanner.nextLine());
                    taxDao.deleteTax(delTaxId);
                    System.out.println("Tax deleted.");
                    break;

                case 6:
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // Placeholder: implement if needed
     private static void financialRecordMenu() {
        while (true) {
            System.out.println("\n--- Financial Record Operations ---");
            System.out.println("1. Add Record");
            System.out.println("2. View by ID");
            System.out.println("3. Update Record");
            System.out.println("4. Delete Record");
            System.out.println("5. List All");
            System.out.println("6. Back");
            System.out.print("Choose: ");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1 : {
                    System.out.print("Employee ID: ");
                    int empId = Integer.parseInt(scanner.nextLine());
                    System.out.print("Date (yyyy-mm-dd): ");
                    LocalDate date = LocalDate.parse(scanner.nextLine());
                    System.out.print("Description: ");
                    String desc = scanner.nextLine();
                    System.out.print("Amount: ");
                    double amt = Double.parseDouble(scanner.nextLine());
                    System.out.print("Type: ");
                    String type = scanner.nextLine();

                    FinancialRecord fr = new FinancialRecord(0, empId, date, desc, amt, type);
                    financialRecordDAO.addFinancialRecord(fr);
                    System.out.println("Record added!");
                }
                break;
                case 2 : {
                    System.out.print("Record ID: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    FinancialRecord fr = financialRecordDAO.getFinancialRecordById(id);
                    if (fr != null) {
                        System.out.println("ID: " + fr.getRecordId() + ", Amount: " + fr.getAmount());
                    } else System.out.println("Not found!");
                }
                break;
                case 3 : {
                    System.out.print("Record ID to update: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    FinancialRecord existing = financialRecordDAO.getFinancialRecordById(id);
                    if (existing == null) {
                        System.out.println("Not found!");
                        return;
                    }
                    System.out.print("Employee ID: ");
                    int empId = Integer.parseInt(scanner.nextLine());
                    System.out.print("Date (yyyy-mm-dd): ");
                    LocalDate date = LocalDate.parse(scanner.nextLine());
                    System.out.print("Description: ");
                    String desc = scanner.nextLine();
                    System.out.print("Amount: ");
                    double amt = Double.parseDouble(scanner.nextLine());
                    System.out.print("Type: ");
                    String type = scanner.nextLine();

                    FinancialRecord updated = new FinancialRecord(id, empId, date, desc, amt, type);
                    financialRecordDAO.updateFinancialRecord(updated);
                    System.out.println("Updated!");
                }
                break;
                case 4 : {
                    System.out.print("Record ID: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    financialRecordDAO.deleteFinancialRecord(id);
                    System.out.println("Deleted!");
                }
                break;
                case 5 : {
                    List<FinancialRecord> all = financialRecordDAO.getAllFinancialRecords();
                    for (FinancialRecord fr : all) {
                        System.out.println("ID: " + fr.getRecordId() + ", Amount: " + fr.getAmount());
                    }
                }
                break;
                case 6 : { return; }
                default : System.out.println("Invalid choice.");
            }
        }
    }
     private static void generateFullReport() {
        System.out.println("\n========== Generating Full Report ==========");

        System.out.println("\n--- All Employees ---");
        List<Employee> employees = employeeDAO.getAllEmployees();
        if (employees.isEmpty()) {
            System.out.println("No employee records found.");
        } else {
            for (Employee emp : employees) {
                System.out.println(emp);
            }
        }

        System.out.println("\n--- All Payroll Records ---");
        List<Payroll> payrolls = payrollDAO.getAllPayrolls();
        if (payrolls.isEmpty()) {
            System.out.println("No payroll records found.");
        } else {
            for (Payroll p : payrolls) {
                System.out.println("Payroll ID: " + p.getPayrollId() +
                                   ", Employee ID: " + p.getEmployeeId() +
                                   ", Start: " + p.getPayPeriodStartDate() +
                                   ", End: " + p.getPayPeriodEndDate() +
                                   ", Basic: " + p.getBasicSalary() +
                                   ", Overtime: " + p.getOvertimePay() +
                                   ", Deductions: " + p.getDeductions() +
                                   ", Net Salary: " + p.getNetSalary());
            }
        }

        System.out.println("\n--- All Tax Records ---");
        List<Tax> taxes = taxDao.getAllTaxes();
        if (taxes.isEmpty()) {
            System.out.println("No tax records found.");
        } else {
            for (Tax t : taxes) {
                System.out.println("Tax ID: " + t.getTaxId() +
                                   ", Employee ID: " + t.getEmployeeId() +
                                   ", Year: " + t.getTaxYear() +
                                   ", Taxable Income: " + t.getTaxableIncome() +
                                   ", Tax Amount: " + t.getTaxAmount());
            }
        }

        System.out.println("\n--- All Financial Records ---");
        List<FinancialRecord> records = financialRecordDAO.getAllFinancialRecords();
        if (records.isEmpty()) {
            System.out.println("No financial records found.");
        } else {
            for (FinancialRecord record : records) {
                System.out.println("Record ID: " + record.getRecordId() +
                                   ", Type: " + record.getRecordType() +
                                   ", Amount: " + record.getAmount() +
                                   ", Description: " + record.getDescription() +
                                   ", Date: " + record.getDate());
            }
        }

        System.out.println("========== Report Generation Complete ==========");
    }
}

