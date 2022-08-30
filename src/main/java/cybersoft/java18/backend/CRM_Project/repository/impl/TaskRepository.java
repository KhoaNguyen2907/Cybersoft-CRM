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
        String query = new StringBuilder().append("select t.id , task_name, task_description, t.start_date,")
                .append(" t.end_date, u.code as user_code, u.fullname as user_name,u.email as user_email, p.id as project_id, p.project_name,p.project_description,")
                .append("p.start_date as project_start_date, p.end_date as project_end_date,")
                .append(" p.user_code as project_user_code, s.id as status_id, s.name as status_name")
                .append("  from Task t inner join Project p on t.project_id = p.id")
                .append(" inner join Status s on t.status_id = s.id inner join User u on t.user_code = u.code")
                .toString();
        return executeQuery(query, mapper);
    }

    @Override
    public TaskModel saveTask(TaskModel task) {
        if (task.getEndDate() != null) {
            String query = "insert into Task(task_name,task_description, start_date, end_date, user_code, project_id, status_id) " +
                    "values(?,?,?,?,?,?,?)";
            int id = executeUpdate(query, task.getName(), task.getDescription(),
                    task.getStartDate().atStartOfDay(), task.getEndDate().atStartOfDay(),
                    task.getUser().getCode(), task.getProject().getId(), task.getStatus().getId());
            task.setId(id);
        } else {
            String query = "insert into Task(project_name,project_description, start_date, user_code, project_id, status_id) " +
                    "values(?,?,?,?,?,?)";
            int id = executeUpdate(query, task.getName(), task.getDescription(),
                    task.getStartDate().atStartOfDay(), task.getUser().getCode(), task.getProject().getId(),
                    task.getStatus().getId());
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
                    task.getUser().getCode(), task.getProject().getId(), task.getStatus().getId(), task.getId());
        } else {
            String query = "update Task set project_name = ?, project_description = ? , start_date = ?, user_code = ?, project_id = ?, status_id = ? " +
                    "where id = ? ";
            executeUpdate(query, task.getName(), task.getDescription(), task.getStartDate().atStartOfDay(),
                    task.getUser().getCode(), task.getProject().getId(), task.getStatus().getId(), task.getId());
        }
        return task;
    }

    @Override
    public TaskModel deleteTask(TaskModel task) {
        String query = "delete from Task where id = ?";
        executeUpdate(query, task.getId());
        return task;
    }

    @Override
    public TaskModel findTaskById(int taskId) {
        String query = new StringBuilder().append("select t.id , task_name, task_description, t.start_date,")
                .append(" t.end_date, u.code as user_code, u.fullname as user_name,u.email as user_email, p.id as project_id, p.project_name,p.project_description,")
                .append("p.start_date as project_start_date, p.end_date as project_end_date,")
                .append(" p.user_code as project_user_code, s.id as status_id, s.name as status_name")
                .append("  from Task t inner join Project p on t.project_id = p.id")
                .append(" inner join Status s on t.status_id = s.id inner join User u on t.user_code = u.code")
                .append(" where t.id = ?")
                .toString();
        List<TaskModel> tasks = executeQuery(query, mapper, taskId);
        return tasks.isEmpty() ? null : tasks.get(0);
    }

    @Override
    public List<TaskModel> findTaskByProjectId(int projectId) {
        String query = new StringBuilder().append("select t.id , task_name, task_description, t.start_date,")
                .append(" t.end_date, u.code as user_code, u.fullname as user_name, u.email as user_email, p.id as project_id, p.project_name,p.project_description,")
                .append("p.start_date as project_start_date, p.end_date as project_end_date,")
                .append(" p.user_code as project_user_code,  s.id as status_id, s.name as status_name")
                .append("  from Task t inner join Project p on t.project_id = p.id")
                .append(" inner join Status s on t.status_id = s.id inner join User u on t.user_code = u.code")
                .append(" where p.id = ?")
                .toString();
        return executeQuery(query, mapper, projectId);
    }

    @Override
    public List<TaskModel> findTaskByUserId(int userId) {
        String query = new StringBuilder().append("select t.id , task_name, task_description, t.start_date,")
                .append(" t.end_date, u.code as user_code, u.fullname as user_name, u.email as user_email, p.id as project_id, p.project_name,p.project_description,")
                .append("p.start_date as project_start_date, p.end_date as project_end_date,")
                .append(" p.user_code as project_user_code, s.id as status_id, s.name as status_name")
                .append("  from Task t inner join Project p on t.project_id = p.id")
                .append(" inner join Status s on t.status_id = s.id inner join User u on t.user_code = u.code")
                .append(" where u.code = ?")
                .toString();
        return executeQuery(query, mapper, userId);
    }
}
