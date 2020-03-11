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
    public PageDto<Pet> getALLPets(int page, int limit);

    /**
     * 获取我的宠物
     *
     * @return
     */
    public PageDto<Pet> getMyPets(int page, int limit, Long userId);
}
