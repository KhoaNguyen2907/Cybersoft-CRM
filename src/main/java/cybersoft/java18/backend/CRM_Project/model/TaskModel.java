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
    int userCode;
    int projectId;
    int statusId;

    public TaskModel(int id, String name, String description, LocalDate startDate, int userCode, int projectId, int statusId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.userCode = userCode;
        this.projectId = projectId;
        this.statusId = statusId;
    }

    public TaskModel(int id, String name, String description, LocalDate startDate, LocalDate endDate, int userCode, int projectId, int statusId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.userCode = userCode;
        this.projectId = projectId;
        this.statusId = statusId;
    }
}
