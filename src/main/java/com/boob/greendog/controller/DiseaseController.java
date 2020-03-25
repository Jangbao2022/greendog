package com.boob.greendog.controller;

import com.boob.greendog.dto.PageDto;
import com.boob.greendog.model.Disease;
import com.boob.greendog.service.diseaseService.IDiseaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/disease")
public class DiseaseController {

    @Autowired
    private IDiseaseService diseaseService;

    /**
     * 获取所有病症
     *
     * @param sPage
     * @param sLimit
     * @param model
     * @return
     */
    @RequestMapping("allDiseases")
    public String getAllStaffs(@RequestParam(name = "sPage", required = false) String sPage,
                               @RequestParam(name = "sLimit", required = false) String sLimit,
                               Model model) {

        //设置每页10人
        sLimit = "10";
        PageDto<Disease> diseasePageDto = diseaseService.getAllDisease(sPage, sLimit);
        model.addAttribute("pageDto", diseasePageDto);
        return "allDisease";
    }

    /**
     * 删除disease
     *
     * @param id
     * @return
     */
    @RequestMapping("delete/{id}")
    public String deleteCustomer(@PathVariable("id") Long id, Model model) {
        boolean canDelete = diseaseService.checkDelete(id);
        if (canDelete) {
            diseaseService.deleteDisease(id);
        } else {
            model.addAttribute("message", "存在关联,无法删除");
        }
        return "forward:/disease/allDiseases";
    }

    /**
     * 添加disease页面
     *
     * @return
     */
    @RequestMapping("info/{id}")
    public String updateDiseasePage(@PathVariable("id") Long id, Model model) {
        Disease disease = diseaseService.getDiseaseById(id);
        model.addAttribute("disease", disease);

        return "disease";
    }

    /**
     * 添加doctor页面
     *
     * @return
     */
    @RequestMapping("addDiseasePage")
    public String addDiseasePage() {
        return "disease";
    }

    /**
     * 添加或修改doctor
     *
     * @return
     */
    @RequestMapping("addOrUpdateDisease")
    public String addOrUpdateDisease(Disease disease) {
        diseaseService.addOrUpdateDisease(disease);

        return "redirect:/disease/allDiseases";
    }

}
