package org.zhanghua.ssm.web.controller.sys;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zhanghua.ssm.common.page.Page;
import org.zhanghua.ssm.common.web.controller.BaseController;
import org.zhanghua.ssm.entity.sys.Resource;
import org.zhanghua.ssm.service.sys.ResourceService;

/**
 * 资源管理
 * 
 * @author Wujun
 * 
 */
@Controller
@RequestMapping("/sys/resource")
public class ResourceController extends BaseController {

	@Autowired
	private ResourceService service;

	@ModelAttribute
	public Resource get(@RequestParam(required = false, value = "id") String id, Model model) throws Exception {
		if (StringUtils.isNotBlank(id)) {
			return service.get(id);
		}
		return new Resource();
	}

	@RequestMapping(value = "list")
	public String list(Resource resource, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Page<Resource> page = service.queryList(new Page<Resource>(request, response), resource);
		model.addAttribute("page", page);
		return "sys/resource/list";
	}

	@RequestMapping(value = "edit")
	public String edit(Resource resource, Model model) throws Exception {
		model.addAttribute("resource", resource);
		return "sys/resource/edit";
	}

	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String save(@Valid Resource resource, Errors errors, RedirectAttributes redirectAttributes, Model model) throws Exception {
		if (errors.hasErrors()) {
			return edit(resource, model);
		}
		service.save(resource);
		redirectAttributes.addFlashAttribute("message", "保存成功");
		return "redirect:/sys/resource/list";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@Valid Resource resource, Errors errors, RedirectAttributes redirectAttributes, Model model) throws Exception {
		if (errors.hasErrors()) {
			return edit(resource, model);
		}
		service.update(resource);
		redirectAttributes.addFlashAttribute("message", "保存成功");
		return "redirect:/sys/resource/list";
	}

	@RequestMapping(value = "delete/{ids}")
	public String delete(@PathVariable("ids") List<String> ids, RedirectAttributes redirectAttributes) throws Exception {
		service.delete(ids);
		redirectAttributes.addFlashAttribute("message", "删除成功");
		return "redirect:/sys/resource/list";
	}

	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") String id, Model model) throws Exception {
		model.addAttribute("resource", service.get(id));
		return "sys/resource/view";
	}

}
