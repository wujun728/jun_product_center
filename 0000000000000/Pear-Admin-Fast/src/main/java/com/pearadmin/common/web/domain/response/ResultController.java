package com.pearadmin.common.web.domain.response;

import com.pearadmin.common.web.domain.response.module.ResultTree;
import com.pearadmin.common.web.domain.response.module.ResultTable;
import org.springframework.web.servlet.ModelAndView;
import java.util.Map;

/**
 * Describe: 统一响应 Controller
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
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
    public Result decide(Boolean b){
        return Result.decide(b);
    }

    /**
     * 选择返回
     * */
    public Result decide(Boolean b,String success,String failure){
        return Result.decide(b,success,failure);
    }

    /**
     * 选择返回
     * */
    public Result decide(int result){
        if(result>0){
            return Result.decide(true);
        }else{
            return Result.decide(false);
        }
    }

    /**
     * 选择返回
     * */
    public Result decide(int result,String success,String failure){
        if(result>0){
            return Result.decide(true,success,failure);
        }else{
            return Result.decide(false,success,failure);
        }
    }

    /**
     * 页面跳转
     * */
    public ModelAndView jumpPage(String path){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(path);
        return modelAndView;
    }

    /**
     * 带参数的页面跳转
     * */
    public ModelAndView jumpPage(String path, Map<String,?> params){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(path);
        modelAndView.addAllObjects(params);
        return modelAndView;
    }

    /**
     * Describe: 返回 Tree 数据
     * Param data
     * Return Tree数据
     * */
    protected  static ResultTree dataTree(Object data){
        ResultTree resuTree = new ResultTree();
        resuTree.setData(data);
        return resuTree;
    }

    /**
     * Describe: 返回数据表格数据 分页
     * Param data
     * Return 表格分页数据
     * */
    protected  static ResultTable pageTable(Object data, long count){
        return ResultTable.pageTable(count,data);
    }

    /**
     * Describe: 返回数据表格数据
     * Param data
     * Return 表格分页数据
     * */
    protected  static ResultTable dataTable(Object data){
        return ResultTable.dataTable(data);
    }

    /**
     * Describe: 返回树状表格数据 分页
     * Param data
     * Return 表格分页数据
     * */
    protected  static ResultTable treeTable(Object data){
        return ResultTable.dataTable(data);
    }

}
