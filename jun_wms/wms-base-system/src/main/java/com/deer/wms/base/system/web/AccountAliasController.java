package com.deer.wms.base.system.web;

import com.alibaba.fastjson.JSONArray;
import com.deer.wms.base.system.model.*;
import com.deer.wms.base.system.model.EbsBack;
import com.deer.wms.base.system.service.AccountAliasService;
import com.deer.wms.base.system.service.ServerVisitAddressService;
import com.deer.wms.base.system.service.SubInventoryService;
import com.deer.wms.common.core.domain.AjaxResult;
import com.deer.wms.common.utils.DateUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.deer.wms.common.core.result.Result;
import com.deer.wms.common.core.result.ResultGenerator;
import org.springframework.stereotype.Controller;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.ui.ModelMap;
import com.deer.wms.common.core.controller.BaseController;
import com.deer.wms.common.core.page.TableDataInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
* Created by  on 2019/10/29.
*/
@Controller
@RequestMapping("/accountAlias")
public class AccountAliasController  extends BaseController{

    private String prefix = "system/accountAlias";

    @Autowired
    private AccountAliasService accountAliasService;
    @Autowired
    private ServerVisitAddressService serverVisitAddressService;
    @Autowired
    private SubInventoryService subInventoryService;


    /**
    * 详情
    */
    @GetMapping("/detail")
    public String detail()
    {
        return prefix + "/detail";
    }

    @RequiresPermissions("accountAlias:view")
    @GetMapping()
    public String accountAlias()
    {
        return prefix + "/accountAlias";
    }

    /**
    * 修改
    */
    @RequiresPermissions("system:accountAlias:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
    AccountAlias accountAlias = accountAliasService.findById(id);
        mmap.put("accountAlias", accountAlias);
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


    @PostMapping("/addSave")
    @ResponseBody
    public AjaxResult add(AccountAlias accountAlias) {
        accountAliasService.save(accountAlias);
        return toAjax(true);
    }

    @RequiresPermissions("system:accountAlias:remove")
    @PostMapping("/remove/{id}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("id") Integer id) {
        accountAliasService.deleteById(id);
        return toAjax(true);
    }

    @PostMapping("/update")
    @ResponseBody
    public AjaxResult update(AccountAlias accountAlias) {
        accountAliasService.update(accountAlias);
        return toAjax(true);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Result detail(@PathVariable Integer id) {
        AccountAlias accountAlias = accountAliasService.findById(id);
        return ResultGenerator.genSuccessResult(accountAlias);
    }

    @PostMapping("/list")
    @ResponseBody
    public  TableDataInfo list(AccountAliasCriteria criteria) {
        PageHelper.startPage(criteria.getPageNum(), criteria.getPageSize());
        List<AccountAlias> list = accountAliasService.findAll();
        return getDataTable(list);
    }

    @PostMapping("/findList")
    @ResponseBody
    public  TableDataInfo findList(AccountAliasCriteria criteria) {
        startPage();
        List<AccountAlias> list = accountAliasService.findList(criteria);
        return getDataTable(list);
    }

    @GetMapping("/synchronizedAccountAlias")
    @ResponseBody
    public AjaxResult synchronizedAccountAlias() {
        List<Map<String, String>> lists = new ArrayList<>();
//        Map<String,String> map = new HashMap<>();
        SubInventory subInventory = subInventoryService.findById(1);
//        map.put("organizationId",subInventory.getOrganizationId().toString());
        EbsBack entityStr = serverVisitAddressService.requestServerCode("",TaskTypeConstant.GET_EBS_ACCOUNT_NAME,
                TaskTypeConstant.QUERY,subInventory.getOrganizationId(), lists);
        if(entityStr.getSuccess().equals("true")&&entityStr.getTotal()>0){
            JSONArray jsonArrays = JSONArray.parseArray(entityStr.getRows());
            for (int i = 0; i < jsonArrays.size(); i++) {
                com.alibaba.fastjson.JSONObject jsonObject = jsonArrays.getJSONObject(i);
                Integer organizationId = jsonObject.get("organizationId") != null ? Integer.parseInt(jsonObject.get("organizationId").toString().trim()) : null;
                Integer dispositionId = jsonObject.get("dispositionId") != null ? Integer.parseInt(jsonObject.get("dispositionId").toString().trim()) : null;
                String segment1 = jsonObject.get("segment1").toString().trim();
                String description = jsonObject.get("description").toString().trim();
                String effectiveDate = jsonObject.get("effectiveDate").toString().trim();
                String disableDate = jsonObject.get("disableDate")!=null?jsonObject.get("disableDate").toString().trim():null;
                String enabledFlag = jsonObject.get("enabledFlag").toString().trim();
                AccountAlias accountAlias = accountAliasService.findByDispositionId(dispositionId);
                if(accountAlias != null){
                    accountAlias.setOrganizationId(organizationId);
                    accountAlias.setAccountAlias(segment1);
                    accountAlias.setDescription(description);
                    accountAlias.setEffectiveDate(effectiveDate);
                    accountAlias.setDisableDate(disableDate);
                    accountAlias.setEnabledFlag(enabledFlag);
                    accountAlias.setUpdateTime(DateUtils.getTime());
                    accountAliasService.update(accountAlias);
                }else{
                    accountAlias = new AccountAlias(segment1,description,dispositionId,effectiveDate,disableDate,enabledFlag,organizationId,DateUtils.getTime());
                    accountAliasService.save(accountAlias);
                }
            }
            return toAjax(true).success("同步成功！");
        }else{
            return toAjax(false).error("同步失败！");
        }
    }

    @PostMapping("/findAccountAliasList")
    @ResponseBody
    public  Result findAccountAliasList(AccountAliasCriteria criteria) {
        List<AccountAlias> list = accountAliasService.findList(criteria);
        return ResultGenerator.genSuccessResult(list);
    }
}
