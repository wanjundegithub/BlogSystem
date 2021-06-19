package com.company.blog.service.serviceImpl;

import com.company.blog.dao.BlogCategoryDao;
import com.company.blog.dao.BlogDao;
import com.company.blog.model.BlogCategory;
import com.company.blog.service.serviceInterfaces.BlogCategoryService;
import com.company.blog.util.PageQueryUtil;
import com.company.blog.util.PageResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BlogCategoryServiceImpl implements BlogCategoryService {

    @Resource
    private BlogCategoryDao blogCategoryDao;

    @Resource
    private BlogDao blogDao;

    @Override
    public PageResult getBlogCategoryPageResult(PageQueryUtil pageQueryUtil) {
        var blogCategories=blogCategoryDao.findBlogCategoryList(pageQueryUtil);
        if(blogCategories==null|| CollectionUtils.isEmpty(blogCategories)){
            return null;
        }
        int count=blogCategoryDao.getTotalBlogCategoryCount(pageQueryUtil);
        PageResult pageResult=new PageResult(blogCategories,count,pageQueryUtil.getLimit(),
                pageQueryUtil.getCurrentPage());
        return pageResult;
    }

    @Override
    public int getTotalBlogCategoryCount() {
        return blogCategoryDao.getTotalBlogCategoryCount(null);
    }

    @Override
    public boolean insertSelectiveBlogCategory(String blogCategoryName, String blogCategoryIcon) {
        var blogCategory=blogCategoryDao.selectBlogCategoryByName(blogCategoryName);
        if(blogCategory==null){
           BlogCategory newBlogCategory=new BlogCategory();
           newBlogCategory.setBlogCategoryName(blogCategoryName);
           newBlogCategory.setBlogCategoryIcon(blogCategoryIcon);
           return blogCategoryDao.insertSelectiveBlogCategory(newBlogCategory)>0;
        }
        return false;
    }

    @Override
    public boolean updateSelectiveBlogCategory(Integer blogCategoryID, String blogCategoryName,
                                               String blogCategoryIcon) {
        var blogCategory=blogCategoryDao.findBlogCategoryByPrimaryKey(blogCategoryID);
        if(blogCategory!=null){
            blogCategory.setBlogCategoryName(blogCategoryName);
            blogCategory.setBlogCategoryIcon(blogCategoryIcon);
            return blogCategoryDao.updateSelectiveBlogCategory(blogCategory)>0;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean deleteBlogCategoriesByBatch(Integer[] blogCategoryIDs) {
        //修改默认分类下的blog
        blogDao.updateCategoryByBatch(0,"默认分类",blogCategoryIDs);
        return blogCategoryDao.deleteBlogCategoryBatch(blogCategoryIDs)>0;
    }

    @Override
    public List<BlogCategory> getAllBlogCategories() {
        return blogCategoryDao.findBlogCategoryList(null);
    }

    @Override
    public BlogCategory getBlogCategory(Integer blogCategoryID) {
        var blogCategory=blogCategoryDao.findBlogCategoryByPrimaryKey(blogCategoryID);
        return blogCategory;
    }
}
