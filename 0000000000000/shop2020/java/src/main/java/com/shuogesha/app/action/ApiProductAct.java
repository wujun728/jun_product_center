package com.shuogesha.app.action;

import static com.shuogesha.platform.web.mongo.SimplePage.cpn;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.shuogesha.app.version.AccessToken;
import com.shuogesha.app.web.util.ApiUtils;
import com.shuogesha.cms.entity.Collect;
import com.shuogesha.cms.entity.Product;
import com.shuogesha.cms.service.CollectService;
import com.shuogesha.cms.service.ProductService;
import com.shuogesha.common.util.JsonResult;
import com.shuogesha.common.util.ResultCode;
import com.shuogesha.platform.web.mongo.Pagination;

@Controller
@RequestMapping("/app/")
public class ApiProductAct {
	private static Logger log = LoggerFactory.getLogger(ApiProductAct.class);
	
	@RequestMapping(value = "get_product")
	@AccessToken
	public @ResponseBody Object get_product(Long id, 
			HttpServletRequest request, HttpServletResponse response) {
		Product product = productService.findById(id);
		if(StringUtils.isNotBlank(product.getImgs())) {
			product.setImgList(JSON.parseArray(product.getImgs()));
		}
		if(StringUtils.isNotBlank(product.getAttributes())) {
			product.setAttributesList(JSON.parseArray(product.getAttributes()));
		}
		//判断用户是否收藏
		Collect bean= new Collect();
		bean.setName(bean.getRefer());
		bean.setRefer(Product.class.getSimpleName());
		bean.setReferid(id);
		bean.setUserId(ApiUtils.getUnifiedUserId(request));
		if (collectService.findByRefer(bean) != null) {// 已经点赞
			product.setCollected(true);
		}
		return new JsonResult(ResultCode.SUCCESS, product);
	}
	
	/**
	 * 分类下面的产品
	 * @param name
	 * @param cateId
	 * @param filterIndex
	 * @param priceOrder
	 * @param pageNo
	 * @param pageSize
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "get_product_list")
	@AccessToken
	public @ResponseBody Object get_product_cat(String name,Long cateId, Integer filterIndex,Integer priceOrder, Integer pageNo, Integer pageSize,
			HttpServletRequest request, HttpServletResponse response) {
		Pagination pagination = productService.getAllPage(name,cateId,filterIndex,priceOrder, cpn(pageNo), pageSize);
		return new JsonResult(ResultCode.SUCCESS, pagination);
	}
	
	
	/**
	 * 首页的产品
	 * @param name
	 * @param pageNo
	 * @param pageSize
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "get_all_product")
	@AccessToken
	public @ResponseBody Object get_product_cat(String name,Integer pageNo, Integer pageSize,
			HttpServletRequest request, HttpServletResponse response) {
		Pagination pagination = productService.getAllPage(name,null,null,null, cpn(pageNo), pageSize);
		return new JsonResult(ResultCode.SUCCESS, pagination);
	}

	@Autowired
	private ProductService productService;
	@Autowired
	public CollectService collectService;

}
