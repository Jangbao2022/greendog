package com.boob.greendog.controller;

import com.boob.greendog.dto.PageDto;
import com.boob.greendog.exp.StaffExp;
import com.boob.greendog.exp.User;
import com.boob.greendog.model.Staff;
import com.boob.greendog.service.staffService.IStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    private IStaffService staffService;

    /**
     * 获取所有工作人员
     *
     * @param sPage
     * @param sLimit
     * @param model
     * @return
     */
    @RequestMapping("allStaffs")
    public String getAllStaffs(@RequestParam(name = "sPage", required = false) String sPage,
                              @RequestParam(name = "sLimit", required = false) String sLimit,
                              Model model) {

        //设置每页10人
        sLimit = "10";
        PageDto<StaffExp> StaffDto = staffService.getAllStaff(sPage, sLimit);
        model.addAttribute("pageDto", StaffDto);
        return "allStaff";
    }


    /**
     * 获取所有工作人员
     *
     * @param sPage
     * @param sLimit
     * @param model
     * @return
     */
    @RequestMapping("myStaffs")
    public String getMyStaffs(@RequestParam(name = "sPage", required = false) String sPage,
                              @RequestParam(name = "sLimit", required = false) String sLimit,
                              HttpServletRequest request,
                              Model model) {

        User user = (User) request.getSession().getAttribute("user");
        //设置每页5篇
        sLimit = "10";
        PageDto<StaffExp> StaffDto = staffService.getMyStaffs(sPage, sLimit, user.getId());
        model.addAttribute("pageDto", StaffDto);
        return "allStaff";
    }


    /**
     * 删除Staff
     *
     * @param id
     * @return
     */
    @RequestMapping("delete/{id}")
    public String deleteCustomer(@PathVariable("id") Long id, Model model) {
        boolean canDelete = staffService.checkDelete(id);
        if (canDelete) {
            staffService.deleteStaffById(id);
        } else {
            model.addAttribute("message", "存在关联,无法删除");
        }
        return "redirect:/staff/allStaffs";
    }

    /**
     * 添加Staff页面
     *
     * @return
     */
    @RequestMapping("info/{id}")
    public String updateStaffPage(@PathVariable("id") Long id, Model model) {
        StaffExp staffExp = staffService.getStaffById(id);
        model.addAttribute("staffExp", staffExp);

        return "staff";
    }

    /**
     * 添加Staff页面
     *
     * @return
     */
    @RequestMapping("addStaffPage")
    public String addStaffPage() {
        return "staff";
    }

    /**
     * 添加或修改Staff
     *
     * @return
     */
    @RequestMapping("addOrUpdateStaff")
    public String addOrUpdateStaff(Staff Staff) {
        staffService.addOrUpdateStaff(Staff);

        return "redirect:/staff/allStaffs";
    }
}
