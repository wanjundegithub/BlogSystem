package com.company.blog.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class BlogTagRelation {

    private Integer relationID;

    private Integer blogID;

    private Integer blogTagID;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date relationCreateTime;

    public BlogTagRelation(Integer relationID, Integer blogID, Integer blogTagID, Date relationCreateTime) {
        this.relationID = relationID;
        this.blogID = blogID;
        this.blogTagID = blogTagID;
        this.relationCreateTime = relationCreateTime;
    }

    public BlogTagRelation() {
    }

    public BlogTagRelation(Integer blogID, Integer blogTagID) {
        this.blogID = blogID;
        this.blogTagID = blogTagID;
    }

    public Integer getRelationID() {
        return relationID;
    }

    public void setRelationID(Integer relationID) {
        this.relationID = relationID;
    }

    public Integer getBlogID() {
        return blogID;
    }

    public void setBlogID(Integer blogID) {
        this.blogID = blogID;
    }

    public Integer getBlogTagID() {
        return blogTagID;
    }

    public void setBlogTagID(Integer blogTagID) {
        this.blogTagID = blogTagID;
    }

    public Date getRelationCreateTime() {
        return relationCreateTime;
    }

    public void setRelationCreateTime(Date relationCreateTime) {
        this.relationCreateTime = relationCreateTime;
    }
}
