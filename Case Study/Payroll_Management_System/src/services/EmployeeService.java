package services;
import Exceptions.EmployeeNotFoundException;
import java.util.*;
import payrollmanagementsystem.Employee;

public class EmployeeService implements IEmployeeService {
    private List<Employee> employees = new ArrayList<>();

  
    @Override
   public Employee getEmployeeById(int id) throws EmployeeNotFoundException {
    for (Employee emp : employees) {
        if (emp.getEmployeeId() == id) {
            return emp;
        }
    }
    throw new EmployeeNotFoundException("Employee with ID " + id + " not found.");
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