package com.company.blog.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class BlogCategory {

    private Integer blogCategoryID;

    private String blogCategoryName;

    private String blogCategoryIcon;

    private Integer blogCategoryRank;

    private Byte blogCategoryIsDeleted;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date blogCategoryCreateTime;

    public BlogCategory(Integer blogCategoryID, String blogCategoryName, String blogCategoryIcon,
                        Integer blogCategoryRank, Byte blogCategoryIsDeleted, Date blogCategoryCreateTime) {
        this.blogCategoryID = blogCategoryID;
        this.blogCategoryName = blogCategoryName;
        this.blogCategoryIcon = blogCategoryIcon;
        this.blogCategoryRank = blogCategoryRank;
        this.blogCategoryIsDeleted = blogCategoryIsDeleted;
        this.blogCategoryCreateTime = blogCategoryCreateTime;
    }

    public BlogCategory() {
    }

    public Integer getBlogCategoryID() {
        return blogCategoryID;
    }

    public void setBlogCategoryID(Integer blogCategoryID) {
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

    public Integer getBlogCategoryRank() {
        return blogCategoryRank;
    }

    public void setBlogCategoryRank(Integer blogCategoryRank) {
        this.blogCategoryRank = blogCategoryRank;
    }

    public Byte getBlogCategoryIsDeleted() {
        return blogCategoryIsDeleted;
    }

    public void setBlogCategoryIsDeleted(Byte blogCategoryIsDeleted) {
        this.blogCategoryIsDeleted = blogCategoryIsDeleted;
    }

    public Date getBlogCategoryCreateTime() {
        return blogCategoryCreateTime;
    }

    public void setBlogCategoryCreateTime(Date blogCategoryCreateTime) {
        this.blogCategoryCreateTime = blogCategoryCreateTime;
    }
}
