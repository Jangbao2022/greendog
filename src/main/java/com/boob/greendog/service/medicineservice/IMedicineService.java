package com.boob.greendog.service.medicineservice;

import com.boob.greendog.dto.PageDto;
import com.boob.greendog.model.Medicine;

/**
 * IMedicineService
 */
public interface IMedicineService {

    /**
     * 获取medicine
     *
     * @param sPage
     * @param sLimit
     * @return
     */
    public PageDto<Medicine> getALLMedicines(String sPage, String sLimit);

    /**
     * 通过id 获取药品信息
     *
     * @param medicineId
     * @return
     */
    public Medicine getMedicineById(Long medicineId);


    /**
     * 更新或插入药品
     *
     * @param medicine
     */
    public void addOrUpdateMedicine(Medicine medicine);

    /**
     * 停售药品
     *
     * @param medicineId
     * @return
     */
    public void closeMedicineById(Long medicineId);
}
