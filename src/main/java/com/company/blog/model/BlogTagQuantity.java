package com.company.blog.model;

public class BlogTagQuantity {

    private int blogTagID;

    private String blogTagName;

    private int blogTagCount;

    public BlogTagQuantity(int blogTagID, String blogTagName, int blogTagCount) {
        this.blogTagID = blogTagID;
        this.blogTagName = blogTagName;
        this.blogTagCount = blogTagCount;
    }

    public BlogTagQuantity() {
    }

    public int getBlogTagID() {
        return blogTagID;
    }

    public void setBlogTagID(int blogTagID) {
        this.blogTagID = blogTagID;
    }

    public String getBlogTagName() {
        return blogTagName;
    }

    public void setBlogTagName(String blogTagName) {
        this.blogTagName = blogTagName;
    }

    public int getBlogTagCount() {
        return blogTagCount;
    }

    public void setBlogTagCount(int blogTagCount) {
        this.blogTagCount = blogTagCount;
    }
}
