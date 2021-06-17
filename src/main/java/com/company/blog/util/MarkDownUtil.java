package com.company.blog.util;

import org.commonmark.Extension;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import java.util.Arrays;
import java.util.List;

public class MarkDownUtil {

    /**
     * 转化md到html格式
     * @param markDownString
     * @return
     */
    public static String convertMdToHtml(String markDownString){
        if(StringUtil.isNullOrEmpty(markDownString)){
            return "";
        }
        List<Extension> extensions= Arrays.asList(TablesExtension.create());
        Parser parser=Parser.builder().extensions(extensions).build();
        Node document= parser.parse(markDownString);
        HtmlRenderer htmlRenderer=HtmlRenderer.builder().extensions(extensions).build();
        String content=htmlRenderer.render(document);
        return  content;
    }
}
