package com.company.blog.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    /**
     * 仅仅匹配数字与字母
     * @param keyword
     * @return
     */
    public static boolean isMatchNumberOrWord(String keyword){
        String regex="^[a-zA-Z0-9\u4E00-\u9FA5]+$";
        Pattern pattern=Pattern.compile(regex);
        Matcher matcher=pattern.matcher(keyword);
        return matcher.matches();
    }

    /**
     * 匹配网络地址
     * @param keyword
     * @return
     */
    public static boolean isMatchNetAddress(String keyword){
        String regex = "^([hH][tT]{2}[pP]:/*|[hH][tT]{2}[pP][sS]:/*|[fF][tT][pP]:/*)" +
                "(([A-Za-z0-9-~]+).)+([A-Za-z0-9-~\\/])+" +
                "(\\?{0,1}(([A-Za-z0-9-~]+\\={0,1})([A-Za-z0-9-~]*)\\&{0,1})*)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher=pattern.matcher(keyword);
        return matcher.matches();
    }

    /**
     * 匹配邮箱正则
     */
    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    /**
     * 判断是否是邮箱
     *
     * @param emailStr
     * @return
     */
    public static boolean isEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }
}
