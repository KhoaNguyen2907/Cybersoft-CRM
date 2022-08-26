package cybersoft.java18.backend.CRM_Project.service.impl;

import cybersoft.java18.backend.CRM_Project.model.ProjectModel;
import cybersoft.java18.backend.CRM_Project.repository.IProjectRepository;
import cybersoft.java18.backend.CRM_Project.repository.impl.ProjectRepository;
import cybersoft.java18.backend.CRM_Project.service.IProjectService;

import java.util.List;

public class ProjectService implements IProjectService {
    IProjectRepository projectRepository = new ProjectRepository();
    @Override
    public List<ProjectModel> findAllProject() {
        return projectRepository.findAllProject();
    }

    @Override
    public ProjectModel addNewProject(ProjectModel project) {
        return projectRepository.saveProject(project);
    }

    @Override
    public ProjectModel modifyProject(ProjectModel newProject) {
        return projectRepository.updateProject(newProject);
    }

    @Override
    public ProjectModel deleteProject(ProjectModel project) {
        return projectRepository.deleteProject(project);
    }

    @Override
    public ProjectModel findProjectById(int id) {
        return projectRepository.findProjectById(id);
    }
}
