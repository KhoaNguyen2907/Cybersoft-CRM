package cybersoft.java18.backend.CRM_Project.filter;

import cybersoft.java18.backend.CRM_Project.model.UserModel;
import cybersoft.java18.backend.CRM_Project.service.IUserService;
import cybersoft.java18.backend.CRM_Project.service.impl.UserService;
import cybersoft.java18.backend.CRM_Project.utils.JspUtil;
import cybersoft.java18.backend.CRM_Project.utils.UrlUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@WebFilter(urlPatterns = {"/*"})
public class AuthFilter implements Filter {
    IUserService userService = new UserService();
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        if (isLogged(req) || isAllowedUrl(req)) {
            resp.setHeader("Access-Control-Allow-Origin", "*");
            resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
            resp.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me,  Authorization, dataType");

            chain.doFilter(request, response);
        } else {
            req.getRequestDispatcher(JspUtil.DANG_NHAP).forward(req, resp);
        }
    }

    private boolean isLogged(HttpServletRequest req) throws UnsupportedEncodingException {

        return true;
    }

    private boolean isAllowedUrl(HttpServletRequest req) {
        String url = req.getServletPath();
        if (url.startsWith(UrlUtil.DANG_NHAP) || url.startsWith(UrlUtil.DANG_KY)
                || url.startsWith(UrlUtil.API_ROLE) || url.startsWith(UrlUtil.API_USER)
                || url.startsWith(UrlUtil.API_PROJECT) || url.startsWith(UrlUtil.API_TASK)
                || url.startsWith(UrlUtil.API_GET_CURRENT_USER) || url.startsWith(UrlUtil.API_STATUS)){
            return true;
        }
        return false;
    }
}
