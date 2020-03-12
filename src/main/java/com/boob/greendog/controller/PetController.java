package com.boob.greendog.controller;

import com.boob.greendog.dto.PageDto;
import com.boob.greendog.exp.User;
import com.boob.greendog.model.Pet;
import com.boob.greendog.service.petService.IPetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

@Controller
@RequestMapping("/pet")
public class PetController {


    @Autowired
    private IPetService petService;

    /**
     * 获取所有宠物
     *
     * @param sPage
     * @param sLimit
     * @param model
     * @return
     */
    @GetMapping("allPets")
    public String getAllPets(@RequestParam(name = "sPage", required = false) String sPage,
                             @RequestParam(name = "sLimit", required = false) String sLimit,
                             Model model) {

        //所有宠物
        PageDto<Pet> petDto = petService.getALLPets(sPage, sLimit);
        model.addAttribute("pageDto", petDto);
        return "allPet";

    }

    /**
     * 获取我的所有宠物
     *
     * @param sPage
     * @param sLimit
     * @param request
     * @param model
     * @return
     */
    @GetMapping("myPets")
    public String getMyPets(@RequestParam(name = "sPage", required = false) String sPage,
                            @RequestParam(name = "sLimit", required = false) String sLimit,
                            HttpServletRequest request,
                            Model model) {

        User user = (User) request.getSession().getAttribute("user");
        PageDto<Pet> petDto = petService.getMyPets(sPage, sLimit, user.getId());
        model.addAttribute("pageDto", petDto);
        return "myPet";

    }

    /**
     * 通过id获取宠物
     *
     * @param id
     * @return
     */
    @RequestMapping("info/{id}")
    public String getPet(@PathVariable("id") Long id, Model model) {

        Pet pet = petService.getPetById(id);
        model.addAttribute("pet", pet);
        return "pet";
    }

    /**
     * 增加宠物页面
     *
     * @return
     */
    @RequestMapping("addPetPage")
    public String addPet() {
        return "pet";
    }

    /**
     * 更新宠物或增加
     *
     * @param pet
     * @return
     */
    @RequestMapping("addOrUpdatePet")
    public String addOrUpdatePet(Pet pet) {
        petService.addOrUpdatePet(pet);
        return "redirect:/pet/allPets";
    }

    /**
     * 删除宠物
     *
     * @param id
     * @return
     */
    @RequestMapping("delete/{id}")
    public String deletePet(@PathVariable("id") Long id, Model model) {
        boolean canDelete = petService.checkDelete(id);
        if (canDelete) {
            petService.deletePetById(id);
        } else {
            model.addAttribute("message", "存在关联,无法删除");
        }
        return "forward:/pet/allPets";
    }

}
