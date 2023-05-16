package leave.mgt.service.implementations;

import leave.mgt.model.Employee;
import leave.mgt.repository.EmployeeRepository;
import leave.mgt.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired private EmployeeRepository repo;

    @Override
    public Employee registerEmployee(Employee employee) {
        employee.setActive(true);
        return repo.save(employee);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        Employee theEmployee = searchEmployeeById(employee);
        if(theEmployee != null){
            theEmployee.setStartedDate(employee.getStartedDate());
            return repo.save(theEmployee);
        }
        return registerEmployee(employee);
    }

    @Override
    public Employee voidEmployee(Employee employee) {
        employee.setActive(false);
        return repo.save(employee);
    }

    @Override
    public List<Employee> AllEmployee() {
        return repo.findAll();
    }

    @Override
    public Employee searchEmployeeById(Employee employee) {
        return repo.findById(employee.getId()).get();
    }
}
