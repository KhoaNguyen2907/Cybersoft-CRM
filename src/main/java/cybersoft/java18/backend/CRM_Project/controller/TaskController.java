package cybersoft.java18.backend.CRM_Project.controller;

import com.google.gson.Gson;
import cybersoft.java18.backend.CRM_Project.model.TaskModel;
import cybersoft.java18.backend.CRM_Project.service.ITaskService;
import cybersoft.java18.backend.CRM_Project.service.impl.TaskService;
import cybersoft.java18.backend.CRM_Project.utils.UrlUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = {UrlUtil.API_TASK, UrlUtil.API_TASK_ADD, UrlUtil.API_TASK_UPDATE, UrlUtil.API_TASK_DELETE})
public class TaskController extends HttpServlet {
    Gson gson = new Gson();
    ITaskService taskService = new TaskService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<TaskModel> taskList = taskService.findAllTask();

        String taskListJson = gson.toJson(taskList);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.print("Danh sách công việc: " + taskListJson);
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case UrlUtil.API_TASK_ADD:
                req.setCharacterEncoding("UTF-8");

                TaskModel task = gson.fromJson(req.getReader(), TaskModel.class);
                task = taskService.addNewTask(task);

                String addedTaskJson = gson.toJson(task);

                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                PrintWriter writer = resp.getWriter();
                writer.print("Đã thêm thành công công việc mới: " + addedTaskJson);
                writer.flush();
                break;

            case UrlUtil.API_TASK_UPDATE:
                req.setCharacterEncoding("UTF-8");
                TaskModel modifiedTask = gson.fromJson(req.getReader(), TaskModel.class);
                modifiedTask = taskService.modifyTask(modifiedTask);

                String modifiedTaskJson = gson.toJson(modifiedTask);

                resp.setCharacterEncoding("UTF-8");
                resp.setContentType("application/json");
                PrintWriter writer1 = resp.getWriter();
                writer1.print("Đã chỉnh sửa công việc thành công: " + modifiedTaskJson);
                writer1.flush();
                break;
            case UrlUtil.API_TASK_DELETE:
                TaskModel deletedTask = gson.fromJson(req.getReader(), TaskModel.class);
                deletedTask = taskService.deleteTask(deletedTask);

                String deletedTaskJson = gson.toJson(deletedTask);

                resp.setCharacterEncoding("UTF-8");
                resp.setContentType("application/json");

                PrintWriter writer2 = resp.getWriter();
                writer2.print("Đã xóa thành công công việc số " + deletedTask.getId() + " " + deletedTaskJson);
                writer2.flush();
                break;
            default:
        }
    }
}
