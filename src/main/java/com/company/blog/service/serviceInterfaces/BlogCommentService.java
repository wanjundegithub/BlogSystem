package com.company.blog.service.serviceInterfaces;

import com.company.blog.model.BlogComment;
import com.company.blog.util.PageQueryUtil;
import com.company.blog.util.PageResult;

public interface BlogCommentService {

    boolean addBlogComment(BlogComment blogComment);

    PageResult getPageResult(PageQueryUtil pageQueryUtil);

    int getTotalBlogCommentCount();

    boolean checkBlogCommentByBatch(Integer[] blogCommentIds);

    boolean deleteBlogCommentByBatch(Integer[] blogCommentIds);

    boolean replyBlogComment(Integer blogCommentID,String replyContent);

    PageResult getPageResultByIdWithPage(Integer blogCommentID,Integer page);

}
