package cybersoft.java18.backend.CRM_Project.repository;

import cybersoft.java18.backend.CRM_Project.model.ProjectModel;
import cybersoft.java18.backend.CRM_Project.model.TaskModel;

import java.util.List;

public interface ITaskRepository {

    List<TaskModel> findAllTask();
    TaskModel saveTask(TaskModel task);
    TaskModel updateTask(TaskModel task);
    TaskModel deleteTask(TaskModel task);

    TaskModel findTaskById(int taskId);


    List<TaskModel> findTaskByProjectId(int projectId);

    List<TaskModel> findTaskByUserId(int userId);

}
