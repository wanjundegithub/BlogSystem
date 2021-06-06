package com.company.blog.daotest;

import com.company.blog.dao.BlogLinkDao;
import com.company.blog.model.BlogLink;
import com.company.blog.util.LoggerUtil;
import com.company.blog.util.PageQueryUtil;
import org.apache.ibatis.annotations.Param;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BlogLinkTest {

    @Autowired
    private BlogLinkDao blogLinkDao;

    @Test
    @Rollback(value = false)
    public void deleteBlogLinkByPrimaryKey(){
        int result=blogLinkDao.deleteBlogLinkByPrimaryKey(30);
        LoggerUtil.info("删除操作:"+result);
    }

    @Test
    @Rollback(value = false)
    public void deleteBlogLinkByBatch(){
        int[] ids={30,31,32};
        int result=blogLinkDao.deleteBlogLinkByBatch(ids);
        LoggerUtil.info("批量删除操作:"+result);
    }

    @Test
    @Rollback(value = false)
    public void insertBlogLink() throws ParseException {
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=dateFormat.parse("2021-5-31 20:43:21");
        BlogLink blogLink=new BlogLink(30,(byte)2,"TestLinkType",
                "www.baidu.com", "Test Hello World",19,
                (byte)0,date);
        int result=blogLinkDao.insertBlogLink(blogLink);
        LoggerUtil.info("插入操作:"+result);
    }


    @Test
    @Rollback(value = false)
    public void insertSelectiveBlogLink() throws ParseException {
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=dateFormat.parse("2021-5-31 20:43:21");
        BlogLink blogLink=new BlogLink(null,(byte)2,"void",
                "www.baidu.com", "Test Hello World",19,
                (byte)0,null);
        int result=blogLinkDao.insertSelectiveBlogLink(blogLink);
        LoggerUtil.info("插入操作:"+result);
    }

    @Test
    @Rollback(value = false)
    public void findBlogLinkByPrimaryKey(){
        var result=blogLinkDao.findBlogLinkByPrimaryKey(19);
        LoggerUtil.info("name:"+result.getBlogLinkName()+",url:"+result.getBlogLinkUrl());
    }

    @Test
    @Rollback(value = false)
    public void findBlogLinkList(){
        Map<String,Object> maps=new HashMap<>();
        maps.put("currentPage",1);
        maps.put("limit",3);
        PageQueryUtil pageQueryUtil=new PageQueryUtil(maps);
        var results=blogLinkDao.findBlogLinkList(pageQueryUtil);
        results.forEach(result->{
            LoggerUtil.info("name:"+result.getBlogLinkName()+",url:"+result.getBlogLinkUrl());
        });
    }

    @Test
    @Rollback(value = false)
    public void getTotalBlogLinkCount(){
        Map<String,Object> maps=new HashMap<>();
        maps.put("currentPage",1);
        maps.put("limit",3);
        PageQueryUtil pageQueryUtil=new PageQueryUtil(maps);
        var count=blogLinkDao.getTotalBlogLinkCount(pageQueryUtil);
        LoggerUtil.info("数量:"+count);
    }

    @Test
    @Rollback(value = false)
    public void updateBlogLink() throws ParseException {
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=dateFormat.parse("2021-5-31 20:43:21");
        BlogLink blogLink=new BlogLink(30,(byte)2,"TestUpdate",
                "ll", "null",19,
                (byte)0,date);
        int result=blogLinkDao.updateBlogLink(blogLink);
        LoggerUtil.info("更新:"+result);
    }

    @Test
    @Rollback(value = false)
    public void updateSelectiveBlogLink() throws ParseException {
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=dateFormat.parse("2021-5-31 20:43:21");
        BlogLink blogLink=new BlogLink(30,(byte)2,"sdb",
                "www.baidu.com", "kkk",19,
                (byte)0,null);
        int result=blogLinkDao.updateSelectiveBlogLink(blogLink);
        LoggerUtil.info("更新:"+result);
    }
}
