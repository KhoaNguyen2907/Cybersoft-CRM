package cybersoft.java18.backend.CRM_Project.service;

import cybersoft.java18.backend.CRM_Project.model.UserModel;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface IUserService {
    UserModel  findUserByEmailAndPassword(String email, String password);

    List<UserModel> findAllUser();

    UserModel addUser(UserModel user);

    UserModel modifyUser(UserModel user);

    UserModel deleteUser(UserModel user);

    UserModel findUserById(int code);

    boolean register(UserModel userModel);

    String generateJwtToken(UserModel userModel) throws UnsupportedEncodingException;

    UserModel decodeJwtToken(String jwtToken) throws UnsupportedEncodingException;

}
