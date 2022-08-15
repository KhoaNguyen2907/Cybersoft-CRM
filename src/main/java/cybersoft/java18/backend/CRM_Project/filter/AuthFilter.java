package cybersoft.java18.backend.CRM_Project.filter;

import cybersoft.java18.backend.CRM_Project.utils.JspUtil;
import cybersoft.java18.backend.CRM_Project.utils.UrlUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter (urlPatterns = {"/*"})
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        if (isLogged(req) || isAllowedUrl(req)){
            chain.doFilter(request,response);
        } else {
            req.getRequestDispatcher(JspUtil.DANG_NHAP).forward(req,resp);
        }
    }

    private boolean isLogged(HttpServletRequest req){
        return req.getSession().getAttribute("currentUser") != null;
    }

    private boolean isAllowedUrl(HttpServletRequest req){
        String url = req.getServletPath();
        if (url.startsWith(UrlUtil.DANG_NHAP) || url.startsWith(UrlUtil.DANG_KY)
            || url.startsWith(UrlUtil.API_ROLE) || url.startsWith(UrlUtil.API_USER)) {
            return true;
        }
        return false;
    }
}
