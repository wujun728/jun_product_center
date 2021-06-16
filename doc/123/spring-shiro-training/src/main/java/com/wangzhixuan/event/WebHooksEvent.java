package com.wangzhixuan.event;
import org.springframework.context.ApplicationEvent;

/**
 * WebHooks事件
 * @author L.cm
 */
public class WebHooksEvent extends ApplicationEvent {

	private static final long serialVersionUID = 3443109525461436619L;

	public WebHooksEvent(Object source) {
		super(source);
	}

}