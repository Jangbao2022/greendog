package com.boob.greendog.service.billservice.impl;

import com.boob.greendog.dto.PageDto;
import com.boob.greendog.enums.GoodsEnum;
import com.boob.greendog.enums.PageUrlEnum;
import com.boob.greendog.exp.BillExp;
import com.boob.greendog.exp.InstanceExp;
import com.boob.greendog.mapper.BillMapper;
import com.boob.greendog.mapper.CustomerMapper;
import com.boob.greendog.mapper.InstanceMapper;
import com.boob.greendog.mapper.MedicineMapper;
import com.boob.greendog.model.*;
import com.boob.greendog.service.billservice.IBillService;
import com.boob.greendog.service.instanceservice.IInstanceService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BillServiceImpl implements IBillService {


    @Autowired
    private IInstanceService instanceService;

    @Autowired
    private InstanceMapper instanceMapper;

    @Autowired
    private BillMapper billMapper;

    @Autowired
    private MedicineMapper medicineMapper;

    @Autowired
    private CustomerMapper customerMapper;


    @Override
    public PageDto<BillExp> getMyBills(String sPage, String sLimit, Long userId) {

        BillExample billExample = new BillExample();
        billExample.createCriteria()
                .andCustomerIdEqualTo(userId);
        return getAppliesByExample(billExample, sPage, sLimit, PageUrlEnum.MY_BILLS.getUrl());
    }

    @Override
    public PageDto<BillExp> getAllBills(String sPage, String sLimit) {
        BillExample billExample = new BillExample();
        return getAppliesByExample(billExample, sPage, sLimit, PageUrlEnum.ALL_BILLS.getUrl());
    }


    /**
     * 通过 条件获取dto
     *
     * @param example
     * @param sPage
     * @param sLimit
     * @return
     */
    private PageDto<BillExp> getAppliesByExample(BillExample example, String sPage, String sLimit, String url) {
        //applyPageDto
        PageDto<BillExp> billExpPageDto = new PageDto<>(url);
        //获取apply总数
        long total = billMapper.countByExample(example);
        billExpPageDto.init((int) total, sPage, sLimit);

        List<Bill> bills = billMapper.selectByExampleWithRowbounds(example, new RowBounds(billExpPageDto.getOffset(), billExpPageDto.getLimit()));
        List<BillExp> billExps = pottingBillList(bills);

        billExpPageDto.setElements(billExps);
        return billExpPageDto;
    }

    /**
     * 封装bill集合
     *
     * @param bills
     * @return
     */
    private List<BillExp> pottingBillList(List<Bill> bills) {
        List<BillExp> billExps = new ArrayList<>();
        for (Bill bill : bills) {
            BillExp billExp = pottingBill(bill);
            billExps.add(billExp);
        }
        return billExps;
    }

    /**
     * 封装bill
     *
     * @param bill
     * @return
     */
    private BillExp pottingBill(Bill bill) {
        BillExp billExp = new BillExp();
        Long goodsId = bill.getGoodsId();
        if (bill.getType().equals(GoodsEnum.DOCTOR.getType())) {
            //医生
            InstanceExp instanceExp = instanceService.getInstanceById(goodsId);
            billExp.setInstanceExp(instanceExp);

        } else {
            //药品
            Medicine medicine = medicineMapper.selectByPrimaryKey(goodsId);
            billExp.setMedicine(medicine);
        }

        billExp.setCustomer(customerMapper.selectByPrimaryKey(bill.getCustomerId()));

        billExp.setBill(bill);

        return billExp;
    }


    @Override
    public void addBillByAppointment(Appointment appointment) {
        //添加账单
        Bill bill = new Bill();
        bill.setCustomerId(appointment.getCustomerId());

        //消费数额
        bill.setPrice(appointment.getPrice());

        //设置类型为看病
        bill.setType(GoodsEnum.DOCTOR.getType());
        //数量设为1
        bill.setCount(1);
        //获取最近病例id(!!!并发出错)
        List<Instance> instances = instanceMapper.selectByExample(new InstanceExample());
        Long goodsId = instances.get(instances.size() - 1).getId();
        bill.setGoodsId(goodsId);

        addBill(bill);
    }

    @Override
    public void addBill(Long customerId, Long goodsId, float price, int count, int type) {

        Bill bill = new Bill();
        bill.setPrice(price);
        bill.setCount(count);
        bill.setGoodsId(goodsId);
        bill.setType(type);
        bill.setCustomerId(customerId);

        addBill(bill);
    }

    @Override
    public void addBill(Bill bill) {
        bill.setGmtCreated(new Date());
        bill.setGmtModified(bill.getGmtCreated());

        billMapper.insert(bill);
    }
}
