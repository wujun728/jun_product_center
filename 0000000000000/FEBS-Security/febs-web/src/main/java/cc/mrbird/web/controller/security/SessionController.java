package cc.mrbird.web.controller.security;

import cc.mrbird.common.annotation.Log;
import cc.mrbird.common.domain.ResponseBo;
import cc.mrbird.common.utils.AddressUtils;
import cc.mrbird.security.domain.FebsSocialUserDetails;
import cc.mrbird.security.domain.FebsUserDetails;
import cc.mrbird.security.properties.FebsSecurityProperties;
import cc.mrbird.web.domain.UserOnLine;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Controller
public class SessionController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Value("${server.servlet.session.timeout}")
    private long sessionTimeout = 0;

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Resource
    private SessionRegistry sessionRegistry;

    @Autowired
    private FebsSecurityProperties securityProperties;

    @Log("获取在线用户信息")
    @RequestMapping("session")
    @PreAuthorize("hasAuthority('session:list')")
    public String online() {
        return "system/monitor/online";
    }

    @RequestMapping("session/invalid")
    public void sessionInvalid(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        // session 失效后直接跳转到登录页
        redirectStrategy.sendRedirect(request, response, securityProperties.getLoginUrl());
    }

    @RequestMapping("session/kickout")
    @ResponseBody
    @PreAuthorize("hasAuthority('session:kickout')")
    public ResponseBo kickOut(String sessionId, HttpServletRequest request, HttpServletResponse response) {
        try {
            String currentSessionId = request.getRequestedSessionId();
            sessionRegistry.getSessionInformation(sessionId).expireNow();
            if (StringUtils.equals(sessionId, currentSessionId)) {
                return ResponseBo.ok("refresh");
            } else {
                return ResponseBo.ok();
            }
        } catch (Exception e) {
            log.error("踢出用户失败", e);
            return ResponseBo.error("踢出用户失败，请联系网站管理员！");
        }
    }

    @PreAuthorize("hasAuthority('session:list')")
    @GetMapping("session/active")
    @ResponseBody
    public Map<String, Object> getAllSession(HttpServletRequest request) {
        try {
            // spring中 session超时时间最少也是 60秒
            if (sessionTimeout <= 60)
                sessionTimeout = 60;
            List<Object> principals = sessionRegistry.getAllPrincipals();
            List<UserOnLine> list = new ArrayList<>();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd: HH:mm:ss");
            for (Object o : principals) {
                List<SessionInformation> informations = sessionRegistry.getAllSessions(o, false);
                informations.forEach(information -> {
                    UserOnLine userOnLine = new UserOnLine();
                    Date lastRequest = information.getLastRequest();
                    Object principal = information.getPrincipal();
                    if (principal instanceof FebsUserDetails) {
                        FebsUserDetails details = (FebsUserDetails) information.getPrincipal();
                        userOnLine.setUsername(details.getUsername());
                        userOnLine.setIp(details.getRemoteAddress());
                        userOnLine.setLocation(AddressUtils.getCityInfo(userOnLine.getIp()));
                        userOnLine.setLoginTime(details.getLoginTime());
                        userOnLine.setLoginType(details.getLoginType());
                    }
                    if (principal instanceof FebsSocialUserDetails) {
                        FebsSocialUserDetails details = (FebsSocialUserDetails) information.getPrincipal();
                        userOnLine.setUsername(details.getUsername());
                        userOnLine.setIp(details.getRemoteAddress());
                        userOnLine.setLocation(AddressUtils.getCityInfo(userOnLine.getIp()));
                        userOnLine.setLoginTime(details.getLoginTime());
                        userOnLine.setLoginType(details.getLoginType());
                    }
                    userOnLine.setSessionId(information.getSessionId());
                    userOnLine.setLastRequested(sdf.format(lastRequest));
                    LocalDateTime lastRequestLocalDateTime = LocalDateTime.ofInstant(lastRequest.toInstant(), ZoneId.systemDefault());

                    // 通过时间判断 session 是否已过期，没过期则添加的在线人员集合中
                    if (LocalDateTime.now().isBefore(lastRequestLocalDateTime.plusSeconds(sessionTimeout)))
                        list.add(userOnLine);
                });
            }
            Map<String, Object> rspData = new HashMap<>();
            rspData.put("rows", list);
            rspData.put("total", list.size());
            return rspData;
        } catch (Exception e) {
            log.error("获取在线用户失败", e);
            return ResponseBo.error("获取在线用户失败，请联系网站管理员！");
        }
    }
}
