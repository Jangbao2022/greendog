package com.boob.greendog.controller;

import com.boob.greendog.dto.PageDto;
import com.boob.greendog.exp.BulletinExp;
import com.boob.greendog.model.Bulletin;
import com.boob.greendog.service.bulletinService.IBulletinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/bulletin")
public class BulletinController {

    @Autowired
    private IBulletinService bulletinService;

    /**
     * 获取所有公告
     *
     * @param sPage
     * @param sLimit
     * @param model
     * @return
     */
    @RequestMapping("allBulletins")
    public String getAllBulletin(@RequestParam(name = "sPage", required = false) String sPage,
                                 @RequestParam(name = "sLimit", required = false) String sLimit,
                                 Model model) {

        //设置每页5篇
        sLimit = "5";
        PageDto<BulletinExp> bulletinDto = bulletinService.getAllBulletin(sPage, sLimit);
        model.addAttribute("pageDto", bulletinDto);
        return "allBulletin";
    }


    /**
     * 删除公告
     *
     * @param id
     * @return
     */
    @RequestMapping("delete/{id}")
    public String deleteBulletin(@PathVariable("id") Long id, Model model) {
        bulletinService.deleteBulletinById(id);
        return "redirect:/bulletin/allBulletins";
    }

    /**
     * 修改bulletin页面
     *
     * @return
     */
    @RequestMapping("info/{id}")
    public String updateBulletinPage(@PathVariable("id") Long id, Model model) {
        BulletinExp bulletinExp = bulletinService.getBulletinById(id);
        model.addAttribute("bulletinExp", bulletinExp);

        return "bulletin";
    }

    /**
     * 添加bulletin页面
     *
     * @return
     */
    @RequestMapping("addBulletinPage")
    public String addBulletinPage() {

        return "bulletin";
    }

    /**
     * 添加或修改bulletin
     *
     * @return
     */
    @RequestMapping("addOrUpdateBulletin")
    public String addOrUpdateBulletin(Bulletin bulletin) {
        bulletinService.addOrUpdateBulletin(bulletin);

        return "redirect:/bulletin/allBulletins";
    }


}
