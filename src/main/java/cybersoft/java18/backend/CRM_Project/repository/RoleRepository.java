package cybersoft.java18.backend.CRM_Project.repository;

import cybersoft.java18.backend.CRM_Project.mapper.IAbstractMapper;
import cybersoft.java18.backend.CRM_Project.mapper.RoleMapper;
import cybersoft.java18.backend.CRM_Project.model.RoleModel;

import java.util.List;

public class RoleRepository extends AbstracRepository implements IRoleRepository {
    IAbstractMapper mapper = new RoleMapper();

    @Override
    public List<RoleModel> findAllRole() {
        String query = "select * from Role";
        return executeQuery(query, mapper);
    }

    @Override
    public RoleModel saveRole(RoleModel role) {
        String query = "insert into Role(id,name,description) " +
                       "values(?,?,?)";
        executeUpdate(query, role.getId(), role.getName(), role.getDescription());
        List<RoleModel> roleById = findRoleById(role.getId());
        return roleById.isEmpty() ? null : roleById.get(0);
    }

    @Override
    public RoleModel updateRole(RoleModel role) {
        String query = "update  Role set name = ?, description = ?" +
                       "where id = ? ";
        executeUpdate(query, role.getName(), role.getDescription(), role.getId());
        List<RoleModel> roleById = findRoleById(role.getId());
        return roleById.isEmpty() ? null : roleById.get(0);
    }

    @Override
    public RoleModel deleteRoleById(int roleId) {
        List<RoleModel> roleById = findRoleById(roleId);
        String query = "delete from Role where id = ?";
        executeUpdate(query, roleId);
        return roleById.isEmpty() ? null : roleById.get(0);
    }

    private List<RoleModel> findRoleById(int id) {
        String query = "select * from Role where id = ?";
        return executeQuery(query, mapper, id);
    }

}
