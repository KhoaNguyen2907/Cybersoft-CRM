package cybersoft.java18.backend.CRM_Project.mapper;

import cybersoft.java18.backend.CRM_Project.model.ProjectModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectMapper implements IAbstractMapper<ProjectModel> {

    @Override
    public ProjectModel rowMapper(ResultSet resultSet) throws SQLException {
        if ( resultSet.getTimestamp("end_date") != null ){
            return new ProjectModel(resultSet.getInt("id"), resultSet.getString("project_name"),
                    resultSet.getString("project_description"),
                    resultSet.getTimestamp("start_date").toLocalDateTime().toLocalDate(),
                    resultSet.getTimestamp("end_date").toLocalDateTime().toLocalDate(),
                    resultSet.getInt("user_code"));
        } else {
            return new ProjectModel(resultSet.getInt("id"), resultSet.getString("project_name"),
                    resultSet.getString("project_description"),
                    resultSet.getTimestamp("start_date").toLocalDateTime().toLocalDate(),
                    resultSet.getInt("user_code"));
        }
    }
}
