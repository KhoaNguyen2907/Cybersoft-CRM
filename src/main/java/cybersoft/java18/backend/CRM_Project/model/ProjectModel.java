package cybersoft.java18.backend.CRM_Project.model;

import java.time.LocalDateTime;
import java.util.List;

public class ProjectModel {
    int id;
    String name;
    String description;
    LocalDateTime startTime;
    LocalDateTime endTime;
    int createdBy;
    List<UserModel> userModelList;

}
