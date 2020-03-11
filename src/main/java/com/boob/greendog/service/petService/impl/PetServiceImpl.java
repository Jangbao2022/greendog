package com.boob.greendog.service.petService.impl;

import com.boob.greendog.dto.PageDto;
import com.boob.greendog.enums.PageUrlEnum;
import com.boob.greendog.mapper.PetMapper;
import com.boob.greendog.model.Pet;
import com.boob.greendog.model.PetExample;
import com.boob.greendog.service.petService.IPetService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetServiceImpl implements IPetService {

    @Autowired
    private PetMapper petMapper;

    @Override
    public PageDto<Pet> getALLPets(int page, int limit) {

        PetExample petExample = new PetExample();
        //注入example,url
        return getPetsByExample(petExample, page, limit, PageUrlEnum.ALL_PETS.getUrl());
    }

    @Override
    public PageDto<Pet> getMyPets(int page, int limit, Long userId) {
        PetExample petExample = new PetExample();
        petExample.createCriteria()
                .andMasterIdEqualTo(userId);
        //注入example,url
        return getPetsByExample(petExample, page, limit, PageUrlEnum.MY_PETS.getUrl());
    }

    /**
     * 通过example获取页面
     *
     * @param example
     * @param page
     * @param limit
     * @return
     */
    private PageDto<Pet> getPetsByExample(PetExample example, int page, int limit, String url) {
        //初始化petPageDto
        PageDto<Pet> petPageDto = new PageDto<>(url);
        //获取Pet总数
        long total = petMapper.countByExample(example);
        petPageDto.init((int) total, page, limit);

        List<Pet> pets = petMapper.selectByExampleWithRowbounds(example, new RowBounds(petPageDto.getPage() - 1, limit));
        petPageDto.setElements(pets);

        return petPageDto;
    }
}
