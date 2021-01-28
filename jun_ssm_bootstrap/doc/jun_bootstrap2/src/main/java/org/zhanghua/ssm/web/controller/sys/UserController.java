package org.zhanghua.ssm.web.controller.sys;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.ValidationException;

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
import org.zhanghua.ssm.entity.sys.User;
import org.zhanghua.ssm.service.sys.RoleService;
import org.zhanghua.ssm.service.sys.UserService;

/**
 * 用户管理
 * 
 * @author Wujun
 * 
 */
@Controller
@RequestMapping("/sys/user")
public class UserController extends BaseController {

	@Autowired
	private UserService service;

	@Autowired
	private RoleService roleService;

	@RequestMapping(value = "list")
	public String list(User user, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Page<User> page = service.queryList(new Page<User>(request, response), user);
		model.addAttribute("page", page);
		return "sys/user/list";
	}

	@RequestMapping(value = "save", method = RequestMethod.GET)
	public String save(User user, Model model) throws Exception {
		model.addAttribute("user", user);
		model.addAttribute("allRoles", roleService.queryList());
		return "sys/user/edit";
	}

	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String save(@Valid User user, Errors errors, RedirectAttributes redirectAttributes, Model model) throws Exception {
//		if(errors.hasErrors()){
//			//跳转到错误页面
//			throw new ValidationException("校验参数不正确.");
//		}
		service.save(user);
		redirectAttributes.addFlashAttribute("message", "保存成功");
		return "redirect:/sys/user/list";
	}

	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") String id, Model model) throws Exception {
		model.addAttribute("user", service.get(id));
		model.addAttribute("allRoles", roleService.queryList());
		return "sys/user/edit";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@Valid User user, Errors errors, RedirectAttributes redirectAttributes, Model model) throws Exception {
//		if(errors.hasErrors()){
//			//跳转到错误页面
//			throw new ValidationException("校验参数不正确.");
//		}
		service.update(user);
		redirectAttributes.addFlashAttribute("message", "保存成功");
		return "redirect:/sys/user/list";
	}

	@RequestMapping(value = "delete/{ids}")
	public String delete(@PathVariable("ids") List<String> ids, RedirectAttributes redirectAttributes) throws Exception {
		service.delete(ids);
		redirectAttributes.addFlashAttribute("message", "删除成功");
		return "redirect:/sys/user/list";
	}

}
