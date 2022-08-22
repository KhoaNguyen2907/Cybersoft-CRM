package cybersoft.java18.backend.CRM_Project.model;

import lombok.Data;

@Data
public class ResponseModel {
    int statusCode;
    boolean isSuccess;
    String message;
    Object data;
}
