package leave.mgt.service.implementations;

import leave.mgt.model.Role;
import leave.mgt.repository.RoleRepository;
import leave.mgt.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired private RoleRepository repo;

    @Override
    public Role registerRole(Role role) {
        return repo.save(role);
    }

    @Override
    public Role updateRole(Role role) {
        return repo.save(role);
    }

    @Override
    public Role deleteRole(Role role) {
        return repo.save(role);
    }

    @Override
    public List<Role> allRoles() {
        return repo.findAll();
    }

    @Override
    public Role searchRoleByName(String role) {
        return repo.findByName(role);
    }
}
