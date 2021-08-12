package cc.mrbird.web.controller.security;

import cc.mrbird.common.domain.FebsConstant;
import cc.mrbird.common.domain.ResponseBo;
import cc.mrbird.security.properties.FebsSecurityProperties;
import cc.mrbird.security.social.SocialUserInfo;
import cc.mrbird.system.domain.MyUser;
import cc.mrbird.system.domain.UserConnection;
import cc.mrbird.system.service.UserConnectionService;
import cc.mrbird.system.service.UserService;
import cc.mrbird.web.controller.base.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class SocialController extends BaseController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Autowired
    private ProviderSignInUtils providerSignInUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private FebsSecurityProperties febsSecurityProperties;

    @Autowired
    private UserService userService;

    @Autowired
    private UserConnectionService userConnectionService;

    @GetMapping("/social")
    public String socialIndex(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        SocialUserInfo socialUserInfo = new SocialUserInfo();
        Connection<?> connection = providerSignInUtils.getConnectionFromSession(new ServletWebRequest(request));
        if (connection == null) {
            redirectStrategy.sendRedirect(request, response, febsSecurityProperties.getLoginUrl());
        } else {
            socialUserInfo.setProviderId(connection.getKey().getProviderId());
            socialUserInfo.setProviderUserId(connection.getKey().getProviderUserId());
            socialUserInfo.setHeadImg(connection.getImageUrl());
            socialUserInfo.setNickname(connection.getDisplayName());
        }
        model.addAttribute("socialUser", socialUserInfo);
        return "social_regist";
    }

    @GetMapping("/social/bind")
    @ResponseBody
    public ResponseBo socialBind(HttpServletRequest request, MyUser user, String providerId) {
        String providerName;
        switch (providerId) {
            case "qq":
                providerName = "QQ号";
                break;
            case "weixin":
                providerName = "微信号";
                break;
            default:
                providerName = "社交账号";
        }
        final String bindFail = "绑定失败，用户名或密码错误！";
        try {
            MyUser myUser = this.userService.findByNameOrMobile(user.getUsername());
            if (myUser != null) {
                boolean isPasswordRight = this.passwordEncoder.matches(user.getPassword(), myUser.getPassword());
                if (isPasswordRight) {
                    boolean isExists = this.userConnectionService.isExist(user.getUsername(), providerId);
                    if (isExists)
                        return ResponseBo.error("该账号已绑定别的" + providerName + "，请勿重复绑定！");
                    providerSignInUtils.doPostSignUp(user.getUsername(), new ServletWebRequest(request));
                    return ResponseBo.ok();
                } else {
                    return ResponseBo.error(bindFail);
                }
            } else {
                return ResponseBo.error(bindFail);
            }
        } catch (Exception e) {
            log.error("绑定失败", e);
            return ResponseBo.error("绑定失败，请联系网站管理员！");
        }
    }

    @GetMapping("/social/regist")
    @ResponseBody
    public ResponseBo socialRegist(HttpServletRequest request, MyUser user) {
        try {
            MyUser result = this.userService.findByName(user.getUsername());
            if (result != null) {
                return ResponseBo.warn("该用户名已被使用！");
            }
            user.setPassword(this.passwordEncoder.encode(user.getPassword()));
            this.userService.registUser(user);
            providerSignInUtils.doPostSignUp(user.getUsername(), new ServletWebRequest(request));
            return ResponseBo.ok();
        } catch (Exception e) {
            log.error("注册绑定失败", e);
            return ResponseBo.error("注册绑定失败，请联系网站管理员！");
        }
    }

    @GetMapping(FebsConstant.SOCIAL_BIND_SUCCESS_URL)
    public String socialBindSuccess(HttpServletRequest request, HttpServletResponse response) {
        /* 判断当前社交账户是否已经和别的系统账户绑定了，是的话删除其和别的系统账户的绑定关系 */
        MyUser currentUser = super.getCurrentUser();

        ServletWebRequest servletWebRequest = new ServletWebRequest(request, response);
        String providerId = (String) sessionStrategy.getAttribute(servletWebRequest, FebsConstant.SESSION_KEY_SOCIAL_OPENID);
        sessionStrategy.removeAttribute(servletWebRequest, FebsConstant.SESSION_KEY_SOCIAL_OPENID);

        List<UserConnection> list = this.userConnectionService.findByProviderUserId(providerId);
        if (list.size() >= 2)
            list.stream()
                    .filter(userConnection -> !StringUtils.equals(currentUser.getUsername(), userConnection.getUserid()))
                    .forEach(userConnection -> this.userConnectionService.delete(userConnection));
        return "connect/bind_success";
    }

    @GetMapping(FebsConstant.SOCIAL_UNBIND_SUCCESS_URL)
    public String socialUnbindSuccess() {
        return "connect/unbind_success";
    }
}
