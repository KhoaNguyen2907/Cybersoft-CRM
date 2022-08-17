package cybersoft.java18.backend.CRM_Project.repository.impl;

import cybersoft.java18.backend.CRM_Project.mapper.IAbstractMapper;
import cybersoft.java18.backend.CRM_Project.mapper.TaskMapper;
import cybersoft.java18.backend.CRM_Project.model.TaskModel;
import cybersoft.java18.backend.CRM_Project.repository.AbstracRepository;
import cybersoft.java18.backend.CRM_Project.repository.ITaskRepository;

import java.util.List;

public class TaskRepository extends AbstracRepository<TaskModel> implements ITaskRepository {
    IAbstractMapper mapper = new TaskMapper();

    @Override
    public List<TaskModel> findAllTask() {
        String query = "select * from Task";
        return executeQuery(query, mapper);
    }

    @Override
    public TaskModel saveTask(TaskModel task) {
        if (task.getEndDate() != null) {
            String query = "insert into Task(task_name,task_description, start_date, end_date, user_code, project_id, status_id) " +
                    "values(?,?,?,?,?,?,?)";
            int id = executeUpdate(query, task.getName(), task.getDescription(),
                    task.getStartDate().atStartOfDay(), task.getEndDate().atStartOfDay(),
                    task.getUserCode(), task.getProjectId(), task.getStatusId());
            task.setId(id);
        } else {
            String query = "insert into Task(project_name,project_description, start_date, user_code, project_id, status_id) " +
                    "values(?,?,?,?,?,?)";
            int id = executeUpdate(query, task.getName(), task.getDescription(),
                    task.getStartDate().atStartOfDay(), task.getUserCode(), task.getProjectId(),
                    task.getStatusId());
            task.setId(id);
        }
        return task;
    }

    @Override
    public TaskModel updateTask(TaskModel task) {
        if (task.getEndDate() != null) {
            String query = "update Task set task_name = ?, task_description = ? , start_date = ?, end_date = ?, user_code = ?, project_id = ?, status_id = ? " +
                    "where id = ? ";
            executeUpdate(query, task.getName(), task.getDescription(),
                    task.getStartDate().atStartOfDay(), task.getEndDate().atStartOfDay(),
                    task.getUserCode(), task.getProjectId(), task.getStatusId(), task.getId());
        } else {
            String query = "update Task set project_name = ?, project_description = ? , start_date = ?, user_code = ?, project_id = ?, status_id = ? " +
                    "where id = ? ";
            executeUpdate(query, task.getName(), task.getDescription(), task.getStartDate().atStartOfDay(),
                    task.getUserCode(), task.getProjectId(), task.getStatusId(), task.getId());
        }
        return task;
    }

    @Override
    public TaskModel deleteTask(TaskModel task) {
        String query = "delete from Task where id = ?";
        executeUpdate(query, task.getId());
        return task;
    }
}
