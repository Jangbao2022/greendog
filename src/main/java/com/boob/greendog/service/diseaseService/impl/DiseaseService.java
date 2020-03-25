package com.boob.greendog.service.diseaseService.impl;

import com.boob.greendog.dto.PageDto;
import com.boob.greendog.enums.PageUrlEnum;
import com.boob.greendog.exp.InstanceExp;
import com.boob.greendog.mapper.AppointmentMapper;
import com.boob.greendog.mapper.DiseaseMapper;
import com.boob.greendog.mapper.InstanceMapper;
import com.boob.greendog.model.*;
import com.boob.greendog.service.diseaseService.IDiseaseService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.Oneway;
import javax.print.attribute.standard.DialogTypeSelection;
import java.util.Date;
import java.util.List;

@Service
public class DiseaseService implements IDiseaseService {

    @Autowired
    private DiseaseMapper diseaseMapper;

    @Autowired
    private AppointmentMapper appointmentMapper;

    @Autowired
    private InstanceMapper instanceMapper;

    @Override
    public PageDto<Disease> getAllDisease(String sPage, String sLimit) {
        //applyPageDto
        PageDto<Disease> diseasePageDto = new PageDto<>(PageUrlEnum.ALL_DISEASES.getUrl());
        DiseaseExample example = new DiseaseExample();
        //获取apply总数
        long total = diseaseMapper.countByExample(example);
        diseasePageDto.init((int) total, sPage, sLimit);

        List<Disease> diseases = diseaseMapper.selectByExampleWithRowbounds(example, new RowBounds(diseasePageDto.getOffset(), diseasePageDto.getLimit()));

        diseasePageDto.setElements(diseases);
        return diseasePageDto;
    }

    @Override
    public List<Disease> getAllDisease() {
        return diseaseMapper.selectByExample(new DiseaseExample());
    }

    @Override
    public Disease getDiseaseById(Long diseaseId) {
        return diseaseMapper.selectByPrimaryKey(diseaseId);
    }

    @Override
    public boolean checkDelete(Long diseaseId) {
        return checkLinkAppointment(diseaseId) &&
                checkLinkInstance(diseaseId);
    }

    /**
     * 检查和Appointment的关联
     *
     * @param diseaseId
     * @return
     */
    private boolean checkLinkAppointment(Long diseaseId) {
        AppointmentExample example = new AppointmentExample();
        example.createCriteria()
                .andDiseaseIdEqualTo(diseaseId);

        return appointmentMapper.countByExample(example) == 0;
    }

    /**
     * 检查和Instance的关联
     *
     * @param diseaseId
     * @return
     */
    private boolean checkLinkInstance(Long diseaseId) {
        InstanceExample example = new InstanceExample();
        example.createCriteria()
                .andDiseaseIdEqualTo(diseaseId);
        return instanceMapper.countByExample(example) == 0;
    }


    @Override
    public void deleteDisease(Long diseaseId) {
        diseaseMapper.deleteByPrimaryKey(diseaseId);
    }

    @Override
    public void addOrUpdateDisease(Disease disease) {
        if (disease.getId() != null) {
            updateDisease(disease);
        } else {
            addDisease(disease);
        }
    }

    private void updateDisease(Disease disease) {
        disease.setGmtModified(new Date());
        diseaseMapper.updateByPrimaryKeySelective(disease);
    }

    private void addDisease(Disease disease) {
        disease.setGmtCreated(new Date());
        disease.setGmtModified(disease.getGmtCreated());

        diseaseMapper.insert(disease);
    }
}
