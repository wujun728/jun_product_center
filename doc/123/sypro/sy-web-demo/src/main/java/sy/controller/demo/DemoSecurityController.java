package sy.controller.demo;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sy.pageModel.base.JsonResult;
import sy.pageModel.base.SessionInfo;

/**
 *
 * 权限演示
 *
 * http://git.oschina.net/sphsyv/sypro
 *
 * @author 孙宇
 *
 */
@Controller
@RequestMapping("/demoSecurityController")
public class DemoSecurityController {

	/**
	 * 在你的登陆方法里面，应该有查询当前用户是否通过验证
	 * 
	 * 如果通过验证，就是用户名和密码都正确，那么还需要查找当前用户拥有的角色
	 * 
	 * 然后再通过角色查找到权限
	 * 
	 * 也就是用户拥有的所有权限
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("/login")
	@ResponseBody
	public JsonResult login(HttpSession session) {
		JsonResult json = new JsonResult();

		// 假设这个用户已经登陆成功service.get(xxx) != null
		// service.find() 查询所有用户的角色、再查可以访问的资源等等

		SessionInfo sessionInfo = new SessionInfo();
		List<String> permissionUrls = new ArrayList<String>();
		// 假设这个是从数据库里面查出来的，一般用户有多个角色、角色有多个资源(url)，所以知道用户就可以查到用户所有的资源了
		permissionUrls.add("/demoInitController/init.do");
		permissionUrls.add("/generatorController/generator.do");
		permissionUrls.add("/demoUserController/save.do");
		permissionUrls.add("/demoUserController/get.do");
		permissionUrls.add("/demoUserController/find.do");
		permissionUrls.add("/demoRoleController/find.do");
		permissionUrls.add("/demoCController/find.do");
		permissionUrls.add("/demoUploadController/upload.do");
		permissionUrls.add("/demoUserController/getBySql.do");
		permissionUrls.add("/demoUserController/getBySql2.do");
		permissionUrls.add("/demoUserController/countBySql.do");
		permissionUrls.add("/demoUserController/get2.do");
		permissionUrls.add("/demoUserController/get3.do");
		permissionUrls.add("/demoCController/find2.do");
		permissionUrls.add("/demoUserController/update.do");
		permissionUrls.add("/demoResourceController/findTreeGrid.do");
		permissionUrls.add("/demoResourceController/findTree.do");
		// permissionUrls.add("/demoUserController/delete.do");//我就是不让你删除用户
		permissionUrls.add("/controllerLogController/find.do");
		sessionInfo.setPermissionUrls(permissionUrls);// 假设将用户所有可以访问的url都放进去了

		session.setAttribute("sessionInfo", sessionInfo);// session里面的key一定要叫sessionInfo，否则拦截器会有问题

		json.setSuccess(true);
		json.setMsg("登陆成功，已经将用户的信息和可以访问的权限存放到了session中。现在你可以进行其他测试了");
		return json;
	}

	/**
	 * 退出登陆无非就是清空session里面的sessionInfo
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("/logout")
	@ResponseBody
	public JsonResult logout(HttpSession session) {
		if (session != null) {
			session.invalidate();
		}
		JsonResult json = new JsonResult();
		json.setSuccess(true);
		json.setMsg("退出登陆！现在进行其他操作就会没权限了。");
		return json;
	}

}
