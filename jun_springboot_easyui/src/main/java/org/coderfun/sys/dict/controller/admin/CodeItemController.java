package org.coderfun.sys.dict.controller.admin;



import java.util.List;

import org.coderfun.sys.dict.entity.CodeItem;
import org.coderfun.sys.dict.entity.CodeItem_;
import org.coderfun.sys.dict.service.CodeItemService;
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

import klg.j2ee.common.model.EasyUIPage;
import klg.j2ee.common.model.JsonData;
import klg.j2ee.common.model.JsonData.Type;
import klg.j2ee.query.jpa.expr.AExpr;


@Controller("adminCodeItemController")
@RequestMapping("/admin/action/codeitem")
public class CodeItemController {
	@Autowired
	CodeItemService codeItemService;
	
	@ResponseBody
	@RequestMapping("/add")
	public JsonData add(
			@ModelAttribute CodeItem codeItem){
		
		if(codeItemService.getOne(AExpr.eq(CodeItem_.classCode, codeItem.getClassCode()),AExpr.eq(CodeItem_.code, codeItem.getCode()))==null)
			codeItemService.save(codeItem);
		else
			JsonData.success().setType(Type.error).setMessage("重复的代码！");
		return JsonData.success();
	}
	
	
	@ResponseBody
	@RequestMapping("/edit")
	public JsonData edit(
			@ModelAttribute CodeItem codeItem){
		
		codeItemService.update(codeItem);
		return JsonData.success();
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public JsonData delete(
			@RequestParam Long id){
		
		codeItemService.delete(id);
		return JsonData.success();
	}
	
	@ResponseBody
	@RequestMapping("/findpage")
	public EasyUIPage findpage(
			@ModelAttribute CodeItem codeItem,
			@RequestParam int page,
			@RequestParam int rows){
		Pageable pageable=new PageRequest(page<1?0:page-1, rows, new Sort(Direction.DESC,"orderNum"));
		Page<CodeItem> pageData=codeItemService.findPage(codeItem, pageable);
		return new EasyUIPage(pageData);
	}
	
	@ResponseBody
	@RequestMapping("/findlist")
	public JsonData findlist(
			@ModelAttribute CodeItem codeItem){
		
		List<CodeItem> listData=codeItemService.findList(codeItem, new Sort(Direction.DESC,"orderNum"));
		return JsonData.success(listData);
	}
	
	@ResponseBody
	@RequestMapping("/datalist")
	public List datalist(
			@ModelAttribute CodeItem codeItem){
		List<CodeItem> listData=codeItemService.findList(codeItem, new Sort(Direction.DESC,"orderNum"));
		return listData;
	}
}
