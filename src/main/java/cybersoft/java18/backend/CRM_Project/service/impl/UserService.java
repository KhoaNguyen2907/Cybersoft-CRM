package cybersoft.java18.backend.CRM_Project.service.impl;

import cybersoft.java18.backend.CRM_Project.model.UserModel;
import cybersoft.java18.backend.CRM_Project.repository.IUserRepository;
import cybersoft.java18.backend.CRM_Project.repository.impl.UserRepository;
import cybersoft.java18.backend.CRM_Project.service.IUserService;

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

    @Override
    public UserModel modifyUser(UserModel user) {
        return userRepository.updateUser(user);
    }

    @Override
    public UserModel deleteUser(UserModel user) {
        return userRepository.deleteUser(user);
    }

}
