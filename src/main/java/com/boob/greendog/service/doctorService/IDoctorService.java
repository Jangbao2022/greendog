package com.boob.greendog.service.doctorService;

import com.boob.greendog.dto.PageDto;
import com.boob.greendog.exp.DoctorExp;
import com.boob.greendog.model.Appointment;
import com.boob.greendog.model.Doctor;

/**
 * doctorService接口
 */
public interface IDoctorService {


    /**
     * 获取所有医生
     *
     * @return
     */
    public PageDto<DoctorExp> getAllDoctors(String sPage, String sLimit);

//
//    /**
//     * 获取我的doctor
//     *
//     * @return
//     */
//    public PageDto<DoctorExp> getMyDoctors(String sPage, String sLimit, Long userId);


    /**
     * 通过Appointment更新doctor
     *
     * @param appointment
     */
    public void updateDoctorByAppointment(Appointment appointment);

    /**
     * 删除doctor
     *
     * @param doctorId
     */
    public void deleteDoctorById(Long doctorId);

    /**
     * 根据Id获取doctor
     *
     * @param id
     * @return
     */
    public DoctorExp getDoctorById(Long id);

    /**
     * 增加或修改doctor
     *
     * @param doctor
     */
    public void addOrUpdateDoctor(Doctor doctor);


    /**
     * 检验能否删除
     *
     * @param id
     * @return
     */
    public boolean checkDelete(Long id);


    /**
     * 上传医生图片
     *
     * @param picUrl
     */
    public void uploadPic(Long id, String picUrl);
}
