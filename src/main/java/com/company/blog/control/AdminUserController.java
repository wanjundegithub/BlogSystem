package com.company.blog.control;

import com.company.blog.service.serviceInterfaces.AdminUserService;
import com.company.blog.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
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
        session.setAttribute("loginUser",user.getAdminNickname());
        session.setAttribute("loginUserID",user.getAdminID());
        return "redirect:/admin/index";
    }


}
