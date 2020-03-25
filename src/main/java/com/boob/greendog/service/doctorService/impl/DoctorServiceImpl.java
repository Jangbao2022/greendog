package com.boob.greendog.service.doctorService.impl;

import com.boob.greendog.dto.PageDto;
import com.boob.greendog.enums.DefaultPicEnum;
import com.boob.greendog.enums.PageUrlEnum;
import com.boob.greendog.exp.DoctorExp;
import com.boob.greendog.mapper.*;
import com.boob.greendog.model.*;
import com.boob.greendog.service.doctorService.IDoctorService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * DoctorService实现类
 */
@Service
public class DoctorServiceImpl implements IDoctorService {


    @Autowired
    private DoctorMapper doctorMapper;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private InstanceMapper instanceMapper;

    @Autowired
    private PetMapper petMapper;

    @Autowired
    private AppointmentMapper appointmentMapper;


    @Override
    public PageDto<DoctorExp> getAllDoctors(String sPage, String sLimit) {

        DoctorExample doctorExample = new DoctorExample();

        //注入example,url
        return getDoctorsByExample(doctorExample, sPage, sLimit, PageUrlEnum.ALL_DOCTORS.getUrl());
    }

//    @Override
//    public PageDto<DoctorExp> getMyDoctors(String sPage, String sLimit, Long userId) {
//        DoctorExample doctorExample = new DoctorExample();
//        doctorExample.createCriteria()
//                .andCustomerIdEqualTo(userId);
//        //注入example,url
//        return getDoctorsByExample(doctorExample, sPage, sLimit, PageUrlEnum.MY_DOCTORS.getUrl());
//    }


    /**
     * 通过example获取页面
     *
     * @param example
     * @param sPage
     * @param sLimit
     * @return
     */
    private PageDto<DoctorExp> getDoctorsByExample(DoctorExample example, String sPage, String sLimit, String url) {
        //初始化petPageDto
        PageDto<DoctorExp> doctorExpPageDto = new PageDto<>(url);
        //获取Pet总数
        long total = doctorMapper.countByExample(example);
        doctorExpPageDto.init((int) total, sPage, sLimit);

        List<Doctor> Doctors = doctorMapper.selectByExampleWithRowbounds(example, new RowBounds(doctorExpPageDto.getOffset(), doctorExpPageDto.getLimit()));

        List<DoctorExp> DoctorExps = pottingDoctorList(Doctors);
        doctorExpPageDto.setElements(DoctorExps);
        return doctorExpPageDto;
    }

    /**
     * 增强DoctorList为DoctorExpList
     *
     * @param DoctorList
     * @return
     */
    private List<DoctorExp> pottingDoctorList(List<Doctor> DoctorList) {
        List<DoctorExp> bulletinExps = new ArrayList<>();
        //封装bulletin
        for (Doctor Doctor : DoctorList) {
            DoctorExp DoctorExp = pottingDoctor(Doctor);
            bulletinExps.add(DoctorExp);
        }
        return bulletinExps;
    }

    /**
     * 增强Doctor为DoctorExp
     *
     * @param doctor
     * @return
     */
    private DoctorExp pottingDoctor(Doctor doctor) {
        DoctorExp DoctorExp = new DoctorExp();
        DoctorExp.setDoctor(doctor);

        //获取医生相关病例
        InstanceExample example = new InstanceExample();
        example.createCriteria()
                .andDoctorIdEqualTo(doctor.getId());
        List<Instance> instances = instanceMapper.selectByExample(example);
        if (instances.size() != 0) {
            //获取病例中的宠物
            Long petId = instances.get(0).getPetId();
            Pet pet = petMapper.selectByPrimaryKey(petId);
            DoctorExp.setPet(pet);

            //获取宠物主人
            Long masterId = pet.getMasterId();
            Customer customer = customerMapper.selectByPrimaryKey(masterId);
            DoctorExp.setCustomer(customer);
        }
        return DoctorExp;
    }


    @Override
    public DoctorExp getDoctorById(Long id) {
        return pottingDoctor(doctorMapper.selectByPrimaryKey(id));
    }

    @Override
    public void addOrUpdateDoctor(Doctor doctor) {
        if (doctor.getId() != null) {
            //更新医生
            updateDoctor(doctor);
        } else {
            //添加医生
            addDoctor(doctor);
        }
    }

    /**
     * 新增医生
     *
     * @param doctor
     */
    private void addDoctor(Doctor doctor) {
        if (doctor.getSex() == null || doctor.getSex().equals(1)) {
            //未填写性别默认为男医生
            doctor.setPic(DefaultPicEnum.MALE_DOCTOR.getPic());

        } else {

            doctor.setPic(DefaultPicEnum.FEMALE_DOCTOR.getPic());
        }
        doctor.setGmtCreated(new Date());
        doctor.setGmtModified(doctor.getGmtCreated());
        doctorMapper.insert(doctor);
    }


    /**
     * 更新医生
     *
     * @param doctor
     */
    private void updateDoctor(Doctor doctor) {
        doctor.setGmtModified(new Date());
        doctorMapper.updateByPrimaryKeySelective(doctor);
    }


    @Override
    public void updateDoctorByAppointment(Appointment appointment) {
        Doctor doctor = new Doctor();
        doctor.setId(appointment.getDoctorId());
        doctor.setCustomerId(appointment.getCustomerId());
        updateDoctor(doctor);
    }


    @Override
    public void uploadPic(Long id, String picUrl) {
        Doctor doctor = new Doctor();
        doctor.setId(id);
        doctor.setPic(picUrl);

        updateDoctor(doctor);
    }


    @Override
    public boolean checkDelete(Long id) {

        return checkLinkAppointment(id) &&
                checkLinkInstance(id);
    }

    /**
     * 检查和Appointment的关联
     *
     * @param doctorId
     * @return
     */
    private boolean checkLinkAppointment(Long doctorId) {
        AppointmentExample example = new AppointmentExample();
        example.createCriteria()
                .andDoctorIdEqualTo(doctorId);

        return appointmentMapper.countByExample(example) == 0;
    }

    /**
     * 检查和Instance的关联
     *
     * @param doctorId
     * @return
     */
    private boolean checkLinkInstance(Long doctorId) {
        InstanceExample example = new InstanceExample();
        example.createCriteria()
                .andDoctorIdEqualTo(doctorId);
        return instanceMapper.countByExample(example) == 0;
    }


    @Override
    public void deleteDoctorById(Long doctorId) {
        doctorMapper.deleteByPrimaryKey(doctorId);
    }

}
