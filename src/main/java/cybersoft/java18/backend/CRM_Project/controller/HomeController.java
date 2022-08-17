package cybersoft.java18.backend.CRM_Project.controller;

import cybersoft.java18.backend.CRM_Project.model.UserModel;
import cybersoft.java18.backend.CRM_Project.utils.JspUtil;
import cybersoft.java18.backend.CRM_Project.utils.RoleUtil;
import cybersoft.java18.backend.CRM_Project.utils.UrlUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {UrlUtil.HOME})
public class HomeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserModel currentUserModel = (UserModel) req.getSession().getAttribute("currentUser");
        String view = "";
        if (currentUserModel.getRole().getName().equals(RoleUtil.ADMIN)) {
            view = JspUtil.ADMIN_HOME;
        } else if (currentUserModel.getRole().getName().equals(RoleUtil.LEAD)) {
            view = JspUtil.LEADER_HOME;
        } else {
            view = JspUtil.USER_HOME;
        }
        req.getRequestDispatcher(view).forward(req, resp);
    }
}
