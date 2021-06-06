package com.company.blog.dao;

import com.company.blog.model.BlogTagRelation;
import com.company.blog.util.PageQueryUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogTagRelationDao {

    int deleteRelationByPrimaryKey(Integer relationID);

    int deleteRelationByBlogID(Integer blogID);

    int insertRelation(BlogTagRelation blogTagRelation);

    int insertSelectiveRelation(BlogTagRelation blogTagRelation);

    int insertRelationByBatch(@Param("blogTagRelations") List<BlogTagRelation> blogTagRelations);

    BlogTagRelation findBlogTagRelationByPrimaryKey(Integer relationID);

    BlogTagRelation findBlogTagRelationByID(Integer blogID,Integer blogTagID);

    List<Integer> findDistinctTagIDs(@Param("tagIDs") int[] tagIDs);

    int updateBlogTagRelation(BlogTagRelation blogTagRelation);

    int updateSelectiveBlogTagRelation(BlogTagRelation blogTagRelation);
}
