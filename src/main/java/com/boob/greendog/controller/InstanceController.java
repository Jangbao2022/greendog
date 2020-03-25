package com.boob.greendog.controller;

import com.boob.greendog.dto.PageDto;
import com.boob.greendog.exp.BillExp;
import com.boob.greendog.exp.InstanceExp;
import com.boob.greendog.service.instanceservice.IInstanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/instance")
public class InstanceController {

    @Autowired
    private IInstanceService instanceService;

    /**
     * 所有账单
     *
     * @param sPage
     * @param sLimit
     * @param model
     * @return
     */
    @GetMapping("allInstances")
    public String getAllBill(@RequestParam(name = "sPage", required = false) String sPage,
                             @RequestParam(name = "sLimit", required = false) String sLimit,
                             Model model) {

        PageDto<InstanceExp> instanceExpPageDto = instanceService.getAllInstances(sPage, sLimit);
        model.addAttribute("pageDto", instanceExpPageDto);
        return "allInstance";
    }
}
