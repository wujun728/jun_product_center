package sy.listener.base;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import ognl.OgnlRuntime;

/**
 * 
 * @author Wujun
 * 
 */
public class OgnlSecurityListener implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {

	public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {

	}

	public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {

	}

	public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {

	}

	public void sessionCreated(HttpSessionEvent httpSessionEvent) {

	}

	public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {

	}

	public void contextDestroyed(ServletContextEvent servletContextEvent) {

	}

	public void contextInitialized(ServletContextEvent servletContextEvent) {
		OgnlRuntime.setSecurityManager(null);
	}

}
