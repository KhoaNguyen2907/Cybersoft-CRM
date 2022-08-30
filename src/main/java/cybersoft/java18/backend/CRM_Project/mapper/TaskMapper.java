package cybersoft.java18.backend.CRM_Project.mapper;

import cybersoft.java18.backend.CRM_Project.model.ProjectModel;
import cybersoft.java18.backend.CRM_Project.model.StatusModel;
import cybersoft.java18.backend.CRM_Project.model.TaskModel;
import cybersoft.java18.backend.CRM_Project.model.UserModel;

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
                    new UserModel(resultSet.getInt("user_code"),resultSet.getString("user_name"), resultSet.getString("user_email")),
                    new ProjectModel(resultSet.getInt("project_id"),
                    resultSet.getString("project_name"),resultSet.getString("project_description"),
                    resultSet.getTimestamp("project_start_date").toLocalDateTime().toLocalDate(),
                    resultSet.getTimestamp("project_end_date").toLocalDateTime().toLocalDate(), resultSet.getInt("project_user_code")),
                    new StatusModel(resultSet.getInt("status_id"), resultSet.getString("status_name"))
                    );
        } else {
            return new TaskModel(resultSet.getInt("id"), resultSet.getString("task_name"),
                    resultSet.getString("task_description"),
                    resultSet.getTimestamp("start_date").toLocalDateTime().toLocalDate(),
                    new UserModel(resultSet.getInt("user_code"),resultSet.getString("user_name"), resultSet.getString("user_email")),
                    new ProjectModel(resultSet.getInt("project_id"),
                    resultSet.getString("project_name"),resultSet.getString("project_description"),
                    resultSet.getTimestamp("project_start_date").toLocalDateTime().toLocalDate(),
                    resultSet.getTimestamp("project_end_date").toLocalDateTime().toLocalDate(), resultSet.getInt("project_user_code")),
                    new StatusModel(resultSet.getInt("status_id"), resultSet.getString("status_name")));
        }
    }
}
