package cybersoft.java18.backend.CRM_Project.controller;

import com.google.gson.*;
import cybersoft.java18.backend.CRM_Project.model.ResponseModel;
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
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet(urlPatterns = {UrlUtil.API_TASK, UrlUtil.API_TASK_ID, UrlUtil.API_TASK_PROJECTID, UrlUtil.API_TASK_USERID,
        UrlUtil.API_TASK_ADD, UrlUtil.API_TASK_UPDATE, UrlUtil.API_TASK_DELETE})
public class TaskController extends HttpServlet {
    ITaskService taskService = new TaskService();
    Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(LocalDate.class, new TaskController.LocalDateAdapter())
            .create();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case UrlUtil.API_TASK:
                List<TaskModel> taskList = taskService.findAllTask();
                String taskListJson = gson.toJson(taskList);

                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                PrintWriter writer = resp.getWriter();
                writer.print(taskListJson);
                writer.flush();
                break;
            case UrlUtil.API_TASK_ID:
                int taskId = Integer.parseInt(req.getParameter("id"));
                TaskModel task = taskService.findTaskById(taskId);
                String taskJson = gson.toJson(task);

                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                PrintWriter writer1 = resp.getWriter();
                writer1.print(taskJson);
                writer1.flush();
                break;
            case UrlUtil.API_TASK_PROJECTID:
                int projectId = Integer.parseInt(req.getParameter("id"));
                List<TaskModel> taskListByProject = taskService.findTaskByProjectId(projectId);
                String taskListByProjectJson = gson.toJson(taskListByProject);

                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                PrintWriter writer2 = resp.getWriter();
                writer2.print(taskListByProjectJson);
                writer2.flush();
                break;
            case UrlUtil.API_TASK_USERID:
                int userId = Integer.parseInt(req.getParameter("id"));
                List<TaskModel> taskListByUser = taskService.findTaskByUserId(userId);
                String taskListByUserJson = gson.toJson(taskListByUser);

                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                PrintWriter writer3 = resp.getWriter();
                writer3.print(taskListByUserJson);
                writer3.flush();
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case UrlUtil.API_TASK_ADD:
                req.setCharacterEncoding("UTF-8");

                TaskModel task = gson.fromJson(req.getReader(), TaskModel.class);
                task = taskService.addNewTask(task);
                ResponseModel responseAdd = new ResponseModel();
                if (task != null) {
                    responseAdd.setStatusCode(200);
                    responseAdd.setSuccess(true);
                    responseAdd.setMessage("Thêm thành công");
                    responseAdd.setData(task);
                } else {
                    responseAdd.setStatusCode(200);
                    responseAdd.setSuccess(false);
                    responseAdd.setMessage("Thêm thất bại");
                }
                String responseAddJson = gson.toJson(responseAdd);

                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                PrintWriter writer = resp.getWriter();
                writer.print(responseAddJson);
                writer.flush();
                break;

            case UrlUtil.API_TASK_UPDATE:
                req.setCharacterEncoding("UTF-8");
                TaskModel modifiedTask = gson.fromJson(req.getReader(), TaskModel.class);
                modifiedTask = taskService.modifyTask(modifiedTask);
                ResponseModel responseUpdate = new ResponseModel();
                if (modifiedTask != null) {
                    responseUpdate.setStatusCode(200);
                    responseUpdate.setSuccess(true);
                    responseUpdate.setMessage("Cập nhật thành công");
                    responseUpdate.setData(modifiedTask);
                } else {
                    responseUpdate.setStatusCode(200);
                    responseUpdate.setSuccess(false);
                    responseUpdate.setMessage("Cập nhật thất bại");
                }
                String responseUpdateJson = gson.toJson(responseUpdate);

                resp.setCharacterEncoding("UTF-8");
                resp.setContentType("application/json");
                PrintWriter writer1 = resp.getWriter();
                writer1.print(responseUpdateJson);
                writer1.flush();
                break;
            case UrlUtil.API_TASK_DELETE:
                TaskModel deletedTask = gson.fromJson(req.getReader(), TaskModel.class);
                deletedTask = taskService.deleteTask(deletedTask);

                ResponseModel responseDelete = new ResponseModel();
                if (deletedTask != null) {
                    responseDelete.setStatusCode(200);
                    responseDelete.setSuccess(true);
                    responseDelete.setMessage("Xóa thành công");
                    responseDelete.setData(deletedTask);
                } else {
                    responseDelete.setStatusCode(200);
                    responseDelete.setSuccess(false);
                    responseDelete.setMessage("Xóa thất bại");
                }
                String responseDeleteJson = gson.toJson(responseDelete);

                resp.setCharacterEncoding("UTF-8");
                resp.setContentType("application/json");

                PrintWriter writer2 = resp.getWriter();
                writer2.print(responseDeleteJson);
                writer2.flush();
                break;
            default:
        }
    }

    //gson string date to local date
    class LocalDateAdapter implements JsonDeserializer<LocalDate> {
        //get a string date dd-MM-yyyy and convert to local date
        @Override
        public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            return LocalDate.parse(json.getAsString(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        }
    }
}
