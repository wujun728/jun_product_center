package com.shuogesha.cms.action.admin;

import static com.shuogesha.platform.web.mongo.SimplePage.cpn;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shuogesha.cms.entity.ProductCat;
import com.shuogesha.cms.service.ProductCatService;
import com.shuogesha.common.util.JsonResult;
import com.shuogesha.common.util.ResultCode;
import com.shuogesha.platform.web.mongo.Pagination;

@RestController
@RequestMapping("/api/productCat/")
public class ProductCatAct {
	@RequestMapping(value = "/list")
	public @ResponseBody Object v_list(String name, Long pId, Integer pageNo, Integer pageSize,
			HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		Pagination pagination = productCatService.getPage(name, pId, cpn(pageNo), pageSize);
		return new JsonResult(ResultCode.SUCCESS, pagination);
	}

	@RequestMapping(value = "/get")
	public @ResponseBody Object v_get(Long id) {
		ProductCat bean = productCatService.findById(id);
		return new JsonResult(ResultCode.SUCCESS, bean);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody Object o_save(@RequestBody ProductCat bean) {
		productCatService.save(bean);
		return new JsonResult(ResultCode.SUCCESS, true);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody Object o_update(@RequestBody ProductCat bean) {
		productCatService.update(bean);
		return new JsonResult(ResultCode.SUCCESS, true);
	}

	@RequestMapping(value = "/delete")
	public @ResponseBody Object o_delete(Long[] ids) {
		productCatService.removeByIds(ids);
		return new JsonResult(ResultCode.SUCCESS, true);
	}

	@RequestMapping(value = "/getNodes")
	public @ResponseBody Object getNodes(Long id, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws UnsupportedEncodingException {
//		List<Map<String, Object>> nodes = new ArrayList<>();
//		Map<String, Object> node = new HashMap<String, Object>();
//		Map<String, Object> state = new HashMap<String, Object>();
//		state.put("opened", true);
//		List<ProductCat> list = productCatService.findAll(id);
//
//		for (ProductCat channel : list) {
//			node = new HashMap<String, Object>();
//			node.put("id", channel.getId());
//			if (channel.getParent() != null && channel.getParent().getId() != null) {
//				node.put("pId", channel.getParent().getId());
//			} else {
//				node.put("pId", 0);
//			}
//			node.put("text", channel.getName());
//			// child
//			List<ProductCat> chidList = productCatService.findAll(channel.getId());
//			List<Map<String, Object>> nodes1 = new ArrayList<>();
//			for (ProductCat channel1 : chidList) {
//				Map<String, Object> node1 = new HashMap<String, Object>();
//				node1.put("id", channel1.getId());
//				if (channel1.getParent() != null && channel1.getParent().getId() != null) {
//					node1.put("pId", channel1.getParent().getId());
//				} else {
//					node1.put("pId", 0);
//				}
//				node1.put("text", channel1.getName());
//				node1.put("state", state);
//				nodes1.add(node1);
//			}
//			if (chidList != null && chidList.size() > 0) {
//				node.put("children", nodes1);
//			}
//			node.put("state", state);
//			nodes.add(node);
//		}
		List<ProductCat> nodes = productCatService.findAllProductCats(id);                
		if (id == null) { // 根节点
			Map<String, Object> re = new HashMap<String, Object>();
			List<Map<String, Object>> nodes2 = new ArrayList<>();
			re.put("id", "-1");
			re.put("pId", "0");
			re.put("name", "根目录");
 			re.put("children", nodes);
			nodes2.add(re);
			return new JsonResult(ResultCode.SUCCESS, nodes2);
 		}
		return new JsonResult(ResultCode.SUCCESS, nodes);
	}

	@Autowired
	public ProductCatService productCatService;
}
