package cybersoft.java18.backend.CRM_Project.controller;

import com.google.gson.*;
import cybersoft.java18.backend.CRM_Project.model.ProjectModel;
import cybersoft.java18.backend.CRM_Project.model.ResponseModel;
import cybersoft.java18.backend.CRM_Project.service.IProjectService;
import cybersoft.java18.backend.CRM_Project.service.impl.ProjectService;
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

@WebServlet(urlPatterns = {UrlUtil.API_PROJECT,UrlUtil.API_PROJECT_ID, UrlUtil.API_PROJECT_ADD,
        UrlUtil.API_PROJECT_UPDATE, UrlUtil.API_PROJECT_DELETE})
public class ProjectController extends HttpServlet {
    Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .create();

    //gson string date to local date
    class LocalDateAdapter implements JsonDeserializer<LocalDate> {
       //get a string date dd-MM-yyyy and convert to local date
        @Override
        public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            return LocalDate.parse(json.getAsString(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        }


    }
    IProjectService projectService = new ProjectService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()){
            case UrlUtil.API_PROJECT:
                List<ProjectModel> projectList = projectService.findAllProject();

                String projectListJson = gson.toJson(projectList);

                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");

                PrintWriter writer = resp.getWriter();
                writer.print(projectListJson);
                writer.flush();
                break;

            case UrlUtil.API_PROJECT_ID:
                int id = Integer.parseInt(req.getParameter("id"));
                ProjectModel project = projectService.findProjectById(id);
                String projectJson = gson.toJson(project);
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                PrintWriter writer1 = resp.getWriter();
                writer1.print(projectJson);
                writer1.flush();
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case UrlUtil.API_PROJECT_ADD:
                req.setCharacterEncoding("UTF-8");

                ProjectModel project = gson.fromJson(req.getReader(), ProjectModel.class);
                project = projectService.addNewProject(project);
                ResponseModel responseAdd = new ResponseModel();
                if (project != null) {
                    responseAdd.setStatusCode(200);
                    responseAdd.setSuccess(true);
                    responseAdd.setMessage("Thêm thành công");
                    responseAdd.setData(project);
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

            case UrlUtil.API_PROJECT_UPDATE:
                req.setCharacterEncoding("UTF-8");
                ProjectModel modifiedProject = gson.fromJson(req.getReader(), ProjectModel.class);
                modifiedProject = projectService.modifyProject(modifiedProject);
                ResponseModel responseUpdate = new ResponseModel();
                if (modifiedProject != null) {
                    responseUpdate.setStatusCode(200);
                    responseUpdate.setSuccess(true);
                    responseUpdate.setMessage("Cập nhật thành công");
                    responseUpdate.setData(modifiedProject);
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

            case UrlUtil.API_PROJECT_DELETE:
                ProjectModel deletedProject = gson.fromJson(req.getReader(), ProjectModel.class);
                deletedProject = projectService.deleteProject(deletedProject);
                ResponseModel responseDelete = new ResponseModel();
                if (deletedProject != null) {
                    responseDelete.setStatusCode(200);
                    responseDelete.setSuccess(true);
                    responseDelete.setMessage("Xóa thành công");
                    responseDelete.setData(deletedProject);
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

}
