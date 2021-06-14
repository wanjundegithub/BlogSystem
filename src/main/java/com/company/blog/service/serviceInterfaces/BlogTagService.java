package com.company.blog.service.serviceInterfaces;

import com.company.blog.model.BlogTag;
import com.company.blog.model.BlogTagQuantity;
import com.company.blog.util.PageQueryUtil;
import com.company.blog.util.PageResult;

import java.util.List;

public interface BlogTagService {

    PageResult getBlogTagPage(PageQueryUtil pageQueryUtil);

    int getTotalBlogTagCount();

    boolean insertSelectiveBlogTag(String blogTagName);

    boolean deleteBlogTagByBatch(Integer[] blogTagIds);

    List<BlogTagQuantity> getBlogQuantities();
}
