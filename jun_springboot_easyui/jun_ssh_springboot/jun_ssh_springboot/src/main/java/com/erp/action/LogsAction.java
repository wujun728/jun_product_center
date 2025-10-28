package com.erp.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erp.dto.GridModel;
import com.erp.model.Log;
import com.erp.service.LogsService;
import com.erp.util.Constants;
import com.erp.util.PageUtil;

import lombok.extern.slf4j.Slf4j;
@Controller
@RequestMapping("/logs")
@Slf4j
public class LogsAction extends BaseAction 
{
	private static final long serialVersionUID = 3658084064057123814L;
	@Autowired
	private LogsService logsService;
	
	/**  
	* 函数功能说明 TODO:查询所有日志
	* Administrator修改者名字
	* 2013-6-18修改日期
	* 修改内容
	* @Title: findLogsAllList 
	* @Description: 
	* @param @return
	* @param @throws Exception    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@ResponseBody
	@RequestMapping(value = "/findLogsAllList")
	public String findLogsAllList() throws Exception
	{
		Map<String, Object> map=new HashMap<String, Object>();
		if (null!=searchValue&&!"".equals(searchValue))
		{
			map.put(searchName, Constants.GET_SQL_LIKE+searchValue+Constants.GET_SQL_LIKE);
		}
		PageUtil pageUtil=new PageUtil(page, rows, searchAnds, searchColumnNames, searchConditions, searchVals);
		GridModel gridModel=new GridModel();
		gridModel.setRows(logsService.findLogsAllList(map, pageUtil));
		gridModel.setTotal(logsService.getCount(map,pageUtil));
		OutputJson(gridModel);
		return null;
	}
	
	/**  
	* 函数功能说明 TODO:持久化日志弹窗
	* Administrator修改者名字
	* 2013-6-18修改日期
	* 修改内容
	* @Title: persistenceLogs 
	* @Description: 
	* @param @return
	* @param @throws Exception    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@ResponseBody
	@RequestMapping(value = "/persistenceLogs")
	public String persistenceLogs(Log log) throws Exception
	{
		OutputJson(getMessage(logsService.persistenceLogs(log)),Constants.TEXT_TYPE_PLAIN);
		return null;
	}
	
	/**  
	* 函数功能说明 TODO:删除日志
	* Administrator修改者名字
	* 2013-6-18修改日期
	* 修改内容
	* @Title: delLogs 
	* @Description: 
	* @param @return
	* @param @throws Exception    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@ResponseBody
	@RequestMapping(value = "/delLogs")
	public String delLogs(Log log) throws Exception
	{
		OutputJson(getMessage(logsService.delLogs(log.getLogId())));
		return null;
	}
	
}
