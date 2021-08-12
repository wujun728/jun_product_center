package com.pearadmin.pro.common.web.domain;

/**
 * Ajax 响 应 处 理
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/03/27
 * */
public class ResultController {

    /**
     * 成功操作
     * */
    public Result success(){
        return Result.success();
    }

    /**
     * 成功操作
     * */
    public Result success(String msg){
        return Result.success(msg);
    }

    /**
     * 成功操作
     * */
    public Result success(Object data){
        return Result.success(data);
    }

    /**
     * 成功操作
     * */
    public Result success(String msg,Object data){
        return Result.success(msg,data);
    }

    /**
     * 成功操作
     * */
    public Result success(int code,String message,Object data){
        return Result.success(code,message,data);
    }

    /**
     * 失败操作
     * */
    public Result failure(){
        return Result.failure();
    }

    /**
     * 失败操作
     * */
    public Result failure(String msg){
        return Result.failure(msg);
    }

    /**
     * 失败操作
     * */
    public Result failure(String msg,Object data){
        return Result.failure(msg,data);
    }

    /**
     * 失败操作
     * */
    public Result failure(int code,String msg,Object data){
        return Result.failure(code,msg,data);
    }

    /**
     * 选择返回
     * */
    public Result auto(Boolean b){
        return Result.auto(b);
    }

    /**
     * 选择返回
     * */
    public Result auto(Boolean b,String success,String failure){
        return Result.auto(b,success,failure);
    }

}
