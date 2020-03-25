package com.boob.greendog.service.medicineservice.impl;

import com.boob.greendog.dto.PageDto;
import com.boob.greendog.enums.MedicineStatusEnum;
import com.boob.greendog.enums.PageUrlEnum;
import com.boob.greendog.mapper.MedicineMapper;
import com.boob.greendog.model.Medicine;
import com.boob.greendog.model.MedicineExample;
import com.boob.greendog.service.medicineservice.IMedicineService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MedicineService implements IMedicineService {

    @Autowired
    private MedicineMapper medicineMapper;

    @Override
    public PageDto<Medicine> getALLMedicines(String sPage, String sLimit) {
        //初始化petPageDto
        PageDto<Medicine> medicinePageDto = new PageDto<>(PageUrlEnum.ALL_MEDICINES.getUrl());

        MedicineExample example = new MedicineExample();
        //获取Pet总数
        long total = medicineMapper.countByExample(example);
        medicinePageDto.init((int) total, sPage, sLimit);

        List<Medicine> pets = medicineMapper.selectByExampleWithRowbounds(example, new RowBounds(medicinePageDto.getOffset(), medicinePageDto.getLimit()));
        medicinePageDto.setElements(pets);

        return medicinePageDto;
    }

    @Override
    public Medicine getMedicineById(Long medicineId) {

        return medicineMapper.selectByPrimaryKey(medicineId);
    }

    @Override
    public void addOrUpdateMedicine(Medicine medicine) {

        if (medicine.getId() != null) {
            updateMedicine(medicine);
        } else {
            addMedicine(medicine);
        }
    }

    private void addMedicine(Medicine medicine) {

        medicine.setGmtCreated(new Date());
        medicine.setGmtModified(medicine.getGmtCreated());
        medicine.setStatus(MedicineStatusEnum.OPEN.getType());

        medicineMapper.insert(medicine);
    }

    private void updateMedicine(Medicine medicine) {
        medicine.setGmtModified(medicine.getGmtCreated());
        medicineMapper.updateByPrimaryKeySelective(medicine);
    }


    @Override
    public void closeMedicineById(Long medicineId) {

        Medicine medicine = new Medicine();
        //设置为停售
        medicine.setId(medicineId);
        medicine.setStatus(MedicineStatusEnum.CLOSE.getType());
        updateMedicine(medicine);
    }
}
