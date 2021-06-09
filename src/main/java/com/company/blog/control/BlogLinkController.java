package com.company.blog.control;

import com.company.blog.service.serviceInterfaces.BlogLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class BlogLinkController {

    @Autowired
    private BlogLinkService blogLinkService;

    @GetMapping("/link")
    public String getBlogLinkPage(){
        return "/admin/link";
    }
}
