package com.jun.plugin.fieldmeta.controller.admin;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jun.plugin.common.model.EasyUIPage;
import com.jun.plugin.common.model.JsonData;
import com.jun.plugin.fieldmeta.entity.PageField;
import com.jun.plugin.fieldmeta.service.PageFieldService;


@Controller("adminPageFieldController")
@RequestMapping("/admin/action/pagefield")
public class PageFieldController {
	@Autowired
	PageFieldService pageFieldService;
	
	@ResponseBody
	@RequestMapping("/findpage")
	public EasyUIPage findpage(
			@ModelAttribute PageField pageField,
			@RequestParam int page,
			@RequestParam int rows){
		Pageable pageable=new PageRequest(page<1?0:page-1, rows, new Sort(Direction.ASC,"entityField.columnSort"));
		Page<PageField> pageData=pageFieldService.findPage(pageField, pageable);
		return new EasyUIPage(pageData);
	}
	
	@ResponseBody
	@RequestMapping("/findlist")
	public JsonData findlist(
			@ModelAttribute PageField pageField){
		
		List<PageField> listData=pageFieldService.findList(pageField, new Sort(Direction.ASC,"entityField.columnSort"));
		return JsonData.success(listData);
	}	
}
