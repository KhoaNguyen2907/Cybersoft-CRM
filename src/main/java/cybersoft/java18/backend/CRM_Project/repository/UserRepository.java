package cybersoft.java18.backend.CRM_Project.repository;

import cybersoft.java18.backend.CRM_Project.mapper.IAbstractMapper;
import cybersoft.java18.backend.CRM_Project.mapper.UserMapper;
import cybersoft.java18.backend.CRM_Project.model.UserModel;

import java.util.List;

public class UserRepository extends AbstracRepository<UserModel> implements IUserRepository {
    IAbstractMapper mapper = new UserMapper();
    @Override
    public List<UserModel> findUserByEmail(String email) {
        return null;
    }

    @Override
    public List<UserModel> findAllUser() {
        String query = "select code, email,password, name, address, phone,role_id, role_name, description " +
                       "from User u inner join Role r where u.role_id = r.id";
        return executeQuery(query,mapper);
    }

    @Override
    public UserModel saveUser(UserModel user) {
        String query = "insert into User(email,password,name,address,phone,role_id) " +
                       "values (?,?,?,?,?,?)";
        executeUpdate(query,user.getEmail(),
                user.getPassword(),user.getFullName(),user.getAddress(),
                user.getPhone(),user.getRole().getId());
        return user;
    }
}
