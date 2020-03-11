package com.boob.greendog.intercepter;

import com.boob.greendog.exp.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                //如果存在account cookie
                if ("account".equals(cookie.getName())) {
                    //检验
                    return cookie.getValue().equals(user.getAccount());
                }
            }
        }
        //返回登录页
        request.getRequestDispatcher("/page/login").forward(request, response);
        return false;
    }
}
