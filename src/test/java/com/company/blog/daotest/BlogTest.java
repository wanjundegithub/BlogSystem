package com.company.blog.daotest;

import com.company.blog.dao.BlogDao;
import com.company.blog.model.Blog;
import com.company.blog.util.LoggerUtil;
import com.company.blog.util.PageQueryUtil;
import org.apache.ibatis.annotations.Param;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BlogTest {

    @Autowired
    private BlogDao blogDao;

    @Test
    @Rollback(value = false)
    public void testDeleteBlogByPrimaryKey(){
        int blogID=1;
        var result=blogDao.deleteBlogByPrimaryKey(1);
        LoggerUtil.info("删除操作："+result);
    }

    @Test
    @Rollback(value = false)
    public void testDeleteBlogByBatch(){
        Integer[] blogIDs={1,2,3};
        var result=blogDao.deleteBlogByBatch(blogIDs);
        LoggerUtil.info("批量删除操作:"+result);
    }

    @Test
    @Rollback(value = false)
    public void testInsertBlog(){
        Date date = new Date(System.currentTimeMillis());
        Blog blog=new Blog(5,"岳飞传","岳飞事迹",
                "/a/b/c",3,"人物传","英雄",
                (byte)1,1000,(byte)1,(byte)0, date,date,
                "岳飞是一个伟大的民族英雄，他的事迹鼓舞着后来的人们...");
        var result=blogDao.insertBlog(blog);
        LoggerUtil.info("增加操作:"+result);
    }

    @Test
    @Rollback(value = false)
    public void testInsertSelectiveBlog(){
        Date date = new Date(System.currentTimeMillis());
        Blog blog=new Blog(5,"岳飞传","岳飞事迹",
                "/a/b/c",3,"人物传","英雄",
                (byte)1,1000,(byte)1,(byte)0, null,null,
                "岳飞是一个伟大的民族英雄，他的事迹鼓舞着后来的人们...");
        int result=blogDao.insertSelectiveBlog(blog);
        LoggerUtil.info("增加可空参数操作:"+result);
    }

    @Test
    public void testQueryBlogByPrimaryID(){
        int id=1;
        var blog=blogDao.queryBlogByPrimaryID(id);
        LoggerUtil.info("title:"+blog.getBlogTitle()+", subTitle:"+blog.getBlogSubUrl());
    }

    @Test
    @Rollback(value = false)
    public void testUpdateBlogWithContent(){
        Date date = new Date(System.currentTimeMillis());
        Blog blog=new Blog(5,"阿飞传","飞事迹",
                "/a/b/c",3,"人物传","英雄",
                (byte)1,1000,(byte)1,(byte)0, date,date,
                "他是一个伟大的人...");
        int result=blogDao.updateBlogWithContent(blog);
        LoggerUtil.info("更新博客操作:"+result);
    }

    @Test
    @Rollback(value = false)
    public void testUpdateBlogWithoutContent(){
        Date date = new Date(System.currentTimeMillis());
        Blog blog=new Blog(5,"瞎子传","我是小飞飞",
                "/a/b/c",3,"哥是个传说","英雄",
                (byte)1,1000,(byte)1,(byte)0, date,date,
                "他是一个平凡的人...");
        int result=blogDao.updateBlogWithoutContent(blog);
        LoggerUtil.info("更新博客操作:"+result);
    }

    @Test
    @Rollback(value = false)
    public void testUpdateSelectiveBlogWithContent(){
        Date date = new Date(System.currentTimeMillis());
        Blog blog=new Blog(5,"好人传","我是小飞飞",
                "/a/b/c",3,"哥是个传说","英雄",
                (byte)1,1000,(byte)1,(byte)0, null,null,
                "他是一个平凡的人...");
        int result=blogDao.updateSelectiveBlogWithContent(blog);
        LoggerUtil.info("更新博客操作:"+result);
    }

    @Test
    public void testFindBlogList(){
        Map<String,Object> maps=new HashMap<>();
        maps.put("start",1);
        maps.put("page",1);
        maps.put("limit",2);
        PageQueryUtil pageQueryUtil=new PageQueryUtil(maps);
        var blogs=blogDao.findBlogList(pageQueryUtil);
        if(blogs!=null&&blogs.size()!=0){
            blogs.forEach(t->{
                LoggerUtil.info("title:"+t.getBlogTitle()+
                        ",imagePath:"+t.getBlogCoverImagePath());
            });
        }
    }

    @Test
    public void testFindBlogListByCondition(){
       var blogs=blogDao.findBlogListByCondition(1,2);
        if(blogs!=null&&blogs.size()!=0){
            blogs.forEach(t->{
                LoggerUtil.info("title:"+t.getBlogTitle()+
                        ",imagePath:"+t.getBlogCoverImagePath());
            });
        }
    }

    @Test
    public void testGetTotalBlogs(){
        Map<String,Object> maps=new HashMap<>();
        maps.put("page",1);
        maps.put("limit",2);
        PageQueryUtil pageQueryUtil=new PageQueryUtil(maps);
        var result=blogDao.getTotalBlogs(pageQueryUtil);
        LoggerUtil.info("博客数量:"+result);
    }

    @Test
    public void testFindBlogBySubUrl(){
        var t=blogDao.findBlogBySubUrl("岳飞事迹");
        LoggerUtil.info("title:"+t.getBlogTitle()+
                ",imagePath:"+t.getBlogCoverImagePath());
    }

    @Test
    public void  getBlogListByTagID(){
        Map<String,Object> maps=new HashMap<>();
        maps.put("page",1);
        maps.put("limit",2);
        maps.put("blogTagID",57);
        PageQueryUtil pageQueryUtil=new PageQueryUtil(maps);
        var blogs=blogDao.getBlogListByTagID(pageQueryUtil);
        blogs.forEach(t->{
            LoggerUtil.info("title:"+t.getBlogTitle()+
                    ",imagePath:"+t.getBlogCoverImagePath());
        });
    }

    @Test
    public void  getTotalBlogCountByTagID(){
        Map<String,Object> maps=new HashMap<>();
        maps.put("page",1);
        maps.put("limit",2);
        maps.put("blogTagID",57);
        PageQueryUtil pageQueryUtil=new PageQueryUtil(maps);
        var result=blogDao.getTotalBlogCountByTagID(pageQueryUtil);
        LoggerUtil.info("数量:"+result);
    }

    @Test
    public void testUpdateCategoryByBatch(){
        Integer[] catIDs={3};
        var result=blogDao.updateCategoryByBatch(99,"test",catIDs);
        LoggerUtil.info("update result:"+result);
    }
}
