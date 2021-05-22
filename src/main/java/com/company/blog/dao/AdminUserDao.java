package com.company.blog.dao;

import com.company.blog.model.AdminUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminUserDao {

    int insertAdminUser(AdminUser adminUser);

    int insertSelectiveAdminUser(AdminUser adminUser);

    AdminUser login(@Param("account") String account, @Param("password") String password);

    AdminUser queryAdminUserByID(int adminID);

    int updateAdminUser(AdminUser adminUser);

    int updateSelectiveAdminUser(AdminUser adminUser);
}
