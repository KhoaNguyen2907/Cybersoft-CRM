package cybersoft.java18.backend.CRM_Project.service;

import cybersoft.java18.backend.CRM_Project.model.ProjectModel;
import cybersoft.java18.backend.CRM_Project.model.RoleModel;

import java.util.List;

public interface IProjectService {

    List<ProjectModel> findAllProject();
    ProjectModel addNewProject(ProjectModel projectModel);
    ProjectModel modifyProject(ProjectModel newProject);
    ProjectModel deleteProject(ProjectModel projectModel);
}
