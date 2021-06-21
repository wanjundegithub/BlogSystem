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
import com.company.blog.util.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;
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
            LoggerUtil.info("blogID:"+blog.getBlogID());
            //更新分类
            blogCategoryDao.updateSelectiveBlogCategory(blogCategory);
            //更新标签(单个blog可拥有多个标签)
            String[] blogTagNames=blog.getBlogTags().split(",");
            if(blogTagNames.length>6){
                return "单个blog最多拥有6个标签";
            }
            LoggerUtil.info("blogTag:"+blog.getBlogTags());
            List<BlogTag> newAddBlogTags=new ArrayList<>();
            List<BlogTag> allBlogTags=new ArrayList<>();
            for(var name:blogTagNames){
                var blogTag=blogTagDao.findBlogTagByName(name);
                if(blogTag==null){
                    blogTag=new BlogTag();
                    blogTag.setBlogTagName(name);
                    newAddBlogTags.add(blogTag);
                }
                else {
                    allBlogTags.add(blogTag);
                }
            }
            if(newAddBlogTags.size()>0){
                //插入时blogTagID主键自增并会自动赋值给blogTagID
                blogTagDao.insertBlogTagByBatch(newAddBlogTags);
            }
            LoggerUtil.info("after insert blogTags");
            for(int i=0;i<newAddBlogTags.size();i++){
                LoggerUtil.info("blogTagID:"+newAddBlogTags.get(i).getBlogTagID()+
                        ",blogTagName:"+newAddBlogTags.get(i).getBlogTagName());
            }
            //将新增的tag赋给allBlogTags
            allBlogTags.addAll(newAddBlogTags);
            //新增tag-blog关系
            List<BlogTagRelation> blogTagRelations=new ArrayList<>();
            for(var tag:allBlogTags){
                BlogTagRelation blogTagRelation=new BlogTagRelation();
                blogTagRelation.setBlogID(blog.getBlogID());
                blogTagRelation.setBlogTagID(tag.getBlogTagID());
                blogTagRelations.add(blogTagRelation);
            }
            if(blogTagRelationDao.insertRelationByBatch(blogTagRelations)>0){
                return CommonUtil.SuccessSave;
            }
        }
        return CommonUtil.SaveFailure;
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
    public String updateBlog(Blog updateBlog) {
        BlogCategory category =blogCategoryDao.findBlogCategoryByPrimaryKey(
                updateBlog.getBlogCategoryID());
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
        String[] blogTagNames=updateBlog.getBlogTags().split(",");
        updateBlog.setBlogTags(updateBlog.getBlogTags());
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
            else {
                allBlogTags.add(blogTag);
            }
        }
        if(!CollectionUtils.isEmpty(newAddBlogTags)){
            blogTagDao.insertBlogTagByBatch(newAddBlogTags);
        }
        allBlogTags.addAll(newAddBlogTags);
        //更新tag-blog关系
        List<BlogTagRelation> blogTagRelations=new ArrayList<>();
        for(var tag:allBlogTags){
            BlogTagRelation blogTagRelation=new BlogTagRelation();
            blogTagRelation.setBlogID(updateBlog.getBlogID());
            blogTagRelation.setBlogTagID(tag.getBlogTagID());
            blogTagRelations.add(blogTagRelation);
        }
        //修改blog信息->修改分类排序值->删除原关系数据->保存新的关系数据
        blogCategoryDao.updateSelectiveBlogCategory(category);
        blogTagRelationDao.deleteRelationByBlogID(updateBlog.getBlogID());
        blogTagRelationDao.insertRelationByBatch(blogTagRelations);
        if(blogDao.updateSelectiveBlogWithContent(updateBlog)>0){
            return CommonUtil.SuccessModify;
        }
        return CommonUtil.FailureModify;
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
        var blogVos=getFromBlogList(blogs);
        int count=blogDao.getTotalBlogs(pageQueryUtil);
        PageResult pageResult=new PageResult(blogVos,count,pageQueryUtil.getLimit(),
                pageQueryUtil.getCurrentPage());
        return pageResult;
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
        LoggerUtil.info("type:"+type);
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

    /**
     * 获取详细Blog
     * @param blogID
     * @return
     */
    @Override
    public DetailBlogVo getDetailBlogVoByID(Integer blogID) {
        Blog blog=blogDao.queryBlogByPrimaryID(blogID);
        if(blog==null){
            return null;
        }
        DetailBlogVo detailBlogVo=getDetailBlogVoFromBlog(blog);
        return detailBlogVo;
    }

    /**
     * 根据tag获取文章列表
     * @param tagName
     * @param page
     * @return
     */
    @Override
    public PageResult getBlogListByTag(String tagName, int page) {
        if(!StringUtil.isMatchNumberOrWord(tagName)){
            return null;
        }
        BlogTag blogTag=blogTagDao.findBlogTagByName(tagName);
        if(blogTag==null||page<=0){
            return null;
        }
        Map<String,Object> params=new HashMap<>();
        params.put("page",page);
        params.put("limit",9);
        params.put("blogTagID",blogTag.getBlogTagID());
        PageQueryUtil pageQueryUtil=new PageQueryUtil(params);
        List<Blog> blogs=blogDao.getBlogListByTagID(pageQueryUtil);
        List<BlogListVo> blogListVos=getFromBlogList(blogs);
        int total=blogDao.getTotalBlogCountByTagID(pageQueryUtil);
        PageResult pageResult=new PageResult(blogListVos,total,pageQueryUtil.getLimit(),
                pageQueryUtil.getCurrentPage());
        return pageResult;
    }

    /**
     * 根据分类获取文章列表
     * @param categoryName
     * @param page
     * @return
     */
    @Override
    public PageResult getBlogListByCategory(String categoryName, int page) {
        if(!StringUtil.isMatchNumberOrWord(categoryName)){
            return null;
        }
        BlogCategory blogCategory=blogCategoryDao.selectBlogCategoryByName(categoryName);
        if(blogCategory==null&&StringUtil.isEqual(categoryName,CommonUtil.DefaultClassification)){
            blogCategory=new BlogCategory();
            blogCategory.setBlogCategoryID(0);
            blogCategory.setBlogCategoryName(CommonUtil.DefaultClassification);
            blogCategory.setBlogCategoryIcon(CommonUtil.DefaultIconPath);
        }
        if(blogCategory!=null&&page>0){
            Map<String,Object> params=new HashMap<>();
            params.put("page",page);
            params.put("limit",9);
            params.put("blogCategoryID",blogCategory.getBlogCategoryID());
            params.put("blogStatus",1);
            PageQueryUtil pageQueryUtil=new PageQueryUtil(params);
            List<Blog> blogs=blogDao.findBlogList(pageQueryUtil);
            List<BlogListVo> blogListVos=getFromBlogList(blogs);
            int total=blogDao.getTotalBlogs(pageQueryUtil);
            return new PageResult(blogListVos,total,pageQueryUtil.getLimit(),
                    pageQueryUtil.getCurrentPage());
        }
        return null;
    }

    /**
     * 根据关键字获取文章列表
     * @param keyword
     * @param page
     * @return
     */
    @Override
    public PageResult getBlogListByKeyword(String keyword, int page) {
        if(StringUtil.isMatchNumberOrWord(keyword)||page<=0){
            return null;
        }
        Map<String,Object> params=new HashMap<>();
        params.put("page",page);
        params.put("limit",9);
        params.put("keyword",keyword);
        params.put("blogStatus",1);
        PageQueryUtil pageQueryUtil=new PageQueryUtil(params);
        List<Blog> blogs=blogDao.findBlogList(pageQueryUtil);
        List<BlogListVo> blogListVos=getFromBlogList(blogs);
        int total=blogDao.getTotalBlogs(pageQueryUtil);
        return new PageResult(blogListVos,total,pageQueryUtil.getLimit(),
                pageQueryUtil.getCurrentPage());
    }

    /**
     * 根据自定义路径获取详细blog
     * @param subUrl
     * @return
     */
    @Override
    public DetailBlogVo getDetailBlogVoBySubUrl(String subUrl) {
        if(!StringUtil.isMatchNetAddress(subUrl)){
            return null;
        }
        Blog blog=blogDao.findBlogBySubUrl(subUrl);
        if(blog==null){
            return null;
        }
        DetailBlogVo detailBlogVo=getDetailBlogVoFromBlog(blog);
        return detailBlogVo;
    }


    /**
     * 将icon加入Blog中,将Blog列表转化为BlogListVo列表
     * @param blogs
     * @return
     */
    private List<BlogListVo> getFromBlogList(List<Blog> blogs){
        List<BlogListVo> blogListVos=new ArrayList<>();
        if((blogs.size()>0)){
            List<Integer> blogCategoryIds=blogs.stream().map(Blog::getBlogCategoryID).
                    collect(Collectors.toList());

            Map<Integer,String> blogCategoryPairs=new HashMap<>();
            if(!CollectionUtils.isEmpty(blogCategoryIds)){
                List<BlogCategory>  blogCategories=blogCategoryDao.selectBlogCategoryByIDs(blogCategoryIds);
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

    /**
     * 将blog转换为DetailBlogVo
     * @param blog
     * @return
     */
    private DetailBlogVo getDetailBlogVoFromBlog(Blog blog){
        if(blog==null||blog.getBlogStatus()==0){
            return null;
        }
        blog.setBlogViews(blog.getBlogViews()+1);
        //更新浏览量
        blogDao.updateBlogWithoutContent(blog);
        DetailBlogVo result=new DetailBlogVo();
        BeanUtils.copyProperties(blog,result);
        BlogCategory blogCategory= blogCategoryDao.findBlogCategoryByPrimaryKey(
                blog.getBlogCategoryID());
        if(blogCategory==null){
            blogCategory=new BlogCategory();
            blogCategory.setBlogCategoryID(0);
            blogCategory.setBlogCategoryName(CommonUtil.DefaultClassification);
            blogCategory.setBlogCategoryIcon(CommonUtil.DefaultIconPath);
        }
        //设置Icon
        result.setBlogCategoryIcon(blogCategory.getBlogCategoryIcon());
        //设置tags
        if(!StringUtil.isNullOrEmpty(blog.getBlogTags())){
            var tags= Arrays.asList(blog.getBlogTags().split(","));
            result.setBlogTags(tags);
        }
        //设置评论数
        Map<String,Object> map=new HashMap<>();
        map.put("blogID",blog.getBlogID());
        map.put("commentStatus",1);
        result.setBlogCommentCount(blogCommentDao.getTotalBlogCommentCount(map));
        return result;
    }
}
