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

@WebServlet(urlPatterns = {UrlUtil.API_ROLE, UrlUtil.API_ROLE_ID, UrlUtil.API_ROLE_ADD,
        UrlUtil.API_ROLE_UPDATE, UrlUtil.API_ROLE_DELETE})
public class RoleController extends HttpServlet {
    Gson gson = new Gson();
    IRoleService roleService = new RoleService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case UrlUtil.API_ROLE:
                List<RoleModel> roleList = roleService.findAllRole();

                String roleListJson = gson.toJson(roleList);
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");

                PrintWriter writer = resp.getWriter();
                writer.print(roleListJson);
                writer.flush();
                break;
            case UrlUtil.API_ROLE_ID:
                String id = req.getParameter("id");
                RoleModel role = roleService.findRoleById(id);
                String roleJson = gson.toJson(role);
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                PrintWriter writer1 = resp.getWriter();
                writer1.print(roleJson);
                writer1.flush();
                break;

        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case UrlUtil.API_ROLE_ADD:
                req.setCharacterEncoding("UTF-8");

                RoleModel role = gson.fromJson(req.getReader(), RoleModel.class);
                role = roleService.addNewRole(role);

                ResponseModel responseAdd = new ResponseModel();
                if (role != null) {
                    responseAdd.setStatusCode(200);
                    responseAdd.setSuccess(true);
                    responseAdd.setMessage("Thêm thành công");
                    responseAdd.setData(role);
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
            case UrlUtil.API_ROLE_UPDATE:
                req.setCharacterEncoding("UTF-8");
                RoleModel modifiedRole = gson.fromJson(req.getReader(), RoleModel.class);
                modifiedRole = roleService.modifyRole(modifiedRole);
                ResponseModel responseUpdate = new ResponseModel();
                if (modifiedRole != null) {
                    responseUpdate.setStatusCode(200);
                    responseUpdate.setSuccess(true);
                    responseUpdate.setMessage("Sửa thành công");
                    responseUpdate.setData(modifiedRole);
                } else {
                    responseUpdate.setStatusCode(200);
                    responseUpdate.setSuccess(false);
                    responseUpdate.setMessage("Sửa thất bại");
                }
                String responseUpdateJson = gson.toJson(responseUpdate);

                resp.setCharacterEncoding("UTF-8");
                resp.setContentType("application/json");

                PrintWriter writer1 = resp.getWriter();
                writer1.print(responseUpdateJson);
                writer1.flush();
                break;
            case UrlUtil.API_ROLE_DELETE:
                RoleModel deletedRole = gson.fromJson(req.getReader(), RoleModel.class);
                deletedRole = roleService.deleteRole(deletedRole);
                ResponseModel responseDelete = new ResponseModel();
                if (deletedRole != null) {
                    responseDelete.setStatusCode(200);
                    responseDelete.setSuccess(true);
                    responseDelete.setMessage("Xóa thành công");
                    responseDelete.setData(deletedRole);
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
