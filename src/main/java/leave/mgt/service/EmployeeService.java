package leave.mgt.service;

import leave.mgt.model.Employee;
import leave.mgt.model.Users;

import java.util.List;

public interface EmployeeService {
    Employee registerEmployee(Employee employee);
    Employee updateEmployee(Employee employee);
    Employee voidEmployee(Employee employee);
    List<Employee> AllEmployee();
    Employee searchEmployeeById(Employee employee);

    Employee searchEmployeeByUser(Users theUser);
}
