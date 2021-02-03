package sy.controller.generator;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sy.annotation.base.MethodName;
import sy.pageModel.base.JsonResult;
import sy.util.base.GeneratorUtil;

/**
 * 代码生成控制器
 * 
 * http://git.oschina.net/sphsyv/sypro
 * 
 * @author 孙宇
 *
 */
@Controller
@RequestMapping("/generatorController")
public class GeneratorController {

	@MethodName(name = "生成项目文件")
	@RequestMapping("/generator")
	@ResponseBody
	public JsonResult generator(String generatorPath, String modelPath, String daoPackageName, String servicePackageName, String controllerPackageName) {
		JsonResult j = new JsonResult();
		try {
			Boolean b = new GeneratorUtil(generatorPath, modelPath, daoPackageName, servicePackageName, controllerPackageName).generator();
			if (b) {
				j.setMsg("生成完毕，请查看【" + generatorPath + "】目录");
				j.setSuccess(true);
			} else {
				j.setMsg("生成失败！请再次确认【" + modelPath + "】目录是否正确");
			}
		} catch (IOException e) {
			e.printStackTrace();
			j.setMsg("生成失败");
		}
		return j;
	}
}
