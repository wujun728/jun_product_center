package com.deer.wms.base.system.web.task;

import com.deer.wms.common.core.controller.BaseController;
import com.deer.wms.base.system.service.task.ITaskInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 任务 信息操作处理
 * 
 * @author guo
 * @date 2019-06-03
 */
@RestController
@RequestMapping("/wcs")
public class TaskInfoRestController extends BaseController
{

	
	@Autowired
	private ITaskInfoService taskInfoService;
	


//	/**
//	 * 查询任务列表
//	 */
//
//
//	@GetMapping("/taskList")
//	public Result wcsList( )
//	{
//
//		List<WCSTaskInfo> list = taskInfoService.wcsList();
//		return ResultGenerator.genSuccessResult(list);
//	}
//
//

	
}
