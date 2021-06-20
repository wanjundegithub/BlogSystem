package com.company.blog.dao;

import com.company.blog.model.BlogCategory;
import com.company.blog.util.PageQueryUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogCategoryDao {

    Integer deleteBlogCategoryByPrimaryKey(Integer blogCategoryID);

    Integer deleteBlogCategoryBatch(@Param("blogCategoryIDs") Integer[] blogCategoryIDs);

    Integer insertBlogCategory(BlogCategory blogCategory);

    Integer insertSelectiveBlogCategory(BlogCategory blogCategory);

    BlogCategory findBlogCategoryByPrimaryKey(Integer blogCategoryID);

    BlogCategory selectBlogCategoryByName(String BlogCategoryName);

    List<BlogCategory> findBlogCategoryList(PageQueryUtil pageQueryUtil);

    List<BlogCategory> selectBlogCategoryByIDs(@Param("blogCategoryIDs") List<Integer> blogCategoryIDs);

    Integer getTotalBlogCategoryCount(PageQueryUtil pageQueryUtil);

    Integer updateBlogCategory(BlogCategory blogCategory);

    Integer updateSelectiveBlogCategory(BlogCategory blogCategory);
}
