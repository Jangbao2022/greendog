package com.boob.greendog.controller;

import com.boob.greendog.dto.PageDto;
import com.boob.greendog.exp.BillExp;
import com.boob.greendog.exp.MyTrolley;
import com.boob.greendog.exp.User;
import com.boob.greendog.service.billservice.IBillService;
import com.boob.greendog.service.trolleyservice.ITrolleyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/bill")
public class BillController {


    @Autowired
    private IBillService billService;

    /**
     * 我的账单
     *
     * @param sPage
     * @param sLimit
     * @param model
     * @return
     */
    @GetMapping("myBills")
    public String getMyBill(@RequestParam(name = "sPage", required = false) String sPage,
                            @RequestParam(name = "sLimit", required = false) String sLimit,
                            HttpServletRequest request,
                            Model model) {

        User user = (User) request.getSession().getAttribute("user");
        PageDto<BillExp> myBills = billService.getMyBills(sPage, sLimit, user.getId());
        model.addAttribute("pageDto", myBills);
        return "bills";
    }


    /**
     * 所有账单
     *
     * @param sPage
     * @param sLimit
     * @param model
     * @return
     */
    @GetMapping("allBills")
    public String getAllBill(@RequestParam(name = "sPage", required = false) String sPage,
                             @RequestParam(name = "sLimit", required = false) String sLimit,
                             Model model) {

        PageDto<BillExp> myBills = billService.getAllBills(sPage, sLimit);
        model.addAttribute("pageDto", myBills);
        return "bills";
    }


}
