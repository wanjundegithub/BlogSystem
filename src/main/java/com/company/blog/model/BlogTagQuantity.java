package com.company.blog.model;

public class BlogTagQuantity {

    private Integer blogTagID;

    private String blogTagName;

    private Integer blogTagCount;

    public BlogTagQuantity(Integer blogTagID, String blogTagName, Integer blogTagCount) {
        this.blogTagID = blogTagID;
        this.blogTagName = blogTagName;
        this.blogTagCount = blogTagCount;
    }

    public BlogTagQuantity() {
    }

    public Integer getBlogTagID() {
        return blogTagID;
    }

    public void setBlogTagID(Integer blogTagID) {
        this.blogTagID = blogTagID;
    }

    public String getBlogTagName() {
        return blogTagName;
    }

    public void setBlogTagName(String blogTagName) {
        this.blogTagName = blogTagName;
    }

    public Integer getBlogTagCount() {
        return blogTagCount;
    }

    public void setBlogTagCount(Integer blogTagCount) {
        this.blogTagCount = blogTagCount;
    }
}
