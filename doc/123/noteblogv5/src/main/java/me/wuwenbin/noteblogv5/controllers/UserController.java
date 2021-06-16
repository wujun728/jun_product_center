package me.wuwenbin.noteblogv5.controllers;

import cn.hutool.cache.Cache;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.code.kaptcha.Constants;
import me.wuwenbin.noteblogv5.constant.NBV5;
import me.wuwenbin.noteblogv5.enums.RoleEnum;
import me.wuwenbin.noteblogv5.model.json.ResultBeanObj;
import me.wuwenbin.noteblogv5.model.bo.login.GithubLoginData;
import me.wuwenbin.noteblogv5.model.bo.login.QqLoginData;
import me.wuwenbin.noteblogv5.model.bo.login.SimpleLoginData;
import me.wuwenbin.noteblogv5.model.entity.Param;
import me.wuwenbin.noteblogv5.model.entity.User;
import me.wuwenbin.noteblogv5.service.LoginService;
import me.wuwenbin.noteblogv5.service.UserService;
import me.wuwenbin.noteblogv5.service.MailService;
import me.wuwenbin.noteblogv5.service.ParamService;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wuwen
 */
@Controller
public class UserController extends BaseController {

    private static final String MANAGEMENT_INDEX = "/management/index";

    private final HttpServletRequest request;
    private final ParamService paramService;
    private final MailService mailService;
    private final UserService userService;
    private final Cache<String, String> codeCache;

    private final LoginService<ResultBeanObj, QqLoginData> qqLoginService;
    private final LoginService<ResultBeanObj, GithubLoginData> githubLoginService;
    private final LoginService<ResultBeanObj, SimpleLoginData> simpleLoginService;

    public UserController(HttpServletRequest request, ParamService paramService,
                          LoginService<ResultBeanObj, QqLoginData> qqLoginService,
                          LoginService<ResultBeanObj, GithubLoginData> githubLoginService,
                          LoginService<ResultBeanObj, SimpleLoginData> simpleLoginService,
                          MailService mailService, Cache<String, String> codeCache, UserService userService) {
        this.request = request;
        this.paramService = paramService;
        this.qqLoginService = qqLoginService;
        this.githubLoginService = githubLoginService;
        this.simpleLoginService = simpleLoginService;
        this.mailService = mailService;
        this.codeCache = codeCache;
        this.userService = userService;
    }

    @GetMapping("/login")
    public ModelAndView loginPage(String redirectUrl, HttpServletRequest request) {
        request.getSession().setAttribute("tempUrl", StringUtils.isEmpty(redirectUrl) ? "/" : redirectUrl);
        User sessionUser = getSessionUser(request);
        if (sessionUser != null) {
            if (StrUtil.isNotEmpty(redirectUrl) && sessionUser.getRole() != RoleEnum.ADMIN) {
                return new ModelAndView(new RedirectView(redirectUrl));
            } else {
                return new ModelAndView(new RedirectView(MANAGEMENT_INDEX));
            }
        } else {
            ModelAndView mav = new ModelAndView("login");
            Param qqParam = paramService.findByName(NBV5.QQ_APP_ID);
            Param githubParam = paramService.findByName(NBV5.GITHUB_CLIENT_ID);
            Param reg = paramService.findByName(NBV5.USER_SIMPLE_REG_ONOFF);
            mav.addObject("isOpenQqLogin",
                    (qqParam != null && StrUtil.isNotEmpty(qqParam.getValue())));
            mav.addObject("isOpenGithubLogin",
                    (githubParam != null && StrUtil.isNotEmpty(githubParam.getValue())));
            mav.addObject("isOpenRegister",
                    (reg != null && "1".equals(reg.getValue())));
            return mav;
        }
    }

    @GetMapping("/reg")
    public ModelAndView register(HttpServletRequest request) {
        if (getSessionUser(request) != null) {
            return new ModelAndView(new RedirectView("/"));
        }
        Param reg = paramService.getOne(Wrappers.<Param>query().eq("name", NBV5.USER_SIMPLE_REG_ONOFF));
        boolean isOpenRegister = reg != null && "1".equals(reg.getValue());
        request.setAttribute("isOpenRegister", isOpenRegister);
        if (isOpenRegister) {
            return new ModelAndView("reg");
        } else {
            return new ModelAndView(new RedirectView("login"));
        }
    }

    @PostMapping("/sendMailCode")
    @ResponseBody
    public ResultBeanObj sendMailCode(String email) {
        try {
            if (StrUtil.isEmpty(email)) {
                User su = getSessionUser(request);
                if (su == null) {
                    return ResultBeanObj.error("发送失败，未知邮箱！");
                } else {
                    email = su.getEmail();
                }
            }
            mailService.sendMailCode(email);
            return ResultBeanObj.ok("发送成功，请在您的邮箱中查收！");
        } catch (Exception e) {
            return ResultBeanObj.error("发送验证码发生错误，错误信息：" + e.getMessage());
        }
    }

    @PostMapping("/registration")
    @ResponseBody
    public ResultBeanObj doRegister(String nbv5regUsername, String nbv5regPassword, String nbv5regMail, String mailCode, String nbv5regNickname) {
        int min = 4, minPass = 6, max = 20;
        if (StringUtils.isEmpty(nbv5regUsername) || StringUtils.isEmpty(nbv5regPassword)
                || StringUtils.isEmpty(mailCode) || StringUtils.isEmpty(nbv5regNickname)) {
            return ResultBeanObj.error("所填信息不完整！");
        } else if (nbv5regUsername.length() < min || nbv5regUsername.length() > max
                || nbv5regPassword.length() < minPass) {
            return ResultBeanObj.error("所填信息不规范！");
        } else {
            String sessionMailCode = codeCache.get(nbv5regMail + "-" + NBV5.MAIL_CODE_KEY);
            if (mailCode.equalsIgnoreCase(sessionMailCode)) {
                if (userService.countEmailAndUsername(nbv5regMail, nbv5regUsername) == 0) {
                    try {
                        int c = userService.userRegister(nbv5regUsername, nbv5regPassword, nbv5regMail, nbv5regNickname);
                        return handle(c == 1, "注册成功！", "注册失败！");
                    } catch (Exception e) {
                        return ResultBeanObj.error("注册失败，错误信息：" + e.getMessage());
                    }
                } else {
                    return ResultBeanObj.error("已存在此邮箱/账号，请勿重复注册！");
                }
            } else {
                return ResultBeanObj.error("注册失败，邮箱验证码错误或过期！");
            }
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResultBeanObj login(SimpleLoginData data) {
        if (StringUtils.isEmpty(data.getNbv5code())) {
            return ResultBeanObj.error("验证码为空！");
        } else {
            String code = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
            if (code == null) {
                return ResultBeanObj.custom(-1, "请刷新页面");
            }
            if (!code.equalsIgnoreCase(data.getNbv5code())) {
                return ResultBeanObj.error("验证码错误！");
            }
        }
        data.setNbv5pass(SecureUtil.md5(data.getNbv5pass()));
        ResultBeanObj loginResult = simpleLoginService.doLogin(data);
        if (loginResult.get(ResultBeanObj.CODE).equals(ResultBeanObj.SUCCESS)) {
            User nbv5su = (User) loginResult.get("nbv5su");
            setSessionUser(request, nbv5su);
            Object url = request.getSession().getAttribute("tempUrl");
            if (getSessionUser(request).getRole() == RoleEnum.ADMIN) {
                loginResult.put("redirectUrl", MANAGEMENT_INDEX);
            } else if (!StringUtils.isEmpty(url)) {
                loginResult.put("redirectUrl", url.toString());
            }
        }
        return loginResult;
    }

    @RequestMapping("/api/qq")
    public String qqLogin() {
        String callbackDomain = basePath(request).concat("api/qqCallback");
        Param qqAppId = paramService.findByName(NBV5.QQ_APP_ID);
        if (qqAppId == null || StringUtils.isEmpty(qqAppId.getValue())) {
            request.getSession().setAttribute("errorMessage", "未设置QQ登录相关参数！");
            //noinspection SpringMVCViewInspection
            return "redirect:/error?errorCode=403";
        } else {
            return String
                    .format("redirect:https://graph.qq.com/oauth2.0/authorize?response_type=code&client_id=%s&redirect_uri=%s&state=%d"
                            , qqAppId.getValue(), callbackDomain, System.currentTimeMillis());
        }
    }

    @RequestMapping("/api/github")
    public String githubLogin(HttpServletRequest request) {
        String callbackDomain = basePath(request).concat("api/githubCallback");
        Param githubClientId = paramService.findByName(NBV5.GITHUB_CLIENT_ID);
        if (githubClientId == null || StringUtils.isEmpty(githubClientId.getValue())) {
            request.getSession().setAttribute("errorMessage", "未设置GITHUB登录相关参数！");
            //noinspection SpringMVCViewInspection
            return "redirect:/error?errorCode=403";
        } else {
            return String
                    .format("redirect:https://github.com/login/oauth/authorize?response_type=code&client_id=%s&redirect_uri=%s&state=%s"
                            , githubClientId.getValue(), callbackDomain, NBV5.GITHUB_AUTH_STATE);
        }
    }

    @RequestMapping("/api/{callbackType}")
    public ModelAndView qqCallback(HttpServletRequest request, String code, @PathVariable("callbackType") String callbackType) {
        String qq = "qqCallback", github = "githubCallback";
        ResultBeanObj r;
        if (qq.equals(callbackType)) {
            String callbackDomain = basePath(request).concat("api/qqCallback");
            r = qqLoginService.doLogin(QqLoginData.builder().callbackDomain(callbackDomain).code(code).build());
        } else if (github.equals(callbackType)) {
            String callbackDomain = basePath(request).concat("api/githubCallback");
            r = githubLoginService.doLogin(GithubLoginData.builder().callbackDomain(callbackDomain).code(code).build());
        } else {
            request.setAttribute("message", "暂未支持其他类型的登录！");
            return new ModelAndView(new RedirectView("error?errorCode=404"));
        }
        if (r.get(ResultBeanObj.CODE).equals(ResultBeanObj.SUCCESS)) {
            setSessionUser(request, (User) r.get(NBV5.SESSION_USER_KEY));
            Object tempUrl = request.getSession().getAttribute("tempUrl");
            String toUrl = !StringUtils.isEmpty(tempUrl) ? tempUrl.toString() : r.get(ResultBeanObj.DATA).toString();
            request.getSession().removeAttribute("tempUrl");
            return new ModelAndView(new RedirectView(toUrl));
        } else {
            return new ModelAndView(new RedirectView("/error?errorCode=404"));
        }
    }

    @GetMapping(value = {"/management/logout", "/token/logout"})
    public ModelAndView logout(HttpServletRequest request, String redirectUrl) {
        invalidSessionUser(request);
        if (StringUtils.isEmpty(redirectUrl)) {
            return new ModelAndView(new RedirectView("/login"));
        } else {
            return new ModelAndView(new RedirectView(redirectUrl));
        }
    }

}
