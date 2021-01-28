package org.coderfun.fieldmeta.controller.admin;



import java.util.List;

import org.coderfun.fieldmeta.entity.TypeMapping;
import org.coderfun.fieldmeta.service.TypeMappingService;
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


@Controller("adminTypeMappingController")
@RequestMapping("/admin/action/typemapping")
public class TypeMappingController {
	@Autowired
	TypeMappingService typeMappingService;
	
	@ResponseBody
	@RequestMapping("/add")
	public JsonData add(
			@ModelAttribute TypeMapping typeMapping){
		
		typeMappingService.save(typeMapping);
		return JsonData.success();
	}
	
	
	@ResponseBody
	@RequestMapping("/edit")
	public JsonData edit(
			@ModelAttribute TypeMapping typeMapping){
		
		typeMappingService.update(typeMapping);
		return JsonData.success();
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public JsonData delete(
			@RequestParam Long id){
		
		typeMappingService.delete(id);
		return JsonData.success();
	}
	
	@ResponseBody
	@RequestMapping("/findpage")
	public EasyUIPage findpage(
			@ModelAttribute TypeMapping typeMapping,
			@RequestParam int page,
			@RequestParam int rows){
		Pageable pageable=new PageRequest(page<1?0:page-1, rows);
		Page<TypeMapping> pageData=typeMappingService.findPage(typeMapping, pageable);
		return new EasyUIPage(pageData);
	}
	
	@ResponseBody
	@RequestMapping("/findlist")
	public JsonData findlist(
			@ModelAttribute TypeMapping typeMapping){
		
		List<TypeMapping> listData=typeMappingService.findList(typeMapping);
		return JsonData.success(listData);
	}	
}
