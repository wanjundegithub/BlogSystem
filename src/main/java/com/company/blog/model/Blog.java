package com.company.blog.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Blog {

    private  Integer blogID;

    private String blogTitle;

    private String blogSubUrl;

    private String blogCoverImagePath;

    private  Integer blogCategoryID;

    private String blogCategoryName;

    private String blogTags;

    private Byte blogStatus;

    private Integer blogViews;

    private Byte blogEnableComment;

    private Byte blogIsDeleted;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date blogCreateTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date blogUpdateTime;

    private String blogContent;

    public Blog(Integer blogID, String blogTitle, String blogSubUrl, String blogCoverImagePath,
                Integer blogCategoryID, String blogCategoryName, String blogTags, Byte blogStatus,
                Integer blogViews, Byte blogEnableComment, Byte blogIsDeleted, Date blogCreateTime,
                Date blogUpdateTime, String blogContent) {
        this.blogID = blogID;
        this.blogTitle = blogTitle;
        this.blogSubUrl = blogSubUrl;
        this.blogCoverImagePath = blogCoverImagePath;
        this.blogCategoryID = blogCategoryID;
        this.blogCategoryName = blogCategoryName;
        this.blogTags = blogTags;
        this.blogStatus = blogStatus;
        this.blogViews = blogViews;
        this.blogEnableComment = blogEnableComment;
        this.blogIsDeleted = blogIsDeleted;
        this.blogCreateTime = blogCreateTime;
        this.blogUpdateTime = blogUpdateTime;
        this.blogContent = blogContent;
    }

    public Blog() {
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

    public String getBlogSubUrl() {
        return blogSubUrl;
    }

    public void setBlogSubUrl(String blogSubUrl) {
        this.blogSubUrl = blogSubUrl;
    }

    public String getBlogCoverImagePath() {
        return blogCoverImagePath;
    }

    public void setBlogCoverImagePath(String blogCoverImagePath) {
        this.blogCoverImagePath = blogCoverImagePath;
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

    public String getBlogTags() {
        return blogTags;
    }

    public void setBlogTags(String blogTags) {
        this.blogTags = blogTags;
    }

    public Byte getBlogStatus() {
        return blogStatus;
    }

    public void setBlogStatus(Byte blogStatus) {
        this.blogStatus = blogStatus;
    }

    public Integer getBlogViews() {
        return blogViews;
    }

    public void setBlogViews(Integer blogViews) {
        this.blogViews = blogViews;
    }

    public Byte getBlogEnableComment() {
        return blogEnableComment;
    }

    public void setBlogEnableComment(Byte blogEnableComment) {
        this.blogEnableComment = blogEnableComment;
    }

    public Byte getBlogIsDeleted() {
        return blogIsDeleted;
    }

    public void setBlogIsDeleted(Byte blogIsDeleted) {
        this.blogIsDeleted = blogIsDeleted;
    }

    public Date getBlogCreateTime() {
        return blogCreateTime;
    }

    public void setBlogCreateTime(Date blogCreateTime) {
        this.blogCreateTime = blogCreateTime;
    }

    public Date getBlogUpdateTime() {
        return blogUpdateTime;
    }

    public void setBlogUpdateTime(Date blogUpdateTime) {
        this.blogUpdateTime = blogUpdateTime;
    }

    public String getBlogContent() {
        return blogContent;
    }

    public void setBlogContent(String blogContent) {
        this.blogContent = blogContent;
    }

    public void copyFrom(Blog fromBlog){
        this.setBlogTitle(fromBlog.getBlogTitle());
        this.setBlogSubUrl(fromBlog.getBlogSubUrl());
        this.setBlogContent(fromBlog.getBlogContent());
        this.setBlogCoverImagePath(fromBlog.getBlogCoverImagePath());
        this.setBlogStatus(fromBlog.getBlogStatus());
        this.setBlogEnableComment(fromBlog.getBlogEnableComment());
    }
}
