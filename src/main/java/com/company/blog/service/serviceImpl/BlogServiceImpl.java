package com.company.blog.service.serviceImpl;

import com.company.blog.dao.*;
import com.company.blog.model.Blog;
import com.company.blog.model.BlogCategory;
import com.company.blog.model.BlogTag;
import com.company.blog.model.BlogTagRelation;
import com.company.blog.model.vo.BlogListVo;
import com.company.blog.model.vo.DetailBlogVo;
import com.company.blog.model.vo.RoughBlogVo;
import com.company.blog.service.serviceInterfaces.BlogService;
import com.company.blog.util.PageQueryUtil;
import com.company.blog.util.PageResult;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BlogServiceImpl implements BlogService {

    @Resource
    private BlogDao blogDao;

    @Resource
    private BlogTagDao blogTagDao;

    @Resource
    private BlogCategoryDao blogCategoryDao;

    @Resource
    private BlogCommentDao blogCommentDao;

    @Resource
    private BlogTagRelationDao blogTagRelationDao;

    @Override
    public PageResult getBlogPage(PageQueryUtil pageQueryUtil) {
        var blogList=blogDao.findBlogList(pageQueryUtil);
        if(CollectionUtils.isEmpty(blogList)){
            return null;
        }
        int count=blogDao.getTotalBlogs(pageQueryUtil);
        PageResult pageResult=new PageResult(blogList,count, pageQueryUtil.getLimit(),
                pageQueryUtil.getCurrentPage());
        return pageResult;
    }

    @Override
    @Transactional
    public String insertSelectiveBlog(Blog blog) {
        var blogCategory=blogCategoryDao.findBlogCategoryByPrimaryKey(
                blog.getBlogCategoryID());
        if(blogCategory==null){
            blog.setBlogCategoryName("默认分类");
            blog.setBlogCategoryID(0);
        }
        else{
            //设置blog的分类名称
            blog.setBlogCategoryName(blogCategory.getBlogCategoryName());
            //增加已有分类的排序名
            blogCategory.setBlogCategoryRank(blogCategory.getBlogCategoryRank()+1);
        }
        if(blogDao.insertSelectiveBlog(blog)>0){
            //更新分类
            blogCategoryDao.updateSelectiveBlogCategory(blogCategory);
            //更新标签(单个blog可拥有多个标签)
            String[] blogTagNames=blog.getBlogTags().split(",");
            if(blogTagNames.length>6){
                return "单个blog最多拥有6个标签";
            }
            List<BlogTag> newAddBlogTags=new ArrayList<>();
            List<BlogTag> allBlogTags=new ArrayList<>();
            for(var name:blogTagNames){
                var blogTag=blogTagDao.findBlogTagByName(name);
                if(blogTag==null){
                    blogTag=new BlogTag();
                    blogTag.setBlogTagName(name);
                    newAddBlogTags.add(blogTag);
                }
                allBlogTags.add(blogTag);
            }
            if(!CollectionUtils.isEmpty(newAddBlogTags)){
                blogTagDao.insertBlogTagByBatch(newAddBlogTags);
            }
            //更新tag-blog关系
            List<BlogTagRelation> blogTagRelations=new ArrayList<>();
            for(var tag:allBlogTags){
                BlogTagRelation blogTagRelation=new BlogTagRelation();
                blogTagRelation.setBlogID(blog.getBlogID());
                blogTagRelation.setBlogTagID(tag.getBlogTagID());
                blogTagRelations.add(blogTagRelation);
            }
            if(blogTagRelationDao.insertRelationByBatch(blogTagRelations)>0){
                return "保存成功";
            }
        }
        return "保存失败";
    }

    @Override
    public boolean deleteBlogsByBatch(Integer[] blogIds) {
        return blogDao.deleteBlogByBatch(blogIds)>0;
    }

    @Override
    public int getTotalBlogCount() {
        return blogDao.getTotalBlogs(null);
    }

    @Override
    public Blog getBlogByBlogID(Integer blogID) {
        return blogDao.queryBlogByPrimaryID(blogID);
    }

    @Override
    public String updateBlog(Blog blog) {
        Blog updateBlog =blogDao.queryBlogByPrimaryID(blog.getBlogID());
        if(updateBlog ==null){
            return "数据不存在";
        }
        updateBlog.copyFrom(blog);
        BlogCategory category =blogCategoryDao.findBlogCategoryByPrimaryKey(
                blog.getBlogCategoryID());
        if(category ==null){
            updateBlog.setBlogCategoryName("默认分类");
            updateBlog.setBlogCategoryID(0);
        }
        else{
            //设置blog的分类名称
            updateBlog.setBlogCategoryName(category.getBlogCategoryName());
            updateBlog.setBlogCategoryID(category.getBlogCategoryID());
            //增加已有分类的排序名
            category.setBlogCategoryRank(category.getBlogCategoryRank()+1);
        }
        //更新标签(单个blog可拥有多个标签)
        String[] blogTagNames=blog.getBlogTags().split(",");
        updateBlog.setBlogTags(blog.getBlogTags());
        if(blogTagNames.length>6){
            return "单个blog最多拥有6个标签";
        }
        List<BlogTag> newAddBlogTags=new ArrayList<>();
        List<BlogTag> allBlogTags=new ArrayList<>();
        for(var name:blogTagNames){
            var blogTag=blogTagDao.findBlogTagByName(name);
            if(blogTag==null){
                blogTag=new BlogTag();
                blogTag.setBlogTagName(name);
                newAddBlogTags.add(blogTag);
            }
            allBlogTags.add(blogTag);
        }
        if(!CollectionUtils.isEmpty(newAddBlogTags)){
            blogTagDao.insertBlogTagByBatch(newAddBlogTags);
        }
        //更新tag-blog关系
        List<BlogTagRelation> blogTagRelations=new ArrayList<>();
        for(var tag:allBlogTags){
            BlogTagRelation blogTagRelation=new BlogTagRelation();
            blogTagRelation.setBlogID(blog.getBlogID());
            blogTagRelation.setBlogTagID(tag.getBlogTagID());
            blogTagRelations.add(blogTagRelation);
        }
        //修改blog信息->修改分类排序值->删除原关系数据->保存新的关系数据
        blogCategoryDao.updateSelectiveBlogCategory(category);
        blogTagRelationDao.deleteRelationByBlogID(blog.getBlogID());
        blogTagRelationDao.insertRelationByBatch(blogTagRelations);
        if(blogDao.updateSelectiveBlogWithContent(updateBlog)>0){
            return "保存成功";
        }
        return "保存失败";
    }

    /**
     * 获取首页page
     * @param page
     * @return
     */
    @Override
    public PageResult getBlogHomePage(int page) {
        Map<String,Object> maps=new HashMap<>();
        maps.put("page",page);
        maps.put("limit",8);
        maps.put("blogStatus",1);
        PageQueryUtil pageQueryUtil=new PageQueryUtil(maps);
        var blogs=blogDao.findBlogList(pageQueryUtil);
        if(CollectionUtils.isEmpty(blogs)){
            return  null;
        }
        var blogVos=getFromBlogList(blogs);
        int count=blogDao.getTotalBlogs(pageQueryUtil);
        PageResult pageResult=new PageResult(blogVos,count,pageQueryUtil.getLimit(),
                pageQueryUtil.getCurrentPage());
        return null;
    }

    /**
     * 首页侧边栏数据列表
     * 0-点击最多 1-最新发布
     *
     * @param type
     * @return
     */
    @Override
    public List<RoughBlogVo> getSideBarPage(int type) {
        List<RoughBlogVo> roughBlogVos=new ArrayList<>();
        var blogs=blogDao.findBlogListByCondition(type,9);
        if(!CollectionUtils.isEmpty(blogs)){
            for(var item:blogs){
                var tempRoughBlogVo=new RoughBlogVo();
                BeanUtils.copyProperties(item,tempRoughBlogVo);
                roughBlogVos.add(tempRoughBlogVo);
            }
        }
        return roughBlogVos;
    }

    @Override
    public DetailBlogVo getDetailBlogVoByID(Integer blogID) {

        return null;
    }

    /**
     * 将icon加入Blog中
     * @param blogs
     * @return
     */
    private List<BlogListVo> getFromBlogList(List<Blog> blogs){
        List<BlogListVo> blogListVos=new ArrayList<>();
        if(!CollectionUtils.isEmpty(blogs)){
            List<Integer> blogCategoryIds=blogs.stream().map(Blog::getBlogCategoryID).
                    collect(Collectors.toList());
            Map<Integer,String> blogCategoryPairs=new HashMap<>();
            if(!CollectionUtils.isEmpty(blogCategoryIds)){
                List<BlogCategory>  blogCategories=blogCategoryDao.selectBlogCategoryByIDs(
                        (Integer[]) blogCategoryIds.toArray());
                if(!CollectionUtils.isEmpty(blogCategories)){
                    blogCategoryPairs= blogCategories.stream().collect(Collectors.toMap(
                            BlogCategory::getBlogCategoryID,
                            BlogCategory::getBlogCategoryIcon,(key1,key2)->key2));
                }
            }
            for(var item:blogs){
                BlogListVo tempBlogListVo =new BlogListVo();
                BeanUtils.copyProperties(item, tempBlogListVo);
                if(blogCategoryPairs.containsKey(item.getBlogCategoryID())){
                    tempBlogListVo.setBlogCategoryIcon(blogCategoryPairs.get(item.getBlogCategoryID()));
                }
                else{
                    tempBlogListVo.setBlogCategoryID(0);
                    tempBlogListVo.setBlogCategoryName("默认分类");
                    tempBlogListVo.setBlogCategoryIcon("/admin/dist/img/category/00.png");
                }
                blogListVos.add(tempBlogListVo);
            }
        }
        return  blogListVos;
    }
}
