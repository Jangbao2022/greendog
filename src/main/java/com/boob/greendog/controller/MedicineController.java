package com.boob.greendog.controller;

import com.boob.greendog.dto.PageDto;
import com.boob.greendog.model.Medicine;
import com.boob.greendog.service.medicineservice.IMedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/medicine")
public class MedicineController {

    @Autowired
    private IMedicineService medicineService;

    @RequestMapping("allMedicines")
    public String getAllMedicines(@RequestParam(name = "sPage", required = false) String sPage,
                                  @RequestParam(name = "sLimit", required = false) String sLimit,
                                  Model model) {

        //设置每页10人
        sLimit = "12";
        PageDto<Medicine> medicinePageDto = medicineService.getALLMedicines(sPage, sLimit);
        model.addAttribute("pageDto", medicinePageDto);
        return "allMedicine";
    }

    /**
     * 下架medicine
     *
     * @param id
     * @return
     */
    @RequestMapping("close/{id}")
    public String closeMedicine(@PathVariable("id") Long id, Model model) {
        medicineService.closeMedicineById(id);
        return "redirect:/medicine/allMedicines";
    }

    /**
     * 修改medicine页面
     *
     * @return
     */
    @RequestMapping("info/{id}")
    public String updateMedicinePage(@PathVariable("id") Long id, Model model) {
        Medicine medicine = medicineService.getMedicineById(id);
        model.addAttribute("medicine", medicine);
        return "medicine";
    }

    /**
     * 添加medicine页面
     *
     * @return
     */
    @RequestMapping("addMedicinePage")
    public String addBulletinPage() {

        return "medicine";
    }

    /**
     * 添加或修改medicine
     *
     * @return
     */
    @RequestMapping("addOrUpdateMedicine")
    public String addOrUpdateMedicine(Medicine medicine) {

        medicineService.addOrUpdateMedicine(medicine);

        return "redirect:/medicine/allMedicines";
    }

}
