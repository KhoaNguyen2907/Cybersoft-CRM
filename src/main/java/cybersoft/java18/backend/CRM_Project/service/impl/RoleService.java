package cybersoft.java18.backend.CRM_Project.service.impl;

import cybersoft.java18.backend.CRM_Project.model.RoleModel;
import cybersoft.java18.backend.CRM_Project.repository.IRoleRepository;
import cybersoft.java18.backend.CRM_Project.repository.impl.RoleRepository;
import cybersoft.java18.backend.CRM_Project.service.IRoleService;

import java.util.List;

public class RoleService implements IRoleService {
    private static RoleService INSTANCE;
    private final IRoleRepository roleRepository = new RoleRepository();

    public static RoleService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RoleService();
        }
        return INSTANCE;

    }

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

    public RoleModel deleteRole(RoleModel role) {
        return roleRepository.deleteRole(role);

    }
}
