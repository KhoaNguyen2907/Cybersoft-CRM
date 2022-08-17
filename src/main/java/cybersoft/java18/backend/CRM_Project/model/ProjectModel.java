package cybersoft.java18.backend.CRM_Project.model;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Data
public class ProjectModel {
    int id;
    String name;
    String description;
    LocalDate startDate;
    LocalDate endDate;
    int createdBy;
    List<UserModel> userModelList;

    public ProjectModel(int id, String name, String description, LocalDate startDate, LocalDate endDate, int createdBy) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createdBy = createdBy;
        this.userModelList = new ArrayList<>();
    }
    public ProjectModel(int id, String name, String description, LocalDate startDate, int createdBy) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.createdBy = createdBy;
        this.userModelList = new ArrayList<>();
    }
}
