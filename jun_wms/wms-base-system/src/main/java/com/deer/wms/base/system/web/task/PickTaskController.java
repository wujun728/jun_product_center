package com.deer.wms.base.system.web.task;

import com.alibaba.fastjson.JSON;
import com.deer.wms.base.system.model.box.BoxInfo;
import com.deer.wms.base.system.model.box.BoxInfoDto;
import com.deer.wms.base.system.model.task.PickTask;
import com.deer.wms.base.system.model.task.PickTaskCriteria;
import com.deer.wms.base.system.model.task.PickTaskDto;
import com.deer.wms.base.system.model.task.TaskInfo;
import com.deer.wms.base.system.service.box.BoxInfoService;
import com.deer.wms.base.system.service.task.ITaskInfoService;
import com.deer.wms.base.system.service.task.PickTaskService;
import com.deer.wms.base.system.service.ware.ICellInfoService;
import com.deer.wms.common.core.controller.BaseController;
import com.deer.wms.common.core.page.TableDataInfo;
import com.deer.wms.common.core.result.Result;
import com.deer.wms.common.core.result.ResultGenerator;
import com.deer.wms.common.utils.GuidUtils;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
* Created by guo on 2019/07/23.
*/
@Controller
@RequestMapping("/pickTask")
public class PickTaskController  extends BaseController{

    private String prefix = "out/outRecord";

    @Autowired
    private PickTaskService pickTaskService;
    @Autowired
    private BoxInfoService boxInfoService;
    @Autowired
    private ITaskInfoService taskInfoService;
    @Autowired
    private ICellInfoService cellInfoService;

    /**
     *  根据pickTasks的信息  保存到pick_task表，
     *  再根据每条picktask信息  生成相应的任务 （任务5）
     *
     *
     * @param
     * @return
     */
    @ApiOperation("根据pickTasks的信息  保存到pick_task表,再根据每条picktask信息  生成相应的任务")
    @ResponseBody
    @RequestMapping("/saveTaskinfoForBillOut")
    @Transactional
    public Result saveTaskinfoForBillOut(@RequestBody Map map){

        if(map != null){

            String isTop = (String)map.get("istop");
            List<PickTask> pickTasks = (List<PickTask>)map.get("pickTasks");
            if(pickTasks != null){
                //初始化任务对象
                TaskInfo taskInfo = new TaskInfo();

                for(int i=0;i<pickTasks.size();i++){

                    PickTask pickTask = JSON.parseObject(JSON.toJSONString(pickTasks.get(i)),PickTask.class);
                    if(pickTask.getPickQuantity() != 0){

                        //pickTaskService.save(pickTask);

                        //根据boxItemId生成(保存)任务
                        String boxCode = pickTask.getBoxCode();
                        BoxInfoDto boxInfoDto =  boxInfoService.getBoxInfoDtoByboxItemId(boxInfoService.getBoxInfoByBoxCode(boxCode).getBoxId());
                        Integer billOutDetailId = pickTask.getBillOutDetailId();

                        String shelfName = boxInfoDto.getShelfName();
                        Integer sRow = boxInfoDto.getsRow();
                        Integer sColumn = boxInfoDto.getsColumn();

                        String start = shelfName +":"+ sColumn +":"+ sRow;
                        String newStart = cellInfoService.toStringForWcs(start);

                        String end = "201";
                        taskInfo.setId(null);
                        taskInfo.setTaskId(new GuidUtils().toString());
                        taskInfo.setStartPosition(newStart);
                        taskInfo.setEndPosition(end);
                        taskInfo.setBoxCode(boxInfoDto.getBoxCode());
                        taskInfo.setBillOutDetailId(billOutDetailId);
                        taskInfo.setIsTop(isTop);
                        taskInfo.setType(5);//5-出库任务-将有合适货物的托盘调度到出库口
                        taskInfoService.insertTaskInfo(taskInfo);

                        //设置托盘状态为2，锁定状态
                        BoxInfo boxInfo = boxInfoService.getBoxInfoByBoxCode(boxInfoDto.getBoxCode());
                        boxInfo.setBoxState(2);
                        boxInfoService.update(boxInfo);
                    }
                }
            }
        }
        return ResultGenerator.genSuccessResult();
    }

    /**
    * 详情
    */
    @GetMapping("/detail")
    public String detail()
    {
        return prefix + "/detail";
    }

    @RequiresPermissions("pickTask:view")
    @GetMapping()
    public String pickTask()
    {
        return prefix + "/outRecord";
    }

    /**
    * 修改
    */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
    PickTask pickTask = pickTaskService.findById(id);
        mmap.put("pickTask", pickTask);
        return prefix + "/edit";
    }

    /**
    * 新增
    */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }


    @PostMapping("/save")
    @ResponseBody
    public Result add(@RequestBody PickTask pickTask) {
        pickTaskService.save(pickTask);
        return ResultGenerator.genSuccessResult();
    }

//    @PostMapping("/addList")
//    @ResponseBody
//    public Result addList(@RequestBody PickTaskInsert pickTaskInsert) {
//        List<PickTask> pickTasks = pickTaskInsert.getPickTasks();
//        for(int i = 0;i<pickTasks.size();i++){
//          PickTask pickTask =   pickTasks.get(i);
//            pickTaskService.save(pickTask);
//        }
//
//        return ResultGenerator.genSuccessResult();
//    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public Result delete(@PathVariable Integer id) {
        pickTaskService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    @ResponseBody
    public Result update(@RequestBody PickTask pickTask) {
        pickTaskService.update(pickTask);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Result detail(@PathVariable Integer id) {
        PickTask pickTask = pickTaskService.findById(id);
        return ResultGenerator.genSuccessResult(pickTask);
    }

    @PostMapping("/list")
    @ResponseBody
    public  TableDataInfo list(PickTaskCriteria criteria) {
        PageHelper.startPage(criteria.getPageNum(), criteria.getPageSize());
        List<PickTask> list = pickTaskService.findAll();
        return getDataTable(list);
    }

    @PostMapping("/findList")
    @ResponseBody
    public  TableDataInfo findList(PickTaskCriteria criteria) {
        startPage();
        List<PickTaskDto> list = pickTaskService.findList(criteria);
        return getDataTable(list);
    }

}
