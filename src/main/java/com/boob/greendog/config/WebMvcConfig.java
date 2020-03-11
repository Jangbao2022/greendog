package com.boob.greendog.config;

import com.boob.greendog.intercepter.LoginInterceptor;
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
        ArrayList<String> excludePathPatterns = new ArrayList<>();
        //静态资源
        excludePathPatterns.add("/assets/**");
        excludePathPatterns.add("/demo/**");
        excludePathPatterns.add("/src/**");
        excludePathPatterns.add("/favicon.ico");

        //登录
        excludePathPatterns.add("/page/login");
        excludePathPatterns.add("/login");
        excludePathPatterns.add("/page/register");
        excludePathPatterns.add("/register");

        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**", "/")
                .excludePathPatterns(excludePathPatterns);
    }
}
