package com.erp.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.erp.common.BaseController;
import com.erp.dto.Json;

/**
 * 类功能说明 TODO:基类action
 * 类修改者	修改日期
 * 修改说明
 * <p>Title: BaseAction.java</p>
 * <p>Description:福产流通科技</p>
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company:福产流通科技</p>
 * @author Wujun
 * @date 2013-4-19 上午08:18:21
 * @version V1.0
 */
//@ParentPackage("default-package")
//@Namespace("/")
public class BaseAction extends BaseController
{
	private static final long	serialVersionUID	= 7493364888065600947L;
	
	public String searchName;
	public String searchValue;
	public String inserted;
	public String updated;
	public String deleted;
	public Integer page;
	public Integer rows;
	public String searchAnds;
	public String searchColumnNames;
	public String searchConditions;
	public String searchVals;
	
	@Autowired
    private HttpServletRequest request1;
	
    @Autowired
    private HttpServletResponse response1;

	public String getSearchName()
	{
		return searchName;
	}
	@ModelAttribute
	public void setSearchName(String searchName )
	{
		this.searchName = searchName;
	}
	public String getSearchValue()
	{
		return searchValue;
	}
	@ModelAttribute
	public void setSearchValue(String searchValue )
	{
		this.searchValue = searchValue;
	}
	public String getInserted()
	{
		return inserted;
	}
	@ModelAttribute
	public void setInserted(String inserted )
	{
		this.inserted = inserted;
	}
	public String getUpdated()
	{
		return updated;
	}
	@ModelAttribute
	public void setUpdated(String updated )
	{
		this.updated = updated;
	}
	public String getDeleted()
	{
		return deleted;
	}
	@ModelAttribute
	public void setDeleted(String deleted )
	{
		this.deleted = deleted;
	}
	public Integer getPage()
	{
		return page;
	}
	@ModelAttribute
	public void setPage(Integer page )
	{
		this.page = page;
	}
	public Integer getRows()
	{
		return rows;
	}
	@ModelAttribute
	public void setRows(Integer rows )
	{
		this.rows = rows;
	}
	public String getSearchAnds()
	{
		return searchAnds;
	}
	@ModelAttribute
	public void setSearchAnds(String searchAnds )
	{
		this.searchAnds = searchAnds;
	}
	public String getSearchColumnNames()
	{
		return searchColumnNames;
	}
	@ModelAttribute
	public void setSearchColumnNames(String searchColumnNames )
	{
		this.searchColumnNames = searchColumnNames;
	}
	public String getSearchConditions()
	{
		return searchConditions;
	}
	@ModelAttribute
	public void setSearchConditions(String searchConditions )
	{
		this.searchConditions = searchConditions;
	}
	public String getSearchVals()
	{
		return searchVals;
	}
	@ModelAttribute
	public void setSearchVals(String searchVals )
	{
		this.searchVals = searchVals;
	}
	public void OutputJson(Object object) {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		HttpServletResponse response = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
		PrintWriter out = null;
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		String json=null;
		try {
			out = response.getWriter();
			json = JSON.toJSONStringWithDateFormat(object, "yyyy-MM-dd HH:mm:ss");
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.print(json);
		out.close();
	}
	public void OutputJson(Object object,String type) {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		HttpServletResponse response = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
		PrintWriter out = null;
		response.setContentType(type);
		response.setCharacterEncoding("utf-8");
		String json=null;
		try {
			out = response.getWriter();
			json = JSON.toJSONStringWithDateFormat(object, "yyyy-MM-dd HH:mm:ss");
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.print(json);
		out.close();
	}
	
	public Json getMessage(boolean flag)
	{
		Json json=new Json();
		if (flag) {
			json.setStatus(true);
			json.setMessage("数据更新成功！");
		}else {
			json.setMessage("提交失败了！");
		}
		return json;
	}
	
}
