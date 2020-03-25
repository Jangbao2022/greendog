package com.boob.greendog.service.trolleyservice.impl;

import com.boob.greendog.exp.MyTrolley;
import com.boob.greendog.exp.TrolleyExp;
import com.boob.greendog.mapper.MedicineMapper;
import com.boob.greendog.mapper.TrolleyMapper;
import com.boob.greendog.model.Medicine;
import com.boob.greendog.model.Trolley;
import com.boob.greendog.model.TrolleyExample;
import com.boob.greendog.service.trolleyservice.ITrolleyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TrolleyServiceImpl implements ITrolleyService {

    @Autowired
    private TrolleyMapper trolleyMapper;
    @Autowired
    private MedicineMapper medicineMapper;

    @Override
    public MyTrolley getMyTrolley(Long customerId) {

        MyTrolley myTrolley = new MyTrolley();

        TrolleyExample example = new TrolleyExample();
        example.createCriteria()
                .andCustomerIdEqualTo(customerId);
        List<Trolley> trolleys = trolleyMapper.selectByExample(example);

        ArrayList<TrolleyExp> trolleyExps = new ArrayList<>();
        //初始化价格
        float totalPrice = 0f;
        for (Trolley trolley : trolleys) {

            TrolleyExp trolleyExp = new TrolleyExp();
            //查出每个商品
            Medicine medicine = medicineMapper.selectByPrimaryKey(trolley.getMedicineId());
            trolleyExp.setMedicine(medicine);
            trolleyExp.setTrolley(trolley);

            //累加价格
            totalPrice += medicine.getPrice() * trolley.getCount();

            trolleyExps.add(trolleyExp);
        }

        myTrolley.setTrolleyExps(trolleyExps);
        myTrolley.setPrice(totalPrice);
        return myTrolley;
    }

    @Override
    public void addTrolley(Long customerId, Long medicineId) {
        TrolleyExample example = new TrolleyExample();
        example.createCriteria()
                .andCustomerIdEqualTo(customerId)
                .andMedicineIdEqualTo(medicineId);
        List<Trolley> trolleys = trolleyMapper.selectByExample(example);
        if (trolleys.size() == 0) {
            //购物车中没有
            Trolley trolley = new Trolley();
            trolley.setCount(1);
            trolley.setCustomerId(customerId);
            trolley.setMedicineId(medicineId);
            insertTrolley(trolley);

        } else {
            //购物车中有 数量加一
            Trolley trolley = trolleys.get(0);
            trolley.setCount(trolley.getCount() + 1);
            updateTrolley(trolley);
        }

    }

    @Override
    public Trolley getTrolleyById(Long trolleyId) {
        return trolleyMapper.selectByPrimaryKey(trolleyId);
    }

    /**
     * 更新
     *
     * @param trolley
     */
    private void updateTrolley(Trolley trolley) {

        trolley.setGmtModified(trolley.getGmtCreated());
        trolleyMapper.updateByPrimaryKeySelective(trolley);
    }

    /**
     * 向数据库插入
     *
     * @param trolley
     */
    private void insertTrolley(Trolley trolley) {

        trolley.setGmtCreated(new Date());
        trolley.setGmtModified(trolley.getGmtCreated());

        trolleyMapper.insert(trolley);
    }

    @Override
    public void deleteById(Long id) {
        trolleyMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteByCustomerId(Long customerId) {
        TrolleyExample example = new TrolleyExample();
        example.createCriteria()
                .andCustomerIdEqualTo(customerId);
        trolleyMapper.deleteByExample(example);
    }
}
