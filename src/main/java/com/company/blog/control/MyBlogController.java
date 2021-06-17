package com.company.blog.control;

import com.company.blog.model.BlogComment;
import com.company.blog.model.BlogLink;
import com.company.blog.model.vo.DetailBlogVo;
import com.company.blog.service.serviceInterfaces.*;
import com.company.blog.util.PageResult;
import com.company.blog.util.Result;
import com.company.blog.util.ResultGeneratorUtil;
import com.company.blog.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class MyBlogController {

    @Resource
    private BlogCategoryService blogCategoryService;

    @Resource
    private BlogCommentService blogCommentService;

    @Resource
    private BlogConfigService blogConfigService;

    @Resource
    private BlogLinkService blogLinkService;

    @Resource
    private BlogService blogService;

    @Resource
    private BlogTagService blogTagService;

    /**
     * 首页 分页数据
     * @param httpServletRequest
     * @param pageNum
     * @return
     */
    @GetMapping("/page/{pageNum}")
    public String getPage(HttpServletRequest httpServletRequest,
                          @PathVariable("pageNum") Integer pageNum){
        PageResult pageResult=blogService.getBlogHomePage(pageNum);
        if(pageResult==null){
            return "error/error_404";
        }
        httpServletRequest.setAttribute("pageResult",pageResult);
        httpServletRequest.setAttribute("newBlogs",blogService.getSideBarPage(1));
        httpServletRequest.setAttribute("hotBlogs",blogService.getSideBarPage(0));
        httpServletRequest.setAttribute("hotTags",blogTagService.getBlogQuantities());
        httpServletRequest.setAttribute("pageName","首页");
        httpServletRequest.setAttribute("configurations",blogConfigService.getAllConfigs());
        return "blog/amaze/index";
    }

    /**
     * 首页
     * @param httpServletRequest
     * @return
     */
    @GetMapping({"/","/index","index.html"})
    public String index(HttpServletRequest httpServletRequest){
        return this.getPage(httpServletRequest,1);
    }

    /**
     * 分类
     * @param httpServletRequest
     * @return
     */
    @GetMapping("/categories")
    public String categories(HttpServletRequest httpServletRequest){
        httpServletRequest.setAttribute("categories",blogCategoryService.getAllBlogCategories());
        httpServletRequest.setAttribute("hotTags",blogTagService.getBlogQuantities());
        httpServletRequest.setAttribute("pageName","分类页面");
        httpServletRequest.setAttribute("configurations",blogConfigService.getAllConfigs());
        return "blog/yummy-jekyll/category";
    }

    /**
     * 详情页
     * @param httpServletRequest
     * @return
     */
    @GetMapping({"/blog/{blogId}", "/article/{blogId}"})
    public String detailBlog(HttpServletRequest httpServletRequest,
                             @PathVariable("blogId") Integer blogId,
                             @RequestParam(value = "commentPage", required = false, defaultValue = "1")
                                         Integer commentPage){
        DetailBlogVo detailBlogVo=blogService.getDetailBlogVoByID(blogId);
        if(detailBlogVo!=null){
            httpServletRequest.setAttribute("blogDetailVO",detailBlogVo);
            httpServletRequest.setAttribute("commentPageResult",
                    blogCommentService.getPageResultByIdWithPage(blogId,commentPage));
        }
        httpServletRequest.setAttribute("pageName","详情");
        httpServletRequest.setAttribute("configurations",blogConfigService.getAllConfigs());
        return "blog/amaze/detail";
    }

    /**
     * 标签列表页
     */
    @GetMapping("/tag/{tagName}/{page}")
    public String getTag(HttpServletRequest httpServletRequest,
                         @PathVariable("tagName") String tagName,
                         @PathVariable("page") Integer page){
        PageResult pageResult=blogService.getBlogListByTag(tagName,page);
        httpServletRequest.setAttribute("pageResult",pageResult);
        httpServletRequest.setAttribute("pageName","标签列表");
        httpServletRequest.setAttribute("tagName",tagName);
        httpServletRequest.setAttribute("pageUrl","tag");
        httpServletRequest.setAttribute("newBlogs",blogService.getSideBarPage(1));
        httpServletRequest.setAttribute("hotBlogs",blogService.getSideBarPage(0));
        httpServletRequest.setAttribute("hotTags",blogTagService.getBlogQuantities());
        httpServletRequest.setAttribute("configurations",blogConfigService.getAllConfigs());
        return "blog/amaze/list";
    }

    /**
     * 标签列表页
     *
     * @return
     */
    @GetMapping({"/tag/{tagName}"})
    public String tag(HttpServletRequest request, @PathVariable("tagName") String tagName) {
        return getTag(request, tagName, 1);
    }

    /**
     * 分类列表页
     *
     * @return
     */
    @GetMapping({"/category/{categoryName}"})
    public String category(HttpServletRequest request, @PathVariable("categoryName") String categoryName) {
        return category(request, categoryName, 1);
    }

    /**
     * 分类列表页
     *
     * @return
     */
    @GetMapping({"/category/{categoryName}/{page}"})
    public String category(HttpServletRequest httpServletRequest, @PathVariable("categoryName") String categoryName, @PathVariable("page") Integer page) {
        PageResult blogPageResult = blogService.getBlogListByCategory(categoryName, page);
        httpServletRequest.setAttribute("blogPageResult", blogPageResult);
        httpServletRequest.setAttribute("pageName", "分类");
        httpServletRequest.setAttribute("pageUrl", "category");
        httpServletRequest.setAttribute("keyword", categoryName);
        httpServletRequest.setAttribute("newBlogs",blogService.getSideBarPage(1));
        httpServletRequest.setAttribute("hotBlogs",blogService.getSideBarPage(0));
        httpServletRequest.setAttribute("hotTags",blogTagService.getBlogQuantities());
        httpServletRequest.setAttribute("configurations",blogConfigService.getAllConfigs());
        return "blog/amaze/list";
    }

    /**
     * 搜索列表页
     *
     * @return
     */
    @GetMapping({"/search/{keyword}"})
    public String search(HttpServletRequest request, @PathVariable("keyword") String keyword) {
        return search(request, keyword, 1);
    }

    /**
     * 搜索列表页
     *
     * @return
     */
    @GetMapping({"/search/{keyword}/{page}"})
    public String search(HttpServletRequest httpServletRequest, @PathVariable("keyword") String keyword, @PathVariable("page") Integer page) {
        PageResult blogPageResult = blogService.getBlogListByKeyword(keyword, page);
        httpServletRequest.setAttribute("blogPageResult", blogPageResult);
        httpServletRequest.setAttribute("pageName", "搜索");
        httpServletRequest.setAttribute("pageUrl", "search");
        httpServletRequest.setAttribute("keyword", keyword);
        httpServletRequest.setAttribute("newBlogs",blogService.getSideBarPage(1));
        httpServletRequest.setAttribute("hotBlogs",blogService.getSideBarPage(0));
        httpServletRequest.setAttribute("hotTags",blogTagService.getBlogQuantities());
        httpServletRequest.setAttribute("configurations",blogConfigService.getAllConfigs());
        return "blog/amaze/list";
    }


    /**
     * 友情链接页
     *
     * @return
     */
    @GetMapping({"/link"})
    public String link(HttpServletRequest request) {
        request.setAttribute("pageName", "友情链接");
        Map<Byte, List<BlogLink>> linkMap = blogLinkService.getLinkMaps();
        if (linkMap != null) {
            //判断友链类别并封装数据 0-友链 1-推荐 2-个人网站
            if (linkMap.containsKey((byte) 0)) {
                request.setAttribute("favoriteLinks", linkMap.get((byte) 0));
            }
            if (linkMap.containsKey((byte) 1)) {
                request.setAttribute("recommendLinks", linkMap.get((byte) 1));
            }
            if (linkMap.containsKey((byte) 2)) {
                request.setAttribute("personalLinks", linkMap.get((byte) 2));
            }
        }
        request.setAttribute("configurations", blogConfigService.getAllConfigs());
        return "blog/amaze/link";
    }

    /**
     * 评论操作
     */
    @PostMapping(value = "/blog/comment")
    @ResponseBody
    public Result comment(HttpServletRequest request, HttpSession session,
                          @RequestParam Integer blogId, @RequestParam String verifyCode,
                          @RequestParam String commentator, @RequestParam String email,
                          @RequestParam String websiteUrl, @RequestParam String commentBody) {
        if (StringUtil.isNullOrEmpty(verifyCode)) {
            return ResultGeneratorUtil.getFailResult("验证码不能为空");
        }
        String kaptchaCode = session.getAttribute("verifyCode") + "";
        if (StringUtil.isNullOrEmpty(kaptchaCode)) {
            return ResultGeneratorUtil.getFailResult("非法请求");
        }
        if (!verifyCode.equals(kaptchaCode)) {
            return ResultGeneratorUtil.getFailResult("验证码错误");
        }
        String ref = request.getHeader("Referer");
        if (StringUtil.isNullOrEmpty(ref)) {
            return ResultGeneratorUtil.getFailResult("非法请求");
        }
        if (null == blogId || blogId < 0) {
            return ResultGeneratorUtil.getFailResult("非法请求");
        }
        if (StringUtil.isNullOrEmpty(commentator)) {
            return ResultGeneratorUtil.getFailResult("请输入称呼");
        }
        if (StringUtil.isNullOrEmpty(email)) {
            return ResultGeneratorUtil.getFailResult("请输入邮箱地址");
        }
        if (!StringUtil.isEmail(email)) {
            return ResultGeneratorUtil.getFailResult("请输入正确的邮箱地址");
        }
        if (StringUtil.isNullOrEmpty(commentBody)) {
            return ResultGeneratorUtil.getFailResult("请输入评论内容");
        }
        if (commentBody.trim().length() > 200) {
            return ResultGeneratorUtil.getFailResult("评论内容过长");
        }
        BlogComment comment = new BlogComment();
        comment.setBlogID(blogId);
        comment.setBlogCommentatorName(commentator);
        comment.setBlogCommentatorEmail(email);
        if (StringUtil.isMatchNetAddress(websiteUrl)) {
            comment.setBlogWebsiteUrl(websiteUrl);
        }
        comment.setBlogCommentContent(commentBody);
        return ResultGeneratorUtil.getSuccessResult(blogCommentService.addBlogComment(comment));
    }

    /**
     * 关于页面 以及其他配置了subUrl的文章页
     *
     * @return
     */
    @GetMapping({"/{subUrl}"})
    public String detail(HttpServletRequest request, @PathVariable("subUrl") String subUrl) {
        DetailBlogVo blogDetailVO = blogService.getDetailBlogVoBySubUrl(subUrl);
        if (blogDetailVO != null) {
            request.setAttribute("blogDetailVO", blogDetailVO);
            request.setAttribute("pageName", subUrl);
            request.setAttribute("configurations", blogConfigService.getAllConfigs());
            return "blog/amaze/detail";
        } else {
            return "error/error_400";
        }
    }
}
