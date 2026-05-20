package com.ruoyi.sms.handler;

import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;

import com.ruoyi.common.exception.base.BaseException;
import com.ruoyi.sms.config.SmsConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;

/**
 * 腾讯短信处理类
 *
 * @author wocurr.com
 */
@Slf4j
@Service
public class TencentSmsHandler extends AbstractSmsHandler {

    @Autowired
    private SmsConfig smsConfig;

    private SmsClient client;

    private static final String ENDOPINT = "sms.tencentcloudapi.com";
    private static final String SIGNMETHOD = "HmacSHA256";
    private static final String REGION = "ap-guangzhou";

    @PostConstruct
    public void initClient() {
        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setReqMethod("POST");
        /*
         * SDK 有默认的超时时间，非必要请不要进行调整 如有需要请在代码中查阅以获取最新的默认值
         */
        httpProfile.setConnTimeout(60);
        /*
         * SDK 会自动指定域名，通常无需指定域名，但访问金融区的服务时必须手动指定域名 例如 SMS 的上海金融区域名为
         * sms.ap-shanghai-fsi.tencentcloudapi.com
         */
        httpProfile.setEndpoint(ENDOPINT);
        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setSignMethod(SIGNMETHOD);
        clientProfile.setHttpProfile(httpProfile);
        this.client = new SmsClient(new Credential(smsConfig.getTencentAccesskeyId(), smsConfig.getTencentAccesskeySecret()), REGION, clientProfile);
    }


    @Override
    public boolean send(List<String> phones, String templateCode, Map<String, String> params) {
        try {
            SendSmsRequest request = getRequest();
            request.setTemplateID(templateCode);
            String[] phoneNumbers = phones.stream().map(phone -> "+86" + phone).toArray(String[]::new);
            request.setPhoneNumberSet(phoneNumbers);
            request.setTemplateParamSet(getParams(params));
            SendSmsResponse response = client.SendSms(request);
            log.info("发送短信成功：" + SendSmsResponse.toJsonString(response));
        } catch (Exception e) {
            log.error("短信发送失败，phone：{}，模板：{}", phones, templateCode);
            log.error(e.getMessage(), e);
            throw new BaseException("短信发送失败，请稍后再试！");
        }
        return true;
    }

    private SendSmsRequest getRequest() {
        SendSmsRequest request = new SendSmsRequest();
        // 签名
        request.setSign(smsConfig.getTencentSignName());
        // 短信应用 ID: 在 [短信控制台] 添加应用后生成的实际 SDKAppID
        request.setSmsSdkAppid(smsConfig.getTencentAppid());
        // 国际/港澳台短信 senderid: 国内短信填空，默认未开通，如需开通请联系 [sms helper]
        request.setSenderId("");
        return request;
    }

    private String[] getParams(Map<String, String> params) {
        if (MapUtils.isEmpty(params)) {
            return new String[0];
        }
        return params.values().toArray(new String[0]);
    }
}
