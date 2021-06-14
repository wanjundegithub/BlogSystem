package com.company.blog.dao;

import com.company.blog.model.BlogLink;
import com.company.blog.util.PageQueryUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogLinkDao {

    int deleteBlogLinkByPrimaryKey(Integer blogLinkID);

    int deleteBlogLinkByBatch(@Param("blogLinkIDs") Integer[] blogLinkIDs);

    int insertBlogLink(BlogLink blogLink);

    int insertSelectiveBlogLink(BlogLink blogLink);

    BlogLink findBlogLinkByPrimaryKey(Integer blogLinkID);

    List<BlogLink> findBlogLinkList(PageQueryUtil pageQueryUtil);

    int getTotalBlogLinkCount(PageQueryUtil pageQueryUtil);

    int updateBlogLink(BlogLink blogLink);

    int updateSelectiveBlogLink(BlogLink blogLink);
}