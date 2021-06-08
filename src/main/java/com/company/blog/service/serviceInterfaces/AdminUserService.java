package com.company.blog.service.serviceInterfaces;

import com.company.blog.model.AdminUser;

public interface AdminUserService {

    AdminUser login(String account,String password);

    AdminUser queryByAdminID(Integer adminID);

    boolean updateAdminUserInfo(Integer adminID,String account,String nickName);

    boolean updateAdminUserPassword(Integer adminID,String oldPassword,String newPassword);
}
