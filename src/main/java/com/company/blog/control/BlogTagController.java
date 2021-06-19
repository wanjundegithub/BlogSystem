package com.company.blog.control;

import com.company.blog.service.serviceInterfaces.BlogTagService;
import com.company.blog.util.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class BlogTagController {

    @Resource
    private BlogTagService blogTagService;

    @GetMapping("/tags")
    public String getBlogTagPage(HttpServletRequest httpServletRequest){
        httpServletRequest.setAttribute("path","tags");
        return "/admin/tag";
    }

    @GetMapping("/tags/list")
    @ResponseBody
    public Result getBlogTagList(@RequestParam Map<String,Object> params){
        if(null==params||params.size()==0){
            return ResultGeneratorUtil.getFailResult("参数为空");
        }
        if(params.get("page")==null||params.get("limit")==null){
            return ResultGeneratorUtil.getFailResult("参数异常");
        }
        PageQueryUtil pageQueryUtil=new PageQueryUtil(params);
        return ResultGeneratorUtil.getSuccessResult(blogTagService.getBlogTagPage(pageQueryUtil));
    }

    @PostMapping("/tags/save/{blogTagName}")
    @ResponseBody
    public Result addBlogTag(@PathVariable("blogTagName") String blogTagName){
        LoggerUtil.info("name:"+blogTagName);
        if(StringUtil.isNullOrEmpty(blogTagName)){
            return ResultGeneratorUtil.getFailResult("参数为空");
        }
        var flag=blogTagService.insertSelectiveBlogTag(blogTagName);
        return flag?ResultGeneratorUtil.getSuccessResult(true):ResultGeneratorUtil.getFailResult(false);
    }

    @PostMapping("/tags/delete")
    @ResponseBody
    public Result deleteBlogTag(@RequestBody Integer[] ids){
        if(ids==null||ids.length==0){
            LoggerUtil.info("id 为空");
            return ResultGeneratorUtil.getFailResult("删除项为空");
        }
        for(int i=0;i<ids.length;i++){
            LoggerUtil.info(ids[i]+",");
        }
        var flag=blogTagService.deleteBlogTagByBatch(ids);
        LoggerUtil.info("删除是否成功:"+flag);
        return flag?ResultGeneratorUtil.getSuccessResult(true):ResultGeneratorUtil.getFailResult(false);
    }


}
