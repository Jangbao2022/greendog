package com.boob.greendog.controller;

import com.boob.greendog.exp.User;
import com.boob.greendog.model.Customer;
import com.boob.greendog.service.userService.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.jws.WebParam;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    @Autowired
    private IUserService userService;

    /**
     * 登录
     *
     * @param user
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/login")
    public String login(User user,
                        HttpServletRequest request,
                        HttpServletResponse response,
                        Model model) {

        User checkUser = userService.checkUser(user);
        if (checkUser != null) {
            //存入session
            request.getSession().setAttribute("user", checkUser);

            //存入cookie
            Cookie cookie = new Cookie("account", checkUser.getAccount());
            cookie.setMaxAge(60 * 60 * 24 * 3);
            response.addCookie(cookie);

            return "redirect:/index";
        } else {
            //添加错误信息
            model.addAttribute("message", "用户名密码错误");
            return "login";
        }

    }


    /**
     * 登出
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/logon")
    public String logon(HttpServletRequest request, HttpServletResponse response) {
        //清除session
        request.getSession().removeAttribute("user");

        //清除cookie
        Cookie cookie = new Cookie("account", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "login";
    }

    /**
     * 注册
     *
     * @return
     */
    @RequestMapping("/register")
    public String register(Customer customer, Model model) {

        boolean checkAccount = userService.checkAccount(customer.getAccount());
        if (!checkAccount) {
            //注册账号
            userService.register(customer);
            return "login";
        } else {
            model.addAttribute("message", "账号已存在,换个试试把");
            return "register";
        }
    }


    /**
     * 首页
     *
     * @return
     */
    @RequestMapping("/index")
    public String index() {
        return "redirect:/pet/allPets";
    }

    /**
     * 去首页
     *
     * @return
     */
    @RequestMapping("/")
    public String toIndex() {
        return "redirect:/pet/allPets";
    }
}
