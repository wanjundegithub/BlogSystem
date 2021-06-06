package com.company.blog.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class BlogTag {

    private Integer blogTagID;

    private String blogTagName;

    private Byte blogTagIsDeleted;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date blogTagCreateTime;

    public BlogTag(Integer blogTagID, String blogTagName, Byte blogTagIsDeleted, Date blogTagCreateTime) {
        this.blogTagID = blogTagID;
        this.blogTagName = blogTagName;
        this.blogTagIsDeleted = blogTagIsDeleted;
        this.blogTagCreateTime = blogTagCreateTime;
    }

    public BlogTag(String blogTagName) {
        this.blogTagName = blogTagName;
    }

    public BlogTag() {
    }

    public Integer getBlogTagID() {
        return blogTagID;
    }

    public void setBlogTagID(int blogTagID) {
        this.blogTagID = blogTagID;
    }

    public String getBlogTagName() {
        return blogTagName;
    }

    public void setBlogTagName(String blogTagName) {
        this.blogTagName = blogTagName;
    }

    public Byte getBlogTagIsDeleted() {
        return blogTagIsDeleted;
    }

    public void setBlogTagIsDeleted(byte blogTagIsDeleted) {
        this.blogTagIsDeleted = blogTagIsDeleted;
    }

    public Date getBlogTagCreateTime() {
        return blogTagCreateTime;
    }

    public void setBlogTagCreateTime(Date blogTagCreateTime) {
        this.blogTagCreateTime = blogTagCreateTime;
    }
}
