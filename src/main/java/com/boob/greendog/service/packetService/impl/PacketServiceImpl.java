package com.boob.greendog.service.packetService.impl;

import com.boob.greendog.mapper.CustomerMapper;
import com.boob.greendog.mapper.PacketMapper;
import com.boob.greendog.model.*;
import com.boob.greendog.service.packetService.IPacketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * PacketServiceImpl
 */
@Service
public class PacketServiceImpl implements IPacketService {

    @Autowired
    private PacketMapper packetMapper;

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public void addPacket() {

        //获取新建用户(!!!并发)
        List<Customer> customers = customerMapper.selectByExample(new CustomerExample());
        Long customerId = customers.get(customers.size() - 1).getId();
        addPacketForCustomer(customerId);
    }

    @Override
    public void addPacketForCustomer(Long customerId) {

        Packet packet = new Packet();
        packet.setGmtCreated(new Date());
        packet.setGmtModified(packet.getGmtCreated());
        packet.setCustomerId(customerId);
        packet.setTotalMoney(100.f);
        packet.setSurplusMoney(100.f);
        packet.setShopMoney(0.f);

        packetMapper.insert(packet);
    }

    /**
     * 根据用户获取钱包
     *
     * @param customerId
     * @return
     */
    @Override
    public Packet getPacketByCustomerId(Long customerId) {
        PacketExample example = new PacketExample();
        example.createCriteria()
                .andCustomerIdEqualTo(customerId);
        List<Packet> packets = packetMapper.selectByExample(example);
        if (packets.size() == 1) {
            return packets.get(0);
        }
        return null;
    }

    @Override
    public void invest(Long customerId, float money) {

    }

    /**
     * 改变用户钱包
     *
     * @param packet
     * @param price
     */
    private void changeMoney(Packet packet, float price) {

        //改变消费额
        packet.setShopMoney(packet.getShopMoney() + price);
        //改变余额
        packet.setSurplusMoney(packet.getSurplusMoney() - price);

        updatePacket(packet);
    }

    /**
     * 更新账户
     *
     * @param packet
     */
    private void updatePacket(Packet packet) {
        packet.setGmtModified(new Date());
        //写入数据库
        packetMapper.updateByPrimaryKeySelective(packet);
    }

    /**
     * 扣用户余额
     *
     * @param appointment
     */
    @Override
    public void payMoneyByAppointment(Appointment appointment) {
        changeMoneyByType(appointment, false);
    }

    /**
     * 返还用户的钱
     *
     * @param appointment
     */
    public void backMoneyByAppointment(Appointment appointment) {
        changeMoneyByType(appointment, true);
    }

    /**
     * 通过类型改变用户余额
     *
     * @param appointment
     * @param plus        是否加钱
     */
    private void changeMoneyByType(Appointment appointment, boolean plus) {
        Packet packet = getPacketByCustomerId(appointment.getCustomerId());
        //获取看病费用
        float price = appointment.getPrice();

        price = plus ? -price : price;

        changeMoney(packet, price);
    }

    @Override
    public void payMoney(Packet packet, Medicine medicine) {
        changeMoney(packet, medicine.getPrice());
    }

    @Override
    public void payMoney(Packet packet, float money) {
        changeMoney(packet, money);
    }
}
