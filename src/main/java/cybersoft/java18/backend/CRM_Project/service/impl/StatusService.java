package cybersoft.java18.backend.CRM_Project.service.impl;

import cybersoft.java18.backend.CRM_Project.model.StatusModel;
import cybersoft.java18.backend.CRM_Project.repository.IStatusRepository;
import cybersoft.java18.backend.CRM_Project.repository.impl.StatusRepository;

import java.util.List;

public class StatusService implements IStatusService {
    IStatusRepository statusRepository = new StatusRepository();
    @Override
    public List<StatusModel> findAllStatus() {
        return statusRepository.findAllStatus();
    }
}
