package leave.mgt.controller;

import leave.mgt.model.Employee;
import leave.mgt.model.Leave;
import leave.mgt.model.Users;
import leave.mgt.security.UserCustomDetails;
import leave.mgt.service.implementations.EmployeeServiceImpl;
import leave.mgt.service.implementations.LeaveServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Controller
public class LeaveController {
    @Autowired private LeaveServiceImpl leaveService;
    @Autowired private EmployeeServiceImpl employeeService;
    @GetMapping("/all-employee-leave")
    public String allLeavesInformation(Model model){
//        Employee emp = new Employee(3);
//        Employee theEmployee = employeeService.searchEmployeeById(emp);
        model.addAttribute("leavesData",leaveService.allLeave());
//        model.addAttribute("emp",theEmployee);
        return "index2";
    }

    @GetMapping("/request_leave")
    public String requestLeaveForm(Model model){
        model.addAttribute("leave", new Leave());
        return "newLeaveRequestForm";
    }

    @PostMapping("/save_request_leave")
    public String saveLeaveRequest(@ModelAttribute("leave") Leave leave, @RequestParam("emp_id") String empId){
        if(leave != null && empId != null){
            Employee emp = new Employee(Integer.parseInt(empId));
            Employee theEmployee = employeeService.searchEmployeeById(emp);
            Set<Employee> employeeSet = new HashSet<>();
            if(theEmployee != null){
                employeeSet.add(theEmployee);
            }
            leave.setStatus("initialized");
            leave.setRequestedDate(new Date());
            leave.setEmployees(employeeSet);
            Leave theLeave = leaveService.requestLeave(leave);
            /**
             * update to have id of employee requested
             */
            if(theLeave != null){
                return "redirect:/employeeProfile";
            }
        }
        return "redirect:/employeeProfile";
    }
    @PostMapping("/approve_request_leave")
    public String approveLeaveRequest(@RequestParam("leave_id") String leaveId){
        if(leaveId != null){
            Leave theLeave = leaveService.searchRequestedLeaveById(new Leave(Integer.parseInt(leaveId)));
            theLeave.setStatus("approved");
            Employee theEmployee = new Employee();
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (!(authentication instanceof AnonymousAuthenticationToken)) {
                UserCustomDetails userDetails = (UserCustomDetails) authentication.getPrincipal();
                Users theUser = new Users(userDetails.getUser().getId());
                theEmployee = employeeService.searchEmployeeByUser(theUser);
            }

//            Employee employee = employeeService.searchEmployeeById(new Employee(Integer.parseInt(empId)));
            if(theEmployee != null)
                theLeave.setApproverId(theEmployee);
            Leave leave = leaveService.updateLeave(theLeave);
            /**
             * update to have id of employee requested
             */
            if(leave != null){
                return "redirect:/all-employee-leave";
            }
        }
        return "redirect:/request_leave";
    }
    @PostMapping("/reject_request_leave")
    public String rejectLeaveRequest(@RequestParam("leave_id") String leaveId){
        if(leaveId != null){
            Leave theLeave = leaveService.searchRequestedLeaveById(new Leave(Integer.parseInt(leaveId)));
            theLeave.setStatus("Rejected");
            Employee theEmployee = new Employee();
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (!(authentication instanceof AnonymousAuthenticationToken)) {
                UserCustomDetails userDetails = (UserCustomDetails) authentication.getPrincipal();
                Users theUser = new Users(userDetails.getUser().getId());
                theEmployee = employeeService.searchEmployeeByUser(theUser);
            }
            if(theEmployee != null)
                theLeave.setApproverId(theEmployee);
            Leave leave = leaveService.updateLeave(theLeave);
            /**
             * update to have id of employee requested
             */
            if(leave != null){
                return "redirect:/all-employee-leave";
            }
        }
        return "redirect:/request_leave";
    }
}
