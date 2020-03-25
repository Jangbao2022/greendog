package com.boob.greendog.service.billservice;

import com.boob.greendog.dto.PageDto;
import com.boob.greendog.exp.BillExp;
import com.boob.greendog.exp.BulletinExp;
import com.boob.greendog.model.Appointment;
import com.boob.greendog.model.Bill;

/**
 * IBillService
 */
public interface IBillService {


    /**
     * 获取所有账单
     *
     * @return
     */
    public PageDto<BillExp> getAllBills(String sPage, String sLimit);

    /**
     * 获取我的账单
     *
     * @return
     */
    public PageDto<BillExp> getMyBills(String sPage, String sLimit, Long userId);

    /**
     * 通过Appointment添加账单
     *
     * @param appointment
     */
    public void addBillByAppointment(Appointment appointment);


    /**
     * 通过customerId,goodsId,type添加账单
     *
     * @param customerId
     * @param goodsId
     * @param count      数量
     * @param type       商品类型 看病还是买药
     */
    public void addBill(Long customerId, Long goodsId, float price, int count, int type);

    /**
     * @param bill
     */
    public void addBill(Bill bill);
}
