package cybersoft.java18.backend.CRM_Project.service;

import cybersoft.java18.backend.CRM_Project.model.UserModel;

import java.util.List;

public interface IUserService {
    List<UserModel>  findUserByEmailAndPassword(String email);
    List<UserModel> findAllUser();
    UserModel addUser(UserModel user);
}
