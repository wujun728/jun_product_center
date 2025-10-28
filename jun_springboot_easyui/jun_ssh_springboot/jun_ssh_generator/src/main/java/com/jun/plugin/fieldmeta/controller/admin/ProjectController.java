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

import com.jun.plugin.common.exception.AppException;
import com.jun.plugin.common.exception.ErrorCodeEnum;
import com.jun.plugin.common.model.EasyUIPage;
import com.jun.plugin.common.model.JsonData;
import com.jun.plugin.fieldmeta.entity.Project;
import com.jun.plugin.fieldmeta.entity.Project_;
import com.jun.plugin.fieldmeta.service.ProjectService;
import com.jun.plugin.query.jpa.expr.AExpr;
import com.jun.plugin.sys.dict.SystemCode;

@Controller("adminProjectController")
@RequestMapping("/admin/action/project")
public class ProjectController {
	@Autowired
	ProjectService projectService;

	@ResponseBody
	@RequestMapping("/add")
	public JsonData add(@ModelAttribute Project project) {
		changeDefault(project);
		projectService.save(project);
		return JsonData.success();
	}

	@ResponseBody
	@RequestMapping("/edit")
	public JsonData edit(@ModelAttribute Project project) {
		changeDefault(project);
		projectService.update(project);
		return JsonData.success();
	}

	@ResponseBody
	@RequestMapping("/delete")
	public JsonData delete(@RequestParam Long id) {

		if(!projectService.delete(id, true)){
			throw new AppException(ErrorCodeEnum.ENTITY_HAS_RELATED_DATA);			
		}
		return JsonData.success();
	}

	@ResponseBody
	@RequestMapping("/findpage")
	public EasyUIPage findpage(@ModelAttribute Project project, @RequestParam int page, @RequestParam int rows) {
		Pageable pageable = new PageRequest(page < 1 ? 0 : page - 1, rows, new Sort(Direction.DESC, "id"));
		Page<Project> pageData = projectService.findPage(project, pageable);
		return new EasyUIPage(pageData);
	}

	@ResponseBody
	@RequestMapping("/findlist")
	public JsonData findlist(@ModelAttribute Project project) {

		List<Project> listData = projectService.findList(project, new Sort(Direction.DESC, "id"));

		return JsonData.success(listData);
	}
	
	private void changeDefault(Project project){
		if(SystemCode.YES.equals(project.getIsDefaultCode())){
			List<Project> projects=projectService.findList(AExpr.eq(Project_.isDefaultCode, SystemCode.YES));
			for(Project p:projects){
				p.setIsDefaultCode(SystemCode.NO);
				projectService.save(p);
			}
		}		
	}
	
	
}
