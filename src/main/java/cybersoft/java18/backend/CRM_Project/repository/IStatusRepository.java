package cybersoft.java18.backend.CRM_Project.repository;

import cybersoft.java18.backend.CRM_Project.model.StatusModel;

import java.util.List;

public interface IStatusRepository {
    List<StatusModel> findAllStatus();

}
