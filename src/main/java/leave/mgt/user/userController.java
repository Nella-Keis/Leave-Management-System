package leave.mgt.user;

import leave.mgt.model.Employee;
import leave.mgt.model.Role;
import leave.mgt.model.Users;
import leave.mgt.service.implementations.EmployeeServiceImpl;
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

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Controller
public class userController {
    @Autowired
    private UsersServiceImpl userService;
    @Autowired
    private RoleServiceImpl roleService;
    @Autowired
    private EmployeeServiceImpl employeeService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @GetMapping("/user/register")
    public String registrationForm(Model model){
        model.addAttribute("user",new Users());
        return "register";
    }
    @PostMapping("/user/register")
    public String saveHr(@ModelAttribute("user") Users user){
        try{
            if(user != null){
                Role role = new Role();
                role.setName("HR");
                Role theRole = roleService.searchRoleByName(role.getName());

                if(theRole != null){
                    Set<Role> roles = new HashSet<>();
                    roles.add(theRole);
                    user.setRoles(roles);
                }
                user.setRegisteredDate(new Date());
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                Users theUser = userService.registerUser(user);
                Employee theEmployee = new Employee();
                if(theUser != null){
                    theEmployee.setActive(true);
                    theEmployee.setStartedDate(theUser.getRegisteredDate());
                    theEmployee.setUser(theUser);
                    employeeService.registerEmployee(theEmployee);
                    return "redirect:/login";
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return "register";
    }
    @GetMapping("/login")
    public String viewLoginPage(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return "login";
        }else {
        	return "redirect:/all_employee";
        }
    }
}
