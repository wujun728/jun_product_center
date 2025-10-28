package com.erp.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erp.dto.GridModel;
import com.erp.model.CustomerContact;
import com.erp.service.CstContactService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/cstContact")
@Slf4j
public class CstContactAction extends BaseAction {
	private static final long serialVersionUID = 5833439394298542947L;
	@Autowired
	private CstContactService cstContactService;

	@Autowired
	public void setCstContactService(CstContactService cstContactService) {
		this.cstContactService = cstContactService;
	}

	/**
	 * 函数功能说明 Administrator修改者名字 2013-6-25修改日期 修改内容 @Title:
	 * findCustomerContactList @Description:
	 * TODO:查询客户联系人 @param @return @param @throws Exception 设定文件 @return String
	 * 返回类型 @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/findCustomerContactList")
	public String findCustomerContactList(CustomerContact customerContact) throws Exception {
		GridModel gridModel = new GridModel();
		gridModel.setRows(cstContactService.findCustomerContactList(customerContact.getCustomerId()));
		gridModel.setTotal(null);
		OutputJson(gridModel);
		return null;
	}

	/**
	 * 函数功能说明 Administrator修改者名字 2013-7-9修改日期 修改内容 @Title:
	 * findCustomerContactListCombobox @Description:
	 * TODO:查询客户联系人 @param @return @param @throws Exception 设定文件 @return String
	 * 返回类型 @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/findCustomerContactListCombobox")
	public String findCustomerContactListCombobox(CustomerContact customerContact) throws Exception {
		OutputJson(cstContactService.findCustomerContactList(customerContact.getCustomerId()));
		return null;
	}

}
