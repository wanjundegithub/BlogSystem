package com.company.blog.dao;

import com.company.blog.model.BlogCategory;
import com.company.blog.util.PageQueryUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogCategoryDao {

    int deleteBlogCategoryByPrimaryKey(Integer blogCategoryID);

    int deleteBlogCategoryBatch(@Param("blogCategoryIDs") Integer[] blogCategoryIDs);

    int insertBlogCategory(BlogCategory blogCategory);

    int insertSelectiveBlogCategory(BlogCategory blogCategory);

    BlogCategory findBlogCategoryByPrimaryKey(Integer blogCategoryID);

    BlogCategory selectBlogCategoryByName(String BlogCategoryName);

    List<BlogCategory> findBlogCategoryList(PageQueryUtil pageQueryUtil);

    List<BlogCategory> selectBlogCategoryByIDs(@Param("blogCategoryIDs") Integer[] blogCategoryIDs);

    int getTotalBlogCategoryCount(PageQueryUtil pageQueryUtil);

    int updateBlogCategory(BlogCategory blogCategory);

    int updateSelectiveBlogCategory(BlogCategory blogCategory);
}
