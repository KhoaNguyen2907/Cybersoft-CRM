package cybersoft.java18.backend.CRM_Project.service;

import cybersoft.java18.backend.CRM_Project.model.RoleModel;
import cybersoft.java18.backend.CRM_Project.repository.IRoleRepository;
import cybersoft.java18.backend.CRM_Project.repository.RoleRepository;

import java.util.List;

public class RoleService implements IRoleService {
    IRoleRepository roleRepository = new RoleRepository();

    @Override
    public List<RoleModel> findAllRole() {
        return roleRepository.findAllRole();
    }

    @Override
    public RoleModel addNewRole(RoleModel roleModel) {
        return roleRepository.saveRole(roleModel);
    }

    @Override
    public RoleModel modifyRole(RoleModel newRole) {
        return roleRepository.updateRole(newRole);
    }

    @Override
    public RoleModel deleteRoleById(int roleId) {
        return roleRepository.deleteRoleById(roleId);

    }
}
