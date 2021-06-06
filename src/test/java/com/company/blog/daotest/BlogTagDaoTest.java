package com.company.blog.daotest;

import com.company.blog.dao.BlogTagDao;
import com.company.blog.model.BlogTag;
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
import java.util.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BlogTagDaoTest {

    @Autowired
    private BlogTagDao blogTagDao;

    @Test
    @Rollback(value = false)
    public void  testDeleteBlogTagByPrimaryKey(){
        int result=blogTagDao.deleteBlogTagByPrimaryKey(134);
        LoggerUtil.info("删除结果："+result);
    }

    @Test
    @Rollback(value = false)
    public void testDeleteBlogByBatch(){
        int[] ids={134,135,136,137};
        int result=blogTagDao.deleteBlogByBatch(ids);
        LoggerUtil.info("批量删除结果:"+result);
    }

    @Test
    @Rollback(value = false)
    public void testInsertBlogTag() throws ParseException {
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=dateFormat.parse("2021-5-31 20:43:21");
        BlogTag blogTag=new BlogTag(150,"标签",(byte) 0,date);
        int result=blogTagDao.insertBlogTag(blogTag);
        LoggerUtil.info("插入结果:"+result);
    }

    @Test
    @Rollback(value = false)
    public void testInsertSelectiveBlogTag() throws ParseException {
        BlogTag blogTag=new BlogTag(null,"空标签",
                null,null);
        int result=blogTagDao.insertSelectiveBlogTag(blogTag);
        LoggerUtil.info("插入结果:"+result);
    }

    @Test
    @Rollback(value = false)
    public void testInsertBlogTagByBatch(){
        List<BlogTag> blogTags=new ArrayList<BlogTag>(Arrays.asList(
                new BlogTag("标签1"),
                new BlogTag("标签2"),
                new BlogTag("标签3")));
        int result=blogTagDao.insertBlogTagByBatch(blogTags);
        LoggerUtil.info("批量插入结果:"+result);
    }

    @Test
    @Rollback(value = false)
    public void testFindBlogTagByPrimaryKey(){
        var result=blogTagDao.findBlogTagByPrimaryKey(131);
        LoggerUtil.info("name:"+result.getBlogTagName()+",time:"+result.getBlogTagCreateTime());
    }

    @Test
    @Rollback(value = false)
    public void testFindBlogTagList(){
        Map<String,Object> maps=new HashMap<>();
        maps.put("currentPage",1);
        maps.put("limit",3);
        PageQueryUtil pageQueryUtil=new PageQueryUtil(maps);
        var results=blogTagDao.findBlogTagList(pageQueryUtil);
        results.forEach(result->{
            LoggerUtil.info("name:"+result.getBlogTagName()+",time:"+
                    result.getBlogTagCreateTime().toString());
        });
    }

    @Test
    @Rollback(value = false)
    public void testFindBlogTagByName(){
        var result=blogTagDao.findBlogTagByName("标签1");
        LoggerUtil.info("name:"+result.getBlogTagName()+",time:"+result.getBlogTagCreateTime());
    }

    @Test
    @Rollback(value = false)
    public void testGetBlogTagCount(){
        Map<String,Object> maps=new HashMap<>();
        maps.put("currentPage",1);
        maps.put("limit",3);
        PageQueryUtil pageQueryUtil=new PageQueryUtil(maps);
        int result=blogTagDao.getBlogTagCount(pageQueryUtil);
        LoggerUtil.info("数量:"+result);
    }

    @Test
    public void getTagQuantityListByBlogID(){
        var results=blogTagDao.getTagQuantityListByBlogID();
        results.forEach(t->{
            LoggerUtil.info("name"+t.getBlogTagName()+",tagID:"+t.getBlogTagID()
            +",count:"+t.getBlogTagCount());
        });
    }

    @Test
    @Rollback(value = false)
    public void testUpdateBlogTag() throws ParseException {
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=dateFormat.parse("2029-5-31 20:43:21");
        BlogTag blogTag=new BlogTag(134,"更新标签",(byte)0,date);
        int result=blogTagDao.updateBlogTag(blogTag);
        LoggerUtil.info("更新:"+result);
    }

    @Test
    @Rollback(value = false)
    public void testUpdateSelectiveBlogTag(){
        BlogTag blogTag=new BlogTag(134,"再次更新标签",null,null);
        int result=blogTagDao.updateSelectiveBlogTag(blogTag);
        LoggerUtil.info("更新:"+result);
    }
}
