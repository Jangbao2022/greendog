package com.boob.greendog.controller;

import com.boob.greendog.model.Pet;
import com.boob.greendog.service.petService.IPetService;
import com.boob.greendog.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Controller
@RequestMapping("/upload")
public class FileUploadController {

    @Autowired
    private IPetService petService;

    /**
     * 上传猫的图片页面
     *
     * @param
     * @return
     */
    @RequestMapping(value = "petPicPage/{id}")
    public String petPicPage(@PathVariable("id") Long id, Model model) {
        Pet pet = petService.getPetById(id);
        model.addAttribute("pet", pet);
        return "uploadPicPage";
    }

    /**
     * 上传猫的图片
     *
     * @param file
     * @return
     */
    @RequestMapping(value = "petPic", method = RequestMethod.POST)
    public String uploadPetPic(@RequestParam("id") Long id,
                               @RequestParam("image") MultipartFile file) {

        try {
            InputStream inputStream = file.getInputStream();

            //获取文件后缀
            String[] split = file.getResource().getFilename().split("\\.");
            String suffix = split[1];

            String fileUrl = FileUploadUtil.upLoadFile(inputStream, "petimage", suffix);

            //更新宠物信息
            petService.uploadPetPic(id, fileUrl);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/petPicPage/" + id;
    }
}