package com.pearadmin.api.modules;

import com.pearadmin.secure.session.SecureSessionService;
import com.pearadmin.system.service.ISysLogService;
import io.swagger.annotations.Api;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pearadmin.common.plugin.logging.aop.annotation.Logging;
import com.pearadmin.common.plugin.logging.aop.enums.BusinessType;
import com.pearadmin.common.tools.secure.SecurityUtil;
import com.pearadmin.common.web.base.BaseController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Describe: 入 口 控 制 器
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
@RestController
@RequestMapping
@Api(tags = {"项目入口"})
public class EntranceController extends BaseController {

    @Resource
    private SessionRegistry sessionRegistry;

    @Resource
    private ISysLogService sysLogService;

    /**
     * Describe: 获取登录视图
     * Param: ModelAndView
     * Return: 登录视图
     * */
    @GetMapping("login")
    public ModelAndView login(HttpServletRequest request){
        if (SecurityUtil.isAuthentication()) {
            SecureSessionService.expiredSession(request, sessionRegistry);
            return jumpPage("index");
        }else{
            return jumpPage("login");
        }
    }

    /**
     * Describe: 获取主页视图
     * Param: ModelAndView
     * Return: 登录视图
     * */
    @GetMapping("index")
    @Logging(title = "主页",describe = "返回 Index 主页视图",type = BusinessType.ADD)
    public ModelAndView index( )
    {
        return jumpPage("index");
    }

    /**
     * Describe: 获取主页视图
     * Param: ModelAndView
     * Return: 主页视图
     * */
    @GetMapping("console")
    public ModelAndView home(Model model)
    {
        return jumpPage("console/console");
    }

    /**
     * Describe:无权限页面
     * Return:返回403页面
     * */
    @GetMapping("error/403")
    public ModelAndView noPermission(){
        return jumpPage("error/403");
    }

    /**
     * Describe:找不带页面
     * Return:返回404页面
     * */
    @GetMapping("error/404")
    public ModelAndView notFound(){
        return jumpPage("error/404");
    }

    /**
     * Describe:异常处理页
     * Return:返回500界面
     * */
    @GetMapping("error/500")
    public ModelAndView onException(){
        return jumpPage("error/500");
    }

}
