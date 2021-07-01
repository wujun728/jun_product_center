package com.projectm.system.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.LambdaQueryChainWrapper;
import com.framework.common.AjaxResult;
import com.framework.common.exception.CustomException;
import com.framework.common.utils.StringUtils;
import com.framework.security.util.UserUtil;
import com.projectm.common.CommUtils;
import com.projectm.common.Constant;
import com.projectm.common.DateUtil;
import com.projectm.system.domain.Notify;
import com.projectm.system.service.NotifyService;
import com.projectm.web.BaseController;
import org.apache.commons.collections.MapUtils;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/project")
public class NotifyController   extends BaseController {
    @Autowired
    private NotifyService notifyService;

    @PostMapping("/notify/setReadied")
    @ResponseBody
    public AjaxResult setReadied(@RequestParam Map<String,Object> mmap)  throws Exception
    {
        String ids = MapUtils.getString(mmap,"ids");
        if(StringUtils.isEmpty(ids)){
            throw new CustomException("请选择消息！");
        }
        JSONArray jsonArray = JSON.parseArray(ids);
        if (StringUtils.isNotEmpty(jsonArray)) {
            List<Integer> idList = new ArrayList<>();
            for(Object obj:jsonArray){
                idList.add(Integer.parseInt(obj.toString()));
            }
            notifyService.setReadied(idList);
        }
        return AjaxResult.success();
    }
    @PostMapping("/notify/batchDel")
    @ResponseBody
    public AjaxResult batchDel(@RequestParam Map<String,Object> mmap)  throws Exception
    {
        String ids = MapUtils.getString(mmap,"ids");
        if(StringUtils.isEmpty(ids)){
            throw new CustomException("请选择消息！");
        }
        JSONArray jsonArray = JSON.parseArray(ids);
        if (StringUtils.isNotEmpty(jsonArray)) {
            List<Integer> idList = new ArrayList<>();
            for(Object obj:jsonArray){
                idList.add(Integer.parseInt(obj.toString()));
            }
            notifyService.batchDel(idList);
        }
        return AjaxResult.success();
    }

    @PostMapping("/notify/_clearAll")
    @ResponseBody
    public AjaxResult _clearAll()
    {
        notifyService.lambdaUpdate().eq(Notify::getIs_read,0).eq(Notify::getTo,UserUtil.getLoginUser().getUser().getCode())
                .set(Notify::getIs_read,1).set(Notify::getRead_time, DateUtil.getCurrentDateTime()).update();
        return AjaxResult.success();
    }

    @PostMapping("/notify/delete")
    @ResponseBody
    public AjaxResult delete(@RequestParam Map<String,Object> mmap)  throws Exception
    {
        Integer id = MapUtils.getInteger(mmap,"id",-1);
        if(-1 == id){
            throw new CustomException("请选择消息！");
        }
        notifyService.lambdaUpdate().eq(Notify::getId,id).remove();
        return AjaxResult.success();
    }


    /**
     * 项目管理	消息提醒 页面初始化
     * @param mmap
     * @return
     */
    @PostMapping("/notify/index")
    @ResponseBody
    public AjaxResult notify(@RequestParam Map<String,Object> mmap)
    {

        IPage<Notify> page = Constant.createPage(new Page<Notify>(),mmap);
        String title = MapUtils.getString(mmap,"title");
        String date = MapUtils.getString(mmap,"date");

        LambdaQueryChainWrapper<Notify> lambdaQuery = notifyService.lambdaQuery()
        		.eq(Notify::getTo, UserUtil.getLoginUser().getUser().getCode())
                .eq(Notify::getTerminal,"project").orderByDesc(Notify::getCreate_time);
        if(StringUtils.isNotEmpty(title)){
        	lambdaQuery.like(Notify::getTitle,title);
        }
        if(StringUtils.isNotEmpty(date)){
            String [] dates = date.split("~");
            lambdaQuery.between(Notify::getCreate_time,dates[0]+" 00:00:00",dates[1]+" 23:59:59");
         }

        page = lambdaQuery.page(page);

        Map data = new HashMap();
        if(null == page){
            page = new Page<>();
        }
        data.put("list",page.getRecords());
        data.put("total",page.getTotal());
        data.put("page",page.getCurrent());
        return AjaxResult.success(data);
    }

    @PostMapping("/notify/noReads")
    @ResponseBody
    public AjaxResult getNoReads(){
        return AjaxResult.success(notifyService.getNoReads());
    }
}
