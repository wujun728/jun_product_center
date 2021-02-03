package com.wangzhixuan.event;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import com.wangzhixuan.commons.utils.RuntimeUtils;

/**
 * WebHook 异步消息
 * @author L.cm
 */
@Component
@EnableAsync(mode = AdviceMode.ASPECTJ)
@SuppressWarnings("unchecked")
public class WebHooksListener implements ApplicationListener<WebHooksEvent> {

	@Value("${git.hook.pwd}")
	private String hookPwd;
	@Value("${git.hook.script_path}")
	private String scriptPath;
	/**
	 * 新线程需要同时 @EnableAsync + @Async
	 */
	@Async
	@Override
	public void onApplicationEvent(WebHooksEvent event) {
		Map<String, Object> hookMap = (Map<String, Object>) event.getSource();
		Object password = hookMap.get("password");
		// 密码不一致
		if (null == password || !password.equals(hookPwd)) {
			return;
		}
		Object hookName = hookMap.get("hook_name");
		if (null == hookName || !hookName.equals("push_hooks")) {
			return;
		}
		// 执行自动部署脚本
		RuntimeUtils.runShell(scriptPath);
	}

}