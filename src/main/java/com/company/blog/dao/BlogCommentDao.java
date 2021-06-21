package com.company.blog.dao;

import com.company.blog.model.BlogComment;
import com.company.blog.util.PageQueryUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface BlogCommentDao {

    Integer deleteBlogCommentByPrimaryKey(Integer blogCommentID);

    Integer deleteBlogCommentByBatch(@Param("blogCommentIDs") Integer[] blogCommentIDs);

    Integer insertBlogComment(BlogComment blogComment);

    Integer insertSelectiveBlogComment(BlogComment blogComment);

    BlogComment findBlogCommentByPrimaryKey(Integer blogCommentID);

    List<BlogComment> findBlogCommentList(Map map);

    Integer getTotalBlogCommentCount(Map map);

    Integer updateBlogComment(BlogComment blogComment);

    Integer updateSelectiveBlogComment(BlogComment blogComment);

    Integer updateBlogCommentCheckStatusByBatch(@Param("blogCommentIDs") Integer[] blogCommentIDs);
}
