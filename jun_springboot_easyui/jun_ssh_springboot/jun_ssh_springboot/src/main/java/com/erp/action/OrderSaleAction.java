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
import com.erp.model.OrderSale;
import com.erp.model.OrderSaleLine;
import com.erp.service.OrderSaleService;
import com.erp.util.Constants;
import com.erp.util.PageUtil;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/orderSale")
@Slf4j
public class OrderSaleAction extends BaseAction {
	private static final long serialVersionUID = -7570327381936186436L;
	@Autowired
	private OrderSaleService orderSaleService;
 
	/**
	 * 函数功能说明 Administrator修改者名字 2013-7-8修改日期 修改内容 @Title:
	 * findOrderSaleLineList @Description:
	 * TODO:查询客户订单明细 @param @return @param @throws Exception 设定文件 @return String
	 * 返回类型 @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/findOrderSaleLineList")
	public String findOrderSaleLineList(OrderSale orderSale) throws Exception {
		GridModel gridModel = new GridModel();
		gridModel.setRows(orderSaleService.findOrderSaleLineList(orderSale.getOrderSaleId()));
		gridModel.setTotal(null);
		OutputJson(gridModel);
		return null;
	}

	/**
	 * 函数功能说明 Administrator修改者名字 2013-7-8修改日期 修改内容 @Title:
	 * findOrderSaleList @Description: TODO:查询客户订单 @param @return @param @throws
	 * Exception 设定文件 @return String 返回类型 @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/findOrderSaleList")
	public String findOrderSaleList() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if (null != searchValue && !"".equals(searchValue)) {
			map.put(getSearchName(), Constants.GET_SQL_LIKE + searchValue + Constants.GET_SQL_LIKE);
		}
		PageUtil pageUtil = new PageUtil(page, rows, searchAnds, searchColumnNames, searchConditions, searchVals);
		GridModel gridModel = new GridModel();
		gridModel.setRows(orderSaleService.findOrderSaleList(map, pageUtil));
		gridModel.setTotal(orderSaleService.getCount(map, pageUtil));
		OutputJson(gridModel);
		return null;
	}

	/**
	 * 函数功能说明 Administrator修改者名字 2013-7-8修改日期 修改内容 @Title:
	 * persistenceOrderSale @Description: TODO:持久化客户订单 @param @return @param @throws
	 * Exception 设定文件 @return String 返回类型 @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/persistenceOrderSale")
	public String persistenceOrderSale(OrderSale orderSale) throws Exception {
		Map<String, List<OrderSaleLine>> map = new HashMap<String, List<OrderSaleLine>>();
		if (inserted != null && !"".equals(inserted)) {
			map.put("addList", JSON.parseArray(inserted, OrderSaleLine.class));
		}
		if (updated != null && !"".equals(updated)) {
			map.put("updList", JSON.parseArray(updated, OrderSaleLine.class));
		}
		if (deleted != null && !"".equals(deleted)) {
			map.put("delList", JSON.parseArray(deleted, OrderSaleLine.class));
		}
		OutputJson(getMessage(orderSaleService.persistenceOrderSale(orderSale, map)), Constants.TEXT_TYPE_PLAIN);
		return null;
	}

	/**
	 * 函数功能说明 Administrator修改者名字 2013-7-8修改日期 修改内容 @Title:
	 * delOrderSale @Description: TODO:删除客户订单 @param @return @param @throws
	 * Exception 设定文件 @return String 返回类型 @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/delOrderSale")
	public String delOrderSale(OrderSale orderSale) throws Exception {
		OutputJson(getMessage(orderSaleService.delOrderSale(orderSale.getOrderSaleId())));
		return null;
	}
}
