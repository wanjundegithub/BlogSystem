package com.company.blog.dao;

import com.company.blog.model.Blog;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogDao {

    int deleteBlogByPrimaryKey(int blogID);

    int insertBlog(Blog blog);

    int insertSelectiveBlog(Blog blog);

    Blog queryBlogByPrimaryID(int blogID);

    int updateBlog(Blog blog);

    int updateSelectiveBlog(Blog blog);
}
