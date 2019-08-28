package com.rnzhiw.ibicycle.common;

import java.util.List;

/**
 * 分页返回格式
 *
 * @author fuenhui
 * @date 2018/12/12
 */
public class PageInfo<T> {

    /**
     * 未分页前的数据总数
     */
    private Long total;

    /**
     * 本页的数据数
     */
    private Integer size;

    /**
     * 本页的数据
     */
    private List<T> list;

    /**
     * 总页数
     */
    private Integer pages;

    /**
     * 页码
     */
    private Integer pageNum;

    /**
     * 一页的标准数据行
     */
    private Integer pageSize;

    /**
     * 是否为第一页
     */
    private Boolean firstPage;

    /**
     * 是否为最后一页
     */
    private Boolean lastPage;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Boolean getFirstPage() {
        return firstPage;
    }

    public void setFirstPage(Boolean firstPage) {
        this.firstPage = firstPage;
    }

    public Boolean getLastPage() {
        return lastPage;
    }

    public void setLastPage(Boolean lastPage) {
        this.lastPage = lastPage;
    }
}
