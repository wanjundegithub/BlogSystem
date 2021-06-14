package com.company.blog.service.serviceImpl;

import com.company.blog.dao.BlogCommentDao;
import com.company.blog.model.BlogComment;
import com.company.blog.service.serviceInterfaces.BlogCommentService;
import com.company.blog.util.PageQueryUtil;
import com.company.blog.util.PageResult;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class BlogCommentServiceServiceImpl implements BlogCommentService {

    @Resource
    private BlogCommentDao blogCommentDao;

    @Override
    public boolean addBlogComment(BlogComment blogComment) {
        return blogCommentDao.insertSelectiveBlogComment(blogComment)>0;
    }

    @Override
    public PageResult getPageResult(PageQueryUtil pageQueryUtil) {
        var blogComments=blogCommentDao.findBlogCommentList(pageQueryUtil);
        if(blogComments==null){
            return null;
        }
        int blogCommentCount=blogCommentDao.getTotalBlogCommentCount(pageQueryUtil);
        PageResult pageResult=new PageResult(blogComments,blogCommentCount, pageQueryUtil.getLimit(),
                pageQueryUtil.getCurrentPage());
        return pageResult;
    }

    @Override
    public int getTotalBlogCommentCount() {
        return blogCommentDao.getTotalBlogCommentCount(null);
    }

    @Override
    public boolean checkBlogCommentByBatch(Integer[] blogCommentIds) {
        return blogCommentDao.updateBlogCommentCheckStatusByBatch(blogCommentIds)>0;
    }

    @Override
    public boolean deleteBlogCommentByBatch(Integer[] blogCommentIds) {
        return blogCommentDao.deleteBlogCommentByBatch(blogCommentIds)>0;
    }

    @Override
    public boolean replyBlogComment(Integer blogCommentID, String replyContent) {
        var blogComment=blogCommentDao.findBlogCommentByPrimaryKey(blogCommentID);
        //已审核状态为1
        if(blogComment!=null&&blogComment.getCommentStatus().intValue()==1){
            blogComment.setCommentReplyContent(replyContent);
            blogComment.setCommentReplyTime(new Date());
            return blogCommentDao.updateSelectiveBlogComment(blogComment)>0;
        }
        return false;
    }

    @Override
    public PageResult getPageResultByIdWithPage(Integer blogCommentID, Integer page) {
        if(page<1){
            return null;
        }
        Map map=new HashMap();
        map.put("page",page);
        map.put("limit",8);
        map.put("blogCommentID",blogCommentID);
        PageQueryUtil pageQueryUtil=new PageQueryUtil(map);
        var blogComments=blogCommentDao.findBlogCommentList(pageQueryUtil);
        if(!CollectionUtils.isEmpty(blogComments)){
            int count=blogCommentDao.getTotalBlogCommentCount(pageQueryUtil);
            PageResult pageResult=new PageResult(blogComments,count,pageQueryUtil.getLimit(),
            pageQueryUtil.getCurrentPage());
            return pageResult;
        }
        return null;
    }
}
