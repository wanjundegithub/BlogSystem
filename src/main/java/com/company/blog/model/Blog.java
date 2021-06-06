package com.company.blog.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Blog {

    private  int blogID;

    private String blogTitle;

    private String blogSubUrl;

    private String blogCoverImagePath;

    private  int blogCategoryID;

    private String blogCategoryName;

    private String blogTags;

    private byte blogStatus;

    private int blogViews;

    private byte blogEnableComment;

    private byte blogIsDeleted;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date blogCreateTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date blogUpdateTime;

    private String blogContent;

    public Blog(Integer blogID, String blogTitle, String blogSubUrl, String blogCoverImagePath,
                int blogCategoryID, String blogCategoryName, String blogTags, byte blogStatus,
                int blogViews, byte blogEnableComment, byte blogIsDeleted, Date blogCreateTime,
                Date blogUpdateTime) {
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
    }

    public Blog() {
    }

    public Blog(int blogID, String blogTitle, String blogSubUrl, String blogCoverImagePath,
                int blogCategoryID, String blogCategoryName, String blogTags, byte blogStatus,
                int blogViews, byte blogEnableComment, byte blogIsDeleted, Date blogCreateTime,
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

    public String getBlogContent() {
        return blogContent;
    }

    public void setBlogContent(String blogContent) {
        this.blogContent = blogContent;
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

    public String getBlogTags() {
        return blogTags;
    }

    public void setBlogTags(String blogTags) {
        this.blogTags = blogTags;
    }

    public byte getBlogStatus() {
        return blogStatus;
    }

    public void setBlogStatus(byte blogStatus) {
        this.blogStatus = blogStatus;
    }

    public int getBlogViews() {
        return blogViews;
    }

    public void setBlogViews(int blogViews) {
        this.blogViews = blogViews;
    }

    public byte getBlogEnableComment() {
        return blogEnableComment;
    }

    public void setBlogEnableComment(byte blogEnableComment) {
        this.blogEnableComment = blogEnableComment;
    }

    public byte getBlogIsDeleted() {
        return blogIsDeleted;
    }

    public void setBlogIsDeleted(byte blogIsDeleted) {
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
}
