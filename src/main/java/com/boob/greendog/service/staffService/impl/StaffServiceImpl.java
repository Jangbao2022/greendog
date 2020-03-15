package com.boob.greendog.service.staffService.impl;

import com.boob.greendog.dto.PageDto;
import com.boob.greendog.enums.PageUrlEnum;
import com.boob.greendog.exp.BulletinExp;
import com.boob.greendog.exp.StaffExp;
import com.boob.greendog.mapper.*;
import com.boob.greendog.model.*;
import com.boob.greendog.service.staffService.IStaffService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * StaffService实现类
 */
@Service
public class StaffServiceImpl implements IStaffService {


    @Autowired
    private StaffMapper staffMapper;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private ApplyMapper applyMapper;

    @Override
    public PageDto<StaffExp> getAllStaff(String sPage, String sLimit) {

        StaffExample staffExample = new StaffExample();

        //注入example,url
        return getStaffsByExample(staffExample, sPage, sLimit, PageUrlEnum.ALL_STAFFS.getUrl());
    }

    @Override
    public PageDto<StaffExp> getMyStaffs(String sPage, String sLimit, Long userId) {
        StaffExample staffExample = new StaffExample();

        staffExample.createCriteria()
                .andCustomerIdEqualTo(userId);
        //注入example,url
        return getStaffsByExample(staffExample, sPage, sLimit, PageUrlEnum.MY_STAFFS.getUrl());
    }

    /**
     * 通过example获取页面
     *
     * @param example
     * @param sPage
     * @param sLimit
     * @return
     */
    private PageDto<StaffExp> getStaffsByExample(StaffExample example, String sPage, String sLimit, String url) {
        //初始化petPageDto
        PageDto<StaffExp> staffExpPageDto = new PageDto<>(url);
        //获取Pet总数
        long total = staffMapper.countByExample(example);
        staffExpPageDto.init((int) total, sPage, sLimit);

        List<Staff> staffs = staffMapper.selectByExampleWithRowbounds(example, new RowBounds(staffExpPageDto.getOffset(), staffExpPageDto.getLimit()));

        List<StaffExp> staffExps = pottingStaffList(staffs);
        staffExpPageDto.setElements(staffExps);
        return staffExpPageDto;
    }

    /**
     * 增强staffList为staffExpList
     *
     * @param staffList
     * @return
     */
    private List<StaffExp> pottingStaffList(List<Staff> staffList) {
        List<StaffExp> bulletinExps = new ArrayList<>();
        //封装bulletin
        for (Staff staff : staffList) {
            StaffExp staffExp = pottingStaff(staff);
            bulletinExps.add(staffExp);
        }
        return bulletinExps;
    }

    /**
     * 增强staff为staffExp
     *
     * @param staff
     * @return
     */
    private StaffExp pottingStaff(Staff staff) {
        StaffExp staffExp = new StaffExp();
        Customer customer = customerMapper.selectByPrimaryKey(staff.getCustomerId());
        staffExp.setStaff(staff);
        staffExp.setCustomer(customer);
        return staffExp;
    }

    @Override
    public void deleteStaffById(Long staffId) {
        staffMapper.deleteByPrimaryKey(staffId);
    }

    @Override
    public StaffExp getStaffById(Long id) {
        return pottingStaff(staffMapper.selectByPrimaryKey(id));
    }

    @Override
    public void addOrUpdateStaff(Staff staff) {
        if (staff.getId() != null) {
            //更新工作人员
            updateStaff(staff);
        } else {
            //添加工作人员
            addStaff(staff);
        }
    }

    /**
     * 新增工作人员
     *
     * @param staff
     */
    private void addStaff(Staff staff) {
        staff.setGmtCreated(new Date());
        staff.setGmtModified(staff.getGmtCreated());
        staffMapper.insert(staff);
    }


    /**
     * 更新工作人员
     *
     * @param staff
     */
    private void updateStaff(Staff staff) {
        staff.setGmtModified(new Date());
        staffMapper.updateByPrimaryKeySelective(staff);
    }

    @Override
    public boolean checkDelete(Long id) {
        return checkApply(id);
    }

    /**
     * 检验apply
     *
     * @param id
     * @return
     */
    private boolean checkApply(Long id) {
        ApplyExample example = new ApplyExample();
        example.createCriteria()
                .andTypeEqualTo(3)
                .andReceiverIdEqualTo(id);
        return applyMapper.countByExample(example) == 0;
    }

    @Override
    public void uploadPic(Long id, String picUrl) {
        Staff staff = new Staff();
        staff.setId(id);
        staff.setPic(picUrl);

        updateStaff(staff);
    }
}
