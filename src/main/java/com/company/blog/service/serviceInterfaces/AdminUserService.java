package com.company.blog.service.serviceInterfaces;

import com.company.blog.model.AdminUser;

public interface AdminUserService {

    AdminUser login(String account,String password);

    AdminUser queryByAdminID(Integer adminID);

    boolean updateAdminUserNickname(Integer adminID,String nickName);

    boolean updateAdminUserAccount(Integer adminID,String account);

    boolean updateAdminUserPassword(Integer adminID,String oldPassword,String newPassword);
}
