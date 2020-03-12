package com.boob.greendog.service.petService.impl;

import com.boob.greendog.dto.PageDto;
import com.boob.greendog.enums.PageUrlEnum;
import com.boob.greendog.mapper.ApplyMapper;
import com.boob.greendog.mapper.PetMapper;
import com.boob.greendog.model.ApplyExample;
import com.boob.greendog.model.Customer;
import com.boob.greendog.model.Pet;
import com.boob.greendog.model.PetExample;
import com.boob.greendog.service.petService.IPetService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PetServiceImpl implements IPetService {

    @Autowired
    private PetMapper petMapper;

    @Autowired
    private ApplyMapper applyMapper;

    @Override
    public PageDto<Pet> getALLPets(String sPage, String sLimit) {

        PetExample petExample = new PetExample();
        //注入example,url
        return getPetsByExample(petExample, sPage, sLimit, PageUrlEnum.ALL_PETS.getUrl());
    }

    @Override
    public PageDto<Pet> getMyPets(String sPage, String sLimit, Long userId) {
        PetExample petExample = new PetExample();

        petExample.createCriteria()
                .andMasterIdEqualTo(userId);
        //注入example,url
        return getPetsByExample(petExample, sPage, sLimit, PageUrlEnum.MY_PETS.getUrl());
    }

    /**
     * 通过example获取页面
     *
     * @param example
     * @param sPage
     * @param sLimit
     * @return
     */
    private PageDto<Pet> getPetsByExample(PetExample example, String sPage, String sLimit, String url) {
        //初始化petPageDto
        PageDto<Pet> petPageDto = new PageDto<>(url);
        //获取Pet总数
        long total = petMapper.countByExample(example);
        petPageDto.init((int) total, sPage, sLimit);

        List<Pet> pets = petMapper.selectByExampleWithRowbounds(example, new RowBounds(petPageDto.getOffset(), petPageDto.getLimit()));
        petPageDto.setElements(pets);

        return petPageDto;
    }

    @Override
    public Pet getPetById(Long petId) {
        Pet pet = petMapper.selectByPrimaryKey(petId);
        return pet;
    }

    @Override
    public void addOrUpdatePet(Pet pet) {
        if (pet.getId() == null) {
            addPet(pet);
        } else {
            updatePet(pet);
        }
    }

    /**
     * 加入宠物
     *
     * @param pet
     * @return
     */
    private void addPet(Pet pet) {
        pet.setGmtCreated(new Date());
        pet.setGmtModified(pet.getGmtCreated());
        petMapper.insert(pet);
    }

    /**
     * 更新宠物
     *
     * @param pet
     */
    private void updatePet(Pet pet) {
        pet.setGmtModified(new Date());
        petMapper.updateByPrimaryKeySelective(pet);
    }

    @Override
    public boolean checkDelete(Long petId) {
        ApplyExample example = new ApplyExample();
        example.createCriteria()
                .andPetIdEqualTo(petId);
        return applyMapper.countByExample(example) == 0;
    }

    @Override
    public void deletePetById(Long petId) {

        petMapper.deleteByPrimaryKey(petId);
    }
}
