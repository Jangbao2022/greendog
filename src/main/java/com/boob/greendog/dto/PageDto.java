package com.boob.greendog.dto;

import lombok.Data;

import java.util.List;

/**
 * 封装页面类
 *
 * @param <E>
 */
@Data
public class PageDto<E> {

    //elements列表
    private List<E> elements;
    //总页数
    private Integer totalPage;
    //当前页
    private Integer page;
    //前一页
    private Integer afterPage;
    //后一页
    private Integer prePage;

    //静态pageUrl
    private String pageUrl;

    public PageDto(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    /**
     * 设置totalPage数量
     *
     * @param total 有多少个elements
     */
    private void countTotalPage(Integer total, Integer limit) {
        if (total % limit == 0) {
            totalPage = total / limit;
        } else {
            totalPage = total / limit + 1;
        }
    }

    /**
     * 计算当前页,前页和后页
     *
     * @param page
     */
    private void countPreAndAfter(Integer page) {

        //如果总共只有一页
        if (totalPage <= 1) {
            this.page = 1;
            prePage = this.page;
            afterPage = this.page;
            return;
        }

        if (page <= 1) {
            //如果当前页小于等于第一页
            this.page = 1;
            prePage = this.page;
            afterPage = this.page + 1;

        } else if (page >= totalPage) {
            //如果当前页大于等于最后一页
            this.page = totalPage;
            prePage = this.page - 1;
            afterPage = this.page;
        } else {
            //当前页在中间
            this.page = page;
            prePage = this.page - 1;
            afterPage = this.page + 1;
        }
    }

    /**
     * 初始化pageDto
     *
     * @param total
     * @param page
     * @param limit
     */
    public void init(int total, int page, int limit) {
        countTotalPage(total, limit);
        countPreAndAfter(page);
    }


}
