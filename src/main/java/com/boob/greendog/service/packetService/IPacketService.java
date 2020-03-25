package com.boob.greendog.service.packetService;

import com.boob.greendog.model.Appointment;
import com.boob.greendog.model.Medicine;
import com.boob.greendog.model.Packet;

/**
 * IPacketService
 */
public interface IPacketService {

    /**
     * 添加钱包
     */
    public void addPacket();

    /**
     * 根据用户id添加钱包
     *
     * @param customerId
     */
    public void addPacketForCustomer(Long customerId);

    /**
     * 根据用户获取钱包
     *
     * @param customerId
     * @return
     */
    public Packet getPacketByCustomerId(Long customerId);

    /**
     * 充值
     *
     * @param customerId
     * @param money
     */
    public void invest(Long customerId, float money);

    /**
     * 付钱
     *
     * @param appointment
     */
    public void payMoneyByAppointment(Appointment appointment);

    /**
     * 返还
     *
     * @param appointment
     */
    public void backMoneyByAppointment(Appointment appointment);


    /**
     * 付款
     *
     * @param packet
     * @param money
     */
    public void payMoney(Packet packet, float money);

    /**
     * 购买药品付款
     *
     * @param packet
     * @param medicine
     */
    public void payMoney(Packet packet, Medicine medicine);
}
