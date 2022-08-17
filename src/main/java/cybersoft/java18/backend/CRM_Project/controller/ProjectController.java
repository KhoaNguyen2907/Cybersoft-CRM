package cybersoft.java18.backend.CRM_Project.controller;

import com.google.gson.Gson;
import cybersoft.java18.backend.CRM_Project.model.ProjectModel;
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
import java.util.List;

@WebServlet(urlPatterns = {UrlUtil.API_PROJECT, UrlUtil.API_PROJECT_ADD,
        UrlUtil.API_PROJECT_UPDATE, UrlUtil.API_PROJECT_DELETE})
public class ProjectController extends HttpServlet {
    Gson gson = new Gson();
    IProjectService projectService = new ProjectService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ProjectModel> projectList = projectService.findAllProject();

        String projectListJson = gson.toJson(projectList);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter writer = resp.getWriter();
        writer.print("Danh sách dự án: " + projectListJson);
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case UrlUtil.API_PROJECT_ADD:
                req.setCharacterEncoding("UTF-8");

                ProjectModel project = gson.fromJson(req.getReader(), ProjectModel.class);
                project = projectService.addNewProject(project);

                String addedProjectJson = gson.toJson(project);

                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                PrintWriter writer = resp.getWriter();
                writer.print("Đã thêm thành công dự án mới: " + addedProjectJson);
                writer.flush();
                break;
            case UrlUtil.API_PROJECT_UPDATE:
                req.setCharacterEncoding("UTF-8");
                ProjectModel modifiedProject = gson.fromJson(req.getReader(), ProjectModel.class);
                modifiedProject = projectService.modifyProject(modifiedProject);

                String modifiedProjectJson = gson.toJson(modifiedProject);

                resp.setCharacterEncoding("UTF-8");
                resp.setContentType("application/json");
                PrintWriter writer1 = resp.getWriter();
                writer1.print("Đã thay đổi dự án thành công: " + modifiedProjectJson);
                writer1.flush();
                break;
            case UrlUtil.API_PROJECT_DELETE:
                ProjectModel deletedProject = gson.fromJson(req.getReader(), ProjectModel.class);
                deletedProject = projectService.deleteProject(deletedProject);

                String deletedProjectJson = gson.toJson(deletedProject);

                resp.setCharacterEncoding("UTF-8");
                resp.setContentType("application/json");

                PrintWriter writer2 = resp.getWriter();
                writer2.print("Đã xóa thành công dự án số " + deletedProject.getId() + " " + deletedProjectJson);
                writer2.flush();
                break;
            default:

        }
    }
}
