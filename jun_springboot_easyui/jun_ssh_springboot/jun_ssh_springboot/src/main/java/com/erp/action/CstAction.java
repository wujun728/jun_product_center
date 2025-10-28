package com.erp.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.erp.dto.GridModel;
import com.erp.model.Customer;
import com.erp.model.CustomerContact;
import com.erp.service.CstService;
import com.erp.util.Constants;
import com.erp.util.PageUtil;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/cst")
@Slf4j
public class CstAction extends BaseAction {
	private static final long serialVersionUID = -1829373389433829721L;
	private CstService cstService;

	@Autowired
	public void setCstService(CstService cstService) {
		this.cstService = cstService;
	}

	/**
	 * 函数功能说明 Administrator修改者名字 2013-6-24修改日期 修改内容 @Title:
	 * findCustomerList @Description: TODO:查询所有客户 @param @return @param @throws
	 * Exception 设定文件 @return String 返回类型 @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/findCustomerList")
	public String findCustomerList() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if (null != searchValue && !"".equals(searchValue)) {
			map.put(searchName, Constants.GET_SQL_LIKE + searchValue + Constants.GET_SQL_LIKE);
		}
		PageUtil pageUtil = new PageUtil(page, rows, searchAnds, searchColumnNames, searchConditions, searchVals);
		GridModel gridModel = new GridModel();
		gridModel.setRows(cstService.findCustomerList(map, pageUtil));
		gridModel.setTotal(cstService.getCount(map, pageUtil));
		OutputJson(gridModel);
		return null;
	}

	/**
	 * 函数功能说明 Administrator修改者名字 2013-7-9修改日期 修改内容 @Title:
	 * findCustomerListNoPage @Description:
	 * TODO:查询所有客户不分页 @param @return @param @throws Exception 设定文件 @return String
	 * 返回类型 @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/findCustomerListNoPage")
	public String findCustomerListNoPage() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if (null != searchValue && !"".equals(searchValue)) {
			map.put(searchName, Constants.GET_SQL_LIKE + searchValue + Constants.GET_SQL_LIKE);
		}
		PageUtil pageUtil = new PageUtil(page, rows, searchAnds, searchColumnNames, searchConditions, searchVals);
		GridModel gridModel = new GridModel();
		gridModel.setRows(cstService.findCustomerList(map, pageUtil));
		gridModel.setTotal(null);
		OutputJson(gridModel);
		return null;
	}

	/**
	 * 函数功能说明 Administrator修改者名字 2013-6-24修改日期 修改内容 @Title:
	 * persistenceCustomer @Description:
	 * TODO:持久化Customer @param @return @param @throws Exception 设定文件 @return String
	 * 返回类型 @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/persistenceCustomer")
	public String persistenceCustomer(Customer customer) throws Exception {
		Map<String, List<CustomerContact>> map = new HashMap<String, List<CustomerContact>>();
		if (inserted != null && !"".equals(inserted)) {
			map.put("addList", JSON.parseArray(inserted, CustomerContact.class));
		}
		if (updated != null && !"".equals(updated)) {
			map.put("updList", JSON.parseArray(updated, CustomerContact.class));
		}
		if (deleted != null && !"".equals(deleted)) {
			map.put("delList", JSON.parseArray(deleted, CustomerContact.class));
		}
		OutputJson(getMessage(cstService.persistenceCustomer(customer, map)), Constants.TEXT_TYPE_PLAIN);
		return null;
	}

	/**
	 * 函数功能说明 Administrator修改者名字 2013-6-26修改日期 修改内容 @Title:
	 * delCustomer @Description: TODO:删除客户 @param @return @param @throws Exception
	 * 设定文件 @return String 返回类型 @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/delCustomer")
	public String delCustomer(Customer customer) throws Exception {
		OutputJson(getMessage(cstService.delCustomer(customer.getCustomerId())));
		return null;
	}


	/**
	 * 函数功能说明 TODO:获取所有销售代表 Administrator修改者名字 2013-6-24修改日期 修改内容 @Title:
	 * findSaleNameList @Description: @param @return @param @throws Exception
	 * 设定文件 @return String 返回类型 @throws
	 */
	public String findSaleNameList() throws Exception {
		OutputJson(cstService.findSaleNameList());
		return null;
	}
}
