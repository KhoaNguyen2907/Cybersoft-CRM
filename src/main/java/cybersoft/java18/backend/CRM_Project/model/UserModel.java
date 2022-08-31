package cybersoft.java18.backend.CRM_Project.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserModel {
    int code;
    String email;
    String password;
    String fullName;
    String address;
    String phone;
    RoleModel role;
    String avatar;
    List<ProjectModel> projectList;

    public UserModel(String email, String password,
                     String fullName, String address, String phone,
                     RoleModel role) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.address = address;
        this.phone = phone;
        this.role = role;
        this.projectList = new ArrayList<>();
    }

    public UserModel(int code, String email, String password,
                     String fullName, String address, String phone,
                     RoleModel roleModel) {
        this.code = code;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.address = address;
        this.phone = phone;
        this.role = roleModel;
        this.projectList = new ArrayList<>();
    }

    public UserModel(int code, String email, String password, String fullName, String address, String phone, RoleModel role, String avatar) {
        this.code = code;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.address = address;
        this.phone = phone;
        this.role = role;
        this.avatar = avatar;
    }

    //to get name in Task
    public UserModel(int user_code, String user_name, String email, String avatar) {
        this.code = user_code;
        this.fullName = user_name;
        this.email = email;
        this.avatar = avatar;
    }

    public UserModel(int code) {
        this.code = code;
    }
}
