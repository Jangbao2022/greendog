package com.boob.greendog.service.instanceservice.impl;

import com.boob.greendog.dto.PageDto;
import com.boob.greendog.enums.PageUrlEnum;
import com.boob.greendog.exp.InstanceExp;
import com.boob.greendog.mapper.*;
import com.boob.greendog.model.*;
import com.boob.greendog.service.instanceservice.IInstanceService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class InstanceServiceImpl implements IInstanceService {

    @Autowired
    private InstanceMapper instanceMapper;
    @Autowired
    private PetMapper petMapper;
    @Autowired
    private DoctorMapper doctorMapper;
    @Autowired
    private DiseaseMapper diseaseMapper;
    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public PageDto<InstanceExp> getAllInstances(String sPage, String sLimit) {

        //applyPageDto
        PageDto<InstanceExp> instanceExpPageDto = new PageDto<>(PageUrlEnum.ALL_INSTANCES.getUrl());
        InstanceExample example = new InstanceExample();
        //获取apply总数
        long total = instanceMapper.countByExample(example);
        instanceExpPageDto.init((int) total, sPage, sLimit);

        List<Instance> bills = instanceMapper.selectByExampleWithRowbounds(example, new RowBounds(instanceExpPageDto.getOffset(), instanceExpPageDto.getLimit()));
        List<InstanceExp> billExps = pottingInstances(bills);

        instanceExpPageDto.setElements(billExps);
        return instanceExpPageDto;
    }

    /**
     * 封装instance集合
     *
     * @param instances
     * @return
     */
    private List<InstanceExp> pottingInstances(List<Instance> instances) {

        ArrayList<InstanceExp> instanceExps = new ArrayList<>();
        for (Instance instance : instances) {

            InstanceExp instanceExp = pottingInstance(instance);
            instanceExps.add(instanceExp);

        }
        return instanceExps;
    }

    /**
     * 封装instance
     *
     * @param instance
     * @return
     */
    private InstanceExp pottingInstance(Instance instance) {

        InstanceExp instanceExp = new InstanceExp();
        instanceExp.setInstance(instance);

        Pet pet = petMapper.selectByPrimaryKey(instance.getPetId());
        instanceExp.setPet(pet);

        Doctor doctor = doctorMapper.selectByPrimaryKey(instance.getDoctorId());
        instanceExp.setDoctor(doctor);

        Disease disease = diseaseMapper.selectByPrimaryKey(instance.getDiseaseId());
        instanceExp.setDisease(disease);

        Customer customer = customerMapper.selectByPrimaryKey(instance.getCustomerId());
        instanceExp.setCustomer(customer);

        return instanceExp;
    }


    @Override
    public void addInstanceByAppointment(Appointment appointment) {
        //添加病例
        Instance instance = new Instance();
        instance.setPetId(appointment.getPetId());
        instance.setDoctorId(appointment.getDoctorId());
        instance.setDiseaseId(appointment.getDiseaseId());
        instance.setCustomerId(appointment.getCustomerId());

        addInstance(instance);
    }

    @Override
    public void addInstance(Instance instance) {
        instance.setGmtCreated(new Date());
        instance.setGmtModified(instance.getGmtCreated());
        instanceMapper.insert(instance);
    }

    @Override
    public InstanceExp getInstanceById(Long instanceId) {
        Instance instance = instanceMapper.selectByPrimaryKey(instanceId);
        return pottingInstance(instance);
    }


}
