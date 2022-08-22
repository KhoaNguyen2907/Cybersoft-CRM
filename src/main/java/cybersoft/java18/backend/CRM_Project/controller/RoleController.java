package cybersoft.java18.backend.CRM_Project.controller;

import com.google.gson.Gson;
import cybersoft.java18.backend.CRM_Project.model.ResponseModel;
import cybersoft.java18.backend.CRM_Project.model.RoleModel;
import cybersoft.java18.backend.CRM_Project.service.IRoleService;
import cybersoft.java18.backend.CRM_Project.service.impl.RoleService;
import cybersoft.java18.backend.CRM_Project.utils.UrlUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = {UrlUtil.API_ROLE, UrlUtil.API_ROLE_ADD,
        UrlUtil.API_ROLE_UPDATE, UrlUtil.API_ROLE_DELETE})
public class RoleController extends HttpServlet {
    Gson gson = new Gson();
    IRoleService roleService = new RoleService();



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<RoleModel> roleList = roleService.findAllRole();

        String roleListJson = gson.toJson(roleList);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter writer = resp.getWriter();
        writer.print(roleListJson);
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case UrlUtil.API_ROLE_ADD:
                req.setCharacterEncoding("UTF-8");

                RoleModel role = gson.fromJson(req.getReader(),RoleModel.class);
                role = roleService.addNewRole(role);

                ResponseModel responseData = new ResponseModel();
                if (role != null){
                    responseData.setStatusCode(200);
                    responseData.setSuccess(true);
                    responseData.setMessage("Thêm thành công");
                    responseData.setData(role);
                } else {
                    responseData.setStatusCode(200);
                    responseData.setSuccess(false);
                    responseData.setMessage("Thêm thất bại");
                }

                String addedRoleJson = gson.toJson(responseData);

                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");

                PrintWriter writer = resp.getWriter();
                writer.print(addedRoleJson);
                writer.flush();
                break;
            case UrlUtil.API_ROLE_UPDATE :
                req.setCharacterEncoding("UTF-8");
                RoleModel modifiedRole = gson.fromJson(req.getReader(),RoleModel.class);
                modifiedRole = roleService.modifyRole(modifiedRole);
                String modifiedRoleJson = gson.toJson(modifiedRole);

                resp.setCharacterEncoding("UTF-8");
                resp.setContentType("application/json");

                PrintWriter writer1 = resp.getWriter();
                writer1.print("Đã thay đổi quyền thành công: " + modifiedRoleJson);
                writer1.flush();
                break;
            case UrlUtil.API_ROLE_DELETE :
                RoleModel deletedRole = gson.fromJson(req.getReader(),RoleModel.class);
                deletedRole = roleService.deleteRole(deletedRole);
                String deletedRoleJson = gson.toJson(deletedRole);

                resp.setCharacterEncoding("UTF-8");
                resp.setContentType("application/json");

                PrintWriter writer2 = resp.getWriter();
                writer2.print("Đã xóa thành công quyền số " + deletedRole.getId() + " " + deletedRoleJson);
                writer2.flush();
                break;
            default:

        }

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RoleModel deletedRole = gson.fromJson(req.getReader(),RoleModel.class);
        deletedRole = roleService.deleteRole(deletedRole);
        String deletedRoleJson = gson.toJson(deletedRole);

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        PrintWriter writer2 = resp.getWriter();
        writer2.print(deletedRoleJson);
        writer2.flush();
    }
}
