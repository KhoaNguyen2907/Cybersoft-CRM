package cybersoft.java18.backend.CRM_Project.service;

import cybersoft.java18.backend.CRM_Project.model.RoleModel;

import java.util.List;

public interface IRoleService {
    List<RoleModel> findAllRole();
    RoleModel addNewRole(RoleModel roleModel);
    RoleModel modifyRole(RoleModel newRole);
    RoleModel deleteRole(RoleModel role);
    RoleModel findRoleById(String id);

}
