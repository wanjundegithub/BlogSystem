package com.company.blog.util;

public class StringUtil {

    public static boolean isNullOrEmpty(String str){
        if(str==null||str.isEmpty()){
            return true;
        }
        return false;
    }

    public static boolean isEqual(String str1,String str2){
        if(isNullOrEmpty(str1)||isNullOrEmpty(str2)){
            return false;
        }
        return str1.equals(str2);
    }
}
