package com.boob.greendog.service.applyService.impl;

import com.boob.greendog.dto.PageDto;
import com.boob.greendog.enums.PageUrlEnum;
import com.boob.greendog.exp.ApplyExp;
import com.boob.greendog.mapper.ApplyMapper;
import com.boob.greendog.mapper.CustomerMapper;
import com.boob.greendog.mapper.PetMapper;
import com.boob.greendog.mapper.StaffMapper;
import com.boob.greendog.model.*;
import com.boob.greendog.service.applyService.IApplyService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * applyService实现类
 */
@Service
public class ApplyServiceImpl implements IApplyService {

    @Autowired
    private ApplyMapper applyMapper;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private PetMapper petMapper;

    @Autowired
    private StaffMapper staffMapper;

    @Override
    public PageDto<ApplyExp> getMyApplies(String sPage, String sLimit, Long userId) {

        ApplyExample applyExample = new ApplyExample();
        applyExample.createCriteria()
                .andCustomerIdEqualTo(userId);
        return getAppliesByExample(applyExample, sPage, sLimit, PageUrlEnum.MY_APPLIES.getUrl());
    }

    @Override
    public PageDto<ApplyExp> getAllApplies(String sPage, String sLimit) {
        ApplyExample applyExample = new ApplyExample();
        return getAppliesByExample(applyExample, sPage, sLimit, PageUrlEnum.ALL_APPLIES.getUrl());
    }


    /**
     * 通过 条件获取dto
     *
     * @param example
     * @param sPage
     * @param sLimit
     * @return
     */
    private PageDto<ApplyExp> getAppliesByExample(ApplyExample example, String sPage, String sLimit, String url) {
        //applyPageDto
        PageDto<ApplyExp> applyPageDto = new PageDto<>(url);
        //获取apply总数
        long total = applyMapper.countByExample(example);
        applyPageDto.init((int) total, sPage, sLimit);

        List<Apply> applies = applyMapper.selectByExampleWithRowbounds(example, new RowBounds(applyPageDto.getOffset(), applyPageDto.getLimit()));
        List<ApplyExp> applyExpList = pottingApplyList(applies);

        applyPageDto.setElements(applyExpList);
        return applyPageDto;
    }

    /**
     * 封装applyList
     *
     * @param applies
     * @return
     */
    private List<ApplyExp> pottingApplyList(List<Apply> applies) {
        List<ApplyExp> applyExpList = new ArrayList<>();
        //封装apply
        for (Apply apply : applies) {
            ApplyExp applyExp = pottingApply(apply);
            applyExpList.add(applyExp);
        }
        return applyExpList;
    }

    /**
     * 封装apply
     *
     * @param apply
     * @return
     */
    private ApplyExp pottingApply(Apply apply) {
        ApplyExp applyExp = new ApplyExp();
        Customer customer = customerMapper.selectByPrimaryKey(apply.getCustomerId());
        if (apply.getType() == 3) {
            //如果申请的工作人员
            Staff staff = staffMapper.selectByPrimaryKey(apply.getReceiverId());
            applyExp.setStaff(staff);
        } else {
            //申请的宠物
            Pet pet = petMapper.selectByPrimaryKey(apply.getReceiverId());
            applyExp.setPet(pet);
        }
        applyExp.setApply(apply);
        applyExp.setCustomer(customer);

        return applyExp;
    }

    @Override
    public void consentApply(Long applyId) {
        //同意请求
        Apply apply = applyMapper.selectByPrimaryKey(applyId);

        //找到申请人
        Customer customer = customerMapper.selectByPrimaryKey(apply.getCustomerId());

        if (apply.getType() == 3) {

            Staff staff = staffMapper.selectByPrimaryKey(apply.getReceiverId());
            //同意预约
            consentOrder(customer, staff);
            staff.setGmtModified(new Date());
            staffMapper.updateByPrimaryKeySelective(staff);

        } else {
            //找到申请的宠物
            Pet pet = petMapper.selectByPrimaryKey(apply.getReceiverId());

            if (apply.getType().equals(1)) {
                //同意领养
                consentAdopt(customer, pet);
            } else {
                //同意寄养
                consentSend(pet);
            }
            //更新宠物信息
            pet.setGmtModified(new Date());
            petMapper.updateByPrimaryKeySelective(pet);
        }

        //同意请求
        apply.setReply(1);
        //更新请求
        updateApply(apply);
    }

    /**
     * 同意预约
     */
    private void consentOrder(Customer customer, Staff staff) {
        //更新被申请人信息
        staff.setCustomerId(customer.getId());
    }

    /**
     * 同意领养
     */
    private void consentAdopt(Customer customer, Pet pet) {
        //更新宠物属性
        pet.setMasterId(customer.getId());
        pet.setMasterNickname(customer.getNickname());
        //宠物状态改为领养
        pet.setStatus(1);
    }

    /**
     * 同意寄养
     */
    private void consentSend(Pet pet) {
        //宠物状态改为寄养
        pet.setStatus(2);
    }

    @Override
    public void rejectApply(Long applyId) {
        //拒绝请求
        Apply apply = new Apply();
        apply.setId(applyId);
        apply.setReply(2);//设为拒绝
        updateApply(apply);
    }

    /**
     * 更新apply
     */
    private void updateApply(Apply apply) {

        //设置为已处理
        apply.setHandle(2);
        apply.setGmtModified(new Date());
        applyMapper.updateByPrimaryKeySelective(apply);
    }


    @Override
    public void adopt(Apply apply) {
        //设置类型为领养
        apply.setType(1);
        addApply(apply);
    }

    @Override
    public void send(Apply apply) {
        //设置类型为寄养
        apply.setType(2);
        addApply(apply);
    }

    @Override
    public void order(Apply apply) {
        //设置类型为预约
        apply.setType(3);
        addApply(apply);
    }

    /**
     * 添加请求
     *
     * @param apply
     */
    private void addApply(Apply apply) {
        //设置为未处理
        apply.setHandle(1);

        apply.setGmtCreated(new Date());
        apply.setGmtModified(apply.getGmtCreated());
        applyMapper.insert(apply);
    }

    @Override
    public void delApply(Long applyId) {
        applyMapper.deleteByPrimaryKey(applyId);
    }
}
