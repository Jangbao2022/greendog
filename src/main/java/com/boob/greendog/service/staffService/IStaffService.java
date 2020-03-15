package com.boob.greendog.service.staffService;

import com.boob.greendog.dto.PageDto;
import com.boob.greendog.exp.BulletinExp;
import com.boob.greendog.exp.StaffExp;
import com.boob.greendog.model.Bulletin;
import com.boob.greendog.model.Pet;
import com.boob.greendog.model.Staff;

/**
 * staffService接口
 */
public interface IStaffService {


    /**
     * 获取所有公告
     * 默认按修改时间倒序
     *
     * @return
     */
    public PageDto<StaffExp> getAllStaff(String sPage, String sLimit);


    /**
     * 获取我的staffs
     *
     * @return
     */
    public PageDto<StaffExp> getMyStaffs(String sPage, String sLimit, Long userId);

    /**
     * 删除staff
     *
     * @param staffId
     */
    public void deleteStaffById(Long staffId);

    /**
     * 根据Id获取bulletin
     *
     * @param id
     * @return
     */
    public StaffExp getStaffById(Long id);

    /**
     * 增加或修改bulletin
     *
     * @param staff
     */
    public void addOrUpdateStaff(Staff staff);


    /**
     * 检验能否删除
     *
     * @param id
     * @return
     */
    public boolean checkDelete(Long id);


    /**
     * 上传工作人员图片
     *
     * @param picUrl
     */
    public void uploadPic(Long id, String picUrl);
}
