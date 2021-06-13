package com.company.blog.util;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 分页查询
 */
public class PageQueryUtil extends LinkedHashMap<String,Object> {

    /**
     * 当前页数
     */
    private int currentPage;

    /**
     * 每页条数
     */
    private int limit;

    public PageQueryUtil(Map<String, Object> m) {
       this.putAll(m);
       this.currentPage=Integer.parseInt(m.get("page").toString());
       this.limit=Integer.parseInt(m.get("limit").toString());
       this.put("start",(currentPage-1)*limit);
       this.put("page",currentPage);
       this.put("limit",limit);
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "当前页数:"+currentPage
                +",每页条数:"+limit;
    }
}
