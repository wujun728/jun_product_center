package io.github.wujun728.admin.common.config;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
//import com.ruoyi.common.core.domain.entity.SysUser;
//import com.ruoyi.common.utils.SecurityUtils;
import io.github.wujun728.admin.common.CrudData;
import io.github.wujun728.admin.common.Result;
import io.github.wujun728.admin.common.constants.Constants;
import io.github.wujun728.admin.db.service.JdbcService;
import io.github.wujun728.admin.page.service.PageService;
import io.github.wujun728.admin.rbac.constants.UserType;
import io.github.wujun728.admin.rbac.data.EnterpriseUser;
import io.github.wujun728.admin.rbac.data.User;
import io.github.wujun728.admin.util.SpringUtil;
import io.github.wujun728.admin.util.TemplateUtil;
import io.github.wujun728.admin.util.UrlUtil;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author hyz
 * @date 2021/3/3 13:49
 */
@Component
@Log4j2
@Scope("request")
public class SessionContext {

    @Resource
    private JdbcService jdbcService;

    @Resource
    @Lazy
    private PageService pageService;

    private UserSession userSession;

    static final String SPLIT = "$_$";

    public UserSession getSession(HttpServletRequest request){
//        UserSession userSession = (UserSession) request.getSession().getAttribute(Constants.USER_SESSION);
//        if(userSession != null){
//
//            // User user = jdbcService.getById(User.class, userSession.getUserId());
//            User user = userService.get(userSession.getUserId());
//            if(!TokenUtil.verify(user.getSalt(),userSession.getToken(),user.getPassword())){
//                //log.info("token失效,超时或者修改密码");
//                return null;
//            }
//            return userSession;
//        }
//        String token = request.getHeader("token");
//        if(StringUtils.isBlank(token)){
//            token = request.getParameter("token");
//        }
//        if(StringUtils.isNotBlank(token)){
//            String key = TokenUtil.getKey(token);
//            String[] arr = key.split(SPLIT);
//            String userCode = arr[0];
//            Long enterpriseId = Long.parseLong(arr[1]);
//
//            User user = jdbcService.findOne(User.class, "userCode", userCode);
//            if(user != null){
//                boolean verify = TokenUtil.verify(user.getSalt(),token, user.getPassword());
//                if(verify){
//                    userSession = newSession(request,user,enterpriseId);
//                    return userSession;
//                }
//            }
//        }
//        return getSession();
        if(this.userSession != null){
            return this.userSession;
        }
        if(!StpUtil.isLogin()){
            return null;
        }
        SaSession tokenSession = StpUtil.getTokenSession();
        if(tokenSession == null){
            return null;
        }
        this.userSession = (UserSession) tokenSession.get(Constants.USER_SESSION);

//        SessionContext sessionContext = SpringContextUtil.getBean(SessionContext.class);
//        HttpServletRequest request = SpringContextUtil.getRequest();
//        return sessionContext.getSession(request);
        return userSession;
    }

    //判断是否有按钮权限
    public static boolean hasButtonPermission(String buttonCode){
        UserSession session = getSession();
        //没有编号不判断,
        //关联与有权限
        //配置的有权限
        return StringUtils.isBlank(buttonCode)
                || UserType.Admin.equals(session.getUserType())
                || session.getButtonCodes().contains(buttonCode);
    }

    //判断是否有按钮权限
    public static boolean hasUrlPermission(String url){
        UserSession session = getSession();
        //没有编号不判断,
        //关联与有权限
        //配置的有权限
        if(StringUtils.isBlank(url)
                || UserType.Admin.equals(session.getUserType())){
            return true;
        }
        Set<String> urls = session.getUrls();
        for(String pattern:urls){
            if(UrlUtil.match(url, pattern)){
                return true;
            }
        }
        return false;
    }

    public UserSession newSession(HttpServletRequest request, User user/*,Long enterpriseId*/){
        UserSession userSession = new UserSession();
        userSession.setUserId(user.getId());
//        String token = TokenUtil.getToken(user.getSalt(),user.getMobile()+SPLIT+enterpriseId, user.getPassword(), SessionTimeOut * 10   * 1000);
//        userSession.setToken(token);
        String userType = user.getUserType();
        /*if(!UserType.Admin.equals(user.getUserType())){
            Map<String, Object> manager = jdbcService.findOne("select id from enterprise_manager where user_id = ? and enterprise_id = ? ", user.getId(), enterpriseId);
            if(manager != null){
                userType = UserType.Admin;
            }
        }*/
        userSession.setUserType(userType);
//        userSession.setEnterpriseId(enterpriseId);
        userSession.setEnterpriseId(4L);

//        request.getSession().setAttribute(Constants.USER_SESSION,userSession);
        StpUtil.login(StrUtil.format("{}:{}",Constants.USER_KEY_PREFIX,user.getId()));
        userSession.setToken(StpUtil.getTokenValue());
        userSession.setTokenName(StpUtil.getTokenName());
        StpUtil.getTokenSession().set(Constants.USER_SESSION,userSession);

        Map<String,Object> params = new HashMap<>();
        params.put("menuType","1");//后台菜单
        Result<CrudData<Map<String, Object>>> currentUserMenu = pageService.queryAll("currentUserMenu",params);
        userSession.setCurrentUserMenu(currentUserMenu.getData().getRows());

        params.put("menuType","3");//移动端菜单
        Result<CrudData<Map<String, Object>>> currentUserMenuMobile = pageService.queryAll("currentUserMenu",params);
        userSession.setCurrentUserMenuMobile(currentUserMenuMobile.getData().getRows());

        if(!UserType.Admin.equals(userType)){
            Result<CrudData<Map<String, Object>>> currentUserButton = pageService.queryAll("currentUserButton");
            List<Map<String, Object>> rows = currentUserButton.getData().getRows();
            Set<String> buttonCodes = new HashSet<>();
            rows.stream().forEach(btn->{
                buttonCodes.add((String)btn.get("menuCode"));
            });
            userSession.setButtonCodes(buttonCodes);

            Result<CrudData<Map<String, Object>>> currentUserUrl = pageService.queryAll("currentUserUrl");
            List<Map<String, Object>> urlRows = currentUserUrl.getData().getRows();
            Set<String> urls = new HashSet<>();
            urlRows.stream().forEach(btn->{
                urls.add((String)btn.get("url"));
            });
            userSession.setUrls(urls);

            EnterpriseUser enterpriseUser = jdbcService.findOne(EnterpriseUser.class, new String[]{
                "userId",
                "enterpriseId"
            }, new Object[]{
                user.getId(),
                4L
            });
            if(enterpriseUser != null){
                userSession.setDeptId(enterpriseUser.getDeptId());
            }
        }
        StpUtil.getTokenSession().set(Constants.USER_SESSION,userSession);
        return userSession;
    }
    public static UserSession getSession(){
        /*SessionContext sessionContext = SpringUtil.getBean(SessionContext.class);
        HttpServletRequest request = SpringUtil.getRequest();
        return sessionContext.getSession(request);*/
        //SysUser user = SecurityUtils.getLoginUser().getUser();
        UserSession session = new UserSession();
//        session.setEnterpriseId(user.getUserId());
//        session.setDeptId(user.getDeptId());
//        session.setUserId(user.getUserId());
        session.setEnterpriseId(1L);
        session.setDeptId(1L);
        session.setUserId(1L);
        return session;
    }
    public static User getUser(){
        UserSession session = getSession();
        JdbcService jdbcService = SpringUtil.getBean(JdbcService.class);
        return jdbcService.getById(User.class, session.getUserId());
    }

    public void deleteSession(HttpServletRequest request){
        //request.getSession().removeAttribute(Constants.USER_SESSION);
        //request.getSession().invalidate();
        StpUtil.logout();
    }

    public static void putUserSessionParams(Map<String,Object> params){
        params.put("curYear", DateUtil.format(new Date(),"yyyy"));
        params.put("curMonth", DateUtil.format(new Date(),"yyyy-MM"));
        params.put("curDate", DateUtil.format(new Date(),"yyyy-MM-dd"));
        params.put("curDateTime", DateUtil.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
        UserSession userSession = getSession();
        if(userSession != null){
            params.put("enterpriseId",userSession.getEnterpriseId());
            params.put("userId",userSession.getUserId());
            params.put("deptId",userSession.getDeptId() == null ? "" :userSession.getDeptId());
        }
    }

    public static String getTemplateValue(String value){
        Map<String,Object> params = new HashMap<>();
        SessionContext.putUserSessionParams(params);
        return TemplateUtil.getValue(value,params);
    }
}
