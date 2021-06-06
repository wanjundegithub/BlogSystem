package com.company.blog.daotest;

import com.company.blog.dao.BlogConfigDao;
import com.company.blog.model.BlogConfig;
import com.company.blog.util.LoggerUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BlogConfigTest {

    @Autowired
    private BlogConfigDao blogConfigDao;

    @Test
     public void testFindAllConfig(){
        var results=blogConfigDao.findAllConfig();
        results.forEach(result->{
            LoggerUtil.info("configName:"+result.getBlogConfigName()
            +",configValue:"+result.getBlogConfigValue());
        });
    }

    @Test
    public void testGetConfigByPrimaryKey(){
        var result=blogConfigDao.getConfigByPrimaryKey("footerAbout");
        LoggerUtil.info("configName:"+result.getBlogConfigName()
                +",configValue:"+result.getBlogConfigValue());
    }

    @Test
    @Rollback(value = false)
    public void testUpdateSelectiveBlogConfig() throws ParseException {
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date createDate=dateFormat.parse("2018-11-11 20:33:23");
        Date updateDate=dateFormat.parse("2018-11-12 11:58:06");
        BlogConfig blogConfig=new BlogConfig("footerAbout",
                "first blog have fun.",
                createDate,updateDate);
        var result=blogConfigDao.updateSelectiveBlogConfig(blogConfig);
        LoggerUtil.info(result+"结果");
    }
}
