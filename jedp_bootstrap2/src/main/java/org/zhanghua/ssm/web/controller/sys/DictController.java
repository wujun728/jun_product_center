package org.zhanghua.ssm.web.controller.sys;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zhanghua.ssm.common.page.Page;
import org.zhanghua.ssm.common.web.controller.BaseController;
import org.zhanghua.ssm.entity.sys.Dict;
import org.zhanghua.ssm.service.sys.DictService;

/**
 * 数据字典
 * 
 * @author Wujun
 * 
 */
@Controller
@RequestMapping("/sys/dict")
public class DictController extends BaseController {

	@Autowired
	private DictService service;

	@RequestMapping(value = "list")
	public String list(Dict dict, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Page<Dict> page = service.queryList(new Page<Dict>(request, response), dict);
		model.addAttribute("page", page);
		return "sys/dict/list";
	}

	@RequestMapping(value = "save", method = RequestMethod.GET)
	public String save(Dict dict, Model model) throws Exception {
		model.addAttribute("dict", dict);
		return "sys/dict/edit";
	}

	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String save(@Valid Dict dict, Errors errors, RedirectAttributes redirectAttributes, Model model) throws Exception {
		if (errors.hasErrors()) {
			return save(dict, model);
		}
		service.save(dict);
		redirectAttributes.addFlashAttribute("message", "保存成功");
		return "redirect:/sys/dict/list";
	}

	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") String id, Model model) throws Exception {
		model.addAttribute("dict", service.get(id));
		return "sys/dict/edit";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@Valid Dict dict, Errors errors, RedirectAttributes redirectAttributes, Model model) throws Exception {
		if (errors.hasErrors()) {
			return update(dict.getId(), model);
		}
		service.update(dict);
		redirectAttributes.addFlashAttribute("message", "保存成功");
		return "redirect:/sys/dict/list";
	}

	@RequestMapping(value = "delete/{ids}")
	public String delete(@PathVariable("ids") List<String> ids, RedirectAttributes redirectAttributes) throws Exception {
		service.delete(ids);
		redirectAttributes.addFlashAttribute("message", "删除成功");
		return "redirect:/sys/dict/list";
	}

	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") String id, Model model) throws Exception {
		model.addAttribute("dict", service.get(id));
		return "sys/dict/view";
	}

}
