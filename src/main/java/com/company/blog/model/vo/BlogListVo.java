package com.company.blog.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class BlogListVo implements Serializable {

    private  int blogID;

    private String blogTitle;

    private String blogSubUrl;

    private String blogCoverImagePath;

    private  int blogCategoryID;

    private String blogCategoryIcon;

    private String blogCategoryName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date blogCreateTime;

    public BlogListVo(int blogID, String blogTitle, String blogSubUrl, String blogCoverImagePath,
                      int blogCategoryID, String blogCategoryIcon, String blogCategoryName,
                      Date blogCreateTime) {
        this.blogID = blogID;
        this.blogTitle = blogTitle;
        this.blogSubUrl = blogSubUrl;
        this.blogCoverImagePath = blogCoverImagePath;
        this.blogCategoryID = blogCategoryID;
        this.blogCategoryIcon = blogCategoryIcon;
        this.blogCategoryName = blogCategoryName;
        this.blogCreateTime = blogCreateTime;
    }

    public BlogListVo() {
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

    public int getBlogCategoryID() {
        return blogCategoryID;
    }

    public void setBlogCategoryID(int blogCategoryID) {
        this.blogCategoryID = blogCategoryID;
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

    public Date getBlogCreateTime() {
        return blogCreateTime;
    }

    public void setBlogCreateTime(Date blogCreateTime) {
        this.blogCreateTime = blogCreateTime;
    }
}
