package cybersoft.java18.backend.CRM_Project.model;

import lombok.Data;

import java.time.LocalDate;
@Data
public class TaskModel {
    int id;
    String name;
    String description;
    LocalDate startDate;
    LocalDate endDate;
    UserModel user;
    ProjectModel project;
    StatusModel status;

    public TaskModel(int id, String name, String description, LocalDate startDate, UserModel user, ProjectModel project, StatusModel status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.user = user;
        this.project = project;
        this.status = status;
    }

    public TaskModel(int id, String name, String description, LocalDate startDate, LocalDate endDate, UserModel user, ProjectModel project, StatusModel status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.user = user;
        this.project = project;
        this.status = status;
    }
}
