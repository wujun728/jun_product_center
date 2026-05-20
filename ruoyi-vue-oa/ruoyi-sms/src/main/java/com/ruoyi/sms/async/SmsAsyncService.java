package com.ruoyi.sms.async;


import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.exception.base.BaseException;
import com.ruoyi.mq.domain.AsyncLog;
import com.ruoyi.mq.execute.IAsyncHandler;
import com.ruoyi.sms.api.ISmsService;
import com.ruoyi.sms.domain.SmsAsyncDTO;
import com.ruoyi.system.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 消息通知异步服务
 * @Author wocurr.com
 */
@Slf4j
@Service
public class SmsAsyncService implements IAsyncHandler {

    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private ISmsService smsService;

    @Override
    public void doAsync(AsyncLog asyncLog) {
        log.info("收到消息，消息内容：{}", asyncLog);
        try {
            SmsAsyncDTO dto = getSmsAsyncDTO(asyncLog);
            List<String> userPhones = getUserPhone(dto.getRecIds());
            if (CollectionUtils.isEmpty(userPhones)) {
                log.error("用户手机号为空，消息内容：{}", dto);
                return;
            }
            smsService.send(userPhones, dto.getTemplateCode(), dto.getParams());
        } catch (Exception e) {
            log.error("异步处理消息通知失败，异常原因：", e);
            throw new BaseException("异步处理消息通知失败:" + e.getMessage());
        }
    }

    /**
     * 获取消息通知
     * @return
     */
    private SmsAsyncDTO getSmsAsyncDTO(AsyncLog asyncLog) {
        if (StringUtils.isBlank(asyncLog.getMessageContent())) {
            throw new BaseException("消息内容不能为空！");
        }
        return JSONObject.parseObject(asyncLog.getMessageContent(), SmsAsyncDTO.class);
    }

    /**
     * 获取用户手机号
     * @return
     */
    private List<String> getUserPhone(List<String> recIds) {
        List<SysUser> users = sysUserService.selectByUserIds(recIds);
        return users.stream().map(SysUser::getPhonenumber).collect(Collectors.toList());
    }
}
