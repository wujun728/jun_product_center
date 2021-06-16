package sy.controller.demo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sy.annotation.base.MethodName;
import sy.controller.base.BaseController;
import sy.model.demo.DemoC;
import sy.pageModel.base.JsonResult;
import sy.pageModel.base.PageResult;
import sy.service.base.BaseService;
import sy.util.base.QueryFilter;

/**
 * DemoC控制器
 *
 * http://git.oschina.net/sphsyv/sypro
 *
 * 由代码生成器生成
 * 
 * @author 孙宇
 *
 */
@Controller
@RequestMapping("/demoCController")
public class DemoCController extends BaseController<DemoC, Long> {

	@Resource(name = "demoCService")
	@Override
	public void setService(BaseService<DemoC, Long> service) {
		super.service = service;
	}

	@MethodName(name = "保存")
	@RequestMapping("/save")
	@ResponseBody
	public JsonResult save(DemoC demoC) {
		return super.save(demoC);
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
	public JsonResult update(DemoC demoC) {
		return super.update(demoC);
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
	 * 自定义方法，查找一批DemoC对象，但是返回的json定制化
	 * 
	 * @param response
	 */
	@RequestMapping("/find2")
	public void find2(HttpServletResponse response) {
		QueryFilter filter = new QueryFilter();
		filter.setJoinFetch(new String[] { "t.demob", "demob.demoa" });// 要预先抓取的属性
		super.returnJsonByIncludeProperties(service.find(filter), new String[] { "[].id", "[].name", "[].demob.name", "[].demob.demoa.name" }, response);
	}

}
