package com.erp.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erp.dto.Json;
import com.erp.model.SystemCode;
import com.erp.service.SystemCodeService;
import com.erp.util.Constants;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/systemCode")
@Slf4j
public class SystemCodeAction extends BaseAction 
{
	private static final long serialVersionUID = -7594149055359363935L;
	@Autowired
	private SystemCodeService systemCodeService;

	private String permissionName;
	private Integer id;
	private Integer codePid;
	
	@ModelAttribute
	public void setCodePid(Integer codePid )
	{
		this.codePid = codePid;
	}

	@ModelAttribute
	public void setId(Integer id )
	{
		this.id = id;
	}

	@ModelAttribute
	public void setPermissionName(String permissionName )
	{
		this.permissionName = permissionName;
	}
	
	/**  
	* 函数功能说明 TODO:按节点查询所有词典
	* Administrator修改者名字
	* 2013-6-19修改日期
	* 修改内容
	* @Title: findSystemCodeList 
	* @Description: 
	* @param @return
	* @param @throws Exception    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@ResponseBody
	@RequestMapping(value = "/findSystemCodeList")
	public String findSystemCodeList() throws Exception
	{
		OutputJson(systemCodeService.findSystemCodeList(id));
		return null;
	}

	/**  
	* 函数功能说明 TODO:查询所有词典
	* Administrator修改者名字
	* 2013-6-19修改日期
	* 修改内容
	* @Title: findAllSystemCodeList 
	* @Description: 
	* @param @return
	* @param @throws Exception    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@ResponseBody
	@RequestMapping(value = "/findAllSystemCodeList")
	public String findAllSystemCodeList() throws Exception
	{
		OutputJson(systemCodeService.findSystemCodeList());
		return null;
	}
	
	/**  
	* 函数功能说明 TODO:弹窗持久化systemCode
	* Administrator修改者名字
	* 2013-6-19修改日期
	* 修改内容
	* @Title: persistenceSystemCodeDig 
	* @Description: 
	* @param @return
	* @param @throws Exception    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@ResponseBody
	@RequestMapping(value = "/persistenceSystemCodeDig")
	public String persistenceSystemCodeDig(SystemCode systemCode) throws Exception
	{
		OutputJson(getMessage(systemCodeService.persistenceSystemCodeDig(systemCode,permissionName,codePid)),Constants.TEXT_TYPE_PLAIN);
		return null;
	}
	
	/**  
	* 函数功能说明 TODO:删除词典
	* Administrator修改者名字
	* 2013-6-19修改日期
	* 修改内容
	* @Title: delSystemCode 
	* @Description: 
	* @param @return
	* @param @throws Exception    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@ResponseBody
	@RequestMapping(value = "/delSystemCode")
	public String delSystemCode(SystemCode systemCode) throws Exception
	{
		Json json=new Json();
		json.setTitle("提示");
		if (systemCodeService.delSystemCode(systemCode.getCodeId()))
		{
			json.setStatus(true);
			json.setMessage("数据更新成功!");
		}else {
			json.setStatus(false);
			json.setMessage("数据更新失败，或含有子项不能删除!");
		}
		OutputJson(json);
		return null;
	}
	
	/**  
	* 函数功能说明 
	* Administrator修改者名字
	* 2013-6-24修改日期
	* 修改内容
	* @Title: findSystemCodeByType 
	* @Description: TODO:查询词典的公用方法
	* @param @return
	* @param @throws Exception    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@ResponseBody
	@RequestMapping(value = "/findSystemCodeByType")
	public String findSystemCodeByType(SystemCode systemCode) throws Exception
	{
		OutputJson(systemCodeService.findSystemCodeByType(systemCode.getCodeMyid()));
		return null;
	}
}
