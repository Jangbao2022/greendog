package com.boob.greendog.controller;

import com.boob.greendog.dto.PageDto;
import com.boob.greendog.exp.ApplyExp;
import com.boob.greendog.exp.User;
import com.boob.greendog.model.Apply;
import com.boob.greendog.service.applyService.IApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/apply")
public class ApplyController {

    @Autowired
    private IApplyService applyService;

    /**
     * 所有申请
     *
     * @param sPage
     * @param sLimit
     * @param model
     * @return
     */
    @GetMapping("allApplies")
    public String getAllApplies(@RequestParam(name = "sPage", required = false) String sPage,
                                @RequestParam(name = "sLimit", required = false) String sLimit,
                                Model model) {

        PageDto<ApplyExp> applyDto = applyService.getAllApplies(sPage, sLimit);
        model.addAttribute("pageDto", applyDto);
        return "allApply";
    }

    /**
     * 我的申请
     *
     * @param sPage
     * @param sLimit
     * @param request
     * @param model
     * @return
     */
    @GetMapping("myApplies")
    public String getMyApplies(@RequestParam(name = "sPage", required = false) String sPage,
                               @RequestParam(name = "sLimit", required = false) String sLimit,
                               HttpServletRequest request,
                               Model model) {

        User user = (User) request.getSession().getAttribute("user");
        PageDto<ApplyExp> applyDto = applyService.getMyApplies(sPage, sLimit, user.getId());
        model.addAttribute("pageDto", applyDto);
        return "myApply";
    }


    /**
     * 同意申请
     *
     * @param id
     * @return
     */
    @RequestMapping("consent/{id}")
    public String consentApply(@PathVariable("id") Long id) {
        applyService.consentApply(id);

        return "redirect:/apply/allApplies";
    }

    /**
     * 拒绝申请
     *
     * @param id
     * @return
     */
    @RequestMapping("reject/{id}")
    public String rejectApply(@PathVariable("id") Long id) {
        applyService.rejectApply(id);
        return "redirect:/apply/allApplies";
    }

    /**
     * 申请领养
     *
     * @return
     */
    @RequestMapping("adopt")
    public String adopt(Apply apply) {
        applyService.adopt(apply);
        return "redirect:/index";
    }

    /**
     * 申请寄养
     *
     * @return
     */
    @RequestMapping("send")
    public String send(Apply apply) {
        applyService.send(apply);
        return "redirect:/index";
    }

    /**
     * 申请预约
     *
     * @return
     */
    @RequestMapping("order")
    public String order(Apply apply) {
        applyService.order(apply);
        return "redirect:/index";
    }

    /**
     * 删除请求
     *
     * @param applyId
     * @return
     */
    @RequestMapping("delete/{id}")
    public String delApply(@PathVariable("id") Long applyId) {
        applyService.delApply(applyId);
        return "redirect:/apply/allApplies";
    }
}
