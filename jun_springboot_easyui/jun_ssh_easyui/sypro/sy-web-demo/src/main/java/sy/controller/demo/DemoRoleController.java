package sy.controller.demo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sy.annotation.base.MethodName;
import sy.controller.base.BaseController;
import sy.model.demo.DemoRole;
import sy.pageModel.base.JsonResult;
import sy.pageModel.base.PageResult;
import sy.service.base.BaseService;
import sy.util.base.QueryFilter;

/**
 * DemoRole控制器
 *
 * http://git.oschina.net/sphsyv/sypro
 *
 * 由代码生成器生成
 * 
 * @author 孙宇
 *
 */
@Controller
@RequestMapping("/demoRoleController")
public class DemoRoleController extends BaseController<DemoRole, Long> {

	@Resource(name = "demoRoleService")
	@Override
	public void setService(BaseService<DemoRole, Long> service) {
		super.service = service;
	}

	@MethodName(name = "保存")
	@RequestMapping("/save")
	@ResponseBody
	public JsonResult save(DemoRole demoRole) {
		return super.save(demoRole);
	}

	@MethodName(name = "删除")
	@RequestMapping("/delete")
	@ResponseBody
	public JsonResult delete(Long id, HttpServletRequest request) {
		QueryFilter filter = new QueryFilter(request);
		if (id != null) {
			filter.addFilter("Q_t.id_=_Long", "" + id);
		}
		return super.delete(filter);
	}

	@MethodName(name = "更新")
	@RequestMapping("/update")
	@ResponseBody
	public JsonResult update(DemoRole demoRole) {
		return super.update(demoRole);
	}

	@MethodName(name = "查找一个")
	@RequestMapping("/get")
	@ResponseBody
	public JsonResult get(Long id, HttpServletRequest request) {
		QueryFilter filter = new QueryFilter(request);
		if (id != null) {
			filter.addFilter("Q_t.id_=_Long", "" + id);
		}
		return super.get(filter);
	}

	@MethodName(name = "查找一批")
	@RequestMapping("/find")
	@ResponseBody
	public PageResult find(HttpServletRequest request) {
		QueryFilter filter = new QueryFilter(request);
		return super.find(filter);
	}

}
