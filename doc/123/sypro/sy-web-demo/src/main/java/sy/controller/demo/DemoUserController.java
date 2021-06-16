package sy.controller.demo;

import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sy.annotation.base.MethodName;
import sy.controller.base.BaseController;
import sy.model.demo.DemoRole;
import sy.model.demo.DemoUser;
import sy.pageModel.base.JsonResult;
import sy.pageModel.base.PageResult;
import sy.service.base.BaseService;
import sy.service.demo.DemoUserService;
import sy.util.base.QueryFilter;
import sy.util.base.SetPropertyEditorSupport;

/**
 * DemoUser控制器
 *
 * http://git.oschina.net/sphsyv/sypro
 *
 * 由代码生成器生成
 * 
 * @author 孙宇
 *
 */
@Controller
@RequestMapping("/demoUserController")
public class DemoUserController extends BaseController<DemoUser, Long> {

	@Resource(name = "demoUserService")
	@Override
	public void setService(BaseService<DemoUser, Long> service) {
		super.service = service;
	}

	/**
	 * demoUser自己的initBinder
	 * 
	 * @param binder
	 */
	@InitBinder
	public void initBinderDemoUser(ServletRequestDataBinder binder) {
		// 由于用户里面的角色信息是个Set类型，所以这地方要转一下才行，前台传递过来的是个一个字符串，类似"1,2,3"这种，后台要自动转成Set
		binder.registerCustomEditor(Set.class, "roles", new SetPropertyEditorSupport(DemoRole.class));
	}

	@MethodName(name = "保存")
	@RequestMapping("/save")
	@ResponseBody
	public JsonResult save(DemoUser demoUser) {
		return super.save(demoUser);
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
	public JsonResult update(DemoUser demoUser) {
		return super.update(demoUser);
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

	/**
	 * 自定义方法，演示sql语句的查询
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/getBySql")
	@ResponseBody
	public JsonResult getBySql(Long id) {
		JsonResult j = new JsonResult();
		j.setSuccess(true);
		j.setObj(((DemoUserService) service).getBySql(id));// 自定义接口，基类没有提供这个接口
		return j;
	}

	/**
	 * 可以使用QueryFilter的sql查询
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/getBySql2")
	@ResponseBody
	public JsonResult getBySql2(HttpServletRequest request) {
		JsonResult j = new JsonResult();
		j.setSuccess(true);
		QueryFilter filter = new QueryFilter(request);
		j.setObj(((DemoUserService) service).getBySql2(filter));
		return j;
	}

	/**
	 * 统计个数
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/countBySql")
	@ResponseBody
	public JsonResult countBySql(HttpServletRequest request) {
		JsonResult j = new JsonResult();
		j.setSuccess(true);
		QueryFilter filter = new QueryFilter(request);
		j.setObj(((DemoUserService) service).countBySql(filter));
		return j;
	}

	/**
	 * 自定义方法，演示返回json定制化
	 * 
	 * @param id
	 * @param response
	 */
	@RequestMapping("/get2")
	public void get2(Long id, HttpServletResponse response) {
		super.returnJsonByIncludeProperties(service.get(id), new String[] { "name", "age" }, response);
	}

	/**
	 * 自定义方法，演示级联查询，并且定制化json
	 * 
	 * @param id
	 * @param response
	 */
	@RequestMapping("/get3")
	public void get3(Long id, HttpServletResponse response) {
		QueryFilter filter = new QueryFilter();
		filter.setJoinFetch(new String[] { "t.company" });// 要一并查出公司的信息
		super.returnJsonByIncludeProperties(service.get(filter), new String[] { "name", "age", "company.id", "company.name" }, response);
	}

}
