package com.company.blog.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class BlogListVo implements Serializable {

    private Integer blogID;

    private String blogTitle;

    private String blogSubUrl;

    private String blogCoverImagePath;

    private  Integer blogCategoryID;

    private String blogCategoryIcon;

    private String blogCategoryName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date blogCreateTime;

    public BlogListVo(Integer blogID, String blogTitle, String blogSubUrl,
                      String blogCoverImagePath, Integer blogCategoryID, String blogCategoryIcon,
                      String blogCategoryName, Date blogCreateTime) {
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
