package com.company.blog.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class DetailBlogVo implements Serializable {

    private  int blogID;

    private String blogTitle;

    private  int blogCategoryID;

    private Integer blogCommentCount;

    private String blogCategoryIcon;

    private String blogCategoryName;

    private String blogCoverImagePath;

    private Integer blogViews;

    private List<String> blogTags;

    private String blogContent;

    private byte blogStatus;

    private byte blogEnableComment;

    private Date blogCreateTime;

    public DetailBlogVo(int blogID, String blogTitle, int blogCategoryID,
                        Integer blogCommentCount, String blogCategoryIcon,
                        String blogCategoryName, String blogCoverImagePath,
                        Integer blogViews, List<String> blogTags, String blogContent,
                        byte blogStatus, byte blogEnableComment, Date blogCreateTime) {
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

    public int getBlogCategoryID() {
        return blogCategoryID;
    }

    public void setBlogCategoryID(int blogCategoryID) {
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

    public byte getBlogStatus() {
        return blogStatus;
    }

    public void setBlogStatus(byte blogStatus) {
        this.blogStatus = blogStatus;
    }

    public byte getBlogEnableComment() {
        return blogEnableComment;
    }

    public void setBlogEnableComment(byte blogEnableComment) {
        this.blogEnableComment = blogEnableComment;
    }

    public Date getBlogCreateTime() {
        return blogCreateTime;
    }

    public void setBlogCreateTime(Date blogCreateTime) {
        this.blogCreateTime = blogCreateTime;
    }
}
