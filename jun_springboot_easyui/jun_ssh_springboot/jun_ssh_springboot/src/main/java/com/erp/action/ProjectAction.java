package com.erp.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.erp.dto.GridModel;
import com.erp.model.Project;
import com.erp.model.ProjectFollow;
import com.erp.service.ProjectService;
import com.erp.util.Constants;
import com.erp.util.PageUtil;

import lombok.extern.slf4j.Slf4j;
@Controller
@RequestMapping("/project")
@Slf4j
public class ProjectAction extends BaseAction 
{
	private static final long serialVersionUID = -8785609987685604362L;
	
	@Autowired
	private ProjectService projectService;
	
	/**  
	* 函数功能说明 
	* Administrator修改者名字
	* 2013-6-27修改日期
	* 修改内容
	* @Title: findCustomers 
	* @Description: TODO:查询所有客户
	* @param @return
	* @param @throws Exception    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@ResponseBody
	@RequestMapping(value = "/findCustomers")
	public String findCustomers() throws Exception
	{
		OutputJson(projectService.findCustomers());
		return null;
	}
	/**  
	* 函数功能说明 
	* Administrator修改者名字
	* 2013-6-27修改日期
	* 修改内容
	* @Title: findProjectFollowsList 
	* @Description: TODO:查询项目记录
	* @param @return
	* @param @throws Exception    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@ResponseBody
	@RequestMapping(value = "/findProjectFollowsList")
	public String findProjectFollowsList(Project project) throws Exception
	{
		OutputJson(projectService.findProjectFollowsList(project.getProjectId()));
		return null;
	}
	
	/**  
	* 函数功能说明 
	* Administrator修改者名字
	* 2013-6-27修改日期
	* 修改内容
	* @Title: findCustomerList 
	* @Description: TODO:查询所有项目
	* @param @return
	* @param @throws Exception    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@ResponseBody
	@RequestMapping(value = "/findProjectList")
	public String findProjectList() throws Exception
	{
		Map<String, Object> map=new HashMap<String, Object>();
		if (null!=searchValue&&!"".equals(searchValue))
		{
			map.put(searchName, Constants.GET_SQL_LIKE+searchValue+Constants.GET_SQL_LIKE);
		}
		PageUtil pageUtil=new PageUtil(page, rows, searchAnds, searchColumnNames, searchConditions, searchVals);
		GridModel gridModel=new GridModel();
		gridModel.setRows(projectService.findProjectList(map, pageUtil));
		gridModel.setTotal(projectService.getCount(map,pageUtil));
		OutputJson(gridModel);
		return null;
	}
	
	/**  
	* 函数功能说明 TODO:
	* Administrator修改者名字
	* 2013-7-1修改日期
	* 修改内容
	* @Title: findProjectListCombobox 
	* @Description: TODO:查询所有项目下拉框格式
	* @param @return
	* @param @throws Exception    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@ResponseBody
	@RequestMapping(value = "/findProjectListCombobox")
	public String findProjectListCombobox() throws Exception
	{
		OutputJson(projectService.findProjectListCombobox());
		return null;
	}
	/**  
	* 函数功能说明 
	* Administrator修改者名字
	* 2013-6-27修改日期
	* 修改内容
	* @Title: persistenceProject 
	* @Description: TODO:持久化persistenceProject
	* @param @return
	* @param @throws Exception    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@ResponseBody
	@RequestMapping(value = "/persistenceProject")
	public String persistenceProject(Project project) throws Exception
	{
		Map<String, List<ProjectFollow>> map=new HashMap<String, List<ProjectFollow>>();
		if (inserted!=null&&!"".equals(inserted))
		{
			map.put("addList", JSON.parseArray(inserted, ProjectFollow.class));
		}
		if (updated!=null&&!"".equals(updated))
		{
			map.put("updList", JSON.parseArray(updated, ProjectFollow.class));
		}
		if (deleted!=null&&!"".equals(deleted))
		{
			map.put("delList", JSON.parseArray(deleted, ProjectFollow.class));
		}
		OutputJson(getMessage(projectService.persistenceProject(project, map)),Constants.TEXT_TYPE_PLAIN);
		return null;
	}
	
	/**  
	* 函数功能说明 
	* Administrator修改者名字
	* 2013-6-27修改日期
	* 修改内容
	* @Title: delProject 
	* @Description: TODO:删除Project
	* @param @return
	* @param @throws Exception    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@ResponseBody
	@RequestMapping(value = "/delProject")
	public String delProject(Project project) throws Exception
	{
		OutputJson(getMessage(projectService.delProject(project.getProjectId())));
		return null;
	}
	
}
