package com.company.blog.model;

/**
 * 管理员model
 */
public class AdminUser {

    private Integer adminID;

    private String adminAccount;

    private String adminPassword;

    private String adminNickname;

    private Byte locked;

    public AdminUser(Integer adminID, String adminAccount, String adminPassword,
                     String adminNickname, Byte locked) {
        this.adminID = adminID;
        this.adminAccount = adminAccount;
        this.adminPassword = adminPassword;
        this.adminNickname = adminNickname;
        this.locked = locked;
    }

    public AdminUser() {
    }

    public Integer getAdminID() {
        return adminID;
    }

    public void setAdminID(Integer adminID) {
        this.adminID = adminID;
    }

    public String getAdminAccount() {
        return adminAccount;
    }

    public void setAdminAccount(String adminAccount) {
        this.adminAccount = adminAccount;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public String getAdminNickname() {
        return adminNickname;
    }

    public void setAdminNickname(String adminNickname) {
        this.adminNickname = adminNickname;
    }

    public Byte getLocked() {
        return locked;
    }

    public void setLocked(Byte locked) {
        this.locked = locked;
    }

    @Override
    public String toString() {
        return "adminID:"+adminID
                +",adminAccount:"+adminAccount
                +",adminPassword:"+adminPassword
                +",adminNickname:"+adminNickname
                +",locked:"+locked;
    }
}
