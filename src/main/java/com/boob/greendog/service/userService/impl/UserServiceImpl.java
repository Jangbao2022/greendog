package com.boob.greendog.service.userService.impl;

import com.boob.greendog.dto.PageDto;
import com.boob.greendog.enums.PageUrlEnum;
import com.boob.greendog.enums.UserTypeEnum;
import com.boob.greendog.exp.User;
import com.boob.greendog.mapper.*;
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

    @Autowired
    private AppointmentMapper appointmentMapper;

    @Autowired
    private InstanceMapper instanceMapper;

    @Autowired
    private BillMapper billMapper;

    @Autowired
    private DoctorMapper doctorMapper;

    @Autowired
    private PacketMapper packetMapper;

    @Autowired
    private TrolleyMapper trolleyMapper;


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
            updateCustomer(customer);

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
            updateAdmin(admin);

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
        PetExample example = new PetExample();
        example.createCriteria()
                .andMasterIdEqualTo(customer.getId());
        //更新我所有宠物的主人名

        Pet record = new Pet();
        record.setMasterNickname(customer.getNickname());

        petMapper.updateByExampleSelective(record, example);

        customer.setGmtModified(new Date());
        return customerMapper.updateByPrimaryKeySelective(customer) == 1;
    }

    @Override
    public void updateMe(Customer customer, User user) {
        //直接更新
        updateCustomer(customer);
        BeanUtils.copyProperties(customer, user);
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

    /**
     * 更新个人信息
     *
     * @param admin
     * @return
     */
    @Override
    public void updateMe(Administrator admin, User user) {
        updateAdmin(admin);
        BeanUtils.copyProperties(admin, user);
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

        return checkLinkPet(customerId) &&
                checkLinkApply(customerId) &&
                checkLinkAppointment(customerId) &&
                checkLinkInstance(customerId) &&
                checkLinkBill(customerId) &&
                checkLinkDoctor(customerId);
    }

    /**
     * 检查和宠物的关联
     *
     * @param customerId
     * @return
     */
    private boolean checkLinkPet(Long customerId) {
        //判断宠物的关联
        PetExample petExample = new PetExample();
        petExample.createCriteria()
                .andMasterIdEqualTo(customerId);
        return petMapper.countByExample(petExample) == 0;
    }

    /**
     * 检查和申请的关联
     *
     * @param customerId
     * @return
     */
    private boolean checkLinkApply(Long customerId) {
        //判断申请中的关联
        ApplyExample applyExample = new ApplyExample();
        applyExample.createCriteria()
                .andCustomerIdEqualTo(customerId);
        return applyMapper.countByExample(applyExample) == 0;
    }

    /**
     * 检查和Appointment的关联
     *
     * @param customerId
     * @return
     */
    private boolean checkLinkBill(Long customerId) {
        BillExample example = new BillExample();
        example.createCriteria()
                .andCustomerIdEqualTo(customerId);
        return billMapper.countByExample(example) == 0;
    }

    /**
     * 检查和Appointment的关联
     *
     * @param customerId
     * @return
     */
    private boolean checkLinkDoctor(Long customerId) {
        DoctorExample example = new DoctorExample();
        example.createCriteria()
                .andCustomerIdEqualTo(customerId);
        return doctorMapper.countByExample(example) == 0;
    }

    /**
     * 检查和Appointment的关联
     *
     * @param customerId
     * @return
     */
    private boolean checkLinkAppointment(Long customerId) {
        AppointmentExample example = new AppointmentExample();
        example.createCriteria()
                .andCustomerIdEqualTo(customerId);
        return appointmentMapper.countByExample(example) == 0;
    }

    /**
     * 检查和Instance的关联
     *
     * @param customerId
     * @return
     */
    private boolean checkLinkInstance(Long customerId) {
        InstanceExample example = new InstanceExample();
        example.createCriteria()
                .andCustomerIdEqualTo(customerId);
        return instanceMapper.countByExample(example) == 0;
    }


    @Override
    public void deleteCustomerById(Long userId) {
        //删除购物车
        TrolleyExample trolleyExample = new TrolleyExample();
        trolleyExample.createCriteria()
                .andCustomerIdEqualTo(userId);
        trolleyMapper.deleteByExample(trolleyExample);

        //删除钱包
        PacketExample packetExample = new PacketExample();
        packetExample.createCriteria()
                .andCustomerIdEqualTo(userId);
        packetMapper.deleteByExample(packetExample);

        customerMapper.deleteByPrimaryKey(userId);
    }
}
