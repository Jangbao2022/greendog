package com.boob.greendog.service.userService;

import com.boob.greendog.exp.User;
import com.boob.greendog.model.Customer;

/**
 * UserService接口
 */
public interface IUserService {

    /**
     * 检验user
     *
     * @param user
     * @return 若不存在返回null
     */
    public User checkUser(User user);


    /**
     * 检查账号是否已存在
     *
     * @param account
     * @return true 账号已存在
     */
    public boolean checkAccount(String account);

    /**
     * 注册
     *
     * @param customer
     * @return true 注册成功
     */
    public boolean register(Customer customer);
}
