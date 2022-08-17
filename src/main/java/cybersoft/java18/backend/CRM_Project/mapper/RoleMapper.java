package cybersoft.java18.backend.CRM_Project.mapper;

import cybersoft.java18.backend.CRM_Project.model.RoleModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleMapper implements IAbstractMapper<RoleModel> {

    @Override
    public RoleModel rowMapper(ResultSet resultSet) throws SQLException {
        return new RoleModel(resultSet.getInt("id"),resultSet.getString("role_name"),
                resultSet.getString("description"));
    }
}
