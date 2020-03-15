package com.boob.greendog.service.petService;

import com.boob.greendog.dto.PageDto;
import com.boob.greendog.model.Pet;

/**
 * iPetService接口
 */
public interface IPetService {

    /**
     * 获取所有宠物
     *
     * @return
     */
    public PageDto<Pet> getALLPets(String sPage, String sLimit);

    /**
     * 获取我的宠物
     *
     * @return
     */
    public PageDto<Pet> getMyPets(String sPage, String sLimit, Long userId);


    /**
     * 通过id 获取宠物信息
     *
     * @param petId
     * @return
     */
    public Pet getPetById(Long petId);


    /**
     * 更新或插入宠物
     *
     * @param pet
     */
    public void addOrUpdatePet(Pet pet);

    /**
     * 检验宠物是否在其他地方有关联
     *
     * @param petId
     * @return
     */
    public boolean checkDelete(Long petId);


    /**
     * 删除宠物
     *
     * @param petId
     * @return
     */
    public void deletePetById(Long petId);

    /**
     * 上传宠物图片
     *
     * @param picUrl
     */
    public void uploadPic(Long id, String picUrl);
}
