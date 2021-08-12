package cn.iocoder.yudao.adminserver.modules.system.job.auth;

import cn.iocoder.yudao.framework.quartz.core.handler.JobHandler;
import cn.iocoder.yudao.adminserver.modules.system.service.auth.SysUserSessionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 用户 Session 超时 Job
 *
 * @author 願
 */
@Component
@Slf4j
public class SysUserSessionTimeoutJob implements JobHandler {

    @Resource
    private SysUserSessionService sysUserSessionService;

    @Override
    public String execute(String param) throws Exception {
        // 执行过期
        Long timeoutCount = sysUserSessionService.clearSessionTimeout();
        // 返回结果，记录每次的超时数量
        return String.format("移除在线会话数量为 %s 个", timeoutCount);
    }

}
