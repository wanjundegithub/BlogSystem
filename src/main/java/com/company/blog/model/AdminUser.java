package com.company.blog.model;

/**
 * 管理员model
 */
public class AdminUser {

    private int adminID;

    private String adminAccount;

    private String adminPassword;

    private String adminNickname;

    private byte locked;

    public AdminUser(int adminID, String adminAccount, String adminPassword, String adminNickname, byte locked) {
        this.adminID = adminID;
        this.adminAccount = adminAccount;
        this.adminPassword = adminPassword;
        this.adminNickname = adminNickname;
        this.locked = locked;
    }

    public AdminUser(String adminAccount, String adminPassword, String adminNickname, byte locked) {
        this.adminAccount = adminAccount;
        this.adminPassword = adminPassword;
        this.adminNickname = adminNickname;
        this.locked = locked;
    }

    public AdminUser() {
    }

    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
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

    public byte getLocked() {
        return locked;
    }

    public void setLocked(byte locked) {
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
