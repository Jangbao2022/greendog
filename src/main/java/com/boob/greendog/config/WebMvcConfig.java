package com.boob.greendog.config;

import com.boob.greendog.interceptor.CustomerInterceptor;
import com.boob.greendog.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 配置viewController
     *
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        registry.addViewController("/page/login").setViewName("login");
        registry.addViewController("/page/register").setViewName("register");
    }

    /**
     * 配置拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        ArrayList<String> loginExPathList = new ArrayList<>();
        //静态资源
        loginExPathList.add("/assets/**");
        loginExPathList.add("/demo/**");
        loginExPathList.add("/src/**");
        loginExPathList.add("/favicon.ico");

        //登录
        loginExPathList.add("/page/login");
        loginExPathList.add("/login");
        loginExPathList.add("/page/register");
        loginExPathList.add("/register");

        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**", "/")
                .excludePathPatterns(loginExPathList);

        ArrayList<String> customerPathList = new ArrayList<>();

        //各种删除更新操作
        customerPathList.add("/apply/delete/**");

        customerPathList.add("/bulletin/delete/**");
        customerPathList.add("/bulletin/addOrUpdateBulletin");

        customerPathList.add("/pet/delete/**");
        customerPathList.add("/pet/addOrUpdateBulletin");

        customerPathList.add("/user/**");

        registry.addInterceptor(new CustomerInterceptor())
                .addPathPatterns(customerPathList)
                .excludePathPatterns("/user/profile",
                        "/user/updateMe"
                );

    }
}
