package com.company.blog.service.serviceInterfaces;

import com.company.blog.model.BlogLink;
import com.company.blog.util.PageQueryUtil;
import com.company.blog.util.PageResult;

import java.util.List;
import java.util.Map;

public interface BlogLinkService {

    PageResult getPageResult(PageQueryUtil pageQueryUtil);

    int getTotalBlogLinks();

    boolean insertSelectiveBlogLink(BlogLink blogLink);

    boolean updateSelectiveBlogLink(BlogLink blogLink);

    BlogLink selectBlogLinkByLinkID(int id);

    boolean deleteBlogLinks(int[] ids);

    Map<Byte, List<BlogLink>> getLinkMaps();
}
