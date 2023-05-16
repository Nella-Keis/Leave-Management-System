package leave.mgt.service.implementations;

import leave.mgt.model.LeaveType;
import leave.mgt.repository.LeaveTypeRepository;
import leave.mgt.service.LeaveTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveTypeServiceImpl implements LeaveTypeService {
    @Autowired private LeaveTypeRepository repo;
    @Override
    public LeaveType recordType(LeaveType leaveType) {
        return repo.save(leaveType);
    }

    @Override
    public LeaveType updateType(LeaveType leaveType) {
        return repo.save(leaveType);
    }

    @Override
    public LeaveType deleteType(LeaveType leaveType) {
        return repo.save(leaveType);
    }

    @Override
    public List<LeaveType> AllLeaveType() {
        return repo.findAll();
    }

    @Override
    public LeaveType searchLeaveTypeById(String id) {
        return repo.findById(Integer.parseInt(id)).get();
    }
}
