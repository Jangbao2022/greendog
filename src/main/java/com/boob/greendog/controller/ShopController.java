package com.boob.greendog.controller;

import com.boob.greendog.enums.GoodsEnum;
import com.boob.greendog.enums.MedicineStatusEnum;
import com.boob.greendog.exp.MyTrolley;
import com.boob.greendog.exp.TrolleyExp;
import com.boob.greendog.exp.User;
import com.boob.greendog.model.Bill;
import com.boob.greendog.model.Medicine;
import com.boob.greendog.model.Packet;
import com.boob.greendog.model.Trolley;
import com.boob.greendog.service.billservice.IBillService;
import com.boob.greendog.service.medicineservice.IMedicineService;
import com.boob.greendog.service.packetService.IPacketService;
import com.boob.greendog.service.trolleyservice.ITrolleyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private IPacketService packetService;

    @Autowired
    private IMedicineService medicineService;

    @Autowired
    private ITrolleyService trolleyService;

    @Autowired
    private IBillService billService;


    /**
     * 购买
     *
     * @param medicineId
     * @return
     */
    @RequestMapping("buy/{medicineId}")
    public String buy(@PathVariable("medicineId") Long medicineId,
                      HttpServletRequest request, Model model) {

        User user = (User) request.getSession().getAttribute("user");
        Packet packet = packetService.getPacketByCustomerId(user.getId());
        Medicine medicine = medicineService.getMedicineById(medicineId);
        if (packet.getSurplusMoney() < medicine.getPrice()) {
            model.addAttribute("message", "您的余额不足");
        } else {
            //付钱
            packetService.payMoney(packet, medicine);
            model.addAttribute("message","支付成功");
            //账单
            billService.addBill(user.getId(), medicineId, medicine.getPrice(), 1, GoodsEnum.MEDICINE.getType());
        }
        return "forward:/medicine/allMedicines";
    }

    /**
     * 根据trolleyId购买
     *
     * @param trolleyId
     * @return
     */
    @RequestMapping("buyTrolley/{trolleyId}")
    public String buyTrolley(@PathVariable("trolleyId") Long trolleyId,
                             HttpServletRequest request, Model model) {

        User user = (User) request.getSession().getAttribute("user");
        Packet packet = packetService.getPacketByCustomerId(user.getId());
        Trolley trolley = trolleyService.getTrolleyById(trolleyId);

        Medicine medicine = medicineService.getMedicineById(trolley.getMedicineId());

        //总价钱
        float money = medicine.getPrice() * trolley.getCount();
        if (packet.getSurplusMoney() < money) {
            model.addAttribute("message", "您的余额不足");
        } else {
            //付钱
            packetService.payMoney(packet, money);
            model.addAttribute("message","支付成功");
            //账单
            billService.addBill(user.getId(), medicine.getId(), money, trolley.getCount(), GoodsEnum.MEDICINE.getType());

            //删除购物车中该商品
            trolleyService.deleteById(trolley.getId());
        }
        return "forward:/trolley/MyTrolley";
    }

    /**
     * 购买所有
     *
     * @return
     */
    @RequestMapping("buyAll")
    public String buyTrolley(HttpServletRequest request, Model model) {

        User user = (User) request.getSession().getAttribute("user");

        Packet packet = packetService.getPacketByCustomerId(user.getId());

        MyTrolley myTrolley = trolleyService.getMyTrolley(user.getId());

        float money = myTrolley.getPrice();
        if (packet.getSurplusMoney() < money) {
            model.addAttribute("message", "您的余额不足");
        } else {
            //付钱
            packetService.payMoney(packet, money);
            model.addAttribute("message","支付成功");
            for (TrolleyExp trolleyExp : myTrolley.getTrolleyExps()) {
                Trolley trolley = trolleyExp.getTrolley();
                if (trolleyExp.getMedicine().getStatus().equals(MedicineStatusEnum.CLOSE.getType())) {
                    //如果商品已下架
                    continue;
                }
                //账单
                billService.addBill(user.getId(), trolleyExp.getMedicine().getId(), money, trolley.getCount(), GoodsEnum.MEDICINE.getType());
                //删除购物车中该商品
                trolleyService.deleteById(trolley.getId());
            }
        }
        return "forward:/trolley/MyTrolley";
    }
}
