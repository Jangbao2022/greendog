package com.boob.greendog.controller;

import com.boob.greendog.dto.PageDto;
import com.boob.greendog.exp.User;
import com.boob.greendog.model.Pet;
import com.boob.greendog.service.petService.IPetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

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
     * @param term
     * @param request
     * @param model
     * @return
     */
    @GetMapping("pets")
    public String getAll(@RequestParam(name = "sPage", required = false) String sPage,
                         @RequestParam(name = "sLimit", required = false) String sLimit,
                         @RequestParam(name = "term", required = false) String term,
                         HttpServletRequest request,
                         Model model) {

        //设置页码 和每页数量
        int page = 1;
        int limit = 10;
        if (sPage != null) {
            page = Integer.parseInt(sPage);
        }
        if (sLimit != null) {
            limit = Integer.parseInt(sLimit);
        }

        PageDto<Pet> petDto;
        if ("my".equals(term)) {
            //我的宠物
            User user = (User) request.getSession().getAttribute("user");
            petDto = petService.getMyPets(page, limit, user.getId());
            model.addAttribute("petDto", petDto);
            return "myPet";
        } else {
            //所有宠物
            petDto = petService.getALLPets(page, limit);
            model.addAttribute("petDto", petDto);
            return "index";
        }

    }
}
