package com.company.blog.control;

import com.company.blog.model.BlogLink;
import com.company.blog.service.serviceInterfaces.BlogLinkService;
import com.company.blog.util.*;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class BlogLinkController {

    @Resource
    private BlogLinkService blogLinkService;

    @GetMapping("/links")
    public String getBlogLinkPage(HttpServletRequest httpServletRequest){
        httpServletRequest.setAttribute("path","links");
        return "/admin/link";
    }

    @GetMapping("/links/list")
    @ResponseBody
    public Result getBlogLinkPage(@RequestParam Map<String,Object> params){
        if(params==null){
            return ResultGeneratorUtil.getFailResult("page参数异常");
        }
        if(params.get("page")==null||params.get("limit")==null){
            return ResultGeneratorUtil.getFailResult("page参数异常");
        }
        PageQueryUtil pageQueryUtil=new PageQueryUtil(params);
        var result=blogLinkService.getPageResult(pageQueryUtil);
        LoggerUtil.info("当前页:"+result.getCurrentPage()+"每页行数,"+result.getPageSize());
        return ResultGeneratorUtil.getSuccessResult(blogLinkService.getPageResult(pageQueryUtil));
    }

    @PostMapping("/links/save")
    @ResponseBody
    public Result AddBlogLink(@RequestParam("blogLinkType") Integer blogLinkType,
                              @RequestParam("blogLinkName")String blogLinkName,
                              @RequestParam("blogLinkUrl") String blogLinkUrl,
                              @RequestParam("blogLinkDescription") String blogLinkDescription,
                              @RequestParam("blogLinkRank") Integer blogLinkRank){
        LoggerUtil.info(blogLinkType+","+blogLinkName);
        if(null==blogLinkType||StringUtil.isNullOrEmpty(blogLinkName)||StringUtil.isNullOrEmpty(blogLinkUrl)||
        StringUtil.isNullOrEmpty(blogLinkDescription)||null==blogLinkRank){
            return ResultGeneratorUtil.getFailResult("参数异常");
        }
        BlogLink blogLink=new BlogLink();
        blogLink.setBlogLinkType(blogLinkType.byteValue());
        blogLink.setBlogLinkName(blogLinkName);
        blogLink.setBlogLinkUrl(blogLinkUrl);
        blogLink.setBlogLinkDescription(blogLinkDescription);
        blogLink.setBlogLinkRank(blogLinkRank);
        var addFlag=blogLinkService.insertSelectiveBlogLink(blogLink);
        return addFlag?ResultGeneratorUtil.getSuccessResult(true):ResultGeneratorUtil.getFailResult(false);
    }

    @PostMapping("/links/delete")
    @ResponseBody
    public Result deleteBlogLink(@RequestBody Integer[] ids){
        if(ids.length<1){
            return ResultGeneratorUtil.getFailResult("选中行数小于1");
        }
        var result=blogLinkService.deleteBlogLinks(ids);
        return result?ResultGeneratorUtil.getSuccessResult(true):ResultGeneratorUtil.getFailResult(false);
    }

    @PostMapping("/links/update")
    @ResponseBody
    public Result updateBlogLink(@RequestParam("blogLinkID") Integer blogLinkID,
                                 @RequestParam("blogLinkType") Integer blogLinkType,
                                 @RequestParam("blogLinkName")String blogLinkName,
                                 @RequestParam("blogLinkUrl") String blogLinkUrl,
                                 @RequestParam("blogLinkDescription") String blogLinkDescription,
                                 @RequestParam("blogLinkRank") Integer blogLinkRank){
        if(null==blogLinkID|| null==blogLinkType||StringUtil.isNullOrEmpty(blogLinkName)
                ||StringUtil.isNullOrEmpty(blogLinkUrl)||
                StringUtil.isNullOrEmpty(blogLinkDescription)||null==blogLinkRank){
            return ResultGeneratorUtil.getFailResult("参数异常");
        }
        var blogLink=blogLinkService.selectBlogLinkByLinkID(blogLinkID);
        if(null==blogLink){
            return ResultGeneratorUtil.getFailResult("空数据");
        }
        blogLink.setBlogLinkType(blogLinkType.byteValue());
        blogLink.setBlogLinkName(blogLinkName);
        blogLink.setBlogLinkUrl(blogLinkUrl);
        blogLink.setBlogLinkDescription(blogLinkDescription);
        blogLink.setBlogLinkRank(blogLinkRank);
        var result=blogLinkService.updateSelectiveBlogLink(blogLink);
        return result?ResultGeneratorUtil.getSuccessResult(true):ResultGeneratorUtil.getFailResult(false);
    }

    @GetMapping("/links/info/{blogLinkID}")
    @ResponseBody
    public Result getDetailBlogLinkInfo(@PathVariable Integer blogLinkID){
        var blogLink=blogLinkService.selectBlogLinkByLinkID(blogLinkID);
        if(null==blogLink){
           return ResultGeneratorUtil.getFailResult(null);
        }
        return ResultGeneratorUtil.getSuccessResult(blogLink);
    }
}
