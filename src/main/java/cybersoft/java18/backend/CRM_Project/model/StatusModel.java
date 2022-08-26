package cybersoft.java18.backend.CRM_Project.model;

import lombok.Data;

@Data
public class StatusModel {
    int id;
    String name;

    public StatusModel(int status_id, String status_name) {
        this.id = status_id;
        this.name = status_name;
    }
}
