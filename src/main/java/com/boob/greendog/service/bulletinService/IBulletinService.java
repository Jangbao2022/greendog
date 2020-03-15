package com.boob.greendog.service.bulletinService;

import com.boob.greendog.dto.PageDto;
import com.boob.greendog.exp.BulletinExp;
import com.boob.greendog.model.Bulletin;

/**
 * bulletinService接口
 */
public interface IBulletinService {


    /**
     * 获取所有公告
     * 默认按修改时间倒序
     *
     * @return
     */
    public PageDto<BulletinExp> getAllBulletin(String sPage, String sLimit);


    /**
     * 删除公告
     *
     * @param bulletinId
     */
    public void deleteBulletinById(Long bulletinId);

    /**
     * 根据Id获取bulletin
     *
     * @param id
     * @return
     */
    BulletinExp getBulletinById(Long id);

    /**
     * 增加或修改bulletin
     *
     * @param bulletin
     */
    void addOrUpdateBulletin(Bulletin bulletin);
}
