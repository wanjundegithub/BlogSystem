package com.company.blog.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class BlogCategory {

    private int blogCategoryID;

    private String blogCategoryName;

    private String blogCategoryIcon;

    private int blogCategoryRank;

    private byte blogCategoryIsDeleted;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date blogCategoryCreateTime;

    public BlogCategory(int blogCategoryID, String blogCategoryName, String blogCategoryIcon, int blogCategoryRank,
                        byte blogCategoryIsDeleted, Date blogCategoryCreateTime) {
        this.blogCategoryID = blogCategoryID;
        this.blogCategoryName = blogCategoryName;
        this.blogCategoryIcon = blogCategoryIcon;
        this.blogCategoryRank = blogCategoryRank;
        this.blogCategoryIsDeleted = blogCategoryIsDeleted;
        this.blogCategoryCreateTime = blogCategoryCreateTime;
    }

    public BlogCategory() {
    }

    public int getBlogCategoryID() {
        return blogCategoryID;
    }

    public void setBlogCategoryID(int blogCategoryID) {
        this.blogCategoryID = blogCategoryID;
    }

    public String getBlogCategoryName() {
        return blogCategoryName;
    }

    public void setBlogCategoryName(String blogCategoryName) {
        this.blogCategoryName = blogCategoryName;
    }

    public String getBlogCategoryIcon() {
        return blogCategoryIcon;
    }

    public void setBlogCategoryIcon(String blogCategoryIcon) {
        this.blogCategoryIcon = blogCategoryIcon;
    }

    public int getBlogCategoryRank() {
        return blogCategoryRank;
    }

    public void setBlogCategoryRank(int blogCategoryRank) {
        this.blogCategoryRank = blogCategoryRank;
    }

    public byte getBlogCategoryIsDeleted() {
        return blogCategoryIsDeleted;
    }

    public void setBlogCategoryIsDeleted(byte blogCategoryIsDeleted) {
        this.blogCategoryIsDeleted = blogCategoryIsDeleted;
    }

    public Date getBlogCategoryCreateTime() {
        return blogCategoryCreateTime;
    }

    public void setBlogCategoryCreateTime(Date blogCategoryCreateTime) {
        this.blogCategoryCreateTime = blogCategoryCreateTime;
    }
}
