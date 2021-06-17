package com.company.blog.control;

import com.company.blog.model.Blog;
import com.company.blog.service.serviceInterfaces.BlogCategoryService;
import com.company.blog.service.serviceInterfaces.BlogService;
import com.company.blog.util.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class BlogController {

    @Resource
    private BlogService blogService;

    @Resource
    private BlogCategoryService blogCategoryService;

    @GetMapping("/blogs")
    public String getBlogPage(HttpServletRequest httpServletRequest){
        httpServletRequest.setAttribute("path","blogs");
        return "/admin/blog";
    }

    @GetMapping("/blogs/list")
    @ResponseBody
    public Result getBlogList(@RequestParam Map<String,Object> params){
        if(CollectionUtils.isEmpty(params)){
            return ResultGeneratorUtil.getFailResult("列表为空");
        }
        if(params.get("limit")==null||params.get("page")==null){
            return ResultGeneratorUtil.getFailResult("参数为空");
        }
        PageQueryUtil pageQueryUtil=new PageQueryUtil(params);
        return ResultGeneratorUtil.getSuccessResult(blogService.getBlogPage(pageQueryUtil));
    }


    @GetMapping("/blogs/edit")
    public String editBlog(HttpServletRequest httpServletRequest){
        httpServletRequest.setAttribute("path","edit");
        httpServletRequest.setAttribute("categories",blogCategoryService.getAllBlogCategories());
        return "/admin/edit";
    }

    @PostMapping("/blogs/edit/{blogID}")
    public String editBlog(HttpServletRequest httpServletRequest,
                           @PathVariable("blogID") Integer blogID){
        httpServletRequest.setAttribute("path","edit");
        Blog blog=blogService.getBlogByBlogID(blogID);
        if(blog==null){
            return "/error/error_404";
        }
        httpServletRequest.setAttribute("blog",blog);
        httpServletRequest.setAttribute("categories",blogCategoryService.getAllBlogCategories());
        return "/admin/edit";
    }

    @PostMapping("/blogs/save")
    @ResponseBody
    public  Result addBlog(@RequestParam("blogTitle") String blogTitle,
                           @RequestParam(name = "blogSubUrl",required = false) String blogSubUrl,
                           @RequestParam("blogCategoryID")  Integer blogCategoryID,
                           @RequestParam("blogTags") String blogTags,
                           @RequestParam("blogContent") String  blogContent,
                           @RequestParam("blogCoverImagePath") String blogCoverImagePath,
                           @RequestParam("blogStatus") Byte blogStatus,
                           @RequestParam("blogEnableComment")  Byte blogEnableComment){

        if(StringUtil.isNullOrEmpty(blogTitle)||StringUtil.isNullOrEmpty(blogContent)
        ||StringUtil.isNullOrEmpty(blogCoverImagePath)){
            return ResultGeneratorUtil.getFailResult("标题或内容或封面路径为空");
        }
        if(blogSubUrl.length()>150||blogTags.length()>150||blogCoverImagePath.length()>150){
            return ResultGeneratorUtil.getFailResult("自定义路径或标签长度大于150");
        }
        if(blogContent.length()>2000){
            return ResultGeneratorUtil.getFailResult("内容太长");
        }
        Blog blog=new Blog();
        blog.setBlogTitle(blogTitle);
        blog.setBlogSubUrl(blogSubUrl);
        blog.setBlogCategoryID(blogCategoryID);
        blog.setBlogTags(blogTags);
        blog.setBlogContent(blogContent);
        blog.setBlogCoverImagePath(blogCoverImagePath);
        blog.setBlogStatus(blogStatus);
        blog.setBlogEnableComment(blogEnableComment);
        var result=blogService.insertSelectiveBlog(blog);
        return result.equals(CommonUtil.SuccessSave)?
                ResultGeneratorUtil.getSuccessResult(CommonUtil.SuccessSave):
                ResultGeneratorUtil.getSuccessResult(result);
    }

    @PostMapping("/blogs/update")
    @ResponseBody
    public Result updateBlog(@RequestParam("blogID") Integer blogID,
                             @RequestParam("blogTitle") String blogTitle,
                             @RequestParam(name = "blogSubUrl",required = false) String blogSubUrl,
                             @RequestParam("blogCategoryID")  Integer blogCategoryID,
                             @RequestParam("blogTags") String blogTags,
                             @RequestParam("blogContent") String  blogContent,
                             @RequestParam("blogCoverImagePath") String blogCoverImagePath,
                             @RequestParam("blogStatus") Byte blogStatus,
                             @RequestParam("blogEnableComment")  Byte blogEnableComment){
        if(StringUtil.isNullOrEmpty(blogTitle)||StringUtil.isNullOrEmpty(blogContent)
                ||StringUtil.isNullOrEmpty(blogCoverImagePath)){
            return ResultGeneratorUtil.getFailResult("标题或内容或封面路径为空");
        }
        if(blogSubUrl.length()>150||blogTags.length()>150||blogCoverImagePath.length()>150){
            return ResultGeneratorUtil.getFailResult("自定义路径或标签长度大于150");
        }
        if(blogContent.length()>2000){
            return ResultGeneratorUtil.getFailResult("内容太长");
        }
        Blog blog=new Blog();
        blog.setBlogID(blogID);
        blog.setBlogTitle(blogTitle);
        blog.setBlogSubUrl(blogSubUrl);
        blog.setBlogCategoryID(blogCategoryID);
        blog.setBlogTags(blogTags);
        blog.setBlogContent(blogContent);
        blog.setBlogCoverImagePath(blogCoverImagePath);
        blog.setBlogStatus(blogStatus);
        blog.setBlogEnableComment(blogEnableComment);
        var result=blogService.updateBlog(blog);
        return result.equals(CommonUtil.SuccessModify)?
                ResultGeneratorUtil.getSuccessResult(CommonUtil.SuccessModify):
                ResultGeneratorUtil.getSuccessResult(result);
    }


    //上传文件功能暂时不添加

    @PostMapping("/blogs/delete")
    @ResponseBody
    public Result deleteBlog(@RequestBody Integer[] ids){
        if(ids==null||ids.length<1){
            return ResultGeneratorUtil.getFailResult("列表为空");
        }
        var result=blogService.deleteBlogsByBatch(ids);
        return  result?ResultGeneratorUtil.getSuccessResult(true):
                ResultGeneratorUtil.getFailResult(false);
    }

}
