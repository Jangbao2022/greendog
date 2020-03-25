package com.boob.greendog.controller;

import com.boob.greendog.exp.MyTrolley;
import com.boob.greendog.exp.User;
import com.boob.greendog.service.trolleyservice.ITrolleyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/trolley")
public class TrolleyController {

    @Autowired
    private ITrolleyService trolleyService;

    /**
     * 购物车
     *
     * @param sPage
     * @param sLimit
     * @param model
     * @return
     */
    @GetMapping("MyTrolley")
    public String getMyTrolley(@RequestParam(name = "sPage", required = false) String sPage,
                               @RequestParam(name = "sLimit", required = false) String sLimit,
                               HttpServletRequest request,
                               Model model) {

        User user = (User) request.getSession().getAttribute("user");
        MyTrolley myTrolley = trolleyService.getMyTrolley(user.getId());
        model.addAttribute("myTrolley", myTrolley);
        return "trolley";
    }


    /**
     * 加入购物车
     *
     * @param medicineId
     * @return
     */
    @RequestMapping("addTrolley/{medicineId}")
    public String addTrolley(@PathVariable("medicineId") Long medicineId,
                             HttpServletRequest request) {

        User user = (User) request.getSession().getAttribute("user");
        trolleyService.addTrolley(user.getId(), medicineId);

        return "redirect:/medicine/allMedicines";
    }

    /**
     * 删除购物车中商品
     *
     * @param trolleyId
     * @return
     */
    @RequestMapping("deleteTrolley/{trolleyId}")
    public String deleteTrolley(@PathVariable("trolleyId") Long trolleyId,
                                HttpServletRequest request) {

        trolleyService.deleteById(trolleyId);

        return "redirect:/trolley/MyTrolley";
    }

    /**
     * 删除购物车中商品
     *
     * @return
     */
    @RequestMapping("deleteAll")
    public String deleteAll(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");

        //删除所有
        trolleyService.deleteByCustomerId(user.getId());

        return "redirect:/trolley/MyTrolley";
    }

}
