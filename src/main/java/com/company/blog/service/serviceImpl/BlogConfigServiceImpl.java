package com.company.blog.service.serviceImpl;

import com.company.blog.dao.BlogConfigDao;
import com.company.blog.model.BlogConfig;
import com.company.blog.service.serviceInterfaces.BlogConfigService;
import com.company.blog.util.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BlogConfigServiceImpl implements BlogConfigService {

    @Resource
    private BlogConfigDao blogConfigDao;

    private HashMap<String,String> defaultConfigs=new HashMap<>();

    public BlogConfigServiceImpl() {
        defaultConfigs.put("websiteName","personal blog");
        defaultConfigs.put("websiteDescription","SpringBoot2+Thymeleaf+Mybatis建造的个人博客网站");
        defaultConfigs.put("websiteLogo","/admin/dist/img/logo2.png");
        defaultConfigs.put("websiteIcon","/admin/dist/img/favicon.png");

        defaultConfigs.put("yourAvatar","/admin/dist/img/13.png");
        defaultConfigs.put("yourEmail","2304564274@qq.com");
        defaultConfigs.put("yourName","十三");

        defaultConfigs.put("footerAbout","your personal blog. have fun.");
        defaultConfigs.put("footerICP","浙ICP备 xxxxxx-x号");
        defaultConfigs.put("footerCopyRight" , "@2018 十三");
        defaultConfigs.put("footerPoweredBy" ,"personal blog");
        defaultConfigs.put("footerPoweredByURL", "##");
    }

    /**
     * 更新博客配置
     * @param configName
     * @param configValue
     * @return
     */
    @Override
    public int updateBlogConfig(String configName, String configValue) {
        var config=blogConfigDao.getConfigByPrimaryKey(configName);
        if(null==config){
            return 0;
        }
        config.setBlogConfigValue(configValue);
        config.setBlogConfigUpdateTime(null);
        return blogConfigDao.updateSelectiveBlogConfig(config);
    }

    /**
     * 获取所有配置项
     * @return
     */
    @Override
    public Map<String, String> getAllConfigs() {
        var allConfigs=blogConfigDao.findAllConfig();
        var configMaps=allConfigs.stream().collect(Collectors.toMap(BlogConfig::getBlogConfigName,
                BlogConfig::getBlogConfigValue));
        for(var config:configMaps.entrySet()){
            if(defaultConfigs.containsKey(config.getKey())&& StringUtil.isNullOrEmpty(config.getValue())){
                config.setValue(defaultConfigs.get(config.getKey()));
            }
        }
        return configMaps;
    }
}
