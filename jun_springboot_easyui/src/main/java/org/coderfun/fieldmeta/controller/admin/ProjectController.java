package org.coderfun.fieldmeta.controller.admin;

import java.util.List;

import org.coderfun.common.exception.AppException;
import org.coderfun.common.exception.ErrorCodeEnum;
import org.coderfun.fieldmeta.entity.Project;
import org.coderfun.fieldmeta.entity.Project_;
import org.coderfun.fieldmeta.service.ProjectService;
import org.coderfun.sys.dict.SystemCode;
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
import klg.j2ee.query.jpa.expr.AExpr;

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
