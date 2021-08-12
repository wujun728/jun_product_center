package com.shuogesha.cms.action.admin;

import static com.shuogesha.platform.web.mongo.SimplePage.cpn;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shuogesha.cms.entity.Adsense;
import com.shuogesha.cms.service.AdsenseService;
import com.shuogesha.common.util.JsonResult;
import com.shuogesha.common.util.ResultCode;
import com.shuogesha.platform.version.Permission;
import com.shuogesha.platform.web.mongo.Pagination;

@Controller
@RequestMapping("/api/adsense/")
public class AdsenseAct {

	@RequestMapping(value = "/list")
	public @ResponseBody Object v_list(String name, Integer pageNo, Integer pageSize, HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Pagination pagination = adsenseService.getPage(name, cpn(pageNo), pageSize);
		return new JsonResult(ResultCode.SUCCESS, pagination);
	}

	@RequestMapping(value = "/get")
	public @ResponseBody Object v_get(Long id) {
		Adsense bean = adsenseService.findById(id);
		return new JsonResult(ResultCode.SUCCESS, bean);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@Permission(value = "sys:adsense:add")
	public @ResponseBody Object o_save(Adsense bean) {
		adsenseService.save(bean);
		return new JsonResult(ResultCode.SUCCESS, bean);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@Permission(value = "sys:adsense:edit")
	public @ResponseBody Object o_update(@RequestBody Adsense bean) {
		adsenseService.update(bean);
		return new JsonResult(ResultCode.SUCCESS, true);
	}

	@RequestMapping(value = "/delete")
	@Permission(value = "sys:adsense:delete")
	public @ResponseBody Object o_delete(Long[] ids) {
		adsenseService.removeByIds(ids);
		return new JsonResult(ResultCode.SUCCESS, true);
	}
	
	@RequestMapping(value = "/findAll")
	public @ResponseBody Object findAll() {
 		return new JsonResult(ResultCode.SUCCESS, adsenseService.findAll());
	} 
	
	@Autowired
	public AdsenseService adsenseService;
}
