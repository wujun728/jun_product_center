/**  
* @Project: erp
* @Title: PermissionAssignmentAction.java
* @Package com.erp.action
* @Description: TODO:
* @author Wujun
* @date 2013-5-17 下午3:16:55
* @Copyright: 2013 www.example.com Inc. All rights reserved.
* @version V1.0  
*/
package com.erp.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.ResultType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.erp.dto.GridModel;
import com.erp.dto.Json;
import com.erp.model.Role;
import com.erp.service.PermissionAssignmentService;
import com.erp.util.Constants;

import lombok.extern.slf4j.Slf4j;

/**
 * 类功能说明 TODO:
 * 类修改者
 * 修改日期
 * 修改说明
 * <p>Title: PermissionAssignmentAction.java</p>
 * <p>Description:福产流通科技</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company:福产流通科技有限公司</p>
 * @author Wujun
 * @date 2013-5-17 下午3:16:55
 * @version V1.0
 */
@Controller
@RequestMapping("/permission")
@Slf4j
public class PermissionAssignmentAction extends BaseAction 
{
	private static final long serialVersionUID = -7653440308109010857L;
	@Autowired
	private PermissionAssignmentService permissionAssignmentService;
	
	
	private Integer id;
	
	
	private String checkedIds;
 
	@ModelAttribute
	public void setCheckedIds(String checkedIds )
	{
		this.checkedIds = checkedIds;
	}
	@ModelAttribute
	public void setId(Integer id )
	{
		this.id = id;
	}

	
	/**  
	* 函数功能说明 TODO:按节点查询所有程式
	* Administrator修改者名字
	* 2013-5-10修改日期
	* 修改内容
	* @Title: findAllFunctionList 
	* @Description:
	* @param @return
	* @param @throws Exception    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@ResponseBody
	@RequestMapping(value = "/findAllFunctionList")
	public String findAllFunctionList() throws Exception
	{
		OutputJson(permissionAssignmentService.findAllFunctionsList(id));
		return null;
	}
	
	/**  
	* 函数功能说明 TODO:查询所有角色
	* Administrator修改者名字
	* 2013-5-20修改日期
	* 修改内容
	* @Title: findAllRoleList 
	* @Description: 
	* @param @return
	* @param @throws Exception    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@ResponseBody
	@RequestMapping(value = "/findAllRoleList")
	public String findAllRoleList() throws Exception
	{
		Map<String, Object> map = searchRole();
		GridModel gridModel=new GridModel();
		gridModel.setRows(permissionAssignmentService.findAllRoleList(map, page, rows,true));
		Long count = permissionAssignmentService.getCount(map);
		gridModel.setTotal(count);
		OutputJson(gridModel);
		return null;
	}

	private Map<String, Object> searchRole()
	{
		Map<String, Object> map=new HashMap<String, Object>();
		if (null!=searchValue&&!"".equals(searchValue))
		{
			map.put(searchName, Constants.GET_SQL_LIKE+searchValue+Constants.GET_SQL_LIKE);
		}
		return map;
	}
	
	/**  
	* 函数功能说明 TODO:查询所有角色不分页
	* Administrator修改者名字
	* 2013-5-29修改日期
	* 修改内容
	* @Title: findAllRoleListNotPage 
	* @Description: 
	* @param @return
	* @param @throws Exception    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@ResponseBody
	@RequestMapping(value = "/findAllRoleListNotPage")
	public String findAllRoleListNotPage() throws Exception
	{
		Map<String, Object> map = searchRole();
		GridModel gridModel=new GridModel();
		gridModel.setRows(permissionAssignmentService.findAllRoleList(map, null, null,false));
		OutputJson(gridModel);
		return null;
	}
	
	/**  
	* 函数功能说明 TODO:有roleId获取角色权限
	* Administrator修改者名字
	* 2013-5-29修改日期
	* 修改内容
	* @Title: getRolePermission 
	* @Description: TODO:
	* @param @return
	* @param @throws Exception    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@ResponseBody
	@RequestMapping(value = "/getRolePermission")
	public String getRolePermission(Role role) throws Exception
	{
		OutputJson(permissionAssignmentService.getRolePermission(role.getRoleId()));
		return null;
	}
	
	/**  
	* 函数功能说明 TODO:保存角色权限
	* Administrator修改者名字
	* 2013-5-29修改日期
	* 修改内容
	* @Title: savePermission 
	* @Description: 
	* @param @return
	* @param @throws Exception    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@ResponseBody
	@RequestMapping(value = "/savePermission")
	public String savePermission(Role role) throws Exception
	{
		Json json=new Json();
		if (permissionAssignmentService.savePermission(role.getRoleId(), checkedIds))
		{
			json.setStatus(true);
			json.setMessage("分配成功！查看已分配权限请重新登录！");
		}else {
			json.setMessage("分配失败！");
		}
		OutputJson(json);
		return null;
	}
	
	/**  
	* 函数功能说明 TODO:持久化角色
	* Administrator修改者名字
	* 2013-5-29修改日期
	* 修改内容
	* @Title: persistenceRole 
	* @Description:
	* @param @return
	* @param @throws Exception    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@ResponseBody
	@RequestMapping(value = "/persistenceRole")
	public String persistenceRole() throws Exception
	{	
		Map<String, List<Role>> map=new HashMap<String, List<Role>>();
		map.put("addList", JSON.parseArray(inserted, Role.class));
		map.put("updList", JSON.parseArray(updated, Role.class));
		map.put("delList", JSON.parseArray(deleted, Role.class));
		Json json=new Json();
		if (permissionAssignmentService.persistenceRole(map)) {
			json.setStatus(true);
			json.setMessage("数据更新成功！");
		}else {
			json.setMessage("提交失败了！");
		}
		OutputJson(json);
		return null;
	}
	
	/**  
	* 函数功能说明 TODO:弹窗持久化角色
	* Administrator修改者名字
	* 2013-6-14修改日期
	* 修改内容
	* @Title: persistenceRoleDlg 
	* @Description: 
	* @param @return
	* @param @throws Exception    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@ResponseBody
	@RequestMapping(value = "/persistenceRoleDlg")
	public String persistenceRoleDlg(Role role) throws Exception
	{
		OutputJson(getMessage(permissionAssignmentService.persistenceRole(role)),Constants.TEXT_TYPE_PLAIN);
		return null;
	}

	@ResponseBody
	@RequestMapping(value = "/delRole")
	public String delRole(Role role) throws Exception
	{
		OutputJson(getMessage(permissionAssignmentService.persistenceRole(role.getRoleId())));
		return null;
	}
}
