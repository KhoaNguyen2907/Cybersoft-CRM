package cybersoft.java18.backend.CRM_Project.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import cybersoft.java18.backend.CRM_Project.model.UserModel;
import cybersoft.java18.backend.CRM_Project.repository.IUserRepository;
import cybersoft.java18.backend.CRM_Project.repository.impl.UserRepository;
import cybersoft.java18.backend.CRM_Project.service.IUserService;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

public class UserService implements IUserService {
    IUserRepository userRepository = new UserRepository();

    public UserModel findUserByEmailAndPassword(String username, String password) {
        return userRepository.findUserByEmailAndPassword(username, password);
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

    @Override
    public UserModel findUserById(int code) {
        return userRepository.findUserById(code);
    }


    @Override
    public boolean register(UserModel userModel) {
        return userRepository.register(userModel);
    }

    @Override
    public String generateJwtToken(UserModel userModel) throws UnsupportedEncodingException {
        //generate token using JWT library
        JWTCreator.Builder builder = JWT.create().withIssuer("auth0");
        builder.withClaim("code", userModel.getCode());
        //expire time is 3 hours
        builder.withExpiresAt(new Date(System.currentTimeMillis() + 3 * 60 * 60 * 1000));
        return builder.sign(Algorithm.HMAC256("secret"));
    }

    @Override
    public UserModel decodeJwtToken(String jwtToken) throws UnsupportedEncodingException {
        //decode token using JWT library
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256("secret"))
                .build()
                .verify(jwtToken);

        int code = decodedJWT.getClaim("code").asInt();
        System.out.println(code);
        return new UserModel(code);


    }

}
