package com.company.blog.service.serviceImpl;

import com.company.blog.dao.BlogTagDao;
import com.company.blog.dao.BlogTagRelationDao;
import com.company.blog.model.BlogTag;
import com.company.blog.model.BlogTagQuantity;
import com.company.blog.service.serviceInterfaces.BlogTagService;
import com.company.blog.util.LoggerUtil;
import com.company.blog.util.PageQueryUtil;
import com.company.blog.util.PageResult;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BlogTagServiceImpl implements BlogTagService {

    @Resource
    private BlogTagDao blogTagDao;

    @Resource
    private BlogTagRelationDao blogTagRelationDao;

    @Override
    public PageResult getBlogTagPage(PageQueryUtil pageQueryUtil) {
        var blogTags=blogTagDao.findBlogTagList(pageQueryUtil);
        if(null==blogTags){
            return null;
        }
        int totalCount=blogTagDao.getBlogTagCount(pageQueryUtil);
        PageResult pageResult=new PageResult(blogTags,totalCount,pageQueryUtil.getLimit(),pageQueryUtil.getCurrentPage());
        return pageResult;
    }

    @Override
    public int getTotalBlogTagCount() {
        return blogTagDao.getBlogTagCount(null);
    }

    @Override
    public boolean insertSelectiveBlogTag(String blogTagName) {
        var blogTag=blogTagDao.findBlogTagByName(blogTagName);
        if(blogTag==null){
            blogTag=new BlogTag();
            blogTag.setBlogTagName(blogTagName);
            return blogTagDao.insertSelectiveBlogTag(blogTag)>0;
        }
       return false;
    }

    @Override
    public boolean deleteBlogTagByBatch(Integer[] blogTagIds) {
        //存在关联不删除
        var results=blogTagRelationDao.findDistinctTagIDs(blogTagIds);
        if(!CollectionUtils.isEmpty(results)){
            LoggerUtil.info("存在关联，无法删除");
            return  false;
        }
        return blogTagDao.deleteBlogByBatch(blogTagIds)>0;
    }

    @Override
    public List<BlogTagQuantity> getBlogQuantities() {
        var result=blogTagDao.getTagQuantityListByBlogID();
        return result;
    }
}
