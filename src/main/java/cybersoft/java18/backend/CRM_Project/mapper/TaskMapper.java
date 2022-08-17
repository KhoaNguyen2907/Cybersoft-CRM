package cybersoft.java18.backend.CRM_Project.mapper;

import cybersoft.java18.backend.CRM_Project.model.ProjectModel;
import cybersoft.java18.backend.CRM_Project.model.TaskModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskMapper implements IAbstractMapper<TaskModel> {
    @Override
    public TaskModel rowMapper(ResultSet resultSet) throws SQLException {
        if ( resultSet.getTimestamp("end_date") != null ){
            return new TaskModel(resultSet.getInt("id"), resultSet.getString("task_name"),
                    resultSet.getString("task_description"),
                    resultSet.getTimestamp("start_date").toLocalDateTime().toLocalDate(),
                    resultSet.getTimestamp("end_date").toLocalDateTime().toLocalDate(),
                    resultSet.getInt("user_code"),resultSet.getInt("project_id"),
                    resultSet.getInt("status_id"));
        } else {
            return new TaskModel(resultSet.getInt("id"), resultSet.getString("project_name"),
                    resultSet.getString("project_description"),
                    resultSet.getTimestamp("start_date").toLocalDateTime().toLocalDate(),
                    resultSet.getInt("user_code"),resultSet.getInt("project_id"),
                    resultSet.getInt("status_id"));
        }
    }
}
