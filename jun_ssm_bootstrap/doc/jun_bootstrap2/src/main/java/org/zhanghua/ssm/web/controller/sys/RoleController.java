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
import org.zhanghua.ssm.entity.sys.Role;
import org.zhanghua.ssm.service.sys.RoleService;

/**
 * 角色管理
 * 
 * @author Wujun
 * 
 */
@Controller
@RequestMapping("/sys/role")
public class RoleController extends BaseController {

	@Autowired
	private RoleService service;

	@ModelAttribute
	public Role get(@RequestParam(required = false, value = "id") String id, Model model) throws Exception {
		if (StringUtils.isNotBlank(id)) {
			return service.get(id);
		}
		return new Role();
	}

	@RequestMapping(value = "list")
	public String list(Role role, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Page<Role> page = service.queryList(new Page<Role>(request, response), role);
		model.addAttribute("page", page);
		return "sys/role/list";
	}

	@RequestMapping(value = "edit")
	public String edit(Role role, Model model) throws Exception {
		model.addAttribute("role", role);
		return "sys/role/edit";
	}

	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String save(@Valid Role role, Errors errors, RedirectAttributes redirectAttributes, Model model) throws Exception {
		if (errors.hasErrors()) {
			return edit(role, model);
		}
		service.save(role);
		redirectAttributes.addFlashAttribute("message", "保存成功");
		return "redirect:/sys/role/list";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@Valid Role role, Errors errors, RedirectAttributes redirectAttributes, Model model) throws Exception {
		if (errors.hasErrors()) {
			return edit(role, model);
		}
		service.update(role);
		redirectAttributes.addFlashAttribute("message", "保存成功");
		return "redirect:/sys/role/list";
	}
	

	@RequestMapping(value = "delete/{ids}")
	public String delete(@PathVariable("ids") List<String> ids, RedirectAttributes redirectAttributes) throws Exception {
		service.delete(ids);
		redirectAttributes.addFlashAttribute("message", "删除成功");
		return "redirect:/sys/role/list";
	}

	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") String id, Model model) throws Exception {
		model.addAttribute("role", service.get(id));
		return "sys/role/view";
	}

}
