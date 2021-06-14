package com.company.blog.dao;

import com.company.blog.model.Blog;
import com.company.blog.util.PageQueryUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogDao {

    int deleteBlogByPrimaryKey(Integer blogID);

    int deleteBlogByBatch(@Param("blogIDS") Integer[] blogIDS);

    int insertBlog(Blog blog);

    int insertSelectiveBlog(Blog blog);

    Blog queryBlogByPrimaryID(Integer blogID);

    int updateBlogWithContent(Blog blog);

    int updateBlogWithoutContent(Blog blog);

    int updateSelectiveBlogWithContent(Blog blog);

    List<Blog> findBlogList(PageQueryUtil pageQueryUtil);

    List<Blog> findBlogListByCondition(@Param("condition") int condition,@Param("limit") int limit);

    int getTotalBlogs(PageQueryUtil pageQueryUtil);

    Blog findBlogBySubUrl(String blogSubUrl);

    List<Blog> getBlogListByTagID(PageQueryUtil pageQueryUtil);

    int getTotalBlogCountByTagID(PageQueryUtil pageQueryUtil);

    int  updateCategoryByBatch(@Param("blogCategoryID") Integer blogCategoryID,
                               @Param("blogCategoryName") String blogCategoryName,
                               @Param("blogCategoryIDs") Integer[] blogCategoryIDs);
}
