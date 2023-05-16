package leave.mgt.service.implementations;

import leave.mgt.model.Leave;
import leave.mgt.repository.LeaveRepository;
import leave.mgt.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LeaveServiceImpl implements LeaveService {
    @Autowired private LeaveRepository repo;

    @Override
    public Leave requestLeave(Leave leave) {
        leave.setStatus("Pending");
        return repo.save(leave);
    }

    @Override
    public Leave updateLeave(Leave leave) {
        return repo.save(leave);
    }

    @Override
    public Leave voidLeave(Leave leave) {
        return repo.save(leave);
    }

    @Override
    public List<Leave> allLeave() {
        return repo.findAll();
    }

    @Override
    public Leave searchRequestedLeaveById(Leave leave) {
        return repo.findById(leave.getId()).get();
    }
}
