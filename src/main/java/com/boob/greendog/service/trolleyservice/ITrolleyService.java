package com.boob.greendog.service.trolleyservice;

import com.boob.greendog.exp.MyTrolley;
import com.boob.greendog.model.Trolley;

/**
 * ITrolleyService
 */
public interface ITrolleyService {

    /**
     * 获取用户购物车
     *
     * @param customerId
     * @return
     */
    public MyTrolley getMyTrolley(Long customerId);

    /**
     * 根据id获取
     *
     * @param trolleyId
     * @return
     */
    public Trolley getTrolleyById(Long trolleyId);

    /**
     * 添加商品进购物车
     *
     * @param customerId
     * @return
     */
    public void addTrolley(Long customerId, Long medicineId);

    /**
     * 根据id删除
     *
     * @param id
     */
    void deleteById(Long id);

    /**
     * 根据id删除
     *
     * @param customerId
     */
    void deleteByCustomerId(Long customerId);
}
