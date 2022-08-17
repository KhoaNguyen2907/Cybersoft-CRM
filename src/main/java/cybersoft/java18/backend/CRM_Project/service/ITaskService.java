package cybersoft.java18.backend.CRM_Project.service;

import cybersoft.java18.backend.CRM_Project.model.TaskModel;

import java.util.List;

public interface ITaskService {
    List<TaskModel> findAllTask();
    TaskModel addNewTask(TaskModel taskModel);
    TaskModel modifyTask(TaskModel newTask);
    TaskModel deleteTask(TaskModel taskModel);
}
