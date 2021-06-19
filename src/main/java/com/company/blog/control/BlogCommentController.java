package com.company.blog.control;

import com.company.blog.service.serviceInterfaces.BlogCommentService;
import com.company.blog.util.LoggerUtil;
import com.company.blog.util.PageQueryUtil;
import com.company.blog.util.Result;
import com.company.blog.util.ResultGeneratorUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class BlogCommentController {

    @Resource
    private BlogCommentService blogCommentService;

    @GetMapping("/comments")
    public String getBlogCommentPage(HttpServletRequest httpServletRequest){
        httpServletRequest.setAttribute("path","comments");
        return "/admin/comment";
    }

    @GetMapping("/comments/list")
    @ResponseBody
    public Result getBlogCommentList(@RequestParam Map<String,Object> params){
        if(params==null||params.size()==0){
            LoggerUtil.info("分页参数为空");
            return ResultGeneratorUtil.getFailResult("分页参数为空");
        }
        if(params.get("page")==null||params.get("limit")==null){
            return ResultGeneratorUtil.getFailResult("分页参数异常");
        }
        PageQueryUtil pageQueryUtil=new PageQueryUtil(params);
        var pageResult=blogCommentService.getPageResult(pageQueryUtil);
        return ResultGeneratorUtil.getSuccessResult(pageResult);
    }

    @PostMapping("/comments/checkDone")
    @ResponseBody
    public Result checkDoneBlogComments(@RequestBody Integer[] ids){
        if(ids==null||ids.length==0){
            return ResultGeneratorUtil.getFailResult("选择评论为空");
        }
        var result=blogCommentService.checkBlogCommentByBatch(ids);
        return result?ResultGeneratorUtil.getSuccessResult(true):
                ResultGeneratorUtil.getFailResult(false);
    }

    @PostMapping("/comments/delete")
    @ResponseBody
    public Result deleteBlogComments(@RequestBody Integer[] ids){
        if(ids==null||ids.length==0){
            return ResultGeneratorUtil.getFailResult("选择评论为空");
        }
        var result=blogCommentService.deleteBlogCommentByBatch(ids);
        return result?ResultGeneratorUtil.getSuccessResult(true):
                ResultGeneratorUtil.getFailResult(false);
    }

    @PostMapping("/comments/reply")
    @ResponseBody
    public Result replyBlogComment(@RequestParam("blogCommentID") Integer blogCommentID,
                                   @RequestParam("commentReplyContent") String commentReplyContent){
        LoggerUtil.info("回复内容:"+ commentReplyContent);
        if(blogCommentID==null|| commentReplyContent ==null){
            return ResultGeneratorUtil.getFailResult("参数为空");
        }
        var result=blogCommentService.replyBlogComment(blogCommentID, commentReplyContent);
        LoggerUtil.info("id"+blogCommentID+",replyBody："+ commentReplyContent +",result:"+result);
        return result?ResultGeneratorUtil.getSuccessResult(true):
                ResultGeneratorUtil.getFailResult(false);
    }
}
