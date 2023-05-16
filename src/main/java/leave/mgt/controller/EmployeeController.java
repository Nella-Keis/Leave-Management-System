package leave.mgt.controller;

import leave.mgt.model.Employee;
import leave.mgt.model.Leave;
import leave.mgt.model.Role;
import leave.mgt.model.Users;
import leave.mgt.service.implementations.EmployeeServiceImpl;
import leave.mgt.service.implementations.LeaveServiceImpl;
import leave.mgt.service.implementations.RoleServiceImpl;
import leave.mgt.service.implementations.UsersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class EmployeeController {
    /**
     *
     */
    @Autowired private EmployeeServiceImpl employeeService;
    @Autowired private UsersServiceImpl usersService;
    @Autowired private RoleServiceImpl roleService;
    @Autowired private LeaveServiceImpl leaveService;

    @GetMapping("/home")
    public String homePage(Model model){
        List<Employee> employeeList = employeeService.AllEmployee();
        List<Employee> employees = new ArrayList<>();
        for(Employee emp : employeeList){
            if(emp.isActive())
                employees.add(emp);
        }
        model.addAttribute("employees",employees);
        return "index";
    }
    @GetMapping("/all_employee")
    public String allEmployeeList(Model model){
        List<Employee> employeeList = employeeService.AllEmployee();
        List<Employee> employees = new ArrayList<>();
        for(Employee emp : employeeList){
            if(emp.isActive())
                employees.add(emp);
        }
        model.addAttribute("employees",employees);
        return "index";
    }

    @GetMapping("/employeeProfile")
    public String employeeProfile(Model model){
        Employee emp = new Employee(1);
        Employee theEmployee = employeeService.searchEmployeeById(emp);

        if(theEmployee != null && theEmployee.isActive()){
            List<Leave> leaves = leaveService.allLeave();
            List<Leave> empLeaves = new ArrayList<>();
            Set<Employee> employeeSet = new HashSet<>();
            employeeSet.add(theEmployee);
            for(Leave leave : leaves){
                if(leave.getEmployees().equals(employeeSet))
                    empLeaves.add(leave);
//                Set<Employee> employees = leave.getEmployees();
//                for(Employee employee : employees){
//                    if(employee.getId() == theEmployee.getId()){
//
//                    }
//
//                }
            }
            model.addAttribute("leave", new Leave());
            model.addAttribute("employee",theEmployee);
            model.addAttribute("leaves",empLeaves);
        }
        return "employee_profile";
    }

    @GetMapping("/new_employee")
    public String newEmployeeForm(Model model){
        model.addAttribute("employee" , new Users());
        return "new-employee";
    }
    @PostMapping("/new_employee")
    public String saveEmployee(@ModelAttribute("employee") Users theUser){
        if(theUser != null){
            Role theRole = roleService.searchRoleByName("EMPLOYEE");
            if(theRole != null){
                Set<Role> roles = new HashSet<>();
                roles.add(theRole);
                theUser.setRoles(roles);
            }

            theUser.setActive(true);
            theUser.setRegisteredDate(new Date());
            Users user = usersService.registerUser(theUser);
            Employee theEmployee = new Employee();
            theEmployee.setActive(true);
            theEmployee.setStartedDate(user.getRegisteredDate());
            theEmployee.setUser(user);
            Employee emp = employeeService.registerEmployee(theEmployee);
            if(emp != null){
                return "redirect:/all_employee";
            }
        }
        return "redirect:/new_employee";
    }
    @PostMapping("/update_employee_form")
    public String updateEmployeeForm(Model model , @RequestParam("emp_id") String id){
        if(id != null){
            Employee employee = new Employee(Integer.parseInt(id));
            Employee theEmployee = employeeService.searchEmployeeById(employee);
            model.addAttribute("employee" , theEmployee);
        }
        return "update-employee_v1";
    }
    @PostMapping("/update_employee")
    public String updateEmployee(@ModelAttribute("employee") Employee theEmployee){
        if(theEmployee != null){
            Employee emp = employeeService.updateEmployee(theEmployee);
            if(emp != null){
                return "redirect:/all_employee";
            }
        }
        return "redirect:/update_employee";
    }
    @PostMapping("/delete_employee")
    public String deleteEmployee(@ModelAttribute("emp_id") String id){
        if(id != null){
            Employee theEmployee = employeeService.searchEmployeeById(new Employee(Integer.parseInt(id)));
            if(theEmployee != null){
                Employee employee = employeeService.voidEmployee(theEmployee);
                if(employee != null)
                    return "redirect:/all_employee";
            }
        }
        return "redirect:/all_employee";
    }
}
