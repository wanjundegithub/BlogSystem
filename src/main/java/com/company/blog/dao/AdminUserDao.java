package com.company.blog.dao;

import com.company.blog.model.AdminUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminUserDao {

    Integer insertAdminUser(AdminUser adminUser);

    Integer insertSelectiveAdminUser(AdminUser adminUser);

    AdminUser login(@Param("account") String account, @Param("password") String password);

    AdminUser queryAdminUserByID(Integer adminID);

    Integer updateAdminUser(AdminUser adminUser);

    Integer updateSelectiveAdminUser(AdminUser adminUser);
}
