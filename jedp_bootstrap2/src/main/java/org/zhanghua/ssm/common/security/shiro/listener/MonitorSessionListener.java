package org.zhanghua.ssm.common.security.shiro.listener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 用户测试
 * 
 * 监听session（创建、停止、过期）
 * 
 * @author Wujun
 * 
 */
public class MonitorSessionListener implements SessionListener {

	/**
	 * 日志对象
	 */
	protected static Logger logger = LoggerFactory.getLogger(MonitorSessionListener.class);

	@Override
	public void onStart(Session session) {
		logger.debug("会话创建：" + session.getId());
	}

	@Override
	public void onStop(Session session) {
		logger.debug("会话停止：" + session.getId());

	}

	@Override
	public void onExpiration(Session session) {
		logger.debug("会话过期：" + session.getId());
	}

}
