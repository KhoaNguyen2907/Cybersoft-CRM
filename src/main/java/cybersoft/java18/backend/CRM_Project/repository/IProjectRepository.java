package cybersoft.java18.backend.CRM_Project.repository;

import cybersoft.java18.backend.CRM_Project.model.ProjectModel;
import cybersoft.java18.backend.CRM_Project.model.UserModel;

import java.util.List;

public interface IProjectRepository {
    List<ProjectModel> findAllProject();
    ProjectModel saveProject(ProjectModel project);
    ProjectModel updateProject(ProjectModel project);
    ProjectModel deleteProject(ProjectModel project);
    ProjectModel findProjectById(int id);

}
