package cybersoft.java18.backend.CRM_Project.controller;

import com.google.gson.Gson;
import cybersoft.java18.backend.CRM_Project.model.ResponseModel;
import cybersoft.java18.backend.CRM_Project.model.UserModel;
import cybersoft.java18.backend.CRM_Project.service.IUserService;
import cybersoft.java18.backend.CRM_Project.service.impl.UserService;
import cybersoft.java18.backend.CRM_Project.utils.UrlUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet (urlPatterns = {UrlUtil.API_USER,UrlUtil.API_USER_ID, UrlUtil.API_USER_ADD,
        UrlUtil.API_USER_UPDATE, UrlUtil.API_USER_DELETE})
public class UserController extends HttpServlet {
    Gson gson = new Gson();
    IUserService userService = new UserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()){
            case UrlUtil.API_USER:
                List<UserModel> userList = userService.findAllUser();
                String userListJson = gson.toJson(userList);
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                PrintWriter writer = resp.getWriter();
                writer.print(userListJson);
                writer.flush();
                break;
            case UrlUtil.API_USER_ID:
                String code = req.getParameter("code");
                UserModel user = userService.findUserById(Integer.parseInt(code));
                String userJson = gson.toJson(user);
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                PrintWriter writer1 = resp.getWriter();
                writer1.print(userJson);
                writer1.flush();
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        switch (req.getServletPath()){
            case UrlUtil.API_USER_ADD:
                req.setCharacterEncoding("UTF-8");
                UserModel addedUser = gson.fromJson(req.getReader(),UserModel.class);
                addedUser = userService.addUser(addedUser);
                ResponseModel responseAdd = new ResponseModel();
                if(addedUser != null){
                    responseAdd.setStatusCode(200);
                    responseAdd.setSuccess(true);
                    responseAdd.setMessage("Thêm thành công");
                    responseAdd.setData(addedUser);}
                else{
                    responseAdd.setStatusCode(200);
                    responseAdd.setSuccess(false);
                    responseAdd.setMessage("Thêm thất bại");
                }
                String responseAddJson = gson.toJson(responseAdd);

                resp.setCharacterEncoding("UTF-8");
                resp.setContentType("application/json");
                PrintWriter writer = resp.getWriter();
                writer.print(responseAddJson);
                writer.flush();
                break;

            case  UrlUtil.API_USER_UPDATE:
                req.setCharacterEncoding("UTF-8");
                UserModel updatedUser = gson.fromJson(req.getReader(),UserModel.class);
                updatedUser = userService.modifyUser(updatedUser);
                ResponseModel responseUpdate = new ResponseModel();
                if(updatedUser != null){
                    responseUpdate.setStatusCode(200);
                    responseUpdate.setSuccess(true);
                    responseUpdate.setMessage("Cập nhật thành công");
                    responseUpdate.setData(updatedUser);}
                else{
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

            case UrlUtil.API_USER_DELETE:
                req.setCharacterEncoding("UTF-8");
                UserModel deletedModel = gson.fromJson(req.getReader(),UserModel.class);
                deletedModel = userService.deleteUser(deletedModel);
                ResponseModel responseDelete = new ResponseModel();
                if(deletedModel != null){
                    responseDelete.setStatusCode(200);
                    responseDelete.setSuccess(true);
                    responseDelete.setMessage("Xóa thành công");
                    responseDelete.setData(deletedModel);}
                else{
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
        }

    }
}
