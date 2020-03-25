package com.boob.greendog.service.instanceservice;

import com.boob.greendog.dto.PageDto;
import com.boob.greendog.exp.BillExp;
import com.boob.greendog.exp.InstanceExp;
import com.boob.greendog.model.Appointment;
import com.boob.greendog.model.Instance;

/**
 * IInstanceService
 */
public interface IInstanceService {

    /**
     * 获取所有病例
     *
     * @return
     */
    public PageDto<InstanceExp> getAllInstances(String sPage, String sLimit);


    /**
     * 通过Appointment添加病例
     *
     * @param appointment
     */
    public void addInstanceByAppointment(Appointment appointment);


    /**
     * 添加病例
     *
     * @param instance
     */
    public void addInstance(Instance instance);


    /**
     * 获取封装的instance
     *
     * @param instanceId
     * @return
     */
    public InstanceExp getInstanceById(Long instanceId);
}
