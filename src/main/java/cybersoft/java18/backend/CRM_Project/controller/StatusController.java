package cybersoft.java18.backend.CRM_Project.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import cybersoft.java18.backend.CRM_Project.model.StatusModel;
import cybersoft.java18.backend.CRM_Project.repository.IStatusRepository;
import cybersoft.java18.backend.CRM_Project.service.impl.IStatusService;
import cybersoft.java18.backend.CRM_Project.service.impl.StatusService;
import cybersoft.java18.backend.CRM_Project.utils.UrlUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet (UrlUtil.API_STATUS)
public class StatusController extends HttpServlet {
    IStatusService statusService = new StatusService();
    Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy").create();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<StatusModel> statusList = statusService.findAllStatus();
        String statusListJson = gson.toJson(statusList);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.print(statusListJson);
        writer.flush();
    }
}
