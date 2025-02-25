package com.jun.plugin.system.controller;

import com.google.common.collect.Lists;
import com.jun.plugin.common.Result;
import com.jun.plugin.common.service.HttpSessionService;
import com.jun.plugin.system.vo.resp.App;
import com.jun.plugin.system.vo.resp.Menu;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jun.plugin.system.service.HomeService;
import com.jun.plugin.system.service.PermissionService;
import com.jun.plugin.system.vo.resp.PermissionRespNode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;


/**
 * 首页
 *
 * @author wujun
 * @version V1.0
 * @date 2020年3月18日
 */
@RestController
@RequestMapping("/sys")
@Api(tags = "首页数据")
public class HomeController {
    @Resource
    private HomeService homeService;
    @Resource
    private HttpSessionService httpSessionService;
    
    @Resource
    private PermissionService permissionService;

    @GetMapping("/home")
    @ApiOperation(value = "获取首页数据接口")
    public Result getHomeInfo() {
        //通过access_token拿userId
        String userId = httpSessionService.getCurrentUserId();
        Result result = Result.success(homeService.getHomeInfo(userId));
        return result;
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/init")
    @ApiOperation(value = "获取首页数据接口")
    public Map getHomeInitInfo() {
    	String userId = httpSessionService.getCurrentUserId();
    	List<PermissionRespNode> menus = permissionService.permissionTreeList(userId);
    	Map result = new HashMap<>();
    	Map homeInfo = new HashMap<>();
    	homeInfo.put("title", "首页");
    	homeInfo.put("href", "welcome1.html");
    	Map logoInfo = new HashMap<>();
    	logoInfo.put("title", "fly666事务所");
//    	logoInfo.put("image", "images/logo.png");
    	logoInfo.put("href", "");
    	result.put("homeInfo", homeInfo);
    	result.put("logoInfo", logoInfo);
    	result.put("menuInfo", menus);
    	result.put("data", homeService.getUserInfo(userId));
    	return result;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/initNew")
    @ApiOperation(value = "获取首页数据接口")
    public Result getHomeInitNewInfo() {
    	String userId = httpSessionService.getCurrentUserId();

    	Map result = new HashMap<>();
    	Map homeInfo = new HashMap<>();
    	homeInfo.put("title", "首页");
    	homeInfo.put("href", "welcome1.html");
    	Map logoInfo = new HashMap<>();
    	logoInfo.put("title", "fly666事务所");
//    	logoInfo.put("image", "images/logo.png");
    	logoInfo.put("href", "");
    	result.put("homeInfo", homeInfo);
    	result.put("logoInfo", logoInfo);
		result.put("data", homeService.getUserInfo(userId));

		List<PermissionRespNode> menus = permissionService.permissionTreeList(userId);

		List<App> apps = Lists.newArrayList();
		menus.forEach(item->{
			App app = new App();
			app.setActive(false);
			app.setCode(item.getTitle());
			app.setName(item.getTitle());
			apps.add(app);
		});
		result.put("apps", apps);

		List<Menu> menus1 = Lists.newArrayList();
		menus.forEach(item->{
			Menu menu = new Menu();
			menu.setCode(item.getTitle());
			menu.setValue(item.getChildren());
			menus1.add(menu);
		});
		result.put("menus", menus1);

		return Result.success(result);
    }
}
