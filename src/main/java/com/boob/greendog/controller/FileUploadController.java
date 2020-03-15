package com.boob.greendog.controller;

import com.boob.greendog.exp.StaffExp;
import com.boob.greendog.model.Pet;
import com.boob.greendog.service.petService.IPetService;
import com.boob.greendog.service.staffService.IStaffService;
import com.boob.greendog.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/upload")
public class FileUploadController {

    @Autowired
    private IPetService petService;

    @Autowired
    private IStaffService staffService;

    /**
     * 上传图片页面
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
     * 上传工作人员图片页面
     *
     * @param
     * @return
     */
    @RequestMapping(value = "staffPicPage/{id}")
    public String staffPicPage(@PathVariable("id") Long id, Model model) {
        StaffExp staffExp = staffService.getStaffById(id);
        model.addAttribute("staffExp", staffExp);
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

        String fileUrl = FileUploadUtil.upLoadFileByMultipartFile(file, "petimage");

        //更新宠物信息
        petService.uploadPic(id, fileUrl);
        return "redirect:/upload/petPicPage/" + id;
    }

    /**
     * 上传工作人员图片
     *
     * @param file
     * @return
     */
    @RequestMapping(value = "staffPic", method = RequestMethod.POST)
    public String uploadStaffPic(@RequestParam("id") Long id,
                                 @RequestParam("image") MultipartFile file) {

        String fileUrl = FileUploadUtil.upLoadFileByMultipartFile(file, "staffimage");

        //更新宠物信息
        staffService.uploadPic(id, fileUrl);
        return "redirect:/upload/staffPicPage/" + id;
    }

}
