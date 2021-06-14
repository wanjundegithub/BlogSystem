package com.company.blog.model.vo;

import java.io.Serializable;

public class RoughBlogVo implements Serializable {

    private  int blogID;

    private String blogTitle;

    public RoughBlogVo(int blogID, String blogTitle) {
        this.blogID = blogID;
        this.blogTitle = blogTitle;
    }

    public RoughBlogVo() {
    }

    public int getBlogID() {
        return blogID;
    }

    public void setBlogID(int blogID) {
        this.blogID = blogID;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }
}
