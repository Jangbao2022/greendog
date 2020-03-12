package com.boob.greendog.service.userService.impl;

import com.boob.greendog.dto.PageDto;
import com.boob.greendog.enums.PageUrlEnum;
import com.boob.greendog.enums.UserTypeEnum;
import com.boob.greendog.exp.User;
import com.boob.greendog.mapper.AdministratorMapper;
import com.boob.greendog.mapper.ApplyMapper;
import com.boob.greendog.mapper.CustomerMapper;
import com.boob.greendog.mapper.PetMapper;
import com.boob.greendog.model.*;
import com.boob.greendog.service.userService.IUserService;
import org.apache.ibatis.session.RowBounds;
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


    @Autowired
    private PetMapper petMapper;

    @Autowired
    private ApplyMapper applyMapper;

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
        return addCustomer(customer);
    }

    @Override
    public Administrator administrator(Long userId) {
        Administrator administrator = administratorMapper.selectByPrimaryKey(userId);
        return administrator;
    }

    @Override
    public Customer customer(Long userId) {
        Customer customer = customerMapper.selectByPrimaryKey(userId);
        return customer;
    }

    @Override
    public boolean addOrUpdateCustomer(Customer customer) {
        if (customer.getId() == null) {
            return addCustomer(customer);
        } else {
            return updateCustomer(customer);
        }
    }

    /**
     * 增加客户
     *
     * @param customer
     * @return
     */
    private boolean addCustomer(Customer customer) {
        customer.setGmtCreated(new Date());
        customer.setGmtModified(customer.getGmtCreated());
        return customerMapper.insert(customer) == 1;
    }

    /**
     * 更新用户
     *
     * @param customer
     * @return
     */
    private boolean updateCustomer(Customer customer) {
        customer.setGmtModified(new Date());
        return customerMapper.updateByPrimaryKeySelective(customer) == 1;
    }

    @Override
    public boolean updateMe(Customer customer) {
        //直接更新
        return updateCustomer(customer);
    }

    /**
     * 更新管理员
     *
     * @param administrator
     * @return
     */
    private boolean updateAdmin(Administrator administrator) {
        administrator.setGmtModified(new Date());
        return administratorMapper.updateByPrimaryKeySelective(administrator) == 1;

    }

    public boolean updateMe(User user) {
        if (user.getType().equals(UserTypeEnum.CUSTOMER.getType())) {
            Customer customer = new Customer();
            BeanUtils.copyProperties(user, customer);
            return updateCustomer(customer);
        } else {
            Administrator admin = new Administrator();
            BeanUtils.copyProperties(user, admin);
            return updateAdmin(admin);
        }
    }

    @Override
    public PageDto<Customer> getAllCustomers(String sPage, String sLimit) {
        PageDto<Customer> customerDto = new PageDto<>(PageUrlEnum.ALL_CUSTOMERS.getUrl());

        CustomerExample bulletinExample = new CustomerExample();
        long total = customerMapper.countByExample(bulletinExample);
        customerDto.init((int) total, sPage, sLimit);

        List<Customer> customers = customerMapper.selectByExampleWithRowbounds(bulletinExample, new RowBounds(customerDto.getOffset(), customerDto.getLimit()));

        customerDto.setElements(customers);
        //按时间倒序
        return customerDto;

    }

    @Override
    public boolean checkDelete(Long customerId) {
        //判断宠物的关联
        PetExample petExample = new PetExample();
        petExample.createCriteria()
                .andMasterIdEqualTo(customerId);
        long petCustomers = petMapper.countByExample(petExample);
        if (petCustomers != 0) {
            return false;
        }

        //判断申请中的关联
        ApplyExample applyExample = new ApplyExample();
        applyExample.createCriteria()
                .andCustomerIdEqualTo(customerId);
        long applyCustomers = applyMapper.countByExample(applyExample);
        if (applyCustomers != 0) {
            return false;
        }

        return true;
    }

    @Override
    public void deleteCustomerById(Long userId) {
        customerMapper.deleteByPrimaryKey(userId);
    }
}
