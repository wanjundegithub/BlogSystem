package com.company.blog.daotest;

import com.company.blog.dao.BlogTagRelationDao;
import com.company.blog.model.BlogTag;
import com.company.blog.model.BlogTagRelation;
import com.company.blog.util.LoggerUtil;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BlogTagRelationDaoTest {

    @Autowired
    private BlogTagRelationDao blogTagRelationDao;

    @Test
    @Rollback(value = false)
    public void testDeleteRelationByPrimaryKey(){
        int result=blogTagRelationDao.deleteRelationByPrimaryKey(284);
        LoggerUtil.info("删除结果:"+result);
    }

    @Test
    @Rollback(value = false)
    public void testDeleteRelationByBlogID(){
        int result=blogTagRelationDao.deleteRelationByBlogID(13);
        LoggerUtil.info("删除结果:"+result);
    }

    @Test
    @Rollback(value = false)
    public void testInsertRelation() throws ParseException {
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=dateFormat.parse("2021-11-11 20:33:23");
        BlogTagRelation blogTagRelation=new BlogTagRelation(280,12,99,date);
        int result=blogTagRelationDao.insertRelation(blogTagRelation);
        LoggerUtil.info("插入结果:"+result);
    }

    @Test
    @Rollback(value = false)
    public void testInsertSelectiveRelation(){
        BlogTagRelation blogTagRelation=new BlogTagRelation(281,12,99,null);
        int result=blogTagRelationDao.insertSelectiveRelation(blogTagRelation);
        LoggerUtil.info("插入结果:"+result);
    }

    @Test
    @Rollback(value = false)
    public void testInsertRelationByBatch(){
        List<BlogTagRelation> blogTagRelations=new ArrayList<BlogTagRelation>(
                Arrays.asList(new BlogTagRelation(13,100),
                        new BlogTagRelation(14,101),
                        new BlogTagRelation(15,102)));
        int result=blogTagRelationDao.insertRelationByBatch(blogTagRelations);
        LoggerUtil.info("插入结果:"+result);
    }

    @Test
    @Rollback(value = false)
    public void testFindBlogTagRelationByPrimaryKey(){
        var result=blogTagRelationDao.findBlogTagRelationByPrimaryKey(280);
        LoggerUtil.info(result.getRelationID()+",blogTagID:"+result.getBlogTagID());
    }

    @Test
    @Rollback(value = false)
    public void testFindBlogTagRelationByID(){
        var result=blogTagRelationDao.findBlogTagRelationByID(3,69);
        LoggerUtil.info(result.getRelationID()+",blogTagID:"+result.getBlogTagID());
    }

    @Test
    @Rollback(value = false)
    public void testFindDistinctTagIDs(){
        Integer[] tagIDs={69,128,99,99,100,101};
        var results=blogTagRelationDao.findDistinctTagIDs(tagIDs);
        results.forEach(result->{
            LoggerUtil.info("tagID:"+result);
        });
    }

    @Test
    @Rollback(value = false)
    public void testUpdateBlogTagRelation() throws ParseException {
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=dateFormat.parse("2021-11-11 20:33:23");
        BlogTagRelation blogTagRelation=new BlogTagRelation(280,12,
                1000, date);
        var result=blogTagRelationDao.updateBlogTagRelation(blogTagRelation);
        LoggerUtil.info("更新结果:"+result);
    }

    @Test
    @Rollback(value = false)
    public void testUpdateSelectiveBlogTagRelation(){
        var result=blogTagRelationDao.updateSelectiveBlogTagRelation(
                new BlogTagRelation(280,12,1001,null));
        LoggerUtil.info("更新结果:"+result);
    }
}
