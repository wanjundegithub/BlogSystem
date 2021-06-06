package com.company.blog.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class BlogLink {

    private Integer blogLinkID;

    private Byte blogLinkType;

    private String blogLinkName;

    private String blogLinkUrl;

    private String blogLinkDescription;

    private Integer blogLinkRank;

    private Byte blogLinkIsDeleted;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date blogLinkCreateTime;

    public BlogLink(Integer blogLinkID, Byte blogLinkType, String blogLinkName, String blogLinkUrl,
                    String blogLinkDescription, Integer blogLinkRank, Byte blogLinkIsDeleted,
                    Date blogLinkCreateTime) {
        this.blogLinkID = blogLinkID;
        this.blogLinkType = blogLinkType;
        this.blogLinkName = blogLinkName;
        this.blogLinkUrl = blogLinkUrl;
        this.blogLinkDescription = blogLinkDescription;
        this.blogLinkRank = blogLinkRank;
        this.blogLinkIsDeleted = blogLinkIsDeleted;
        this.blogLinkCreateTime = blogLinkCreateTime;
    }

    public BlogLink() {
    }

    public Integer getBlogLinkID() {
        return blogLinkID;
    }

    public void setBlogLinkID(Integer blogLinkID) {
        this.blogLinkID = blogLinkID;
    }

    public Byte getBlogLinkType() {
        return blogLinkType;
    }

    public void setBlogLinkType(Byte blogLinkType) {
        this.blogLinkType = blogLinkType;
    }

    public String getBlogLinkName() {
        return blogLinkName;
    }

    public void setBlogLinkName(String blogLinkName) {
        this.blogLinkName = blogLinkName;
    }

    public String getBlogLinkUrl() {
        return blogLinkUrl;
    }

    public void setBlogLinkUrl(String blogLinkUrl) {
        this.blogLinkUrl = blogLinkUrl;
    }

    public String getBlogLinkDescription() {
        return blogLinkDescription;
    }

    public void setBlogLinkDescription(String blogLinkDescription) {
        this.blogLinkDescription = blogLinkDescription;
    }

    public Integer getBlogLinkRank() {
        return blogLinkRank;
    }

    public void setBlogLinkRank(Integer blogLinkRank) {
        this.blogLinkRank = blogLinkRank;
    }

    public Byte getBlogLinkIsDeleted() {
        return blogLinkIsDeleted;
    }

    public void setBlogLinkIsDeleted(Byte blogLinkIsDeleted) {
        this.blogLinkIsDeleted = blogLinkIsDeleted;
    }

    public Date getBlogLinkCreateTime() {
        return blogLinkCreateTime;
    }

    public void setBlogLinkCreateTime(Date blogLinkCreateTime) {
        this.blogLinkCreateTime = blogLinkCreateTime;
    }
}
