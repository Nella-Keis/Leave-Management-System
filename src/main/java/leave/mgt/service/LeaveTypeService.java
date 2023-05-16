package leave.mgt.service;

import leave.mgt.model.LeaveType;

import java.util.List;

public interface LeaveTypeService {
    LeaveType recordType(LeaveType leaveType);
    LeaveType updateType(LeaveType leaveType);
    LeaveType deleteType(LeaveType leaveType);
    List<LeaveType> AllLeaveType();
    LeaveType searchLeaveTypeById(String id);
}