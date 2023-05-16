package leave.mgt.controller;

import leave.mgt.model.LeaveType;
import leave.mgt.service.implementations.LeaveTypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LeaveTypeController {
    @Autowired private LeaveTypeServiceImpl leaveTypeService;

    @GetMapping("/leave_type_list")
    public String leaveTypeList(Model model){
        model.addAttribute("leaveList",leaveTypeService.AllLeaveType());
        return "allLeavePage";
    }
    @GetMapping("/add_leave_type")
    public String newLeaveTypeForm(Model model){
        model.addAttribute("leave_type",new LeaveType());
        return "newLeaveTypeForm";
    }
    @PostMapping("/save_leave_type")
    public String saveLeaveType(@ModelAttribute("leave_type")LeaveType theLeaveType){
        try{
            if(theLeaveType != null){
                LeaveType leaveType = leaveTypeService.recordType(theLeaveType);
                if(leaveType != null){
                    return "redirect:/leave_type_list";
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return "redirect:/add_leave_type";
    }

    @PostMapping("/update_leave_type_form")
    public String updateLeaveTypeForm(Model model, @RequestParam("id")String id){
        try{
            if(id != null){
                LeaveType theLeaveType = leaveTypeService.searchLeaveTypeById(id);
                if(theLeaveType != null) {
                    model.addAttribute("leave_type", theLeaveType);
                    return "updateLeaveTypeForm";
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return "redirect:/leave_type_list";
    }
    @PostMapping("/update_leave_type")
    public String updateLeaveType(@ModelAttribute("leave_type")LeaveType theLeaveType){
        try{
            if(theLeaveType != null){
                LeaveType leaveType = leaveTypeService.updateType(theLeaveType);
                if(leaveType != null){
                    return "redirect:/leave_type_list";
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return "redirect:/update_leave_type_form";
    }
    @PostMapping("/delete_leave_type_form")
    public String deleteLeaveTypeForm(@RequestParam("id")String id){
        try{
            if(id != null){
                LeaveType theLeaveType = leaveTypeService.searchLeaveTypeById(id);
                if(theLeaveType != null) {
                    LeaveType leaveType = leaveTypeService.deleteType(theLeaveType);
                    if(leaveType != null){
                        return "redirect:/leave_type_list";
                    }
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return "redirect:/leave_type_list";
    }
}
