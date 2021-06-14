package com.company.blog.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class BlogComment {

    private Integer blogCommentID;

    private Integer blogID;

    private String blogCommentatorName;

    private String blogCommentatorEmail;

    private String blogWebsiteUrl;

    private String blogCommentContent;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date blogCommentSubmitTime;

    private String blogCommentatorIP;

    private String commentReplyContent;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date commentReplyTime;

    private Byte commentStatus;

    private Byte commentIsDeleted;

    public BlogComment(Integer blogCommentID, Integer blogID, String blogCommentatorName,
                       String blogCommentatorEmail, String blogWebsiteUrl, String blogCommentContent,
                       Date blogCommentSubmitTime, String blogCommentatorIP, String commentReplyContent,
                       Date commentReplyTime, Byte commentStatus, Byte commentIsDeleted) {
        this.blogCommentID = blogCommentID;
        this.blogID = blogID;
        this.blogCommentatorName = blogCommentatorName;
        this.blogCommentatorEmail = blogCommentatorEmail;
        this.blogWebsiteUrl = blogWebsiteUrl;
        this.blogCommentContent = blogCommentContent;
        this.blogCommentSubmitTime = blogCommentSubmitTime;
        this.blogCommentatorIP = blogCommentatorIP;
        this.commentReplyContent = commentReplyContent;
        this.commentReplyTime = commentReplyTime;
        this.commentStatus = commentStatus;
        this.commentIsDeleted = commentIsDeleted;
    }

    public BlogComment() {
    }

    public Integer getBlogCommentID() {
        return blogCommentID;
    }

    public void setBlogCommentID(Integer blogCommentID) {
        this.blogCommentID = blogCommentID;
    }

    public Integer getBlogID() {
        return blogID;
    }

    public void setBlogID(Integer blogID) {
        this.blogID = blogID;
    }

    public String getBlogCommentatorName() {
        return blogCommentatorName;
    }

    public void setBlogCommentatorName(String blogCommentatorName) {
        this.blogCommentatorName = blogCommentatorName;
    }

    public String getBlogCommentatorEmail() {
        return blogCommentatorEmail;
    }

    public void setBlogCommentatorEmail(String blogCommentatorEmail) {
        this.blogCommentatorEmail = blogCommentatorEmail;
    }

    public String getBlogWebsiteUrl() {
        return blogWebsiteUrl;
    }

    public void setBlogWebsiteUrl(String blogWebsiteUrl) {
        this.blogWebsiteUrl = blogWebsiteUrl;
    }

    public String getBlogCommentContent() {
        return blogCommentContent;
    }

    public void setBlogCommentContent(String blogCommentContent) {
        this.blogCommentContent = blogCommentContent;
    }

    public Date getBlogCommentSubmitTime() {
        return blogCommentSubmitTime;
    }

    public void setBlogCommentSubmitTime(Date blogCommentSubmitTime) {
        this.blogCommentSubmitTime = blogCommentSubmitTime;
    }

    public String getBlogCommentatorIP() {
        return blogCommentatorIP;
    }

    public void setBlogCommentatorIP(String blogCommentatorIP) {
        this.blogCommentatorIP = blogCommentatorIP;
    }

    public String getCommentReplyContent() {
        return commentReplyContent;
    }

    public void setCommentReplyContent(String commentReplyContent) {
        this.commentReplyContent = commentReplyContent;
    }

    public Date getCommentReplyTime() {
        return commentReplyTime;
    }

    public void setCommentReplyTime(Date commentReplyTime) {
        this.commentReplyTime = commentReplyTime;
    }

    public Byte getCommentStatus() {
        return commentStatus;
    }

    public void setCommentStatus(Byte commentStatus) {
        this.commentStatus = commentStatus;
    }

    public Byte getCommentIsDeleted() {
        return commentIsDeleted;
    }

    public void setCommentIsDeleted(Byte commentIsDeleted) {
        this.commentIsDeleted = commentIsDeleted;
    }
}
