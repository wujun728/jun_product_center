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
import org.zhanghua.ssm.entity.sys.Org;
import org.zhanghua.ssm.service.sys.OrgService;

/**
 * 组织管理
 * 
 * @author Wujun
 * 
 */
@Controller
@RequestMapping("/sys/org")
public class OrgController extends BaseController {

	@Autowired
	private OrgService service;

	@ModelAttribute
	public Org get(@RequestParam(required = false, value = "id") String id, Model model) throws Exception {
		if (StringUtils.isNotBlank(id)) {
			return service.get(id);
		}
		return new Org();
	}

	@RequestMapping(value = "list")
	public String list(Org org, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Page<Org> page = service.queryList(new Page<Org>(request, response), org);
		model.addAttribute("page", page);
		return "sys/org/list";
	}

	@RequestMapping(value = "edit")
	public String edit(Org org, Model model) throws Exception {
		model.addAttribute("org", org);
		return "sys/org/edit";
	}

	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String save(@Valid Org org, Errors errors, RedirectAttributes redirectAttributes, Model model) throws Exception {
		if (errors.hasErrors()) {
			return edit(org, model);
		}
		service.save(org);
		redirectAttributes.addFlashAttribute("message", "保存成功");
		return "redirect:/sys/org/list";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@Valid Org org, Errors errors, RedirectAttributes redirectAttributes, Model model) throws Exception {
		if (errors.hasErrors()) {
			return edit(org, model);
		}
		service.update(org);
		redirectAttributes.addFlashAttribute("message", "保存成功");
		return "redirect:/sys/org/list";
	}

	@RequestMapping(value = "delete/{ids}")
	public String delete(@PathVariable("ids") List<String> ids, RedirectAttributes redirectAttributes) throws Exception {
		service.delete(ids);
		redirectAttributes.addFlashAttribute("message", "删除成功");
		return "redirect:/sys/org/list";
	}

	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") String id, Model model) throws Exception {
		model.addAttribute("org", service.get(id));
		return "sys/org/view";
	}

}
