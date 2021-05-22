package com.company.blog.control.common;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Controller
public class CommonController {

    @Autowired
    private DefaultKaptcha defaultKaptcha;

    @GetMapping("/common/captcha")
    public void defaultCaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        byte[] captchaStream=null;
        ByteArrayOutputStream arrayOutputStream=new ByteArrayOutputStream();
        try {
            //将产生的验证码保存在session中
            String verifyCode=defaultKaptcha.createText();
            httpServletRequest.getSession().setAttribute("verifyCode",verifyCode);
            BufferedImage bufferedImage=defaultKaptcha.createImage(verifyCode);
            ImageIO.write(bufferedImage,"jpg",arrayOutputStream);
        }catch (IOException e){
            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        captchaStream=arrayOutputStream.toByteArray();
        httpServletResponse.setHeader("Cache-Control", "no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream = httpServletResponse.getOutputStream();
        responseOutputStream.write(captchaStream);
        responseOutputStream.flush();
        responseOutputStream.close();
    }
}
