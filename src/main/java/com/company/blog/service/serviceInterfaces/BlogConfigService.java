package com.company.blog.service.serviceInterfaces;

import java.util.Map;

public interface BlogConfigService {

    int updateBlogConfig(String configName,String configValue);

    Map<String,String> getAllConfigs();
}
