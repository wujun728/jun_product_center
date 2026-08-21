package com.ruoyi.sms.service;

import com.ruoyi.common.exception.base.BaseException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.sms.context.SendSmsContext;
import com.ruoyi.sms.api.ISmsService;
import com.ruoyi.sms.config.SmsConfig;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 短信发送实现
 *
 * @author wocurr.com
 */
@Service
public class SmsService implements ISmsService {

    @Autowired
    private SmsConfig smsConfig;

    @Override
    public boolean send(String phone, String templateCode, Map<String, String> params) {
        if(StringUtils.isBlank(phone)) {
            throw new BaseException("手机号不能为空！");
        }
        return send(Arrays.asList(phone), templateCode, params);
    }

    @Override
    public boolean send(List<String> phones, String templateCode, Map<String, String> params) {
        if(CollectionUtils.isEmpty(phones)) {
            throw new BaseException("手机号不能为空！");
        }
        if (StringUtils.isBlank(templateCode)) {
            throw new BaseException("模板不能为空！");
        }
        return SendSmsContext.getSmsHandler(smsConfig.getUseRoute()).send(phones, templateCode, params);
    }
}
