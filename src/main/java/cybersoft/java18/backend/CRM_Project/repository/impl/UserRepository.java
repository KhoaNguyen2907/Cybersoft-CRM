package cybersoft.java18.backend.CRM_Project.repository.impl;

import cybersoft.java18.backend.CRM_Project.mapper.IAbstractMapper;
import cybersoft.java18.backend.CRM_Project.mapper.UserMapper;
import cybersoft.java18.backend.CRM_Project.model.UserModel;
import cybersoft.java18.backend.CRM_Project.repository.AbstracRepository;
import cybersoft.java18.backend.CRM_Project.repository.IUserRepository;

import java.util.List;

public class UserRepository extends AbstracRepository<UserModel> implements IUserRepository {
    IAbstractMapper mapper = new UserMapper();

    @Override
    public List<UserModel> findUserByEmail(String email) {
        return null;
    }

    @Override
    public List<UserModel> findAllUser() {
        String query = "select code, email,password, fullname, address, phone,role_id, role_name, description, avatar " +
                "from User u inner join Role r on u.role_id = r.id";
        return executeQuery(query, mapper);
    }

    @Override
    public UserModel saveUser(UserModel user) {
        String query = "insert into User(email,password,fullname,address,phone,role_id,avatar) " +
                "values (?,?,?,?,?,?,?)";
        int code = executeUpdate(query, user.getEmail(),
                user.getPassword(), user.getFullName(), user.getAddress(),
                user.getPhone(), user.getRole().getId(), user.getAvatar());
        user.setCode(code);
        return user;
    }

    @Override
    public UserModel updateUser(UserModel user) {
        String query = "update User set email = ? , password = ? , fullname = ? , address = ?, phone = ?, role_id = ?, avatar = ? " +
                "where code = ?";
        executeUpdate(query, user.getEmail(),
                user.getPassword(), user.getFullName(), user.getAddress(),
                user.getPhone(), user.getRole().getId(), user.getCode(), user.getAvatar());
        return user;
    }

    @Override
    public UserModel deleteUser(UserModel user) {
        String query = "delete from User where code = ?";
        executeUpdate(query, user.getCode());
        return user;
    }

    @Override
    public UserModel findUserById(int code) {
        String query = "select code, email,password, fullname, address, phone,role_id, role_name, description, avatar " +
                "from User u inner join Role r on u.role_id = r.id where u.code = ?";
        List<UserModel> users = executeQuery(query, mapper, code);
        return users.size() > 0 ? users.get(0) : null;

    }

    @Override
    public boolean register(UserModel userModel) {
        String query = "insert into User(email,password,fullname,address,phone,role_id,avatar) " +
                "values (?,?,?,?,?,?,?)";
        int code = executeUpdate(query, userModel.getEmail(),
                userModel.getPassword(), userModel.getFullName(), userModel.getAddress(),
                userModel.getPhone(), userModel.getRole().getId(), userModel.getAvatar());
        userModel.setCode(code);
        return true;
    }

    @Override
    public UserModel findUserByEmailAndPassword(String username, String password) {
        String query = "select code, email,password, fullname, address, phone,role_id, role_name, description, avatar " +
                "from User u inner join Role r on u.role_id = r.id where u.email = ? and u.password = ?";
        List<UserModel> list = executeQuery(query, mapper, username, password);
        return list.size() > 0 ? list.get(0) : null;
    }


}
