package com.boob.greendog.controller;

import com.boob.greendog.dto.PageDto;
import com.boob.greendog.exp.DoctorExp;
import com.boob.greendog.model.Doctor;
import com.boob.greendog.service.doctorService.IDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private IDoctorService doctorService;

    /**
     * 获取所有医生
     *
     * @param sPage
     * @param sLimit
     * @param model
     * @return
     */
    @RequestMapping("allDoctors")
    public String getAllStaffs(@RequestParam(name = "sPage", required = false) String sPage,
                               @RequestParam(name = "sLimit", required = false) String sLimit,
                               Model model) {

        //设置每页10人
        sLimit = "10";
        PageDto<DoctorExp> doctorExpPageDto = doctorService.getAllDoctors(sPage, sLimit);
        model.addAttribute("pageDto", doctorExpPageDto);
        return "allDoctor";
    }


//    /**
//     * 获取所有工作人员
//     *
//     * @param sPage
//     * @param sLimit
//     * @param model
//     * @return
//     */
//    @RequestMapping("myDoctors")
//    public String getMyStaffs(@RequestParam(name = "sPage", required = false) String sPage,
//                              @RequestParam(name = "sLimit", required = false) String sLimit,
//                              HttpServletRequest request,
//                              Model model) {
//
//        User user = (User) request.getSession().getAttribute("user");
//        //设置每页5篇
//        sLimit = "10";
//        PageDto<DoctorExp> doctorExpPageDto = doctorService.getMyDoctors(sPage, sLimit, user.getId());
//        model.addAttribute("pageDto", doctorExpPageDto);
//        return "allDoctor";
//    }


    /**
     * 删除doctor
     *
     * @param id
     * @return
     */
    @RequestMapping("delete/{id}")
    public String deleteCustomer(@PathVariable("id") Long id, Model model) {
        boolean canDelete = doctorService.checkDelete(id);
        if (canDelete) {
            doctorService.deleteDoctorById(id);
        } else {
            model.addAttribute("message", "存在关联,无法删除");
        }
        return "forward:/doctor/allDoctors";
    }

    /**
     * 添加doctor页面
     *
     * @return
     */
    @RequestMapping("info/{id}")
    public String updateDoctorPage(@PathVariable("id") Long id, Model model) {
        DoctorExp doctorExp = doctorService.getDoctorById(id);
        model.addAttribute("doctorExp", doctorExp);

        return "doctor";
    }

    /**
     * 添加doctor页面
     *
     * @return
     */
    @RequestMapping("addDoctorPage")
    public String addDoctorPage() {
        return "doctor";
    }

    /**
     * 添加或修改doctor
     *
     * @return
     */
    @RequestMapping("addOrUpdateDoctor")
    public String addOrUpdateDoctor(Doctor doctor) {
        doctorService.addOrUpdateDoctor(doctor);

        return "redirect:/doctor/allDoctors";
    }
}
