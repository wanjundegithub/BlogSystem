package com.company.blog.control;

import com.company.blog.service.serviceInterfaces.AdminUserService;
import com.company.blog.util.LoggerUtil;
import com.company.blog.util.Result;
import com.company.blog.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminUserController {

    private static final Logger logger= LoggerFactory.getLogger(AdminUserController.class);

    @Resource
    private AdminUserService adminUserService;

    @GetMapping("/login")
    public String login(){
       return "admin/login";
    }

    @GetMapping({"", "/", "/index", "/index.html"})
    public String index(){
        return "admin/index";
    }

    @PostMapping("/login")
    public String login(@RequestParam("userName") String account, @RequestParam("password") String password,
                        @RequestParam("verifyCode") String verifyCode, HttpSession session) {
        if (StringUtil.isNullOrEmpty(account)) {
            session.setAttribute("errorMsg", "账号为空");
            logger.error("账号为空");
            return "admin/login";
        }
        if (StringUtil.isNullOrEmpty(password)) {
            session.setAttribute("errorMsg", "密码为空");
            logger.error("密码为空");
            return "admin/login";
        }
        if (StringUtil.isNullOrEmpty(verifyCode)) {
            session.setAttribute("errorMsg", "验证码为空");
            logger.error("验证码为空");
            return "admin/login";
        }
        var user = adminUserService.login(account, password);
        if (user == null) {
            session.setAttribute("errorMsg", "账号或密码错误");
            logger.error("账号或密码错误");
            return "admin/login";
        }
        String captchaCode=session.getAttribute("verifyCode")+"";
        if(StringUtil.isNullOrEmpty(captchaCode)||!StringUtil.isEqual(captchaCode,verifyCode)){
            session.setAttribute("errorMsg","验证码为空或错误");
            logger.error("验证码为空或错误");
            return "admin/login";
        }
        logger.info("账号，密码，验证码正确");
        session.setAttribute("loginUserName",user.getAdminNickname());
        session.setAttribute("loginUserID",user.getAdminID());
        //这里应该到主页index
        return "redirect:/admin/index";
    }

    @GetMapping("/profile")
    public String getProfile(HttpServletRequest httpServletRequest){
        var adminID=(int)httpServletRequest.getSession().getAttribute("loginUserID");
        var adminUser=adminUserService.queryByAdminID(adminID);
        if(null==adminUser){
            LoggerUtil.error("缓存ID失效,用户为空");
            return "/admin/login";
        }
        httpServletRequest.setAttribute("path","profile");
        httpServletRequest.setAttribute("adminAccount",adminUser.getAdminAccount());
        httpServletRequest.setAttribute("adminNickname",adminUser.getAdminNickname());
        return "/admin/profile";
    }

    @PostMapping("/profile/name")
    @ResponseBody
    public String updateName(HttpServletRequest httpServletRequest,
                             @RequestParam("adminAccount")String adminAccount,
                             @RequestParam("adminNickname")String adminNickname){
        LoggerUtil.info("开始修改account");
        if(StringUtil.isNullOrEmpty(adminAccount)&&StringUtil.isNullOrEmpty(adminNickname)){
            return "参数不能同时为空";
        }
        var adminUserID=(int)httpServletRequest.getSession().getAttribute("loginUserID");
        LoggerUtil.info("loginUserName:"+adminAccount+",nickName:"+adminNickname+",id:"+adminUserID);
        if(!adminUserService.updateAdminUserInfo(adminUserID,adminAccount,adminNickname)){
            return "修改账户或昵称失败";
        }
        LoggerUtil.info("修改成功");
        return "success";
    }

    @PostMapping("/profile/password")
    @ResponseBody
    public String updatePassword(HttpServletRequest httpServletRequest,
                                 @RequestParam("oldPassword")String oldPassword,
                                 @RequestParam("newPassword")String newPassword){
        LoggerUtil.info("begin to modify password");
        if(StringUtil.isNullOrEmpty(oldPassword)||StringUtil.isNullOrEmpty(newPassword)){
            return "参数不能为空";
        }
        var adminID=(Integer)httpServletRequest.getSession().getAttribute("loginUserID");
        LoggerUtil.info("oldPassword:"+oldPassword+",newPassword:"+newPassword);
        if(!adminUserService.updateAdminUserPassword(adminID,oldPassword,newPassword)){
            return "修改密码失败";
        }
        httpServletRequest.getSession().removeAttribute("loginUser");
        httpServletRequest.getSession().removeAttribute("loginUserID");
        httpServletRequest.getSession().removeAttribute("errorMsg");
        return "success";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest httpServletRequest){
        httpServletRequest.getSession().removeAttribute("loginUser");
        httpServletRequest.getSession().removeAttribute("loginUserID");
        httpServletRequest.getSession().removeAttribute("errorMsg");
        return "/admin/login";
    }
}
