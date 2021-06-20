package com.company.blog.dao;

import com.company.blog.model.BlogConfig;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogConfigDao {

    List<BlogConfig> findAllConfig();

    BlogConfig getConfigByPrimaryKey(String blogConfigName);

    Integer updateSelectiveBlogConfig(BlogConfig blogConfig);
}
