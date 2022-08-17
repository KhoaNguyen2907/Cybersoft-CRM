package cybersoft.java18.backend.CRM_Project.repository;

import cybersoft.java18.backend.CRM_Project.model.RoleModel;

import java.util.List;

public interface IRoleRepository {
    List<RoleModel> findAllRole();
    RoleModel saveRole(RoleModel role);
    RoleModel updateRole (RoleModel role);
    RoleModel deleteRole(RoleModel role);
}
