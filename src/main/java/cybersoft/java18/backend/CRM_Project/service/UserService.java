package cybersoft.java18.backend.CRM_Project.service;

import cybersoft.java18.backend.CRM_Project.model.UserModel;
import cybersoft.java18.backend.CRM_Project.repository.IUserRepository;
import cybersoft.java18.backend.CRM_Project.repository.UserRepository;

import java.util.List;

public class UserService implements IUserService {
    IUserRepository userRepository = new UserRepository();

    public List<UserModel> findUserByEmailAndPassword(String username) {
        return null;
    }

    @Override
    public List<UserModel> findAllUser() {
        return userRepository.findAllUser();
    }

    @Override
    public UserModel addUser(UserModel user) {
        return userRepository.saveUser(user);
    }

}
