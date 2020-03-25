package com.boob.greendog.service.diseaseService;

import com.boob.greendog.dto.PageDto;
import com.boob.greendog.exp.InstanceExp;
import com.boob.greendog.model.Disease;

import java.util.List;

/**
 * IDiseaseService
 */
public interface IDiseaseService {

    /**
     * 获取所有病症
     *
     * @return
     */
    public PageDto<Disease> getAllDisease(String sPage, String sLimit);


    /**
     * 获取所有病症
     *
     * @return
     */
    public List<Disease> getAllDisease();

    public Disease getDiseaseById(Long diseaseId);

    public void deleteDisease(Long diseaseId);

    public boolean checkDelete(Long diseaseId);

    public void addOrUpdateDisease(Disease disease);

}
