package sy.controller.demo;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sy.annotation.base.MethodName;
import sy.controller.base.BaseController;
import sy.model.demo.DemoResource;
import sy.pageModel.base.JsonResult;
import sy.pageModel.base.PageResult;
import sy.pageModel.demo.DemoResourceTreeGrid;
import sy.pageModel.demo.Tree;
import sy.service.base.BaseService;
import sy.service.demo.DemoResourceService;
import sy.util.base.QueryFilter;

/**
 * DemoResource控制器
 *
 * http://git.oschina.net/sphsyv/sypro
 *
 * 由代码生成器生成
 * 
 * @author 孙宇
 *
 */
@Controller
@RequestMapping("/demoResourceController")
public class DemoResourceController extends BaseController<DemoResource, Long> {

	@Resource(name = "demoResourceService")
	@Override
	public void setService(BaseService<DemoResource, Long> service) {
		super.service = service;
	}

	@MethodName(name = "保存")
	@RequestMapping("/save")
	@ResponseBody
	public JsonResult save(DemoResource demoResource) {
		return super.save(demoResource);
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
	public JsonResult update(DemoResource demoResource) {
		return super.update(demoResource);
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

	@MethodName(name = "查找tree")
	@RequestMapping("/findTree")
	@ResponseBody
	public List<Tree> findTree() {
		return ((DemoResourceService) service).findTree();
	}

	@MethodName(name = "查找treeGrid")
	@RequestMapping("/findTreeGrid")
	@ResponseBody
	public List<DemoResourceTreeGrid> findTreeGrid(HttpServletRequest request) {
		String level = request.getParameter("n_level");
		QueryFilter filter = new QueryFilter(request);
		if (StringUtils.isBlank(level)) {
			filter.addFilter("Q_t.resource_isNull", "null");
			level = "0";
		} else {
			filter.addFilter("Q_t.resource.id_=_Long", request.getParameter("nodeid"));
			level = (Integer.parseInt(level) + 1) + "";
		}
		return ((DemoResourceService) service).findTreeGrid(filter, level);
	}

}
