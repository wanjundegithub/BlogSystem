package com.company.blog.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class DetailBlogVo implements Serializable {

    private  Integer blogID;

    private String blogTitle;

    private  Integer blogCategoryID;

    private Integer blogCommentCount;

    private String blogCategoryIcon;

    private String blogCategoryName;

    private String blogCoverImagePath;

    private Integer blogViews;

    private List<String> blogTags;

    private String blogContent;

    private Byte blogStatus;

    private Byte blogEnableComment;

    private Date blogCreateTime;

    public DetailBlogVo(Integer blogID, String blogTitle, Integer blogCategoryID,
                        Integer blogCommentCount, String blogCategoryIcon, String blogCategoryName,
                        String blogCoverImagePath, Integer blogViews, List<String> blogTags,
                        String blogContent, Byte blogStatus, Byte blogEnableComment, Date blogCreateTime) {
        this.blogID = blogID;
        this.blogTitle = blogTitle;
        this.blogCategoryID = blogCategoryID;
        this.blogCommentCount = blogCommentCount;
        this.blogCategoryIcon = blogCategoryIcon;
        this.blogCategoryName = blogCategoryName;
        this.blogCoverImagePath = blogCoverImagePath;
        this.blogViews = blogViews;
        this.blogTags = blogTags;
        this.blogContent = blogContent;
        this.blogStatus = blogStatus;
        this.blogEnableComment = blogEnableComment;
        this.blogCreateTime = blogCreateTime;
    }

    public DetailBlogVo() {
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

    public Integer getBlogCategoryID() {
        return blogCategoryID;
    }

    public void setBlogCategoryID(Integer blogCategoryID) {
        this.blogCategoryID = blogCategoryID;
    }

    public Integer getBlogCommentCount() {
        return blogCommentCount;
    }

    public void setBlogCommentCount(Integer blogCommentCount) {
        this.blogCommentCount = blogCommentCount;
    }

    public String getBlogCategoryIcon() {
        return blogCategoryIcon;
    }

    public void setBlogCategoryIcon(String blogCategoryIcon) {
        this.blogCategoryIcon = blogCategoryIcon;
    }

    public String getBlogCategoryName() {
        return blogCategoryName;
    }

    public void setBlogCategoryName(String blogCategoryName) {
        this.blogCategoryName = blogCategoryName;
    }

    public String getBlogCoverImagePath() {
        return blogCoverImagePath;
    }

    public void setBlogCoverImagePath(String blogCoverImagePath) {
        this.blogCoverImagePath = blogCoverImagePath;
    }

    public Integer getBlogViews() {
        return blogViews;
    }

    public void setBlogViews(Integer blogViews) {
        this.blogViews = blogViews;
    }

    public List<String> getBlogTags() {
        return blogTags;
    }

    public void setBlogTags(List<String> blogTags) {
        this.blogTags = blogTags;
    }

    public String getBlogContent() {
        return blogContent;
    }

    public void setBlogContent(String blogContent) {
        this.blogContent = blogContent;
    }

    public Byte getBlogStatus() {
        return blogStatus;
    }

    public void setBlogStatus(Byte blogStatus) {
        this.blogStatus = blogStatus;
    }

    public Byte getBlogEnableComment() {
        return blogEnableComment;
    }

    public void setBlogEnableComment(Byte blogEnableComment) {
        this.blogEnableComment = blogEnableComment;
    }

    public Date getBlogCreateTime() {
        return blogCreateTime;
    }

    public void setBlogCreateTime(Date blogCreateTime) {
        this.blogCreateTime = blogCreateTime;
    }
}
