package com.company.blog.control;

import com.company.blog.service.serviceInterfaces.BlogCategoryService;
import com.company.blog.util.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class BlogCategoryController {

    @Resource
    private BlogCategoryService blogCategoryService;

    @GetMapping("/categories")
    public String getBlogCategoryPage(HttpServletRequest httpServletRequest){
        httpServletRequest.setAttribute("path","categories");
        return "/admin/category";
    }

    @GetMapping("/categories/list")
    @ResponseBody
    public Result getBlogCategoryList(@RequestParam  Map<String,Object> params){
        if(params==null|| CollectionUtils.isEmpty(params)){
            return ResultGeneratorUtil.getFailResult("参数为空");
        }
        if(params.get("page")==null||params.get("limit")==null){
            return ResultGeneratorUtil.getFailResult("参数page,limit为空");
        }
        PageQueryUtil pageQueryUtil=new PageQueryUtil(params);
        return ResultGeneratorUtil.getSuccessResult(
                blogCategoryService.getBlogCategoryPageResult(pageQueryUtil));
    }

    @PostMapping("/categories/save")
    @ResponseBody
    public Result addBlogCategory(@RequestParam("blogCategoryName") String blogCategoryName,
                                  @RequestParam("blogCategoryIcon") String blogCategoryIcon){
        if(StringUtil.isNullOrEmpty(blogCategoryName)||StringUtil.isNullOrEmpty(blogCategoryIcon)){
            return ResultGeneratorUtil.getFailResult("参数为空");
        }
        var result=blogCategoryService.insertSelectiveBlogCategory(blogCategoryName,
                blogCategoryIcon);
        return result?ResultGeneratorUtil.getSuccessResult(true):
                ResultGeneratorUtil.getFailResult(false);
    }

    @PostMapping("/categories/delete")
    @ResponseBody
    public  Result deleteBlogCategoriesByBatch(@RequestBody Integer[] ids){
        if(ids==null||ids.length==0){
            return ResultGeneratorUtil.getFailResult("参数为空");
        }
        var result=blogCategoryService.deleteBlogCategoriesByBatch(ids);
        return result?ResultGeneratorUtil.getSuccessResult(true):
                ResultGeneratorUtil.getFailResult(false);
    }

    @PostMapping("/categories/update")
    @ResponseBody
    public Result updateBlogCategory(@RequestParam("blogCategoryID") Integer blogCategoryID,
                                     @RequestParam("blogCategoryName") String blogCategoryName,
                                     @RequestParam("blogCategoryIcon") String blogCategoryIcon){
        if(blogCategoryID==null||StringUtil.isNullOrEmpty(blogCategoryName)||
        StringUtil.isNullOrEmpty(blogCategoryIcon)){
            return ResultGeneratorUtil.getFailResult("参数为空");
        }
        var result=blogCategoryService.updateSelectiveBlogCategory(blogCategoryID,
                blogCategoryName,blogCategoryIcon);
        return result?ResultGeneratorUtil.getSuccessResult(true):
                ResultGeneratorUtil.getFailResult(false);
    }

    @GetMapping("/categories/info/{blogCategoryID}")
    @ResponseBody
    public Result getCategoryInfo(@PathVariable("blogCategoryID") Integer blogCategoryID){
        LoggerUtil.info("id"+blogCategoryID);
        if(null==blogCategoryID){
            return ResultGeneratorUtil.getFailResult("参数为空");
        }
        var blogCategory=blogCategoryService.getBlogCategory(blogCategoryID);
        LoggerUtil.info("name:"+blogCategory.getBlogCategoryName());
        return ResultGeneratorUtil.getSuccessResult(blogCategory);
    }
}
