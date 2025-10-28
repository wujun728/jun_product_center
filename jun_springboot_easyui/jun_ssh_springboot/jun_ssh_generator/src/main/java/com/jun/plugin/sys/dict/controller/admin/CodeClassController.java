package com.jun.plugin.sys.dict.controller.admin;



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
import com.jun.plugin.common.model.JsonData.Type;
import com.jun.plugin.query.jpa.expr.AExpr;
import com.jun.plugin.sys.dict.entity.CodeClass;
import com.jun.plugin.sys.dict.entity.CodeClass_;
import com.jun.plugin.sys.dict.service.CodeClassService;


@Controller("adminCodeClassController")
@RequestMapping("/admin/action/codeclass")
public class CodeClassController {
	@Autowired
	CodeClassService codeClassService;
	
	@ResponseBody
	@RequestMapping("/add")
	public JsonData add(
			@ModelAttribute CodeClass codeClass){
		
		if(codeClassService.getOne(AExpr.eq(CodeClass_.code,codeClass.getCode()))==null)
			codeClassService.save(codeClass);
		else
			JsonData.success().setType(Type.error).setMessage("重复的代码！");
		return JsonData.success();
	}
	
	
	@ResponseBody
	@RequestMapping("/edit")
	public JsonData edit(
			@ModelAttribute CodeClass codeClass){
		
		codeClassService.update(codeClass);
		return JsonData.success();
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public JsonData delete(
			@RequestParam Long id){
		
		codeClassService.delete(id);
		return JsonData.success();
	}
	
	@ResponseBody
	@RequestMapping("/findpage")
	public EasyUIPage findpage(
			@ModelAttribute CodeClass codeClass,
			@RequestParam int page,
			@RequestParam int rows){
		Pageable pageable=new PageRequest(page<1?0:page-1, rows, new Sort(Direction.DESC,"orderNum"));
		Page<CodeClass> pageData=codeClassService.findPage(codeClass, pageable);
		return new EasyUIPage(pageData);
	}
	
	@ResponseBody
	@RequestMapping("/findlist")
	public JsonData findlist(
			@ModelAttribute CodeClass codeClass){
		
		List<CodeClass> listData=codeClassService.findList(codeClass, new Sort(Direction.DESC,"orderNum"));
		return JsonData.success(listData);
	}	
	@ResponseBody
	@RequestMapping("/datalist")
	public List datalist(
			@ModelAttribute CodeClass codeClass){
		
		List<CodeClass> listData=codeClassService.findList(
				new Sort(Direction.DESC,"orderNum"),
				AExpr.contain(CodeClass_.name, codeClass.getName()).igEmpty(),
				AExpr.eq(CodeClass_.moduleCode, codeClass.getModuleCode()).igEmpty());
		return listData;
	}	
}
