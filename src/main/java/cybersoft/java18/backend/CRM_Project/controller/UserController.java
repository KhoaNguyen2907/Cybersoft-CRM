package cybersoft.java18.backend.CRM_Project.controller;

import com.google.gson.Gson;
import cybersoft.java18.backend.CRM_Project.model.UserModel;
import cybersoft.java18.backend.CRM_Project.service.IUserService;
import cybersoft.java18.backend.CRM_Project.service.UserService;
import cybersoft.java18.backend.CRM_Project.utils.UrlUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;

@WebServlet (urlPatterns = {UrlUtil.API_USER, UrlUtil.API_USER_ADD,
        UrlUtil.API_USER_UPDATE, UrlUtil.API_USER_DELETE})
public class UserController extends HttpServlet {
    Gson gson = new Gson();
    IUserService userService = new UserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<UserModel> userList = userService.findAllUser();

        String userListJson = gson.toJson(userList);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter writer = resp.getWriter();
        writer.print("Tất cả user: " + userListJson);
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        UserModel addedUser = gson.fromJson(req.getReader(),UserModel.class);
        addedUser = userService.addUser(addedUser);

        String addedUserJson = gson.toJson(addedUser);

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        writer.print("Đã thêm user: " + addedUserJson);
        writer.flush();
    }
}
