package cybersoft.java18.backend.CRM_Project.repository.impl;

import cybersoft.java18.backend.CRM_Project.mapper.IAbstractMapper;
import cybersoft.java18.backend.CRM_Project.mapper.RoleMapper;
import cybersoft.java18.backend.CRM_Project.model.RoleModel;
import cybersoft.java18.backend.CRM_Project.repository.AbstracRepository;
import cybersoft.java18.backend.CRM_Project.repository.IRoleRepository;

import java.util.List;

public class RoleRepository extends AbstracRepository<RoleModel> implements IRoleRepository {
    IAbstractMapper<RoleModel> mapper = new RoleMapper();

    @Override
    public List<RoleModel> findAllRole() {
        String query = "select * from Role";
        return executeQuery(query, mapper);
    }

    @Override
    public RoleModel saveRole(RoleModel role) {
        String query = "insert into Role(id,role_name,description) " +
                "values(?,?,?)";
        int id = executeUpdate(query, role.getId(), role.getName(), role.getDescription());
        role.setId(id);
        return role;
    }

    @Override
    public RoleModel updateRole(RoleModel role) {
        String query = "update  Role set role_name = ?, description = ? " +
                "where id = ? ";
        executeUpdate(query, role.getName(), role.getDescription(), role.getId());
        return role;
    }

    @Override
    public RoleModel deleteRole(RoleModel role) {
        String query = "delete from Role where id = ? ";
        executeUpdate(query, role.getId());
        return role;
    }

    @Override
    public RoleModel findRoleById(String id) {
        String query = "select * from Role where id = ? ";
        List<RoleModel> roles = executeQuery(query, mapper, id);
        return roles.size() > 0 ? roles.get(0) : null;
    }


}
