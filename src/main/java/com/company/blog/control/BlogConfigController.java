package com.company.blog.control;

import com.company.blog.service.serviceInterfaces.BlogConfigService;
import com.company.blog.util.LoggerUtil;
import com.company.blog.util.Result;
import com.company.blog.util.ResultGeneratorUtil;
import com.company.blog.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class BlogConfigController {

    @Resource
    private BlogConfigService blogConfigService;

    @GetMapping("/configurations")
    public String showConfigs(HttpServletRequest httpServletRequest){
        httpServletRequest.setAttribute("path","configurations");
        httpServletRequest.setAttribute("configurations",blogConfigService.getAllConfigs());
        return "/admin/configurations";
    }

    @PostMapping("/configurations/website")
    @ResponseBody
    public Result updateWebsiteConfig(@RequestParam(value = "websiteName",required = false) String websiteName,
                                      @RequestParam(value = "websiteDescription",required = false) String websiteDescription,
                                      @RequestParam(value = "websiteLogo",required = false) String websiteLogo,
                                      @RequestParam(value = "websiteIcon",required = false) String websiteIcon){
        boolean resultFlag=true;
        if(!StringUtil.isNullOrEmpty(websiteName)){
            resultFlag&=(blogConfigService.updateBlogConfig("websiteName",websiteName)!=0);
        }
        if(!StringUtil.isNullOrEmpty(websiteDescription)){
            resultFlag&=(blogConfigService.updateBlogConfig(
                    "websiteDescription",websiteDescription)!=0);
        }
        if(!StringUtil.isNullOrEmpty(websiteLogo)){
            resultFlag&=(blogConfigService.updateBlogConfig("websiteLogo",websiteLogo)!=0);
        }
        if(!StringUtil.isNullOrEmpty(websiteIcon)){
            resultFlag&=(blogConfigService.updateBlogConfig("websiteIcon",websiteIcon)!=0);
        }
        return resultFlag?ResultGeneratorUtil.getSuccessResult(true):
                ResultGeneratorUtil.getFailResult(false);
    }

    @PostMapping("/configurations/userInfo")
    @ResponseBody
    public Result updateUserInfo(@RequestParam(value = "yourAvatar",required = false) String yourAvatar,
                                 @RequestParam(value = "yourEmail",required = false) String yourEmail,
                                 @RequestParam(value = "yourName",required = false) String yourName){
        LoggerUtil.info("开始响应修改");
        boolean resultFlag=true;
        if(!StringUtil.isNullOrEmpty(yourAvatar)){
            resultFlag&=(blogConfigService.updateBlogConfig(
                    "yourAvatar",yourAvatar)!=0);
        }
        if(!StringUtil.isNullOrEmpty(yourEmail)){
            resultFlag&=(blogConfigService.updateBlogConfig(
                    "yourEmail",yourEmail)!=0);
        }
        if(!StringUtil.isNullOrEmpty(yourName)){
            resultFlag&=(blogConfigService.updateBlogConfig(
                    "yourName",yourName)!=0);
        }
        LoggerUtil.info("最终修改结果:"+resultFlag);
        return resultFlag?ResultGeneratorUtil.getSuccessResult(true):
                ResultGeneratorUtil.getFailResult(false);
    }

    @PostMapping("/configurations/footer")
    @ResponseBody
    public Result updateFooterConfig(@RequestParam(value = "footerAbout",required = false) String footerAbout,
                                     @RequestParam(value = "footerICP",required = false)String footerICP,
                                     @RequestParam(value = "footerCopyRight",required = false)String footerCopyRight,
                                     @RequestParam(value = "footerPoweredBy",required = false)String footerPoweredBy,
                                     @RequestParam(value = "footerPoweredByURL",required = false)String footerPoweredByURL){
        boolean resultFlag=true;
        if(!StringUtil.isNullOrEmpty(footerAbout)){
            resultFlag&=(blogConfigService.updateBlogConfig(
                    "footerAbout",footerAbout)!=0);
        }
        if(!StringUtil.isNullOrEmpty(footerICP)){
            resultFlag&=(blogConfigService.updateBlogConfig(
                    "footerICP",footerICP)!=0);
        }
        if(!StringUtil.isNullOrEmpty(footerCopyRight)){
            resultFlag&=(blogConfigService.updateBlogConfig(
                    "footerCopyRight",footerCopyRight)!=0);
        }
        if(!StringUtil.isNullOrEmpty(footerPoweredBy)){
            resultFlag&=(blogConfigService.updateBlogConfig(
                    "footerPoweredBy",footerPoweredBy)!=0);
        }
        if(!StringUtil.isNullOrEmpty(footerPoweredByURL)){
            resultFlag&=(blogConfigService.updateBlogConfig(
                    "footerPoweredByURL",footerPoweredByURL)!=0);
        }
        LoggerUtil.info("最终修改结果:"+resultFlag);
        return resultFlag?ResultGeneratorUtil.getSuccessResult(true):
                ResultGeneratorUtil.getFailResult(false);
    }
}
