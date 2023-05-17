package leave.mgt.controller;

import leave.mgt.model.Employee;
import leave.mgt.model.Leave;
import leave.mgt.model.Role;
import leave.mgt.model.Users;
import leave.mgt.security.UserCustomDetails;
import leave.mgt.service.implementations.EmployeeServiceImpl;
import leave.mgt.service.implementations.LeaveServiceImpl;
import leave.mgt.service.implementations.RoleServiceImpl;
import leave.mgt.service.implementations.UsersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    @Autowired
    private PasswordEncoder passwordEncoder;

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
        Employee theEmployee = new Employee();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            UserCustomDetails userDetails = (UserCustomDetails) authentication.getPrincipal();
            Users theUser = new Users(userDetails.getUser().getId());
            theEmployee = employeeService.searchEmployeeByUser(theUser);
        }

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
    public String saveEmployee(@ModelAttribute("employee") Users user){
        if(user != null) {
            Role role = new Role();
            role.setName("EMPLOYEE");
            Role theRole = roleService.searchRoleByName(role.getName());

            if (theRole != null) {
                Set<Role> roles = new HashSet<>();
                roles.add(theRole);
                user.setRoles(roles);
            }
            System.out.println("The User Email is: "+user.getEmail());
            user.setRegisteredDate(new Date());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            Users theUser = usersService.registerUser(user);
            Employee theEmployee = new Employee();
            if (theUser != null) {
                theEmployee.setActive(true);
                theEmployee.setStartedDate(theUser.getRegisteredDate());
                theEmployee.setUser(theUser);
                employeeService.registerEmployee(theEmployee);
                return "redirect:/all_employee";
            }
        }
//        if(theUser != null){
//            Role theRole = roleService.searchRoleByName("EMPLOYEE");
//            if(theRole != null){
//                Set<Role> roles = new HashSet<>();
//                roles.add(theRole);
//                theUser.setRoles(roles);
//            }
//
//            theUser.setActive(true);
//            theUser.setRegisteredDate(new Date());
//            theUser.setPassword(passwordEncoder.encode(theUser.getPassword()));
//            Users user = usersService.registerUser(theUser);
//            Employee theEmployee = new Employee();
//            theEmployee.setActive(true);
//            theEmployee.setStartedDate(user.getRegisteredDate());
//            theEmployee.setUser(user);
//            Employee emp = employeeService.registerEmployee(theEmployee);
//            if(emp != null){
//                return "redirect:/all_employee";
//            }
//        }
        return "redirect:/new_employee";
    }
    @PostMapping("/update_employee_form")
    public String updateEmployeeForm(Model model , @RequestParam("emp_id") String id){
        if(id != null){
            Users theUser = usersService.searchUserById(Integer.parseInt(id));
            model.addAttribute("employee" , theUser);
        }
        return "update-employee";
    }
    @PostMapping("/update_employee")
    public String updateEmployee(@ModelAttribute("employee") Users theUser){
        if(theUser != null){
            Users user = usersService.updateUser(theUser);
            if(user != null){
                return "redirect:/all_employee";
            }
        }
        return "redirect:/update_employee";
    }
    @PostMapping("/delete_employee")
    public String deleteEmployee(@ModelAttribute("emp_id") String id){
        if(id != null){
            Users theUser = usersService.searchUserById(Integer.parseInt(id));
            if(theUser != null){
                Employee employee = employeeService.voidEmployee(employeeService.searchEmployeeByUser(theUser));
                if(employee != null)
                    return "redirect:/all_employee";
            }
        }
        return "redirect:/all_employee";
    }
}
