package com.company.blog.service.serviceInterfaces;

import com.company.blog.model.Blog;
import com.company.blog.model.vo.DetailBlogVo;
import com.company.blog.model.vo.RoughBlogVo;
import com.company.blog.util.PageQueryUtil;
import com.company.blog.util.PageResult;

import java.util.List;

public interface BlogService {

    PageResult getBlogPage(PageQueryUtil pageQueryUtil);

    String insertSelectiveBlog(Blog blog);

    boolean deleteBlogsByBatch(Integer[] blogIds);

    int getTotalBlogCount();

    Blog getBlogByBlogID(Integer blogID);

    String updateBlog(Blog blog);

    PageResult getBlogHomePage(int page);

    List<RoughBlogVo> getSideBarPage(int type);

    DetailBlogVo getDetailBlogVoByID(Integer blogID);

    PageResult getBlogListByTag(String tagName,int page);

    PageResult getBlogListByCategory(String categoryName,int page);

    PageResult getBlogListByKeyword(String keyword,int page);

    DetailBlogVo getDetailBlogVoBySubUrl(String subUrl);
}
