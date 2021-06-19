package com.company.blog.service.serviceInterfaces;

import com.company.blog.model.BlogCategory;
import com.company.blog.util.PageQueryUtil;
import com.company.blog.util.PageResult;

import java.util.List;

public interface BlogCategoryService {

    PageResult getBlogCategoryPageResult(PageQueryUtil pageQueryUtil);

    int getTotalBlogCategoryCount();

    boolean insertSelectiveBlogCategory(String blogCategoryName,String blogCategoryIcon);

    boolean updateSelectiveBlogCategory(Integer blogCategoryID,String blogCategoryName,String blogCategoryIcon);

    boolean deleteBlogCategoriesByBatch(Integer[] blogCategoryIDs);

    List<BlogCategory> getAllBlogCategories();

    BlogCategory getBlogCategory(Integer blogCategoryID);
}
