package com.company.blog.model.vo;

import java.io.Serializable;

public class RoughBlogVo implements Serializable {

    private Integer blogID;

    private String blogTitle;

    public RoughBlogVo(Integer blogID, String blogTitle) {
        this.blogID = blogID;
        this.blogTitle = blogTitle;
    }

    public RoughBlogVo() {
    }

    public Integer getBlogID() {
        return blogID;
    }

    public void setBlogID(Integer blogID) {
        this.blogID = blogID;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }
}
