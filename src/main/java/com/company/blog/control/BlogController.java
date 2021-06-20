package com.company.blog.control;

import com.company.blog.model.Blog;
import com.company.blog.service.serviceInterfaces.BlogCategoryService;
import com.company.blog.service.serviceInterfaces.BlogService;
import com.company.blog.util.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Random;

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
        return "admin/edit";
    }

    @GetMapping("/blogs/edit/{blogID}")
    public String editBlog(HttpServletRequest httpServletRequest,
                           @PathVariable("blogID") Integer blogID){
        httpServletRequest.setAttribute("path","edit");
        LoggerUtil.info("blogID:"+blogID);
        Blog blog=blogService.getBlogByBlogID(blogID);
        if(blog==null){
            return "error/error_404";
        }
        httpServletRequest.setAttribute("blog",blog);
        httpServletRequest.setAttribute("categories",blogCategoryService.getAllBlogCategories());
        return "admin/edit";
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
        LoggerUtil.info("标题:"+blogTitle);
        if(StringUtil.isNullOrEmpty(blogTitle)
        ||StringUtil.isNullOrEmpty(blogCoverImagePath)){
            return ResultGeneratorUtil.getFailResult("标题为空");
        }
        if(StringUtil.isNullOrEmpty(blogTags)){
            return ResultGeneratorUtil.getFailResult("标签为空");
        }
        if(StringUtil.isNullOrEmpty(blogContent)){
            return ResultGeneratorUtil.getFailResult("文章内容为空");
        }
        if(StringUtil.isNullOrEmpty(blogCoverImagePath)){
            return ResultGeneratorUtil.getFailResult("封面路径为空");
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
                             @RequestParam(value = "blogSubUrl",required = false) String blogSubUrl,
                             @RequestParam("blogCategoryID")  Integer blogCategoryID,
                             @RequestParam("blogTags") String blogTags,
                             @RequestParam("blogContent") String  blogContent,
                             @RequestParam("blogCoverImagePath") String blogCoverImagePath,
                             @RequestParam("blogStatus") Byte blogStatus,
                             @RequestParam("blogEnableComment")  Byte blogEnableComment){
        LoggerUtil.info("开始修改:"+blogID);
        Blog blog=blogService.getBlogByBlogID(blogID);
        if(null==blog){
            return ResultGeneratorUtil.getFailResult(blogID+"对应blog为空");
        }
        if(blogSubUrl.length()>150||blogTags.length()>150||blogCoverImagePath.length()>150){
            return ResultGeneratorUtil.getFailResult("自定义路径或标签长度大于150");
        }
        if(StringUtil.isNullOrEmpty(blogTitle)||StringUtil.isNullOrEmpty(blogContent)
                ||StringUtil.isNullOrEmpty(blogCoverImagePath)){
            return ResultGeneratorUtil.getFailResult("标题或内容或封面路径为空");
        }
        if(blogContent.length()>2000){
            return ResultGeneratorUtil.getFailResult("内容太长");
        }
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


    @PostMapping("/blogs/md/uploadfile")
    public void uploadFileByEditormd(HttpServletRequest request,
                                     HttpServletResponse response,
                                     @RequestParam(name = "editormd-image-file", required = true)
                                             MultipartFile file) throws IOException, URISyntaxException {
        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //生成文件名称通用方法
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Random r = new Random();
        StringBuilder tempName = new StringBuilder();
        tempName.append(sdf.format(new Date())).append(r.nextInt(100)).append(suffixName);
        String newFileName = tempName.toString();
        //创建文件
        File destFile = new File(CommonUtil.FILE_UPLOAD_DIC + newFileName);
        String fileUrl = BlogUtil.getHost(new URI(request.getRequestURL() + "")) + "/upload/" + newFileName;
        File fileDirectory = new File(CommonUtil.FILE_UPLOAD_DIC);
        try {
            if (!fileDirectory.exists()) {
                if (!fileDirectory.mkdir()) {
                    throw new IOException("文件夹创建失败,路径为：" + fileDirectory);
                }
            }
            file.transferTo(destFile);
            request.setCharacterEncoding("utf-8");
            response.setHeader("Content-Type", "text/html");
            response.getWriter().write("{\"success\": 1, \"message\":\"success\",\"url\":\"" + fileUrl + "\"}");
        } catch (UnsupportedEncodingException e) {
            response.getWriter().write("{\"success\":0}");
        } catch (IOException e) {
            response.getWriter().write("{\"success\":0}");
        }
    }

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
