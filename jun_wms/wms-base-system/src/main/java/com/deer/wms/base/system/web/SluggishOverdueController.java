package com.deer.wms.base.system.web;

import com.deer.wms.base.system.model.*;
import com.deer.wms.base.system.model.box.BoxItem;
import com.deer.wms.base.system.model.box.BoxItemCriteria;
import com.deer.wms.base.system.model.box.BoxItemDto;
import com.deer.wms.base.system.model.task.TaskInfo;
import com.deer.wms.base.system.model.task.TaskInfoCriteria;
import com.deer.wms.base.system.service.SluggishOverdueService;
import com.deer.wms.base.system.service.WorkerOrderIssueTimeService;
import com.deer.wms.base.system.service.box.IBoxItemService;
import com.deer.wms.base.system.service.task.ITaskInfoService;
import com.deer.wms.common.core.domain.AjaxResult;
import com.deer.wms.common.utils.DateUtils;
import com.deer.wms.common.utils.poi.ExcelUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.deer.wms.common.core.result.Result;
import com.deer.wms.common.core.result.ResultGenerator;
import org.springframework.stereotype.Controller;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.ui.ModelMap;
import com.deer.wms.common.core.controller.BaseController;
import com.deer.wms.common.core.page.TableDataInfo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
* Created by  on 2019/11/21.
*/
@Controller
@RequestMapping("/sluggishOverdue")
public class SluggishOverdueController  extends BaseController{

    private String prefix = "in/boxItem";

    @Autowired
    private SluggishOverdueService sluggishOverdueService;
    @Autowired
    private WorkerOrderIssueTimeService workerOrderIssueTimeService;
    @Autowired
    private IBoxItemService boxItemService;
    @Autowired
    private ITaskInfoService taskInfoService;

    /**
    * 详情
    */
    @GetMapping("/detail")
    public String detail()
    {
        return prefix + "/detail";
    }

    @RequiresPermissions("sluggishOverdue:view")
    @GetMapping()
    public String sluggishOverdue()
    {
        return prefix + "/sluggishOverdue";
    }

    /**
    * 修改
    */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
    SluggishOverdue sluggishOverdue = sluggishOverdueService.findById(id);
        mmap.put("sluggishOverdue", sluggishOverdue);
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


    @PostMapping
    @ResponseBody
    public Result add(@RequestBody SluggishOverdue sluggishOverdue) {
        sluggishOverdueService.save(sluggishOverdue);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public Result delete(@PathVariable Integer id) {
        sluggishOverdueService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    @ResponseBody
    public Result update(@RequestBody SluggishOverdue sluggishOverdue) {
        sluggishOverdueService.update(sluggishOverdue);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Result detail(@PathVariable Integer id) {
        SluggishOverdue sluggishOverdue = sluggishOverdueService.findById(id);
        return ResultGenerator.genSuccessResult(sluggishOverdue);
    }

    @PostMapping("/list")
    @ResponseBody
    public  TableDataInfo list(SluggishOverdueCriteria criteria) {
        PageHelper.startPage(criteria.getPageNum(), criteria.getPageSize());
        List<SluggishOverdue> list = sluggishOverdueService.findAll();
        return getDataTable(list);
    }

    @PostMapping("/findList")
    @ResponseBody
    public  TableDataInfo findList(SluggishOverdueCriteria criteria) {
        PageHelper.startPage(criteria.getPageNum(), criteria.getPageSize());
        List<SluggishOverdueDto> list = sluggishOverdueService.findList(criteria);
        return getDataTable(list);
    }

    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SluggishOverdueCriteria sluggishOverdueCriteria) {
        if(sluggishOverdueCriteria.getSluggishExportParam() == 0){
            return error("请选择条件！");
        }
        ExcelUtil<SluggishOverdueDto> util = new ExcelUtil<SluggishOverdueDto>(SluggishOverdueDto.class);
        List<SluggishOverdueDto> sluggishOverdueDtos= new ArrayList<>();
        try {
            BoxItemCriteria boxItemCriteria = new BoxItemCriteria();
            boxItemCriteria.setSluggishExportParam(sluggishOverdueCriteria.getSluggishExportParam());
            List<BoxItemDto> boxItemDtos = boxItemService.findSluggishOverdue(boxItemCriteria);

            SluggishOverdueDto sluggishOverdueDto = null;
            if(boxItemDtos.size()>0){
                //上次申报数量
                TaskInfoCriteria taskInfoCriteria = new TaskInfoCriteria();
                List<Integer> lists = new ArrayList<>();
                lists.add(TaskTypeConstant.CELL_TO_OPERATOR_FLOOR);
                lists.add(TaskTypeConstant.CELL_TO_PAPER_COUNTERS);
                taskInfoCriteria.setTypes(lists);
                for(BoxItemDto boxItemDto : boxItemDtos){
                    sluggishOverdueDto = new SluggishOverdueDto();
                    sluggishOverdueCriteria.setItemCode(boxItemDto.getItemCode());
                    sluggishOverdueCriteria.setBatch(boxItemDto.getBatch());
                    sluggishOverdueCriteria.setExp(boxItemDto.getExp());
                    sluggishOverdueCriteria.setSluggishExportParam(null);
                    List<SluggishOverdue> sluggishOverdueOne = sluggishOverdueService.findSluggishByParam(sluggishOverdueCriteria);
                    taskInfoCriteria.setItemCode(boxItemDto.getItemCode());
                    taskInfoCriteria.setBatch(boxItemDto.getBatch());
                    taskInfoCriteria.setExp(boxItemDto.getExp());
                    TaskInfo taskInfo = taskInfoService.findByItemCodeAndBatchAndExp(taskInfoCriteria);

                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date date1 = simpleDateFormat.parse(boxItemDto.getPd());
                    Date date2 = simpleDateFormat.parse(boxItemDto.getExp());
                    Date date3 = new Date();
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(date1);
                    long time1 = cal.getTimeInMillis();
                    cal.setTime(date2);
                    long time2 = cal.getTimeInMillis();
                    cal.setTime(date3);
                    long time3 = cal.getTimeInMillis();
                    int saveDay=(int)((time2-time1)/(1000*3600*24));
                    int surplusDay = (int)((time2-time3)/(1000*3600*24));

                    sluggishOverdueDto.setItemCode(boxItemDto.getItemCode());
                    sluggishOverdueDto.setItemName(boxItemDto.getItemName());
                    sluggishOverdueDto.setUnit(boxItemDto.getUnit());
                    sluggishOverdueDto.setBatch(boxItemDto.getBatch());
                    sluggishOverdueDto.setProductionDate(boxItemDto.getPd());
                    sluggishOverdueDto.setExp(boxItemDto.getExp());
                    sluggishOverdueDto.setQuantity(boxItemDto.getQuantity());
                    sluggishOverdueDto.setSaveDay(saveDay);
                    sluggishOverdueDto.setSurplusValidDay(surplusDay>0?surplusDay:0);
                    sluggishOverdueDto.setLastTime(boxItemDto.getInTime());
                    if(taskInfo != null){
                        sluggishOverdueDto.setLastOutTime(taskInfo.getTaskStartTime());
                    }
                    sluggishOverdueDto.setSluggishType(sluggishOverdueCriteria.getSluggishExportParam());
                    if(sluggishOverdueOne.size() == 1){
                        sluggishOverdueDto.setLastQuantity(sluggishOverdueOne.get(0).getQuantity());
                        sluggishOverdueDto.setFirstDeclareQuantity(sluggishOverdueOne.get(0).getQuantity());
                        sluggishOverdueDto.setFirstDeclareTime(sluggishOverdueOne.get(0).getCreateTime());
                    }else if(sluggishOverdueOne.size()>1){
                        sluggishOverdueDto.setLastQuantity(sluggishOverdueOne.get(sluggishOverdueOne.size()-1).getQuantity());
                        sluggishOverdueDto.setFirstDeclareQuantity(sluggishOverdueOne.get(0).getQuantity());
                        sluggishOverdueDto.setFirstDeclareTime(sluggishOverdueOne.get(0).getCreateTime());
                    }
                    sluggishOverdueDto.setConsumeQuantity(
                            (sluggishOverdueDto.getQuantity()==null?0:sluggishOverdueDto.getQuantity()) - boxItemDto.getQuantity());
                    sluggishOverdueDtos.add(sluggishOverdueDto);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
        return util.exportExcel(sluggishOverdueDtos, "呆滞过期报表");
    }

}
