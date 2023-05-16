package leave.mgt.service;

import leave.mgt.model.Leave;

import java.util.List;

public interface LeaveService {
    Leave requestLeave(Leave leave);
    Leave updateLeave(Leave leave);
    Leave voidLeave(Leave leave);
    List<Leave> allLeave();
    Leave searchRequestedLeaveById(Leave leave);
}
