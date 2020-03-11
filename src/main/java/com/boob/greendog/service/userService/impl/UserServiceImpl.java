package com.boob.greendog.service.userService.impl;

import com.boob.greendog.enums.UserTypeEnum;
import com.boob.greendog.exp.User;
import com.boob.greendog.mapper.AdministratorMapper;
import com.boob.greendog.mapper.CustomerMapper;
import com.boob.greendog.model.Administrator;
import com.boob.greendog.model.AdministratorExample;
import com.boob.greendog.model.Customer;
import com.boob.greendog.model.CustomerExample;
import com.boob.greendog.service.userService.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * iUserService具体实现
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private AdministratorMapper administratorMapper;

    @Override
    public User checkUser(User user) {
        if (user.getType().equals(UserTypeEnum.CUSTOMER.getType())) {
            return checkCustomer(user);
        } else if (user.getType().equals(UserTypeEnum.ADMIN.getType())) {
            return checkAdmin(user);
        }
        return null;
    }

    /**
     * 在customer中找
     *
     * @param user
     * @return
     */
    private User checkCustomer(User user) {

        CustomerExample example = new CustomerExample();
        example.createCriteria()
                .andAccountEqualTo(user.getAccount())
                .andPasswordEqualTo(user.getPassword());
        //根据账号密码查出数据库中的customer
        List<Customer> customers = customerMapper.selectByExample(example);
        if (customers.size() == 1) {
            Customer customer = customers.get(0);

            //把属性中的值copy给user
            BeanUtils.copyProperties(customer, user);

            //修改日期更新
            customer.setGmtModified(new Date());
            customerMapper.updateByPrimaryKeySelective(customer);

            return user;
        }
        return null;
    }

    /**
     * 在customer中找
     *
     * @param user
     * @return
     */
    private User checkAdmin(User user) {
        AdministratorExample example = new AdministratorExample();
        example.createCriteria()
                .andAccountEqualTo(user.getAccount())
                .andPasswordEqualTo(user.getPassword());
        //根据账号密码查出数据库中的customer
        List<Administrator> admins = administratorMapper.selectByExample(example);
        if (admins.size() == 1) {
            Administrator admin = admins.get(0);

            //把属性中的值copy给user
            BeanUtils.copyProperties(admin, user);

            //修改日期更新
            admin.setGmtModified(new Date());
            administratorMapper.updateByPrimaryKeySelective(admin);

            return user;
        }
        return null;
    }


    @Override
    public boolean checkAccount(String account) {
        CustomerExample example = new CustomerExample();
        example.createCriteria().andAccountEqualTo(account);
        return customerMapper.countByExample(example) >= 1;
    }

    @Override
    public boolean register(Customer customer) {
        customer.setGmtModified(new Date());
        customer.setGmtModified(customer.getGmtCreated());
        return customerMapper.insert(customer) == 1;
    }
}
