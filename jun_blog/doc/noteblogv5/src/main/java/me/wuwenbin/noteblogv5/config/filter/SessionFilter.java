package me.wuwenbin.noteblogv5.config.filter;

import cn.hutool.core.map.MapUtil;
import cn.hutool.http.useragent.Browser;
import cn.hutool.http.useragent.UserAgentInfo;
import cn.hutool.http.useragent.UserAgentUtil;
import me.wuwenbin.noteblogv5.constant.NBV5;
import me.wuwenbin.noteblogv5.controllers.BaseController;
import me.wuwenbin.noteblogv5.model.entity.Log;
import me.wuwenbin.noteblogv5.model.entity.User;
import me.wuwenbin.noteblogv5.service.ParamService;
import me.wuwenbin.noteblogv5.utils.NbUtils;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 用于记录访问日志以及设置sessionUser的filter，没有任何URL的过滤作用
 * created by Wuwenbin on 2019-08-09 at 10:03
 *
 * @author wuwenbin
 */
public class SessionFilter extends BaseController implements HandlerInterceptor {

    private static final String DEVELOP_KEY = "app.develop";
    private ParamService paramService = NbUtils.getBean(ParamService.class);
    private ServletContext nbServletContext = NbUtils.getServletContext();

    private HttpSession session;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        session = request.getSession();
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mv) throws Exception {
        String username = "";
        Long userId = null;
        if (mv != null) {
            User user = getSessionUser(request);
            if (user == null) {
                //sessionUser为空
                mv.getModelMap().addAttribute("nbv5su", null);
            } else {
                username = user.getUsername();
                userId = user.getId();
                Map<Object, Object> userMap = MapUtil.of(new Object[][]{
                        {"id", user.getId()},
                        {"username", user.getUsername()},
                        {"email", user.getEmail()},
                        {"nickname", user.getNickname()},
                        {"avatar", user.getAvatar()},
                        {"role", user.getRole().getValue()},
                        {"roleName", user.getRole().getDescp()}});
                //统一设置会话用户的值，不需要再controller中设置
                mv.getModelMap().addAttribute("nbv5su", userMap);
            }
        }

        boolean develop = NbUtils.getBean(Environment.class).getProperty(DEVELOP_KEY, Boolean.class, true);
        String ipAddr = NbUtils.getRemoteAddress(request);
        String statistics = paramService.findByName(NBV5.STATISTICS_ONOFF).getValue();
        boolean openAnalysis = Integer.parseInt(statistics) == 1;
        if (openAnalysis) {
            Log logger = Log.builder()
                    .ipAddr(ipAddr)
                    .ipInfo(develop ? "开发中内网地址" : NbUtils.getIpInfo(ipAddr).getAddress())
                    .time(new Date())
                    .url(request.getRequestURL().toString())
                    .userAgent(request.getHeader("User-Agent"))
                    .username(username)
                    .requestMethod(request.getMethod())
                    .contentType(request.getContentType())
                    .build();
            try {
                HttpSession session = request.getSession();
                logger.setSessionId(session.getId());
            } catch (Exception e) {
                if (session != null) {
                    logger.setSessionId(session.getId());
                }
            }
            if (userId != null) {
                logger.setUserId(userId);
            }
            Browser browser = UserAgentUtil.parse(logger.getUserAgent()).getBrowser();
            logger.setBrowser(UserAgentInfo.NameUnknown.equals(browser.getName()) ? "脚本/搜索引擎/爬虫等" : browser.getName());
            //noinspection unchecked
            List<Log> oldCacheLogs = (List<Log>) nbServletContext.getAttribute(NBV5.LOG_CACHE_KEY);
            List<Log> newCacheLogs = new LinkedList<>();
            newCacheLogs.add(logger);
            if (oldCacheLogs == null || oldCacheLogs.isEmpty()) {
                nbServletContext.setAttribute(NBV5.LOG_CACHE_KEY, newCacheLogs);
            } else {
                newCacheLogs.addAll(oldCacheLogs);
                nbServletContext.setAttribute(NBV5.LOG_CACHE_KEY, newCacheLogs);
            }
        }
    }
}
