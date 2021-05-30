package com.company.blog.model;

import java.util.Date;

public class BlogTag {

    private int blogTagID;

    private String blogTagName;

    private byte blogTagIsDeleted;

    private Date blogTagCreateTime;

    public BlogTag(int blogTagID, String blogTagName, byte blogTagIsDeleted, Date blogTagCreateTime) {
        this.blogTagID = blogTagID;
        this.blogTagName = blogTagName;
        this.blogTagIsDeleted = blogTagIsDeleted;
        this.blogTagCreateTime = blogTagCreateTime;
    }

    public BlogTag() {
    }

    public int getBlogTagID() {
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

    public byte getBlogTagIsDeleted() {
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
