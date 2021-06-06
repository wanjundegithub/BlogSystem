package com.company.blog.dao;

import com.company.blog.model.BlogTag;
import com.company.blog.model.BlogTagQuantity;
import com.company.blog.util.PageQueryUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogTagDao {

    int deleteBlogTagByPrimaryKey(int blogTagID);

    int deleteBlogByBatch(@Param("blogTageIDs") int[] blogTageIDs);

    int insertBlogTag(BlogTag blogTag);

    int insertSelectiveBlogTag(BlogTag blogTag);

    int insertBlogTagByBatch(@Param("blogTags") List<BlogTag> blogTags);

    BlogTag findBlogTagByPrimaryKey(int blogTagID);

    List<BlogTag> findBlogTagList(PageQueryUtil pageQueryUtil);

    BlogTag findBlogTagByName(String blogTagName);

    int getBlogTagCount(PageQueryUtil pageQueryUtil);

    List<BlogTagQuantity> getTagQuantityListByBlogID();

    int updateBlogTag(BlogTag blogTag);

    int updateSelectiveBlogTag(BlogTag blogTag);
}
