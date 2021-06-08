package com.company.blog.service.serviceImpl;

import com.company.blog.dao.AdminUserDao;
import com.company.blog.model.AdminUser;
import com.company.blog.service.serviceInterfaces.AdminUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminUserServiceImpl implements AdminUserService {

    @Resource
    private AdminUserDao adminUserDao;

    @Override
    public AdminUser login(String account, String password) {
        return adminUserDao.login(account,password);
    }

    @Override
    public AdminUser queryByAdminID(Integer adminID) {
        return adminUserDao.queryAdminUserByID(adminID);
    }

    @Override
    public boolean updateAdminUserInfo(Integer adminID,String account,String nickName) {
        var user=adminUserDao.queryAdminUserByID(adminID);
        if(null==user){
            return false;
        }
        user.setAdminAccount(account);
        user.setAdminNickname(nickName);
        return adminUserDao.updateAdminUser(user)!=0;
    }


    @Override
    public boolean updateAdminUserPassword(Integer adminID, String oldPassword, String newPassword) {
        var user=adminUserDao.queryAdminUserByID(adminID);
        if(null==user){
            return false;
        }
        if(null==adminUserDao.login(user.getAdminAccount(),oldPassword)){
            return false;
        }
        user.setAdminPassword(newPassword);
        return adminUserDao.updateAdminUser(user)!=0;
    }


}
