package cybersoft.java18.backend.CRM_Project.controller;

import com.google.gson.Gson;
import cybersoft.java18.backend.CRM_Project.model.ResponseModel;
import cybersoft.java18.backend.CRM_Project.model.UserModel;
import cybersoft.java18.backend.CRM_Project.service.IUserService;
import cybersoft.java18.backend.CRM_Project.service.impl.UserService;
import cybersoft.java18.backend.CRM_Project.utils.UrlUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {UrlUtil.API_GET_CURRENT_USER, UrlUtil.DANG_NHAP, UrlUtil.DANG_KY, UrlUtil.DANG_XUAT})
public class AuthController extends HttpServlet {
    IUserService userService = new UserService();
    Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case UrlUtil.API_GET_CURRENT_USER:
                //get jwt token
                String authorization = req.getHeader("Authorization");
                String jwtToken = authorization.substring(7);
                System.out.println(jwtToken);
                //decode jwt token
                UserModel user = userService.decodeJwtToken(jwtToken);
                System.out.println(user.toString());
                user = userService.findUserById(user.getCode());
                String currentUserJson = gson.toJson(user);
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                resp.getWriter().print(currentUserJson);
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case UrlUtil.DANG_NHAP:
                UserModel user = gson.fromJson(req.getReader(), UserModel.class);
                ResponseModel responseLogin = new ResponseModel();
                //check login
                user = userService.findUserByEmailAndPassword(user.getEmail(), user.getPassword());
                if (user != null) {
                    //get jwttoken session
                    String jwtToken = userService.generateJwtToken(user);
                    System.out.println("jwtToken: " + jwtToken);
                    //set jwtToken to cookie
                    responseLogin.setSuccess(true);
                    responseLogin.setMessage("Đăng nhập thành công");
                    responseLogin.setData(jwtToken);
                } else {
                    responseLogin.setSuccess(false);
                    responseLogin.setMessage("Đăng nhập thất bại");
                }
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                resp.getWriter().write(gson.toJson(responseLogin));
                break;

            case UrlUtil.DANG_KY:
                UserModel userModel = gson.fromJson(req.getReader(), UserModel.class);
                if (userService.register(userModel)) {
                    resp.getWriter().write("true");
                } else {
                    resp.getWriter().write("false");
                }
                break;
            case UrlUtil.DANG_XUAT:
                break;
        }
    }
}
