package sy.controller.demo;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sy.pageModel.base.JsonResult;
import sy.service.demo.DemoInitService;

/**
 * 初始化数据测试控制器
 * 
 * http://git.oschina.net/sphsyv/sypro
 * 
 * @author 孙宇
 *
 */
@Controller
@RequestMapping("/demoInitController")
public class DemoInitController {

	@Resource(name = "demoInitService")
	private DemoInitService demoInitService;

	/**
	 * 初始化一些数据，提供演示
	 * 
	 * @return
	 */
	@RequestMapping("/init")
	@ResponseBody
	public JsonResult init() {
		JsonResult j = new JsonResult();
		demoInitService.init();
		j.setSuccess(true);
		j.setMsg("初始化成功！");
		return j;
	}

}
