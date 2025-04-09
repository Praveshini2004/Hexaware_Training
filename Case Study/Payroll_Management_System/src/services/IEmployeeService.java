package services;
import Exceptions.EmployeeNotFoundException;
import java.util.*;
import payrollmanagementsystem.Employee;

interface IEmployeeService {
    Employee getEmployeeById(int id) throws EmployeeNotFoundException;;
    List<Employee> getAllEmployees();
    void addEmployee(Employee employee);
    void updateEmployee(Employee employee);
    void removeEmployee(int id);
}
