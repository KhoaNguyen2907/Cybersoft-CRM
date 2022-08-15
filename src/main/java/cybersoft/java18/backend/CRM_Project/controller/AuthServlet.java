package cybersoft.java18.backend.CRM_Project.controller;

import cybersoft.java18.backend.CRM_Project.service.IUserService;
import cybersoft.java18.backend.CRM_Project.service.UserService;
import cybersoft.java18.backend.CRM_Project.utils.JspUtil;
import cybersoft.java18.backend.CRM_Project.utils.UrlUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {UrlUtil.DANG_NHAP, UrlUtil.DANG_KY, UrlUtil.DANG_XUAT})
public class AuthServlet extends HttpServlet {
IUserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()){
            case UrlUtil.DANG_NHAP:
                req.getRequestDispatcher(JspUtil.DANG_NHAP).forward(req,resp);
                break;
            case UrlUtil.DANG_KY:
            case UrlUtil.DANG_XUAT:
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()){
            case UrlUtil.DANG_NHAP:
                String email = req.getParameter("email");
                String password = req.getParameter("password");

                req.getRequestDispatcher(JspUtil.DANG_NHAP).forward(req,resp);
                break;
            case UrlUtil.DANG_KY:
            case UrlUtil.DANG_XUAT:
                break;
        }
    }
}
