package cybersoft.java18.backend.CRM_Project.mapper;

import cybersoft.java18.backend.CRM_Project.model.RoleModel;
import cybersoft.java18.backend.CRM_Project.model.UserModel;

import javax.management.relation.Role;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements IAbstractMapper<UserModel> {

    @Override
    public UserModel rowMapper(ResultSet resultSet) throws SQLException {
        return new UserModel(resultSet.getInt("code"), resultSet.getString("email"), resultSet.getString("password"),
                resultSet.getString("name"), resultSet.getString("address"),resultSet.getString("phone"),
                new RoleModel(resultSet.getInt("role_id"), resultSet.getString("role_name"),resultSet.getString("description"))) ;
    }
}
