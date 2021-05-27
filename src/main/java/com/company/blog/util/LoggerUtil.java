package com.company.blog.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerUtil {

    private static Logger getLogger(Class clazz){
        return LoggerFactory.getLogger(clazz);
    }

    public static void error(String errorMsg,Class clazz){
        getLogger(clazz).error(errorMsg);
    }

    public static void error(String errorMsg){
        LoggerFactory.getLogger("错误信息:").info(errorMsg);
    }

    public static void info(String msg,Class clazz){
        getLogger(clazz).info(msg);
    }

    public static void info(String msg){
        LoggerFactory.getLogger("提示信息:").info(msg);
    }
}
