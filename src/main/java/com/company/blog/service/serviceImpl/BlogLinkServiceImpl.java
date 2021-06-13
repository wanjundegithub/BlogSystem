package com.company.blog.service.serviceImpl;

import com.company.blog.dao.BlogLinkDao;
import com.company.blog.model.BlogLink;
import com.company.blog.service.serviceInterfaces.BlogLinkService;
import com.company.blog.util.PageQueryUtil;
import com.company.blog.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BlogLinkServiceImpl implements BlogLinkService {

    @Autowired
    private BlogLinkDao blogLinkDao;

    @Override
    public PageResult getPageResult(PageQueryUtil pageQueryUtil) {
        var links=blogLinkDao.findBlogLinkList(pageQueryUtil);
        if(null==links){
            return null;
        }
        int totalCount=blogLinkDao.getTotalBlogLinkCount(pageQueryUtil);
        var pageResult=new PageResult(links,totalCount,pageQueryUtil.getLimit(), pageQueryUtil.getCurrentPage());
        return pageResult;
    }

    @Override
    public int getTotalBlogLinks() {
        int totalCount=blogLinkDao.getTotalBlogLinkCount(null);
        return 0;
    }

    @Override
    public boolean insertSelectiveBlogLink(BlogLink blogLink) {
        return blogLinkDao.insertSelectiveBlogLink(blogLink)>0;
    }

    @Override
    public boolean updateSelectiveBlogLink(BlogLink blogLink) {
        return blogLinkDao.updateSelectiveBlogLink(blogLink)>0;
    }

    @Override
    public BlogLink selectBlogLinkByLinkID(Integer id) {
        return blogLinkDao.findBlogLinkByPrimaryKey(id);
    }

    @Override
    public boolean deleteBlogLinks(Integer[] ids) {
        return blogLinkDao.deleteBlogLinkByBatch(ids)>0;
    }

    @Override
    public Map<Byte, List<BlogLink>> getLinkMaps() {
        var totalBlogLinks=blogLinkDao.findBlogLinkList(null);
        if(!CollectionUtils.isEmpty(totalBlogLinks)){
            var maps=totalBlogLinks.stream().
                    collect(Collectors.groupingBy(BlogLink::getBlogLinkType));
            return maps;
        }
        return null;
    }
}
