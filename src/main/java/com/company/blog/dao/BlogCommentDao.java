package com.company.blog.dao;

import com.company.blog.model.BlogComment;
import com.company.blog.util.PageQueryUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogCommentDao {

    int deleteBlogCommentByPrimaryKey(int blogCommentID);

    int deleteBlogCommentByBatch(@Param("blogCommentIDs") int[] blogCommentIDs);

    int insertBlogComment(BlogComment blogComment);

    int insertSelectiveBlogComment(BlogComment blogComment);

    BlogComment findBlogCommentByPrimaryKey(int blogCommentID);

    List<BlogComment> findBlogCommentList(PageQueryUtil pageQueryUtil);

    int getTotalBlogCommentCount(PageQueryUtil pageQueryUtil);

    int updateBlogComment(BlogComment blogComment);

    int updateSelectiveBlogComment(BlogComment blogComment);

    int updateBlogCommentCheckStatusByBatch(@Param("blogCommentIDs") int[] blogCommentIDs);
}
