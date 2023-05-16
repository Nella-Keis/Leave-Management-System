package leave.mgt.service;

import leave.mgt.model.Role;

import java.util.List;

public interface RoleService {
    Role registerRole(Role role);
    Role updateRole(Role role);
    Role deleteRole(Role role);
    List<Role> allRoles();
    Role searchRoleByName(String role);
}
