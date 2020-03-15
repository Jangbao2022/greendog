package com.boob.greendog.service.bulletinService.impl;

import com.boob.greendog.dto.PageDto;
import com.boob.greendog.enums.PageUrlEnum;
import com.boob.greendog.exp.BulletinExp;
import com.boob.greendog.mapper.AdministratorMapper;
import com.boob.greendog.mapper.BulletinMapper;
import com.boob.greendog.model.Administrator;
import com.boob.greendog.model.Bulletin;
import com.boob.greendog.model.BulletinExample;
import com.boob.greendog.service.bulletinService.IBulletinService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * BulletinService实现类
 */
@Service
public class BulletinServiceImpl implements IBulletinService {


    @Autowired
    private BulletinMapper bulletinMapper;

    @Autowired
    private AdministratorMapper administratorMapper;

    @Override
    public PageDto<BulletinExp> getAllBulletin(String sPage, String sLimit) {
        PageDto<BulletinExp> bulletinDto = new PageDto<>(PageUrlEnum.ALL_BULLETINS.getUrl());

        BulletinExample bulletinExample = new BulletinExample();
        long total = bulletinMapper.countByExample(bulletinExample);
        bulletinDto.init((int) total, sPage, sLimit);

        List<Bulletin> bulletins = bulletinMapper.selectByExampleWithRowbounds(bulletinExample, new RowBounds(bulletinDto.getOffset(), bulletinDto.getLimit()));

        //按时间倒序
        Collections.sort(bulletins, (b1, b2) -> (int) (b2.getGmtModified().getTime() - b1.getGmtModified().getTime()));

        List<BulletinExp> bulletinExps = pottingBulletinList(bulletins);

        bulletinDto.setElements(bulletinExps);
        return bulletinDto;
    }


    /**
     * 增强bulletinList为BulletinExpList
     *
     * @param bulletins
     * @return
     */
    private List<BulletinExp> pottingBulletinList(List<Bulletin> bulletins) {
        List<BulletinExp> bulletinExps = new ArrayList<>();
        //封装bulletin
        for (Bulletin bulletin : bulletins) {

            BulletinExp bulletinExp = pottingBulletin(bulletin);
            bulletinExps.add(bulletinExp);
        }
        return bulletinExps;
    }

    @Override
    public void deleteCustomerById(Long bulletinId) {
        bulletinMapper.deleteByPrimaryKey(bulletinId);
    }

    @Override
    public BulletinExp getBulletinById(Long id) {
        Bulletin bulletin = bulletinMapper.selectByPrimaryKey(id);

        return pottingBulletin(bulletin);
    }

    /**
     * 增强bulletin为bulletin
     *
     * @param bulletin
     * @return
     */
    private BulletinExp pottingBulletin(Bulletin bulletin) {
        BulletinExp bulletinExp = new BulletinExp();
        Administrator administrator = administratorMapper.selectByPrimaryKey(bulletin.getPublisherId());

        bulletinExp.setPublisher(administrator);
        bulletinExp.setBulletin(bulletin);
        return bulletinExp;
    }

    @Override
    public void addOrUpdateBulletin(Bulletin bulletin) {
        if (bulletin.getId() != null) {
            //更新公告
            updateBulletin(bulletin);
        } else {
            //添加公告
            addBulletin(bulletin);
        }
    }

    /**
     * 新增公告
     *
     * @param bulletin
     */
    private void addBulletin(Bulletin bulletin) {
        bulletin.setGmtCreated(new Date());
        bulletin.setGmtModified(bulletin.getGmtCreated());
        bulletinMapper.insert(bulletin);
    }


    /**
     * 更新公告
     *
     * @param bulletin
     */
    private void updateBulletin(Bulletin bulletin) {
        bulletin.setGmtModified(new Date());
        bulletinMapper.updateByPrimaryKeySelective(bulletin);
    }
}
