package com.boob.greendog.service.userService;

import com.boob.greendog.dto.PageDto;
import com.boob.greendog.exp.User;
import com.boob.greendog.model.Administrator;
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


    /**
     * 根据用户id获取用户信息(管理员)
     *
     * @param userId
     * @return
     */
    public Administrator administrator(Long userId);


    /**
     * 根据用户id获取用户信息(客户)
     *
     * @param userId
     * @return
     */
    public Customer customer(Long userId);


    /**
     * 增加或更新客户
     *
     * @param customer
     * @return
     */
    public boolean addOrUpdateCustomer(Customer customer);


    /**
     * 更新用户
     *
     * @param user
     * @return
     */
    public boolean updateMe(User user);

    /**
     * 更新用户
     *
     * @param customer
     * @return
     */
    public boolean updateMe(Customer customer);


    /**
     * 获取所有用户
     *
     * @return
     */
    public PageDto<Customer> getAllCustomers(String sPage, String sLimit);

    /**
     * 检验用户是否在其他地方有关联
     *
     * @param customerId
     * @return
     */
    public boolean checkDelete(Long customerId);


    /**
     * 删除用户
     *
     * @param userId
     * @return
     */
    public void deleteCustomerById(Long userId);
}
