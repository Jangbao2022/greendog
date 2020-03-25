package com.boob.greendog.service.applyService;

import com.boob.greendog.dto.PageDto;
import com.boob.greendog.exp.ApplyExp;
import com.boob.greendog.model.Apply;

/**
 * applyService接口
 */
public interface IApplyService {

    /**
     * 获取我的所有申请
     *
     * @return
     */
    public PageDto<ApplyExp> getMyApplies(String sPage, String sLimit, Long userId);

    /**
     * 获取所有申请
     *
     * @return
     */
    public PageDto<ApplyExp> getAllApplies(String sPage, String sLimit);


    /**
     * 同意申请
     *
     * @param applyId
     */
    void consentApply(Long applyId);

    /**
     * 拒绝申请
     *
     * @param applyId
     */
    void rejectApply(Long applyId);


    /**
     * 申请领养
     *
     * @param apply
     */
    void adopt(Apply apply);


    /**
     * 申请寄养
     *
     * @param apply
     */
    void send(Apply apply);


    /**
     * 删除请求
     *
     * @param applyId
     */
    void delApply(Long applyId);
}
