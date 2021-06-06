package com.company.blog.util;

/**
 * 结果生成工具
 */
public class ResultGeneratorUtil {

    public static Result getDefaultSuccessResult(){
       return new Result(CommonUtil.DEFAULT_SUCCESS_MESSAGE,
               CommonUtil.RESULT_CODE_SUCCESS);
    }

    public static Result getSuccessResult(String message){
        if(StringUtil.isNullOrEmpty(message)){
            return new Result(CommonUtil.DEFAULT_SUCCESS_MESSAGE,
                    CommonUtil.RESULT_CODE_SUCCESS);
        }
        return new Result(message,CommonUtil.RESULT_CODE_SUCCESS);
    }

    public static Result getSuccessResult(Object data){
        var result=new Result(CommonUtil.DEFAULT_SUCCESS_MESSAGE,
                CommonUtil.RESULT_CODE_SUCCESS);
        result.setData(data);
        return result;
    }

    public static  Result getDefaultFailResult(){
       return new Result(CommonUtil.DEFAULT_FAIL_MESSAGE,
                CommonUtil.RESULT_CODE_SERVER_ERROR);
    }

    public static Result getFailResult(String message){
        if(StringUtil.isNullOrEmpty(message)){
            return new Result(CommonUtil.DEFAULT_FAIL_MESSAGE,
                    CommonUtil.RESULT_CODE_SERVER_ERROR);
        }
        return new Result(message,CommonUtil.RESULT_CODE_SERVER_ERROR);
    }

    public static Result getFailResult(String message,int resultCode){
        return new Result(message,resultCode);
    }

    public static Result getFailResult(Object data){
        var result=new Result<>(CommonUtil.DEFAULT_FAIL_MESSAGE,
                CommonUtil.RESULT_CODE_SERVER_ERROR);
        result.setData(data);
        return result;
    }
}
