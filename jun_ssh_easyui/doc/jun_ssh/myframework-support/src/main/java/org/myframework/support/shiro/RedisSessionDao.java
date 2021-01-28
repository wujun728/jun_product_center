package org.myframework.support.shiro;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

/**
 * 
 * <ol>使用redis缓存来集中管理会话信息
 * <li>{@link  }</li>
 * 
 * </ol>
 * @see
 * @author Wujun
 * @since 1.0
 * @2016年4月25日
 *
 */
public class RedisSessionDao extends AbstractSessionDAO {
	
	/** 
	 * redis session key前缀 
	 */  
    private final String SHIRO_REDIS_SESSION = "shiro_redis_session:"; 
    
    //单位为秒，会话信息在REDIS中失效的时间
    private int globalSessionTimeout= 3600;
	
	ValueOperations<String, String> valueOperations;
	
	RedisTemplate<String, String> redisTemplate ;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass()); 
	
	public void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
		this.redisTemplate = redisTemplate;
		this.valueOperations = redisTemplate.opsForValue();
	}

	//更新会话信息及过期时间
	@Override  
    public void update(Session session) throws UnknownSessionException {  
    	if (session == null || session.getId() == null) {  
        	logger.error("session或者session id为空");
            return;  
        }
    	
    	//idleTime :当前时间距离上次多久没有进行操作
    	long idleTime = System.currentTimeMillis() - session.getLastAccessTime().getTime();
    	//expire :还剩多久会话讲超时
    	long expire = session.getTimeout() - idleTime  ;
    	if(expire>0)
    		valueOperations.set(getkey(session.getId()), SerializableUtils.serialize(session),
    				Long.valueOf(expire/1000).intValue(), TimeUnit.SECONDS);
    	else
    		delete(session);
    }
  
	//删除会话
    @Override  
    public void delete(Session session) {  
        if (session == null) {  
        	logger.error("session can not be null,delete failed"); 
            return;  
        }  
        Serializable id = session.getId();  
        if (id != null){
        	redisTemplate.delete(getkey(session.getId()));
        }
            
    }  
  
    //从redis读取缓存信息
    @Override  
    protected Session doReadSession(Serializable sessionId) {
    	String sessionStr = valueOperations.get(getkey(sessionId)) ;
    	return SerializableUtils.deserialize(sessionStr);
    }
    
    @Override  
    public Set<Session> getActiveSessions() {  
    	Set<String> keys = redisTemplate.keys(SHIRO_REDIS_SESSION+"*");
    	Set<Session> sessions = new HashSet<Session>();
    	for(String key:keys){
    		String sessionStr = valueOperations.get(key);
    		sessions.add(SerializableUtils.deserialize (sessionStr));
    	}
    	return sessions;
    }  
 

    //创建会话，并设置过期时间
	@Override  
    protected Serializable doCreate(Session session) {  
        Serializable sessionId = this.generateSessionId(session); 
        this.assignSessionId(session, sessionId);  
        valueOperations.set(getkey(session.getId()), SerializableUtils.serialize(session),
        		globalSessionTimeout, TimeUnit.SECONDS);
        return sessionId;
    }  
  

    private String getkey(Serializable sessionId) {  
        return this.SHIRO_REDIS_SESSION + sessionId;  
    }
}
