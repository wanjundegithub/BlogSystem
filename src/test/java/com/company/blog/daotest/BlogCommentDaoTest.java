package com.company.blog.daotest;

import com.company.blog.dao.BlogCommentDao;
import com.company.blog.model.BlogComment;
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
public class BlogCommentDaoTest {

    @Autowired
    private BlogCommentDao blogCommentDao;

    @Test
    @Rollback(value = false)
    public void testDeleteBlogCommentByPrimaryKey(){
        int key=32;
        int result=blogCommentDao.deleteBlogCommentByPrimaryKey(key);
        LoggerUtil.info("删除操作:"+result);
    }

    @Test
    @Rollback(value = false)
    public void testDeleteBlogCommentByBatch(){
        int[] keys={26,32};
        int result=blogCommentDao.deleteBlogCommentByBatch(keys);
        LoggerUtil.info("删除操作:"+result);
    }

    @Test
    @Rollback(value = false)
    public void  testInsertBlogComment() throws ParseException {
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date submitTime=dateFormat.parse("2021-05-13 10:12:19");
        Date replyTime=dateFormat.parse("2019-05-12 21:13:31");
        BlogComment blogComment=new BlogComment(22,4,"十三",
                "110@qq.com","","第一条评论",
                submitTime,"127.0.0.1","你的评论真好",replyTime,(byte) 1,(byte) 0);
        int result=blogCommentDao.insertBlogComment(blogComment);
        LoggerUtil.info("插入操作:"+result);
    }

    @Test
    @Rollback(value = false)
    public void testInsertSelectiveBlogComment() throws ParseException {
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date submitTime=dateFormat.parse("2021-05-13 10:12:19");
        Date replyTime=dateFormat.parse("2019-05-12 21:13:31");
        BlogComment blogComment=new BlogComment(32,4,"null",
                "null","","第三条评论",
                null,"127.0.0.1","你的评论真好",null,(byte) 1,(byte) 0);
        int result=blogCommentDao.insertSelectiveBlogComment(blogComment);
        LoggerUtil.info("插入操作:"+result);
    }

    @Test
    public void testFindBlogCommentByPrimaryKey(){
        int key=22;
        var result=blogCommentDao.findBlogCommentByPrimaryKey(key);
        LoggerUtil.info("评论者:"+result.getBlogCommentatorName()+",邮箱:"+result.getBlogCommentatorEmail());
    }

    @Test
    public void testFindBlogCommentList(){
        Map<String,Object> maps=new HashMap<>();
        maps.put("page",1);
        maps.put("limit",2);
        PageQueryUtil pageQueryUtil=new PageQueryUtil(maps);
        var results=blogCommentDao.findBlogCommentList(pageQueryUtil);
        results.forEach(result->{
            LoggerUtil.info("评论者:"+result.getBlogCommentatorName()+",邮箱:"+result.getBlogCommentatorEmail());
        });
    }

    @Test
    public void testGetTotalBlogCommentCount(){
        Map<String,Object> maps=new HashMap<>();
        maps.put("page",1);
        maps.put("limit",2);
        PageQueryUtil pageQueryUtil=new PageQueryUtil(maps);
        var count=blogCommentDao.getTotalBlogCommentCount(pageQueryUtil);
        LoggerUtil.info("评论数量:"+count);
    }

    @Test
    @Rollback(value = false)
    public void testUpdateBlogComment() throws ParseException {
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date submitTime=dateFormat.parse("2021-05-29 10:12:19");
        Date replyTime=dateFormat.parse("2021-05-30 10:13:31");
        BlogComment blogComment=new BlogComment(22,4,"十三",
                "gamil@qq.com","","第一条评论",
                submitTime,"127.0.0.1","你的评论真好",replyTime,(byte) 1,(byte) 0);
        var result=blogCommentDao.updateBlogComment(blogComment);
        LoggerUtil.info("更新操作:"+result);
    }

    @Test
    @Rollback(value = false)
    public void testUpdateSelectiveBlogComment(){
        BlogComment blogComment=new BlogComment(32,4,"null",
                "null","","Hello,World",
                null,"127.0.0.1","你的评论真好",null,(byte) 1,(byte) 0);
        var result=blogCommentDao.updateSelectiveBlogComment(blogComment);
        LoggerUtil.info("更新操作:"+result);
    }

    @Test
    @Rollback(value = false)
    public void testUpdateBlogCommentCheckStatusByBatch(){
        int[] ids={22,26,32};
        var result=blogCommentDao.updateBlogCommentCheckStatusByBatch(ids);
        LoggerUtil.info("更新操作:"+result);
    }

}
