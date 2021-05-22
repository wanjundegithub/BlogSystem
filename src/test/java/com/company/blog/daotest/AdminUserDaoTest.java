package com.company.blog.daotest;

import com.company.blog.dao.AdminUserDao;
import com.company.blog.model.AdminUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AdminUserDaoTest {

    private static final Logger logger= LoggerFactory.getLogger(AdminUserDaoTest.class);

    @Resource
    private AdminUserDao adminUserDao;

    @Test
    @Rollback(value = false)
    public void testInsertAdminUser(){
        AdminUser adminUser=new AdminUser(3,"admin2","111","str",(byte) 0);
        var result=adminUserDao.insertAdminUser(adminUser);
        logger.info("是否成功:"+result);
    }

    @Test
    @Rollback(value = false)
    public void testInsertSelectiveAdminUser(){
        AdminUser adminUser=new AdminUser(4,"admin2","111","hhh",(byte) 0);
        var result=adminUserDao.insertSelectiveAdminUser(adminUser);
        logger.info("是否成功:"+result);
    }

    @Test
    public void testLogin(){
        String account="admin";
        String password="12345";
        var result=adminUserDao.login(account,password);
        if(result==null){
            logger.info("查询失败");
        }
        logger.info(result.toString());
    }

    @Test
    public void testQueryAdminUserByID(){
        int adminID=2;
        var result=adminUserDao.queryAdminUserByID(adminID);
        logger.info(result.toString());
    }

    @Test
    @Rollback(value = false)
    public void testUpdateAdminUser(){
        int adminID=1;
        var user=adminUserDao.queryAdminUserByID(adminID);
        if(user==null){
            logger.info("查询失败");
            return;
        }
        user.setAdminPassword("111");
        var result=adminUserDao.updateAdminUser(user);
        logger.info("是否成功:"+result);
    }

    @Test
    @Rollback(value = false)
    public void updateSelectiveAdminUser(){
        int adminID=1;
        var user=adminUserDao.queryAdminUserByID(adminID);
        if(user==null){
            logger.info("查询失败");
            return;
        }
        user.setAdminPassword("999");
        var result=adminUserDao.updateAdminUser(user);
        logger.info("是否成功:"+result);
    }
}
