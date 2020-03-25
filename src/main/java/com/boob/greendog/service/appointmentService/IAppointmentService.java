package com.boob.greendog.service.appointmentService;

import com.boob.greendog.dto.PageDto;
import com.boob.greendog.exp.ApplyExp;
import com.boob.greendog.exp.AppointmentExp;
import com.boob.greendog.model.Apply;
import com.boob.greendog.model.Appointment;

import java.util.List;

/**
 * applyService接口
 */
public interface IAppointmentService {

    /**
     * 获取我的所有预约
     *
     * @return
     */
    public PageDto<AppointmentExp> getMyAppointments(String sPage, String sLimit, Long userId);

    /**
     * 获取所有预约
     *
     * @return
     */
    public PageDto<AppointmentExp> getAllAppointments(String sPage, String sLimit);


    /**
     * 根据Id获取
     *
     * @param appointmentId
     * @return
     */
    public Appointment getAppointmentById(Long appointmentId);

    /**
     * 同意预约
     *
     * @param appointmentId
     */
    public void consentAppointment(Long appointmentId);

    /**
     * 拒绝预约
     *
     * @param appointmentId
     */
    public void rejectAppointment(Long appointmentId);


    /**
     * 添加预约
     *
     * @param appointment
     */
    public void addAppointment(Appointment appointment);

    /**
     * 检查能否删除
     *
     * @param appointmentId
     */
    public boolean checkDelete(Long appointmentId);

    /**
     * 删除请求
     *
     * @param appointmentId
     */
    public void delAppointment(Long appointmentId);
}
