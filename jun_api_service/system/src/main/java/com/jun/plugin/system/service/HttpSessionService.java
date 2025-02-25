package com.jun.plugin.system.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.jun.plugin.common.constant.Constant;
import com.jun.plugin.common.service.RedisService;
import com.jun.plugin.system.entity.SysUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * session管理器
 *
 * @author wujun
 * @version V1.0
 * @date 2020年3月18日
 */
@Service
public class HttpSessionService {
    @Resource
    private RedisService redisService;
    @Resource
    private UserRoleService userRoleService;
    @Resource
    private RolePermissionService rolePermissionService;
    @Resource
    private HttpServletRequest request;
    @Resource
    @Lazy
    private PermissionService permissionService;
    @Resource
    private RoleService roleService;

    @Value("${spring.redis.key.prefix.userToken}")
    private String userTokenPrefix;

    @Value("${spring.redis.key.expire.userToken}")
    private int exire;

    @Value("${spring.redis.key.prefix.permissionRefresh}")
    private String redisPermissionRefreshKey;
    
    @Value("${spring.redis.key.expire.permissionRefresh}")
    private Long redisPermissionRefreshExpire;

    public String createTokenAndUser(SysUser user, List<String> roles, Set<String> permissions, String jwtToken) {
        //方便根据id找到redis的key， 修改密码/退出登陆 方便使用
        //String token = jwtToken + "#" + user.getId();
        String token = jwtToken;
        JSONObject sessionInfo = new JSONObject();
        sessionInfo.put(Constant.USERID_KEY, user.getId());
        sessionInfo.put(Constant.USERNAME_KEY, user.getUsername());
        sessionInfo.put(Constant.USERPHONE_KEY, user.getPhone());
        sessionInfo.put(Constant.USERREALNAME_KEY, user.getRealName());
        sessionInfo.put(Constant.USERDEPTID_KEY, user.getDeptId());
        sessionInfo.put(Constant.USERDEPTNO_KEY, user.getDeptNo());
        sessionInfo.put(Constant.USERDEPTNAME_KEY, user.getDeptName());
        sessionInfo.put(Constant.ROLES_KEY, roles);
        sessionInfo.put(Constant.USER_KEY, user);
        sessionInfo.put(Constant.PERMISSIONS_KEY, permissions);
        String key = userTokenPrefix + token;
        //设置该用户已登录的token
        redisService.setAndExpire(key, sessionInfo.toJSONString(), exire);
        redisService.setAndExpire(userTokenPrefix+jwtToken, sessionInfo.toJSONString(), exire);

        //登陆后删除权限刷新标志
        redisService.del(redisPermissionRefreshKey + user.getId());
        return token;
    }

    /**
     * 根据token获取userid
     *
     * @param token token
     * @return userid
     */
    public static String getUserIdByToken(String token) {
        if (StringUtils.isBlank(token) || !token.contains("#")) {
            return "";
        } else {
            return token.substring(token.indexOf("#") + 1);
        }
    }

    /**
     * 获取参数中的token
     *
     * @return token
     */
    public String getTokenFromHeader() {
        String token = null;
        try {
            token = request.getHeader(Constant.ACCESS_TOKEN);
            //如果header中不存在token，则从参数中获取token
            if (StringUtils.isBlank(token)) {
                token = request.getParameter(Constant.ACCESS_TOKEN);
            }
        } catch (IllegalStateException  e) {
            return null;
        }
        return token;
    }

    /**
     * 获取当前session信息
     *
     * @return session信息
     */
    public JSONObject getCurrentSession() {
        String token = getTokenFromHeader();
        if (null != token) {
            if (redisService.exists(userTokenPrefix + token)) {
                String sessionInfoStr = redisService.get(userTokenPrefix + token);
                return JSON.parseObject(sessionInfoStr);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * 获取当前session信息 username
     *
     * @return username
     */
    public String getCurrentUsername() {
        if (getCurrentSession() != null) {
            return getCurrentSession().getString(Constant.USERNAME_KEY);
        } else {
            return null;
        }
    }
    /**
     * 获取当前session信息 username
     *
     * @return username
     */
    public String getCurrentUserRealname() {
    	if (getCurrentSession() != null) {
    		return getCurrentSession().getString(Constant.USERREALNAME_KEY);
    	} else {
    		return null;
    	}
    }

    /**
     * 获取当前session信息 UserId
     *
     * @return UserId
     */
//    @Cacheable(cacheNames = "cache1m")
    public String getCurrentUserId() {
        if (getCurrentSession() != null) {
            return getCurrentSession().getString(Constant.USERID_KEY);
        } else {
            return null;
        }
    }


    /**
     * 使当前用户的token失效
     */
    public void abortUserByToken() {
        String token = getTokenFromHeader();
        redisService.del(userTokenPrefix + token);
    }

    /**
     * 使所有用户的token失效
     */
    public void abortAllUserByToken() {
        String token = getTokenFromHeader();
        String userId = getUserIdByToken(token);
        redisService.delKeys(userTokenPrefix + "*#" + userId);
    }

    /**
     * 使用户的token失效
     */
    public void abortUserById(String userId) {
        redisService.delKeys(userTokenPrefix + "*#" + userId);
    }

    /**
     * 使多个用户的token失效
     */
    public void abortUserByUserIds(List<String> userIds) {
        if (CollectionUtils.isNotEmpty(userIds)) {
            for (String id : userIds) {
                redisService.delKeys(userTokenPrefix + "*#" + id);
            }

        }
    }

    /**
     * 根据用户id， 刷新redis用户权限
     *
     * @param userId userId
     */
    public void refreshUerId(String userId) {
//        redisService.delKeys(userTokenPrefix + "*#" + userId);


        Set<String> keys = redisService.keys("#" + userId);
        //如果修改了角色/权限， 那么刷新权限
        for (String key : keys) {
            JSONObject redisSession = JSON.parseObject(redisService.get(key));

            List<String> roleNames = getRolesByUserId(userId);
            if (!CollectionUtils.isEmpty(roleNames)) {
                redisSession.put(Constant.ROLES_KEY, roleNames);
            }
            Set<String> permissions = getPermissionsByUserId(userId);
            redisSession.put(Constant.PERMISSIONS_KEY, permissions);
            Long redisTokenKeyExpire = redisService.getExpire(key);
            //刷新token绑定的角色权限
            redisService.setAndExpire(key, redisSession.toJSONString(), redisTokenKeyExpire);

        }
    }

    /**
     * 根据角色id， 刷新redis用户权限
     *
     * @param roleId roleId
     */
    public void refreshRolePermission(String roleId) {
        List<String> userIds = userRoleService.getUserIdsByRoleId(roleId);
        if (!CollectionUtils.isEmpty(userIds)) {
            userIds.parallelStream().forEach(this::refreshUerId);
        }
    }

    /**
     * 根据权限id， 刷新redis用户权限
     *
     * @param permissionId permissionId
     */
    public void refreshPermission(String permissionId) {
        List<String> userIds = permissionService.getUserIdsById(permissionId);
        if (!CollectionUtils.isEmpty(userIds)) {
            userIds.parallelStream().forEach(this::refreshUerId);
        }
    }

    private String getJWTToken(String username) {
        Map<String, Object> map = new HashMap<String, Object>() {
            private static final long serialVersionUID = 1L;
            {
                put("uid", Integer.parseInt("123"));
                put("expire_time", System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 15);
            }
        };
        map.put("userId",IdUtil.fastSimpleUUID());
        map.put("role", 0);
        map.put("pwd",SecureUtil.md5("123456"));
        String token = JWTUtil.createToken(map, "1234".getBytes());
        System.out.println(token);
        return token;
    }
    /**
     * 生成随机的token
     *
     * @return token
     */
    @Deprecated
    private String getRandomToken() {
            Random random = new Random();
            StringBuilder randomStr = new StringBuilder();

            // 根据length生成相应长度的随机字符串
            for (int i = 0; i < 32; i++) {
                String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";

                //输出字母还是数字
                if ("char".equalsIgnoreCase(charOrNum)) {
                    //输出是大写字母还是小写字母
                    int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                    randomStr.append((char) (random.nextInt(26) + temp));
                } else {
                    randomStr.append(random.nextInt(10));
                }
            }

            return randomStr.toString();
        }


    private List<String> getRolesByUserId(String userId) {
        return roleService.getRoleNames(userId);
    }

    private Set<String> getPermissionsByUserId(String userId) {
        return permissionService.getPermissionsByUserId(userId);
    }

}
