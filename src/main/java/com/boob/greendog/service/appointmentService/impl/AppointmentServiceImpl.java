package com.boob.greendog.service.appointmentService.impl;

import com.boob.greendog.dto.PageDto;
import com.boob.greendog.enums.HandleEnum;
import com.boob.greendog.enums.PageUrlEnum;
import com.boob.greendog.enums.ReplyEnum;
import com.boob.greendog.exp.AppointmentExp;
import com.boob.greendog.mapper.*;
import com.boob.greendog.model.*;
import com.boob.greendog.service.appointmentService.IAppointmentService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * applyService实现类
 */
@Service
public class AppointmentServiceImpl implements IAppointmentService {

    @Autowired
    private AppointmentMapper appointmentMapper;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private PetMapper petMapper;

    @Autowired
    private DoctorMapper doctorMapper;


    @Autowired
    private DiseaseMapper diseaseMapper;


    @Override
    public PageDto<AppointmentExp> getMyAppointments(String sPage, String sLimit, Long userId) {

        AppointmentExample appointmentExample = new AppointmentExample();
        appointmentExample.createCriteria()
                .andCustomerIdEqualTo(userId);
        return getAppointmentsByExample(appointmentExample, sPage, sLimit, PageUrlEnum.MY_APPOINTMENTS.getUrl());
    }

    @Override
    public PageDto<AppointmentExp> getAllAppointments(String sPage, String sLimit) {
        AppointmentExample appointmentExample = new AppointmentExample();
        return getAppointmentsByExample(appointmentExample, sPage, sLimit, PageUrlEnum.ALL_APPOINTMENTS.getUrl());
    }

    @Override
    public Appointment getAppointmentById(Long appointmentId) {
        return appointmentMapper.selectByPrimaryKey(appointmentId);
    }

    /**
     * 通过 条件获取dto
     *
     * @param example
     * @param sPage
     * @param sLimit
     * @return
     */
    private PageDto<AppointmentExp> getAppointmentsByExample(AppointmentExample example, String sPage, String sLimit, String url) {
        //applyPageDto
        PageDto<AppointmentExp> appointmentExpPageDto = new PageDto<>(url);
        //获取apply总数
        long total = appointmentMapper.countByExample(example);
        appointmentExpPageDto.init((int) total, sPage, sLimit);

        List<Appointment> appointments = appointmentMapper.selectByExampleWithRowbounds(example, new RowBounds(appointmentExpPageDto.getOffset(), appointmentExpPageDto.getLimit()));
        List<AppointmentExp> appointmentExps = pottingAppointmentList(appointments);

        appointmentExpPageDto.setElements(appointmentExps);
        return appointmentExpPageDto;
    }

    /**
     * 封装appointments
     *
     * @param appointments
     * @return
     */
    private List<AppointmentExp> pottingAppointmentList(List<Appointment> appointments) {
        List<AppointmentExp> appointmentExps = new ArrayList<>();
        //封装appointment
        for (Appointment appointment : appointments) {
            AppointmentExp appointmentExp = pottingAppointment(appointment);
            appointmentExps.add(appointmentExp);
        }
        return appointmentExps;
    }

    /**
     * 封装appointment
     *
     * @param appointment
     * @return
     */
    private AppointmentExp pottingAppointment(Appointment appointment) {
        AppointmentExp appointmentExp = new AppointmentExp();
        appointmentExp.setAppointment(appointment);

        Customer customer = customerMapper.selectByPrimaryKey(appointment.getCustomerId());
        //申请的宠物
        appointmentExp.setCustomer(customer);

        Pet pet = petMapper.selectByPrimaryKey(appointment.getPetId());
        appointmentExp.setPet(pet);

        Disease disease = diseaseMapper.selectByPrimaryKey(appointment.getDiseaseId());
        appointmentExp.setDisease(disease);

        Doctor doctor = doctorMapper.selectByPrimaryKey(appointment.getDoctorId());
        appointmentExp.setDoctor(doctor);

        return appointmentExp;
    }

    @Override
    public void consentAppointment(Long appointmentId) {
        //同意请求
        Appointment appointment = appointmentMapper.selectByPrimaryKey(appointmentId);

        //同意请求
        appointment.setReply(ReplyEnum.CONSENT.getType());
        //更新请求
        updateAppointment(appointment);
    }


    @Override
    public void rejectAppointment(Long appointmentId) {
        //拒绝请求
        Appointment appointment = new Appointment();
        appointment.setId(appointmentId);
        appointment.setReply(ReplyEnum.REJECT.getType());//设为拒绝

        //更新预约
        updateAppointment(appointment);
    }

    /**
     * 更新appointment
     */
    private void updateAppointment(Appointment appointment) {

        //设置为已处理
        appointment.setHandle(HandleEnum.PROCESSED.getType());
        appointment.setGmtModified(new Date());
        appointmentMapper.updateByPrimaryKeySelective(appointment);
    }


    /**
     * 添加请求
     *
     * @param appointment
     */
    public void addAppointment(Appointment appointment) {
        //设置为未处理
        appointment.setHandle(HandleEnum.WAITING.getType());

        appointment.setGmtCreated(new Date());
        appointment.setGmtModified(appointment.getGmtCreated());
        appointmentMapper.insert(appointment);
    }

    @Override
    public boolean checkDelete(Long appointmentId) {
        //处理后才能删除
        Appointment appointment = appointmentMapper.selectByPrimaryKey(appointmentId);
        return appointment.getHandle().equals(HandleEnum.PROCESSED.getType());
    }

    @Override
    public void delAppointment(Long appointmentId) {
        appointmentMapper.deleteByPrimaryKey(appointmentId);
    }
}
