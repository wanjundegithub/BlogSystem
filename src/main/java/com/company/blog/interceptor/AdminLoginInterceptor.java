package com.company.blog.interceptor;


import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AdminLoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        //获取ip地址后的url路径(以/开始)
        String urlSuffixPath=request.getServletPath();
        if(urlSuffixPath.startsWith("/admin")&&
                request.getSession().getAttribute("loginUserName")==null){
            request.getSession().setAttribute("errorMsg","请先登录");
            //获取ip地址前的路径
            response.sendRedirect(request.getContextPath()+"/admin/login");
            return false;
        }
        else{
            request.getSession().removeAttribute("errorMsg");
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
