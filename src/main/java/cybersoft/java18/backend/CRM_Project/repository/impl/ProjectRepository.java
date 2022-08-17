package cybersoft.java18.backend.CRM_Project.repository.impl;

import cybersoft.java18.backend.CRM_Project.mapper.IAbstractMapper;
import cybersoft.java18.backend.CRM_Project.mapper.ProjectMapper;
import cybersoft.java18.backend.CRM_Project.model.ProjectModel;
import cybersoft.java18.backend.CRM_Project.repository.AbstracRepository;
import cybersoft.java18.backend.CRM_Project.repository.IProjectRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class ProjectRepository extends AbstracRepository<ProjectModel> implements IProjectRepository {
    IAbstractMapper mapper = new ProjectMapper();

    @Override
    public List<ProjectModel> findAllProject() {
        String query = "select * from Project";
        return executeQuery(query, mapper);
    }

    @Override
    public ProjectModel saveProject(ProjectModel project) {
        if (project.getEndDate() != null){
            String query = "insert into Project(id,project_name,project_description, start_date, end_date, user_code) " +
                    "values(?,?,?,?,?,?)";
            int id = executeUpdate(query, project.getId(), project.getName(), project.getDescription(),
                    project.getStartDate().atStartOfDay(), project.getEndDate().atStartOfDay(),
                    project.getCreatedBy());
            project.setId(id);
        } else {
            String query = "insert into Project(id,project_name,project_description, start_date, user_code) " +
                    "values(?,?,?,?,?)";
            int id = executeUpdate(query, project.getId(), project.getName(), project.getDescription(),
                    project.getStartDate().atStartOfDay(), project.getCreatedBy());
            project.setId(id);
        }

        return project;
    }

    @Override
    public ProjectModel updateProject(ProjectModel project) {
        if (project.getEndDate() != null) {
            String query = "update Project set project_name = ?, project_description = ? , start_date = ?, end_date = ?, user_code = ? " +
                    "where id = ? ";
            executeUpdate(query, project.getName(), project.getDescription(),
                    project.getStartDate().atStartOfDay(), project.getEndDate().atStartOfDay(),
                    project.getCreatedBy(),project.getId());
        } else {
            String query = "update Project set project_name = ?, project_description = ? , start_date = ?, user_code = ? " +
                    "where id = ? ";
            executeUpdate(query, project.getName(), project.getDescription(),
                   project.getStartDate().atStartOfDay(), project.getCreatedBy(),project.getId());
        }

        return project;
    }

    @Override
    public ProjectModel deleteProject(ProjectModel project) {
        String query = "delete from Project where id = ?";
        executeUpdate(query, project.getId());
        return project;
    }
}
