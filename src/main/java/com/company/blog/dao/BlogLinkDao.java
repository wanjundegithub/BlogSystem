package com.company.blog.dao;

import com.company.blog.model.BlogLink;
import com.company.blog.util.PageQueryUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogLinkDao {

    Integer deleteBlogLinkByPrimaryKey(Integer blogLinkID);

    Integer deleteBlogLinkByBatch(@Param("blogLinkIDs") Integer[] blogLinkIDs);

    Integer insertBlogLink(BlogLink blogLink);

    Integer insertSelectiveBlogLink(BlogLink blogLink);

    BlogLink findBlogLinkByPrimaryKey(Integer blogLinkID);

    List<BlogLink> findBlogLinkList(PageQueryUtil pageQueryUtil);

    Integer getTotalBlogLinkCount(PageQueryUtil pageQueryUtil);

    Integer updateBlogLink(BlogLink blogLink);

    Integer updateSelectiveBlogLink(BlogLink blogLink);
}
