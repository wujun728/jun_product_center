package com.projectm.common;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.projectm.task.domain.TaskStagesTemplete;
import org.apache.commons.collections.MapUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Constant {
    /**
     * 与分页相关
     */
    public static String PAGE_NUM="page";//第几页
    public static String PAGE_TOTAL="total";//总条数
    public static String PAGE_DATAS="list";//数据清单
    public static String PAGE_SIZE="pageSize";//每页显示条数据

    public static String CURRENT_USER="current_user";//当前登录用户
    public static String NODES = "nodes";

    public static List<TaskStagesTemplete> getDefaultTaskStageTemplate(){
        TaskStagesTemplete ts1=new TaskStagesTemplete();
        TaskStagesTemplete ts2=new TaskStagesTemplete();
        TaskStagesTemplete ts3=new TaskStagesTemplete();
        ts1.setName("待处理");ts2.setName("进行中");ts3.setName("已完成");
        return new ArrayList<TaskStagesTemplete>(){{
            add(ts1);add(ts2);add(ts3);
        }};
    }


    /**
     * 創建mybatis-plus分頁
     * @param param
     * @return
     */
    public static IPage createPage(Map param){
        Integer page = MapUtils.getInteger(param,Constant.PAGE_NUM,1);
        Integer pageSize = MapUtils.getInteger(param,Constant.PAGE_SIZE,10);
        IPage<Map> ipage = new Page();
        ipage.setSize(pageSize);
        ipage.setCurrent(page);
        return ipage;
    }
    public static IPage createPage(IPage page,Map param){
        Integer pagei = MapUtils.getInteger(param,Constant.PAGE_NUM,1);
        Integer pageSizei = MapUtils.getInteger(param,Constant.PAGE_SIZE,10);
        page.setSize(pageSizei);
        page.setCurrent(pagei);
        return page;
    }
    /**
     * 創建返回分頁數據的Map
     * @param page
     * @return
     */
    public static Map createPageResultMap(IPage page){
        Map resultData = new HashMap();
        resultData.put(Constant.PAGE_DATAS,page.getRecords());
        resultData.put(Constant.PAGE_TOTAL,page.getTotal());
        resultData.put(Constant.PAGE_NUM,page.getCurrent());
        return resultData;
    }
    public static Map createPageResultMap(List records, Long total, Long current){
        Map resultData = new HashMap();
        resultData.put(Constant.PAGE_DATAS,records);
        resultData.put(Constant.PAGE_TOTAL,total);
        resultData.put(Constant.PAGE_NUM,current);
        return resultData;
    }

}
