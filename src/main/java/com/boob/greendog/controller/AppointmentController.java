package com.boob.greendog.controller;

import com.boob.greendog.dto.PageDto;
import com.boob.greendog.exp.AppointmentExp;
import com.boob.greendog.exp.DoctorExp;
import com.boob.greendog.exp.User;
import com.boob.greendog.model.Appointment;
import com.boob.greendog.model.Disease;
import com.boob.greendog.model.Packet;
import com.boob.greendog.model.Pet;
import com.boob.greendog.service.appointmentService.IAppointmentService;
import com.boob.greendog.service.billservice.IBillService;
import com.boob.greendog.service.diseaseService.IDiseaseService;
import com.boob.greendog.service.doctorService.IDoctorService;
import com.boob.greendog.service.instanceservice.IInstanceService;
import com.boob.greendog.service.packetService.IPacketService;
import com.boob.greendog.service.petService.IPetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private IAppointmentService appointmentService;

    @Autowired
    private IDoctorService doctorService;

    @Autowired
    private IPetService petService;

    @Autowired
    private IDiseaseService diseaseService;

    @Autowired
    private IBillService billService;

    @Autowired
    private IPacketService packetService;

    @Autowired
    private IInstanceService instanceService;


    /**
     * 所有申请
     *
     * @param sPage
     * @param sLimit
     * @param model
     * @return
     */
    @GetMapping("allAppointments")
    public String getAllApplies(@RequestParam(name = "sPage", required = false) String sPage,
                                @RequestParam(name = "sLimit", required = false) String sLimit,
                                Model model) {

        PageDto<AppointmentExp> appDto = appointmentService.getAllAppointments(sPage, sLimit);
        model.addAttribute("pageDto", appDto);
        return "allAppointment";
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
    @GetMapping("myAppointments")
    public String getMyApplies(@RequestParam(name = "sPage", required = false) String sPage,
                               @RequestParam(name = "sLimit", required = false) String sLimit,
                               HttpServletRequest request,
                               Model model) {

        User user = (User) request.getSession().getAttribute("user");
        PageDto<AppointmentExp> appDto = appointmentService.getMyAppointments(sPage, sLimit, user.getId());
        model.addAttribute("pageDto", appDto);
        return "allAppointment";
    }


    /**
     * 添加appointment页面
     *
     * @return
     */
    @RequestMapping("addAppointmentPage/{doctorId}")
    public String addAppointmentPage(@PathVariable("doctorId") Long doctorId,
                                     Model model, HttpServletRequest request) {

        User user = (User) request.getSession().getAttribute("user");
        //获取医生信息
        DoctorExp doctorExp = doctorService.getDoctorById(doctorId);
        model.addAttribute("doctorExp", doctorExp);

        //获取病症信息
        List<Disease> allDisease = diseaseService.getAllDisease();
        model.addAttribute("diseases", allDisease);

        //获取我的账户信息
        Packet packet = packetService.getPacketByCustomerId(user.getId());
        model.addAttribute("packet", packet);

        //获取我的所有宠物
        List<Pet> myPets = petService.getMyPets(user.getId());
        model.addAttribute("myPets", myPets);
        return "appointment";
    }

    /**
     * 同意申请
     *
     * @param id
     * @return
     */
    @RequestMapping("consent/{id}")
    @Transactional
    public String consentAppointment(@PathVariable("id") Long id) {
        appointmentService.consentAppointment(id);
        Appointment appointment = appointmentService.getAppointmentById(id);

        //添加病例
        instanceService.addInstanceByAppointment(appointment);
        //添加账单
        billService.addBillByAppointment(appointment);
        //更新医生
        doctorService.updateDoctorByAppointment(appointment);

        return "redirect:/appointment/allAppointments";
    }

    /**
     * 拒绝申请
     *
     * @param id
     * @return
     */
    @RequestMapping("reject/{id}")
    @Transactional
    public String rejectAppointment(@PathVariable("id") Long id) {
        appointmentService.rejectAppointment(id);

        Appointment appointment = appointmentService.getAppointmentById(id);
        //退钱
        packetService.backMoneyByAppointment(appointment);

        return "redirect:/appointment/allAppointments";
    }

    /**
     * 申请预约
     *
     * @return
     */
    @RequestMapping("order")
    @Transactional
    public String order(Appointment appointment, HttpServletRequest request) {

        User user = (User) request.getSession().getAttribute("user");
        appointment.setCustomerId(user.getId());

        appointmentService.addAppointment(appointment);

        //扣钱
        packetService.payMoneyByAppointment(appointment);
        return "redirect:/appointment/myAppointments";
    }

    /**
     * 删除请求
     *
     * @param appId
     * @return
     */
    @RequestMapping("delete/{id}")
    public String delAppointment(@PathVariable("id") Long appId, Model model) {
        boolean b = appointmentService.checkDelete(appId);
        if (b) {
            appointmentService.delAppointment(appId);
        } else {
            model.addAttribute("message", "存在关联,无法删除");
        }
        return "forward:/appointment/allAppointments";
    }
}
