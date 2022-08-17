package cybersoft.java18.backend.CRM_Project.repository;

import cybersoft.java18.backend.CRM_Project.model.UserModel;

import java.util.List;

public interface IUserRepository {
    List<UserModel> findUserByEmail(String email);
    List<UserModel> findAllUser();
    UserModel saveUser(UserModel user);
    UserModel updateUser(UserModel user);
    UserModel deleteUser(UserModel user);

}
