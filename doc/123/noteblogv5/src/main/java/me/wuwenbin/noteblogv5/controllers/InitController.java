package me.wuwenbin.noteblogv5.controllers;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import me.wuwenbin.noteblogv5.constant.NBV5;
import me.wuwenbin.noteblogv5.model.json.ResultBeanObj;
import me.wuwenbin.noteblogv5.model.entity.Param;
import me.wuwenbin.noteblogv5.service.ParamService;
import me.wuwenbin.noteblogv5.utils.CacheUtils;
import me.wuwenbin.noteblogv5.utils.NbUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wuwen
 */
@Controller
public class InitController extends BaseController {

    private final ParamService paramService;
    private final HttpServletRequest request;

    public InitController(ParamService paramService, HttpServletRequest request) {
        this.paramService = paramService;
        this.request = request;
    }

    @RequestMapping("/init")
    public ModelAndView initPage() {
        if (NbUtils.noteBlogIsInstalled()) {
            return new ModelAndView(new RedirectView("/"));
        }
        request.setAttribute("uploadPathInEnv",
                NbUtils.getEnvPropertyByKey(NBV5.APP_UPLOAD_PATH, String.class));
        return new ModelAndView("init");
    }

    @RequestMapping("/init/submit")
    @ResponseBody
    public ResultBeanObj initSubmit(String username, String password, String email) {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password) || StringUtils.isEmpty(email)) {
            return ResultBeanObj.error("用户名/密码/邮箱不能为空！");
        } else {
            paramService.saveInitParam(getParameterMap(request.getParameterMap()));
            paramService.initMasterAccount(username, password, email);
            paramService.update(Param.builder().value(email).build(),
                    Wrappers.<Param>update().eq("name", NBV5.MAIL_SERVER_ACCOUNT));
            boolean res = paramService.update(Param.builder().value("1").build(),
                    Wrappers.<Param>update().eq("name", NBV5.INIT_STATUS));
            if (res) {
                CacheUtils.getParamCache().evict(NBV5.INIT_STATUS);
            }
            return ResultBeanObj.ok("初始化设置成功！");
        }
    }
}
