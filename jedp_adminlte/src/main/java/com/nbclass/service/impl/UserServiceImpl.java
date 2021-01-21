package com.nbclass.service.impl;

import com.nbclass.mapper.UserMapper;
import com.nbclass.mapper.UserRoleMapper;
import com.nbclass.model.User;
import com.nbclass.model.UserRole;
import com.nbclass.service.UserService;
import com.nbclass.util.ResultUtil;
import com.nbclass.vo.UserOnlineVo;
import com.nbclass.vo.base.ResponseVo;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.*;

/**
 * @version V1.0
 * @date 2018年7月11日
 * @author Wujun
 */
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private RedisSessionDAO redisSessionDAO;

    @Autowired
    private SessionManager sessionManager;

    @Autowired
    private RedisCacheManager redisCacheManager;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public User selectByUsername(String username) {
        return userMapper.selectByUsername(username);
    }

    @Override
    public int register(User user) {
        int a = userMapper.insert(user);
        return a;
    }

    @Override
    public void updateLastLoginTime(User user) {
        userMapper.updateLastLoginTime(user);
    }

    @Override
    public List<User> selectUsers(User user) {
        return userMapper.selectUsers(user);
    }

    @Override
    public User selectByUserId(String userId) {
        return userMapper.selectByUserId(userId);
    }

    @Override
    public int updateByUserId(User user) {
        return userMapper.updateByUserId(user);
    }

    @Override
    public int updateStatusBatch(List<String> userIds,Integer status) {
        Map<String,Object> params = new HashMap<String,Object>(2);
        params.put("userIds",userIds);
        params.put("status",status);
        return userMapper.updateStatusBatch(params);
    }

    @Override
    public ResponseVo addAssignRole(String userId, List<String> roleIds) {
        try{
            UserRole userRole = new UserRole();
            userRole.setUserId(userId);
            userRoleMapper.delete(userRole);
            for(String roleId :roleIds){
                userRole.setId(null);
                userRole.setRoleId(roleId);
                userRoleMapper.insert(userRole);
            }
            return ResultUtil.success("分配角色成功");
        }catch(Exception e){
            return ResultUtil.error("分配角色失败");
        }
    }

    @Override
    public int updateUserByPrimaryKey(User user) {
        return userMapper.updateByPrimaryKey(user);
    }

    @Override
    public List<UserOnlineVo> selectOnlineUsers(UserOnlineVo userVo) {
        // 因为我们是用redis实现了shiro的session的Dao,而且是采用了shiro+redis这个插件
        // 所以从spring容器中获取redisSessionDAO
        // 来获取session列表.
        Collection<Session> sessions = redisSessionDAO.getActiveSessions();
        Iterator<Session> it = sessions.iterator();
        List<UserOnlineVo> onlineUserList = new ArrayList<UserOnlineVo>();
        // 遍历session
        while (it.hasNext()) {
            // 这是shiro已经存入session的
            // 现在直接取就是了
            Session session = it.next();
            //标记为已提出的不加入在线列表
            if(session.getAttribute("kickout") != null) {
                continue;
            }
            UserOnlineVo onlineUser = getSessionBo(session);
            if(onlineUser!=null){
                /*用户名搜索*/
                if(StringUtils.isNotBlank(userVo.getUsername())){
                    if(onlineUser.getUsername().contains(userVo.getUsername()) ){
                        onlineUserList.add(onlineUser);
                    }
                }else{
                    onlineUserList.add(onlineUser);
                }
            }
        }
        return onlineUserList;
    }

    @Override
    public void kickout(Serializable sessionId, String username) {
        getSessionBysessionId(sessionId).setAttribute("kickout", true);
        //读取缓存,找到并从队列中移除
        Cache<String, Deque<Serializable>> cache = redisCacheManager.getCache(redisCacheManager.getKeyPrefix()+username);
        Deque<Serializable> deques = cache.get(username);
        for(Serializable deque : deques){
            if(sessionId.equals(deque)){
                deques.remove(deque);
                break;
            }
        }
        cache.put(username,deques);
    }


    private Session getSessionBysessionId(Serializable sessionId){
        Session kickoutSession = sessionManager.getSession(new DefaultSessionKey(sessionId));
        return kickoutSession;
    }

    private UserOnlineVo getSessionBo(Session session){
        //获取session登录信息。
        Object obj = session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
        if(null == obj){
            return null;
        }
        //确保是 SimplePrincipalCollection对象。
        if(obj instanceof SimplePrincipalCollection){
            SimplePrincipalCollection spc = (SimplePrincipalCollection)obj;
            obj = spc.getPrimaryPrincipal();
            if(null != obj && obj instanceof User){
                User user = (User) obj;
                //存储session + user 综合信息
                UserOnlineVo userBo = new UserOnlineVo();
                //最后一次和系统交互的时间
                userBo.setLastAccess(session.getLastAccessTime());
                //主机的ip地址
                userBo.setHost(user.getLoginIpAddress());
                //session ID
                userBo.setSessionId(session.getId().toString());
                //最后登录时间
                userBo.setLastLoginTime(user.getLastLoginTime());
                //回话到期 ttl(ms)
                userBo.setTimeout(session.getTimeout());
                //session创建时间
                userBo.setStartTime(session.getStartTimestamp());
                //是否踢出
                userBo.setSessionStatus(false);
                /*用户名*/
                userBo.setUsername(user.getUsername());
                return userBo;
            }
        }
        return null;
    }


}
