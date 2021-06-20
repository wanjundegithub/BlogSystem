package com.company.blog.dao;

import com.company.blog.model.BlogTagRelation;
import com.company.blog.util.PageQueryUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogTagRelationDao {

    Integer deleteRelationByPrimaryKey(Integer relationID);

    Integer deleteRelationByBlogID(Integer blogID);

    Integer insertRelation(BlogTagRelation blogTagRelation);

    Integer insertSelectiveRelation(BlogTagRelation blogTagRelation);

    Integer insertRelationByBatch(@Param("blogTagRelations") List<BlogTagRelation> blogTagRelations);

    BlogTagRelation findBlogTagRelationByPrimaryKey(Integer relationID);

    BlogTagRelation findBlogTagRelationByID(Integer blogID,Integer blogTagID);

    List<Integer> findDistinctTagIDs(@Param("tagIDs") Integer[] tagIDs);

    Integer updateBlogTagRelation(BlogTagRelation blogTagRelation);

    Integer updateSelectiveBlogTagRelation(BlogTagRelation blogTagRelation);
}
