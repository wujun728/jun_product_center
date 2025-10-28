package com.jun.plugin.qixing.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import cn.hutool.core.map.MapUtil;
import com.jun.plugin.common.service.RedisService;
import com.jun.plugin.common.utils.DataResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson2.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jun.plugin.qixing.entity.BizCommonEntity;
import com.jun.plugin.qixing.entity.PjProjectEntity;
import com.jun.plugin.qixing.mapper.BizCommonMapper;
import com.jun.plugin.qixing.service.BizCommonService;
import com.jun.plugin.qixing.service.PjProjectMemberService;
import com.jun.plugin.qixing.service.PjProjectService;
import com.jun.plugin.qixing.service.SysDictService;
import com.jun.plugin.common.aop.annotation.DataScope;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;



/**
 * 公共信息
 *
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-10-27 11:17:30
 */
@Controller
@RequestMapping("/")
@Slf4j
public class BizCommonController {
	
    @Resource
    private RedisService redisService;

    @Autowired
    private BizCommonService bizCommonService;

    @Autowired
    private BizCommonMapper bizCommonMapper;
    
	@Autowired
	private PrimaryKeyService primaryKeyService;
	
	
	@Autowired
    private PjProjectMemberService pjProjectMemberService;
	
    @Autowired
    private PjProjectService pjProjectService;
	
    @Autowired
    private SysDictService sysDictService;
    
    /**
    * 跳转到页面
    */
    @GetMapping("/index/bizCommon")
    public String bizCommon() {
        return "bizcommon/list";
    }
    
    /**
     * 跳转到页面
     * http://qixing.vip321.vip/public/view/pjProject/add/1461527612906463233
     * http://qixing.vip321.vip/public/view/pjProject/edit/111
     * http://qixing.vip321.vip/public/view/pjcustomer/edit
     * http://qixing.vip321.vip/public/view/pjcustomer/add/
     */
    @GetMapping("/public/view/{processName}/{pageName}")
    public String gotoEditPageURL2(Model model,@PathVariable String processName,@PathVariable String pageName) {
    	return this.gotoEditPageURL(model, processName, pageName, "");
    	
    }
    
    
    //项目明细跳转页面
	@GetMapping("/public/view/{processName}/{pageName}/{id}")
	public String gotoEditPageURL(Model model,@PathVariable String processName,@PathVariable String pageName,@PathVariable String id) {
    	String pageURl =  processName+"/edit";
    	if("pjProject".equalsIgnoreCase(processName)) {
    		pageURl = "pjproject/edit";
    		model.addAttribute("pkCode", primaryKeyService.genCodeAndCheckExists("PRJ"));
    	}
    	if("pjcustomer".equalsIgnoreCase(processName)) {
    		pageURl = "pjcustomer/edit";
    		model.addAttribute("pkCode", primaryKeyService.genCodeAndCheckExists("CUS"));
    		
    		// 预先加载字典数据
            JSONArray customerType = sysDictService.getType("dict_customer_type");
            JSONArray companyType = sysDictService.getType("dict_company_type");
            JSONArray isOldCustomer = sysDictService.getType("dict_is_old_customer");
            
            model.addAttribute("customerType", customerType);
            model.addAttribute("companyType", companyType);
            model.addAttribute("isOldCustomer", isOldCustomer);
    		
    		if("pjedit".equalsIgnoreCase(pageName)) {
    			id = this.bizCommonMapper.queryCustomerIdByProjectId(id);
    		}
    	}
    	if("pjcontract".equalsIgnoreCase(processName)) {
    		pageURl = "pjcontract/edit";
    		model.addAttribute("pkCode", primaryKeyService.genCodeAndCheckExists("CON"));
    		if("pjedit".equalsIgnoreCase(pageName)) {
    			id = this.bizCommonMapper.queryContractIdByProjectId(id);
    		}
    	}
    	if("pjprojectplan".equalsIgnoreCase(processName)) {
    		pageURl = "pjprojectplan/edit";
    		if("pjedit".equalsIgnoreCase(pageName)) {
    			id = this.bizCommonMapper.queryPlanIdByProjectId(id);
    		}
    	}
    	if("pjprojectdraft".equalsIgnoreCase(processName)) {
    		pageURl = "pjprojectdraft/edit";
    		if("pjedit".equalsIgnoreCase(pageName)) {
    			id = this.bizCommonMapper.queryDraftIdByProjectId(id);
    		}
    	}
    	if("pjprojectreport".equalsIgnoreCase(processName)) {
    		pageURl = "pjprojectreport/edit";
    		if("pjedit".equalsIgnoreCase(pageName)) {
    			id = this.bizCommonMapper.queryReportIdByProjectId(id);
    		}
    	}
    	if("pjprojectinvoice".equalsIgnoreCase(processName)) {
    		pageURl = "pjprojectinvoice/edit";
    		if("pjedit".equalsIgnoreCase(pageName)) {
    			id = this.bizCommonMapper.queryInvoiceReportIdByProjectId(id);
    		}
    	}
    	
    	if("pjprojectappraise".equalsIgnoreCase(processName)) {
    		pageURl = "pjprojectappraise/edit";
    	}
    	if("pjprojectborrow".equalsIgnoreCase(processName)) {
    		pageURl = "pjprojectborrow/edit";
    	}
    	if("pjprojectdaily".equalsIgnoreCase(processName)) {
    		pageURl = "pjprojectdaily/edit";
    	}
    	
    	
    	if("pjprojectmember".equalsIgnoreCase(processName)) {
    		pageURl = "pjprojectmember/edit";
    	}
    	if("pjprojectprodesstask".equalsIgnoreCase(processName)) {
    		pageURl = "pjprojectprodesstask/edit";
    	}
    	if("pjprojectrecheck".equalsIgnoreCase(processName)) {
    		pageURl = "pjprojectrecheck/edit";
    	}
    	
    	if("pjprojectreportnumber".equalsIgnoreCase(processName)) {
    		pageURl = "pjprojectreportnumber/edit";
    	}
    	if("daily".equalsIgnoreCase(processName)) {
    		PjProjectEntity one = pjProjectService.getById(id);
    		model.addAttribute("flagType", "multiview");
    		model.addAttribute("pname", one.getProjectName());
    		pageURl = "pjprojectdaily/viewList";
    	}
    	if(id!=null && id.contains(",")) {
    		id=id.split(",")[0];
    	}
    	model.addAttribute("flagType", pageName);
    	model.addAttribute("id", id);
    	return pageURl;
    }
	
	

	/**
     * 跳转到页面
     * http://qixing.vip321.vip/public/flow/task/bizContent/view/{processName}/1461527612906463233
     */
    @GetMapping("/public/flow/task/bizContent/view/{processName}/{id}")
    public String gotoPageDetailURL(Model model,@PathVariable String processName,@PathVariable String id) {
    	String pageURl = "";
    	//<!-- 流程flow-mark001,新增流程,指定流程的类型 -->
//    	List<Map<String, String>> list = Workflows.getList(); 
//    	for(Map<String, String> item : list) {
//    		if(processName.equalsIgnoreCase(item.get("processName"))) {
//    			pageURl = item.get("pageURl");
//    		}
//    	}
    	model.addAttribute("flagType", "view");
    	model.addAttribute("showType", "0");
//    	if("customer".equalsIgnoreCase(processName)) {
//    		String customerId = this.bizCommonMapper.queryCustomerIdByProjectId(id);
//    		model.addAttribute("flagType", "multiview");
//    		pageURl = "pjprojectmember/viewList";
//    	}
    	if("project".equalsIgnoreCase(processName)) {
    		//model.addAttribute("flagType", "multiview");
    		pageURl = "pjproject/edit";
    	}else
    	if("daily".equalsIgnoreCase(processName)) {
    		PjProjectEntity one = pjProjectService.getById(id);
    		model.addAttribute("flagType", "multiview");
    		//model.addAttribute("pname", one.getProjectName());
    		pageURl = "pjprojectdaily/viewList";
    	}else
    	if("member".equalsIgnoreCase(processName)) {
    		model.addAttribute("flagType", "multiview");
    		pageURl = "pjprojectmember/viewList";
    	}else
    	if("projectFiles".equalsIgnoreCase(processName)) {
    		model.addAttribute("flagType", "multiview");
    		pageURl = "sysfiles/listByProject";
    	}else
    	if("contract".equalsIgnoreCase(processName)) {
    		//model.addAttribute("flagType", "multiview");
    		pageURl = "pjcontract/list";
    	}else
    	if("customer".equalsIgnoreCase(processName)) {
    		pageURl = "pjcustomer/edit";
    	}else
    	if("plan".equalsIgnoreCase(processName)) {
    		//model.addAttribute("flagType", "multiview");
    		pageURl = "pjprojectplan/list";
    	}else
    	if("draft".equalsIgnoreCase(processName)) {
    		//model.addAttribute("flagType", "multiview");
    		pageURl = "pjprojectdraft/list";
    	}else
    	if("recheck".equalsIgnoreCase(processName)) {
    		//model.addAttribute("flagType", "multiview");
    		pageURl = "pjprojectreport/list";
    	}else
    	if("invoice".equalsIgnoreCase(processName)) {
    		//model.addAttribute("flagType", "multiview");
    		pageURl = "pjprojectinvoice/list";
    	}
//    	@todo  需要处理多条记录的问题，wujun
    	if(id.contains(",")) {
    		id=id.split(",")[0];
//    		model.addAttribute("showType", "1");
    	}
    	System.err.println(processName);
    	System.err.println(pageURl);
    	model.addAttribute("id", id);
    	return pageURl;
    }
    
    /**
     * http://qixing.vip321.vip/public/listBySelect/getType/dict_bank
     * @param name
     * @return
     */
    @ApiOperation(value = "查询下拉框数据")
    @RequestMapping("/public/listBySelect/getType/{name}")
    @ResponseBody
    public DataResult findListBySelect(@PathVariable String name){
        return DataResult.success(/*sysDictService.getType(name)*/);
    }

    @ApiOperation(value = "新增")
    @PostMapping("bizCommon/add")
    //@RequiresPermissions("bizCommon:add")
    @ResponseBody
    public DataResult add(@RequestBody BizCommonEntity bizCommon){
        bizCommonService.save(bizCommon);
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("bizCommon/delete")
    //@RequiresPermissions("bizCommon:delete")
    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        bizCommonService.removeByIds(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("bizCommon/update")
    //@RequiresPermissions("bizCommon:update")
    @ResponseBody
    public DataResult update(@RequestBody BizCommonEntity bizCommon){
        bizCommonService.updateById(bizCommon);
        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("bizCommon/listByPage")
    //@RequiresPermissions("bizCommon:list")
    @ResponseBody
    @DataScope
    public DataResult findListByPage(@RequestBody BizCommonEntity bizCommon){
        Page page = new Page(bizCommon.getPage(), bizCommon.getLimit());
        LambdaQueryWrapper<BizCommonEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        //queryWrapper.eq(BizCommonEntity::getId, bizCommon.getId());
        IPage<BizCommonEntity> iPage = bizCommonService.page(page, queryWrapper);
        return DataResult.success(iPage);
    }
    
    @ApiOperation(value = "查询单条数据")
    @PostMapping("bizCommon/findOne")
    @ResponseBody
    public DataResult findOne(@RequestBody BizCommonEntity bizCommon){
    	LambdaQueryWrapper<BizCommonEntity> queryWrapper = Wrappers.lambdaQuery();
    	//查询条件示例
    	//queryWrapper.eq(BizCommonEntity::getId, bizCommon.getId());
    	//BizCommonEntity one = bizCommonService.getOne(queryWrapper);
    	BizCommonEntity one = bizCommonService.getById(bizCommon.getId());
    	return DataResult.success(one);
    }

    @ApiOperation(value = "查询下拉框数据")
    @PostMapping("bizCommon/listBySelect")
    @ResponseBody
    public DataResult findListBySelect(@RequestBody BizCommonEntity bizCommon){
        Page page = new Page(bizCommon.getPage(), bizCommon.getLimit());
        LambdaQueryWrapper<BizCommonEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        //queryWrapper.eq(BizCommonEntity::getId, bizCommon.getId());
        IPage<BizCommonEntity> iPage = bizCommonService.page(page, queryWrapper);
        log.info("\n this.bizCommonMapper.selectCountUser()="+this.bizCommonMapper.selectCountUser());
        return DataResult.success(iPage);
    }
    
    @ApiOperation(value = "查询所有项目清单")
    @RequestMapping("public/getAllProjectList")
    @ResponseBody
    public DataResult getAllProjectList(){
    	List<Map> datas = this.bizCommonMapper.getAllProjectList();
    	log.info("\n this.bizCommonMapper.selectCountUser()="+JSONArray.toJSONString(datas));
    	return DataResult.success(datas);
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@ApiOperation(value = "查询项目明细ByID")
    @RequestMapping("public/getAllProjectDetail/{projectID}")
    @ResponseBody
    public Map getProjectDetailByPathID(@PathVariable String projectID){
    	Map item = this.bizCommonMapper.getAllProjectDetailListByCode(projectID);
    	Map node = Maps.newHashMap();
		Map base = Maps.newHashMap();
		base.put("name", MapUtil.getStr(item,"name",""));
		base.put("url", MapUtil.getStr(item,"url",""));
		base.put("desc", MapUtil.getStr(item,"desc",""));
		base.put("mobile",Boolean.valueOf(String.valueOf(item.get("mobile"))));

		Map schedule = Maps.newHashMap();
		schedule.put("status",MapUtil.getStr(item,"status","未启动"));
		schedule.put("actualStartTime", MapUtil.getStr(item,"actualStartTime",""));
		schedule.put("actualEndTime",MapUtil.getStr(item,"actualEndTime",""));
		schedule.put("estimatedStartTime",MapUtil.getStr(item,"estimatedStartTime",""));
		schedule.put("estimatedEndTime",MapUtil.getStr(item,"estimatedEndTime",""));
		schedule.put("process",Integer.valueOf(String.valueOf(item.get("process"))));
		schedule.put("delay",Boolean.valueOf(String.valueOf(item.get("delay"))));
		schedule.put("money",String.valueOf(item.get("money")));
		schedule.put("technology",String.valueOf(item.get("technology")));
		schedule.put("technology",MapUtil.getStr(item,"technology","").split(","));

		Map resources = Maps.newHashMap();
		resources.put("demander",item.get("demander"));
		resources.put("type",item.get("type"));
		resources.put("manager",item.get("manager"));
		resources.put("charge",item.get("charge"));
		List list = this.bizCommonMapper.getProjectMemberList(projectID);
		resources.put("affiliate",list!=null?list.toArray():new String[]{""});
		Map bug = Maps.newHashMap();
		bug.put("total",Integer.valueOf(String.valueOf(item.get("total"))));
		bug.put("resolved",Integer.valueOf(String.valueOf(item.get("resolved"))));
		bug.put("unsolved",Integer.valueOf(String.valueOf(item.get("unsolved"))));

		Map others = Maps.newHashMap();
		others.put("remark",String.valueOf(item.get("remark")));
		node.put("others",others);

		node.put("bug",bug);
		node.put("resources",resources);
		node.put("schedule",schedule);
		node.put("base",base);
    	log.info("\n this.bizCommonMapper.selectCountUser()="+JSONArray.toJSONString(node));
    	return node;
    }
    
    @SuppressWarnings("unchecked")
	@ApiOperation(value = "查询项目明细")
    @RequestMapping("public/getAllProjectDetail")
    @ResponseBody
    public List getProjectDetail(){
//    	redisService.
    	List resutls = new ArrayList();
    	List<Map> datas = this.bizCommonMapper.getAllProjectDetailList();
    	datas.forEach(item->{
    		List node = Lists.newArrayList();
    		Map base = Maps.newHashMap();
    		base.put("name",item.get("name"));
    		base.put("url",item.get("url"));
    		base.put("desc",item.get("desc"));
    		base.put("mobile",item.get("mobile"));
    		Map schedule = Maps.newHashMap();
    		schedule.put("status",item.get("status"));
    		schedule.put("actualStartTime",item.get("actualStartTime"));
    		schedule.put("actualEndTime",item.get("actualEndTime"));
    		schedule.put("estimatedStartTime",item.get("estimatedStartTime"));
    		schedule.put("estimatedEndTime",item.get("estimatedEndTime"));
    		schedule.put("process",item.get("process"));
    		schedule.put("delay",item.get("delay"));
    		schedule.put("technology","[\"a\",\"b\"]");
    		Map resources = Maps.newHashMap();
    		resources.put("demander",item.get("demander"));
    		resources.put("type",item.get("type"));
    		resources.put("manager",item.get("manager"));
    		resources.put("charge",item.get("charge"));
    		resources.put("affiliate",item.get("affiliate"));
    		Map bug = Maps.newHashMap();
    		bug.put("total",item.get("total"));
    		bug.put("resolved",item.get("resolved"));
    		bug.put("unsolved",item.get("unsolved"));
    		Map others = Maps.newHashMap();
    		others.put("remark",item.get("remark"));
    		node.add(base);
    		node.add(base);
    		node.add(base);
    		node.add(base);
    		resutls.add(node);
    	});
    	log.info("\n this.bizCommonMapper.selectCountUser()="+JSONArray.toJSONString(datas));
    	return datas;
    }
    

	@ApiOperation(value = "生成各种系统编码：项目编码、客户编码、合同编码等")
    @RequestMapping("public/getCodeByType/{type}")
    @ResponseBody
    public String getCodeByType(@PathVariable String type){
    	String code = "";
    	if(StringUtils.isEmpty(type)) {
    		type = "COM";
    	}
    	try {
			code = primaryKeyService.genCodeAndCheckExists(type);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	log.info("\n this.bizCommonMapper.getCodeByType()="+JSONArray.toJSONString(code));
    	return code.toUpperCase();
    }
	
	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "查询客户名下的项目进度及各种状态")
	@RequestMapping("public/getCustomerProjects")
	@ResponseBody
	public DataResult getCustomerProjects(){
		List<Map> datas = this.bizCommonMapper.getCustomerProjects();
    	return DataResult.success(datas);
	}
	
	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "查询项目进度及各种状态")
	@RequestMapping("public/getProjectState")
	@ResponseBody
	public DataResult getProjectState(){
		List<Map> datas = this.bizCommonMapper.getProjectState();
		return DataResult.success(datas);
	}
	
    @SuppressWarnings({ "rawtypes" })
	@ApiOperation(value = "统计客户项目数")
    @RequestMapping("public/getAllProjectCount")
    @ResponseBody
    public List getAllProjectCount(){
    	List<Map> datas = this.bizCommonMapper.getAllProjectCount();
    	return datas;
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @ApiOperation(value = "查询当前首页发布的公共信息前五条")
    @RequestMapping("public/getPageIndexInfoList")
    @ResponseBody
    public List getPageIndexInfoList(){
    	List datas = Lists.newArrayList();
    	datas.add(this.bizCommonMapper.getNotesInfoList());
    	datas.add(this.bizCommonMapper.getLawInfoList());
    	datas.add(this.bizCommonMapper.getLearnInfoList());
    	return datas;
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@ApiOperation(value = "查询客户编辑权限用户项目明细ByID")
    @RequestMapping("public/getCustomerEditorList/{id}")
    @ResponseBody
    public Map getCustomerEditorList(@PathVariable String id){
    	Map item = Maps.newHashMap();
    	List<Map> data1 = this.bizCommonMapper.getEditorInfoList(id);
    	for(Map m : data1) {
    		if(m.get("value").equals("admin")) {
    			m.put("disabled", true);
    		}
    	}
    	item.put("data1", data1);
//    	item.put("data1", this.bizCommonMapper.getEditorInfoList(id));
		String editor = this.bizCommonMapper.getEditorInfoList2(id);
		String[] editors =  editor.split(",");
    	List<String> data = Lists.newArrayList();
		if(editors!=null){
			data = Arrays.asList(editors);
		}
    	if(CollectionUtils.isEmpty(data)) {
    		item.put("data2",data.add("admin"));
    	}
    	item.put("data2", data.toArray());
		return item;
    }
    
    @ApiOperation(value = "查询客户编辑权限用户项目明细ByID")
    @RequestMapping("public/saveCustomerEditorList/{id}")
    @ResponseBody
    public DataResult saveCustomerEditorList(@PathVariable String id,@RequestParam String data){
    	log.info(data);
    	this.bizCommonMapper.saveCustomerEditorList(id,data);
    	return DataResult.success();
    }

    @ApiOperation(value = "查询客户编辑权限用户项目明细ByID")
    @RequestMapping("public/updateReportNumber/{id}")
    @ResponseBody
    public DataResult updateReportNumber(@PathVariable String id,@RequestParam(name ="reportNumber" ) String reportNumber){
    	this.bizCommonMapper.updateReportNumber(id,reportNumber);
    	this.bizCommonMapper.updateReportNumber2(id,reportNumber);
    	return DataResult.success();
    }

}
