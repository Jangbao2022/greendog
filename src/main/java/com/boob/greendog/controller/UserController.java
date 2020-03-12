package com.boob.greendog.controller;

import com.boob.greendog.dto.PageDto;
import com.boob.greendog.enums.UserTypeEnum;
import com.boob.greendog.exp.User;
import com.boob.greendog.model.Customer;
import com.boob.greendog.service.userService.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     * 获取个人信息
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("profile")
    public String profile(HttpServletRequest request, Model model) {
        User user = (User) request.getSession().getAttribute("user");
        if (user.getType().equals(UserTypeEnum.CUSTOMER.getType())) {
            //用户为客户
            Customer customer = userService.customer(user.getId());
            model.addAttribute("customer", customer);
        }

        return "profile";
    }


    /**
     * 更新个人信息
     *
     * @param customer
     * @return
     */
    @PostMapping("updateMe")
    public String updateMe(Customer customer, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user.getType().equals(UserTypeEnum.CUSTOMER.getType())) {
            userService.updateMe(customer);
        } else {
            BeanUtils.copyProperties(customer, user);
            userService.updateMe(user);
        }
        //更新session域
//        request.getSession().setAttribute("user", user);
        return "redirect:/user/profile";
    }

    /**
     * 获取用户信息
     *
     * @param userId
     * @param model
     * @return
     */
    @RequestMapping("/info/{id}")
    public String userInfo(@PathVariable("id") Long userId, Model model) {
        Customer customer = userService.customer(userId);
        model.addAttribute("customer", customer);
        return "user";
    }

    /**
     * 更新用户信息
     *
     * @param customer
     * @return
     */
    @PostMapping("updateCustomer")
    public String updateCustomer(Customer customer) {
        userService.addOrUpdateCustomer(customer);
        return "redirect:/user/info/" + customer.getId();
    }


    /**
     * 获取所有用户
     *
     * @param sPage
     * @param sLimit
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("allCustomers")
    public String getAllCustomers(@RequestParam(name = "sPage", required = false) String sPage,
                                  @RequestParam(name = "sLimit", required = false) String sLimit,
                                  HttpServletRequest request,
                                  Model model) {
        PageDto<Customer> allCustomers = userService.getAllCustomers(sPage, sLimit);
        model.addAttribute("pageDto", allCustomers);
        return "allCustomer";
    }


    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @RequestMapping("delete/{id}")
    public String deleteCustomer(@PathVariable("id") Long id, Model model) {
        boolean canDelete = userService.checkDelete(id);
        if (canDelete) {
            userService.deleteCustomerById(id);
        } else {
            model.addAttribute("message", "存在关联,无法删除");
        }
        return "forward:/user/allCustomers";
    }

}
