package cybersoft.java18.backend.CRM_Project.service.impl;

import cybersoft.java18.backend.CRM_Project.model.TaskModel;
import cybersoft.java18.backend.CRM_Project.repository.ITaskRepository;
import cybersoft.java18.backend.CRM_Project.repository.impl.TaskRepository;
import cybersoft.java18.backend.CRM_Project.service.ITaskService;

import java.util.List;

public class TaskService implements ITaskService {
    ITaskRepository taskRepository = new TaskRepository();
    @Override
    public List<TaskModel> findAllTask() {
        return taskRepository.findAllTask();
    }

    @Override
    public TaskModel addNewTask(TaskModel taskModel) {
        return taskRepository.saveTask(taskModel);
    }

    @Override
    public TaskModel modifyTask(TaskModel newTask) {
        return taskRepository.updateTask(newTask);
    }

    @Override
    public TaskModel deleteTask(TaskModel taskModel) {
        return taskRepository.deleteTask(taskModel);
    }

    @Override
    public TaskModel findTaskById(int taskId) {

        return taskRepository.findTaskById(taskId);
    }

    @Override
    public List<TaskModel> findTaskByProjectId(int projectId) {
        return taskRepository.findTaskByProjectId(projectId);
    }

    @Override
    public List<TaskModel> findTaskByUserId(int userId) {
        return taskRepository.findTaskByUserId(userId);
    }
}
