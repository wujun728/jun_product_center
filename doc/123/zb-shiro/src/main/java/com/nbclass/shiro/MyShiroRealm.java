package com.nbclass.shiro;

import com.nbclass.model.User;
import com.nbclass.service.PermissionService;
import com.nbclass.service.RoleService;
import com.nbclass.service.UserService;
import com.nbclass.util.CoreConst;
import com.nbclass.util.IpUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.util.ByteSource;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @version V1.0
 * @date 2018年7月11日
 * @author superzheng
 */
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private RedisSessionDAO redisSessionDAO;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        if(principals == null){
            throw new AuthorizationException("principals should not be null");
        }
        User user = (User) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(roleService.findRoleByUserId(user.getUserId()));
        info.setStringPermissions(permissionService.findPermsByUserId(user.getUserId()));
        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取用户的输入的账号.
        String username = (String)token.getPrincipal();
        User user = userService.selectByUsername(username);
        if(user==null) {
            throw new UnknownAccountException();
        }
        if (CoreConst.STATUS_INVALID.equals(user.getStatus())) {
            // 帐号锁定
            throw new LockedAccountException();
        }
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        // 把ip放入user存入redis缓存里
        user.setLoginIpAddress(IpUtil.getIpAddr(request));
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user,
                user.getPassword(),
                ByteSource.Util.bytes(user.getCredentialsSalt()),
                getName()
        );
        return authenticationInfo;
    }

    /**清除认证信息*/
    public void removeCachedAuthenticationInfo(List<String> userIds) {
        if(null == userIds || userIds.size() == 0)	{
            return ;
        }
        List<SimplePrincipalCollection> list = getSpcListByUserIds(userIds);
        RealmSecurityManager securityManager =
                (RealmSecurityManager) SecurityUtils.getSecurityManager();
        MyShiroRealm realm = (MyShiroRealm)securityManager.getRealms().iterator().next();
        for (SimplePrincipalCollection simplePrincipalCollection : list) {
            realm.clearCachedAuthenticationInfo(simplePrincipalCollection);
        }
    }

    /**
     * 根据userId 清除当前session存在的用户的权限缓存
     * @param userIds 已经修改了权限的userId
     */
    public void clearAuthorizationByUserId(List<String> userIds){
        if(null == userIds || userIds.size() == 0)	{
            return ;
        }
        List<SimplePrincipalCollection> list = getSpcListByUserIds(userIds);
        RealmSecurityManager securityManager =
                (RealmSecurityManager) SecurityUtils.getSecurityManager();
        MyShiroRealm realm = (MyShiroRealm)securityManager.getRealms().iterator().next();
        for (SimplePrincipalCollection simplePrincipalCollection : list) {
            realm.clearCachedAuthorizationInfo(simplePrincipalCollection);
        }
    }

    /**
     * 根据用户id获取所有spc
     * @param userIds 已经修改了权限的userId
     */
    private  List<SimplePrincipalCollection> getSpcListByUserIds(List<String> userIds){
        //获取所有session
        Collection<Session> sessions = redisSessionDAO.getActiveSessions();
        //定义返回
        List<SimplePrincipalCollection> list = new ArrayList<SimplePrincipalCollection>();
        for (Session session:sessions){
            //获取session登录信息。
            Object obj = session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
            if(null != obj && obj instanceof SimplePrincipalCollection){
                //强转
                SimplePrincipalCollection spc = (SimplePrincipalCollection)obj;
                //判断用户，匹配用户ID。
                obj = spc.getPrimaryPrincipal();
                if(null != obj && obj instanceof User){
                    User user = (User) obj;
                    System.out.println("user:"+user);
                    //比较用户ID，符合即加入集合
                    if(null != user && userIds.contains(user.getUserId())){
                        list.add(spc);
                    }
                }
            }
        }
        return list;
    }
}
