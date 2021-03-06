package com.company.blog.dao;

import com.company.blog.model.Blog;
import com.company.blog.util.PageQueryUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogDao {

    Integer deleteBlogByPrimaryKey(Integer blogID);

    Integer deleteBlogByBatch(@Param("blogIDS") Integer[] blogIDS);

    Integer insertBlog(Blog blog);

    Integer insertSelectiveBlog(Blog blog);

    Blog queryBlogByPrimaryID(Integer blogID);

    Integer updateBlogWithContent(Blog blog);

    Integer updateBlogWithoutContent(Blog blog);

    Integer updateSelectiveBlogWithContent(Blog blog);

    List<Blog> findBlogList(PageQueryUtil pageQueryUtil);

    List<Blog> findBlogListByCondition(@Param("condition") int condition,@Param("limit") int limit);

    Integer getTotalBlogs(PageQueryUtil pageQueryUtil);

    Blog findBlogBySubUrl(String blogSubUrl);

    List<Blog> getBlogListByTagID(PageQueryUtil pageQueryUtil);

    Integer getTotalBlogCountByTagID(PageQueryUtil pageQueryUtil);

    Integer  updateCategoryByBatch(@Param("blogCategoryID") Integer blogCategoryID,
                               @Param("blogCategoryName") String blogCategoryName,
                               @Param("blogCategoryIDs") Integer[] blogCategoryIDs);
}
