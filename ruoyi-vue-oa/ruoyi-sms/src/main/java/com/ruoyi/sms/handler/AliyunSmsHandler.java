package com.ruoyi.sms.handler;

import javax.annotation.PostConstruct;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.teaopenapi.models.Config;
import com.ruoyi.sms.config.SmsConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.exception.base.BaseException;
import com.ruoyi.common.utils.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * 阿里云短信处理类
 *
 * @author wocurr.com
 */
@Slf4j
@Service
public class AliyunSmsHandler extends AbstractSmsHandler {

    @Autowired
    private SmsConfig smsConfig;
    private Client client;

    /**
     * 初始化阿里云短信服务客户端
     */
    @PostConstruct
    public void initClient() {
        try {
            Config config = new Config()
                    .setAccessKeyId(smsConfig.getAliyunAccesskeyId())
                    .setAccessKeySecret(smsConfig.getAliyunAccesskeySecret())
                    .setEndpoint(smsConfig.getAliyunEndpoint());
            this.client = new Client(config);
        } catch (Exception e) {
            log.error("初始化阿里云短信服务客户端失败", e);
        }
    }

    @Override
    public boolean send(List<String> phones, String templateCode, Map<String, String> params) {
        SendSmsRequest request = new SendSmsRequest()
                .setPhoneNumbers(StringUtils.join(phones, Constants.COMMA))
                .setTemplateCode(templateCode)
                .setTemplateParam(getQueryParameter(params))
                .setSignName(smsConfig.getAliyunSignName());
        try {
            SendSmsResponse response = client.sendSms(request);
            log.info("发送短信成功：" + JSONObject.toJSONString(response));
        } catch (Exception e) {
            log.error("短信发送失败，phone：{}，模板：{}", phones, templateCode);
            log.error(e.getMessage(), e);
            throw new BaseException("短信发送失败，请稍后再试！");
        }
        return true;
    }

    /**
     * 获取参数json对象字符串
     *
     * @param map
     * @return
     */
    private String getQueryParameter(Map<String, String> map) {
        if (MapUtils.isEmpty(map)) {
            return "";
        }
        JSONObject parameter = new JSONObject(map);
        return JSON.toJSONString(parameter);
    }

}
