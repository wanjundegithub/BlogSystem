package com.company.blog.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class BlogConfig {

    private String blogConfigName;

    private String blogConfigValue;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date blogConfigCreateTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date blogConfigUpdateTime;

    public BlogConfig(String blogConfigName, String blogConfigValue,
                      Date blogConfigCreateTime, Date blogConfigUpdateTime) {
        this.blogConfigName = blogConfigName;
        this.blogConfigValue = blogConfigValue;
        this.blogConfigCreateTime = blogConfigCreateTime;
        this.blogConfigUpdateTime = blogConfigUpdateTime;
    }

    public BlogConfig() {
    }

    public String getBlogConfigName() {
        return blogConfigName;
    }

    public void setBlogConfigName(String blogConfigName) {
        this.blogConfigName = blogConfigName;
    }

    public String getBlogConfigValue() {
        return blogConfigValue;
    }

    public void setBlogConfigValue(String blogConfigValue) {
        this.blogConfigValue = blogConfigValue;
    }

    public Date getBlogConfigCreateTime() {
        return blogConfigCreateTime;
    }

    public void setBlogConfigCreateTime(Date blogConfigCreateTime) {
        this.blogConfigCreateTime = blogConfigCreateTime;
    }

    public Date getBlogConfigUpdateTime() {
        return blogConfigUpdateTime;
    }

    public void setBlogConfigUpdateTime(Date blogConfigUpdateTime) {
        this.blogConfigUpdateTime = blogConfigUpdateTime;
    }
}
