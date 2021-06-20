package com.company.blog.daotest;

import com.company.blog.dao.BlogCategoryDao;
import com.company.blog.model.BlogCategory;
import com.company.blog.util.LoggerUtil;
import com.company.blog.util.PageQueryUtil;
import org.apache.ibatis.annotations.Param;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BlogCategoryTest {

    @Autowired
    private BlogCategoryDao blogCategoryDao;

    @Test
    @Rollback(value = false)
    public void testInsertBlogCategory(){
        Date date=new Date(System.currentTimeMillis());
        BlogCategory blogCategory=new BlogCategory(99,"英雄传","/a/b/c",
                19,(byte) 0,date);
        int result=blogCategoryDao.insertBlogCategory(blogCategory);
        LoggerUtil.info("插入操作:"+result);
    }

    @Test
    @Rollback(value = false)
    public void testInsertSelectiveBlogCategory(){
        Date date=new Date(System.currentTimeMillis());
        BlogCategory blogCategory=new BlogCategory(100,"电影","/e/b/c",
                20,(byte) 0,null);
        int result=blogCategoryDao.insertSelectiveBlogCategory(blogCategory);
        LoggerUtil.info("插入操作:"+result);
    }

    @Test
    @Rollback(value = false)
    public void testDeleteBlogCategoryByPrimaryKey(){
        int key=99;
        int result=blogCategoryDao.deleteBlogCategoryByPrimaryKey(key);
        LoggerUtil.info("删除操作:"+result);
    }

    @Test
    @Rollback(value = false)
    public void testDeleteBlogCategoryBatch(){
        Integer[] keys={99,100};
        int result=blogCategoryDao.deleteBlogCategoryBatch(keys);
        LoggerUtil.info("批量删除操作:"+result);
    }

    @Test
    public void testFindBlogCategoryByPrimaryKey(){
        int key=99;
        var result=blogCategoryDao.findBlogCategoryByPrimaryKey(key);
        LoggerUtil.info("BlogCategoryName:"+result.getBlogCategoryName()+",BlogCategoryIcon:"+result.getBlogCategoryIcon());
    }

    @Test
    public void testSelectBlogCategoryByName(){
        String name="日常随笔";
        var result=blogCategoryDao.selectBlogCategoryByName(name);
        LoggerUtil.info("BlogCategoryName:"+result.getBlogCategoryName()+",BlogCategoryIcon:"+result.getBlogCategoryIcon());
    }

    @Test
    public void testFindBlogCategoryList(){
        Map<String,Object> maps=new HashMap<>();
        maps.put("page",1);
        maps.put("limit",2);
        PageQueryUtil pageQueryUtil=new PageQueryUtil(maps);
        var results=blogCategoryDao.findBlogCategoryList(pageQueryUtil);
        results.forEach(result->{
            LoggerUtil.info("BlogCategoryName:"+result.getBlogCategoryName()+",BlogCategoryIcon:"+result.getBlogCategoryIcon());
        });
    }

    @Test
    public void testSelectBlogCategoryByIDs(){
        List<Integer> ids= Arrays.asList(20,22,24);
        var results=blogCategoryDao.selectBlogCategoryByIDs(ids);
        results.forEach(result->{
            LoggerUtil.info("BlogCategoryName:"+result.getBlogCategoryName()+",BlogCategoryIcon:"+result.getBlogCategoryIcon());
        });
    }

    @Test
    public void testGetTotalBlogCategoryCount(){
        Map<String,Object> maps=new HashMap<>();
        maps.put("page",1);
        maps.put("limit",2);
        PageQueryUtil pageQueryUtil=new PageQueryUtil(maps);
        var result=blogCategoryDao.getTotalBlogCategoryCount(pageQueryUtil);
        LoggerUtil.info("总分类博客数:"+result);
    }

    @Test
    @Rollback(value = false)
    public void testUpdateBlogCategory(){
        Date date=new Date(System.currentTimeMillis());
        BlogCategory blogCategory=new BlogCategory(100,"喜欢","/kk/b/c",
                20,(byte) 0,date);
        var result=blogCategoryDao.updateBlogCategory(blogCategory);
        LoggerUtil.info("更新操作:"+result);
    }

    @Test
    @Rollback(value = false)
    public void testUpdateSelectiveBlogCategory(){
        Date date=new Date(System.currentTimeMillis());
        BlogCategory blogCategory=new BlogCategory(99,"余生有你","/kk/b/c",
                20,(byte) 0,null);
        var result=blogCategoryDao.updateSelectiveBlogCategory(blogCategory);
        LoggerUtil.info("更新操作:"+result);
    }
}
