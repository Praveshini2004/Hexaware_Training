/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import java.util.*;
import payrollmanagementsystem.Employee;
/**
 *
 * @author DELL
 */class EmployeeService implements IEmployeeService {
    private List<Employee> employees = new ArrayList<>();

    @Override
    public Employee getEmployeeById(int id) {
        for (Employee emp : employees) {
            if (emp.getEmployeeId() == id) {
                return emp;
            }
        }
        return null;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employees);
    }

    @Override
    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    @Override
    public void updateEmployee(Employee employee) {
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getEmployeeId() == employee.getEmployeeId()) {
                employees.set(i, employee);
                return;
            }
        }
    }

    @Override
    public void removeEmployee(int id) {
        employees.removeIf(emp -> emp.getEmployeeId() == id);
    }
}