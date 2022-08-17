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
    List<ProjectModel> projectList;
    public UserModel( String email, String password,
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
}
