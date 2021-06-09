package com.company.blog.util;

import java.util.List;

/**
 * 数据分页
 */
public class PageResult {

    /**
     * 总记录数
     */
    private int totalCount;

    /**
     * 每页记录数
     */
    private int pageSize;

    /**
     * 当前页数
     */
    private int currentPage;

    /**
     * 所有数据项
     */
    private List<?> list;

    /**
     * 总页数
     */
    private int totalPage;

    public PageResult(int totalCount, int pageSize, int currentPage, List<?> list) {
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.currentPage = currentPage;
        this.list = list;
        this.totalPage=(int) Math.floor(totalCount*1.0/pageSize)+1;
    }

    public PageResult() {
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }
}
