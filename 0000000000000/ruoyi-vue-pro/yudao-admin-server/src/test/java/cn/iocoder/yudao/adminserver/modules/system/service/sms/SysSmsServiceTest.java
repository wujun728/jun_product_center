package cn.iocoder.yudao.adminserver.modules.system.service.sms;

import cn.hutool.core.map.MapUtil;
import cn.iocoder.yudao.adminserver.BaseMockitoUnitTest;
import cn.iocoder.yudao.framework.common.core.KeyValue;
import cn.iocoder.yudao.framework.common.enums.CommonStatusEnum;
import cn.iocoder.yudao.framework.common.enums.UserTypeEnum;
import cn.iocoder.yudao.framework.sms.core.client.SmsClient;
import cn.iocoder.yudao.framework.sms.core.client.SmsClientFactory;
import cn.iocoder.yudao.framework.sms.core.client.SmsCommonResult;
import cn.iocoder.yudao.framework.sms.core.client.dto.SmsReceiveRespDTO;
import cn.iocoder.yudao.framework.sms.core.client.dto.SmsSendRespDTO;
import cn.iocoder.yudao.adminserver.modules.system.dal.dataobject.sms.SysSmsTemplateDO;
import cn.iocoder.yudao.adminserver.modules.system.mq.message.sms.SysSmsSendMessage;
import cn.iocoder.yudao.adminserver.modules.system.mq.producer.sms.SysSmsProducer;
import cn.iocoder.yudao.adminserver.modules.system.service.sms.impl.SysSmsServiceImpl;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static cn.hutool.core.util.RandomUtil.randomEle;
import static cn.iocoder.yudao.adminserver.modules.system.enums.SysErrorCodeConstants.*;
import static cn.iocoder.yudao.framework.test.core.util.AssertUtils.assertServiceException;
import static cn.iocoder.yudao.framework.test.core.util.RandomUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

/**
 * {@link SysSmsServiceImpl} 的单元测试类
 *
 * @author 芋道源码
 */
public class SysSmsServiceTest extends BaseMockitoUnitTest {

    @InjectMocks
    private SysSmsServiceImpl smsService;

    @Mock
    private SysSmsTemplateService smsTemplateService;
    @Mock
    private SysSmsLogService smsLogService;
    @Mock
    private SysSmsProducer smsProducer;
    @Mock
    private SmsClientFactory smsClientFactory;

    /**
     * 发送成功，当短信模板开启时
     */
    @Test
    public void testSendSingleSms_successWhenSmsTemplateEnable() {
        // 准备参数
        String mobile = randomString();
        Long userId = randomLongId();
        Integer userType = randomEle(UserTypeEnum.values()).getValue();
        String templateCode = randomString();
        Map<String, Object> templateParams = MapUtil.<String, Object>builder().put("code", "1234")
                .put("op", "login").build();
        // mock SmsTemplateService 的方法
        SysSmsTemplateDO template = randomPojo(SysSmsTemplateDO.class, o -> {
            o.setStatus(CommonStatusEnum.ENABLE.getStatus());
            o.setContent("验证码为{code}, 操作为{op}");
            o.setParams(Lists.newArrayList("code", "op"));
        });
        when(smsTemplateService.getSmsTemplateByCodeFromCache(eq(templateCode))).thenReturn(template);
        String content = randomString();
        when(smsTemplateService.formatSmsTemplateContent(eq(template.getContent()), eq(templateParams)))
                .thenReturn(content);
        // mock SmsLogService 的方法
        Long smsLogId = randomLongId();
        when(smsLogService.createSmsLog(eq(mobile), eq(userId), eq(userType), eq(Boolean.TRUE), eq(template),
                eq(content), eq(templateParams))).thenReturn(smsLogId);

        // 调用
        Long resultSmsLogId = smsService.sendSingleSms(mobile, userId, userType, templateCode, templateParams);
        // 断言
        assertEquals(smsLogId, resultSmsLogId);
        // 断言调用
        verify(smsProducer, times(1)).sendSmsSendMessage(eq(smsLogId), eq(mobile),
                eq(template.getChannelId()), eq(template.getApiTemplateId()),
                eq(Lists.newArrayList(new KeyValue<>("code", "1234"), new KeyValue<>("op", "login"))));
    }

    /**
     * 发送成功，当短信模板关闭时
     */
    @Test
    public void testSendSingleSms_successWhenSmsTemplateDisable() {
        // 准备参数
        String mobile = randomString();
        Long userId = randomLongId();
        Integer userType = randomEle(UserTypeEnum.values()).getValue();
        String templateCode = randomString();
        Map<String, Object> templateParams = MapUtil.<String, Object>builder().put("code", "1234")
                .put("op", "login").build();
        // mock SmsTemplateService 的方法
        SysSmsTemplateDO template = randomPojo(SysSmsTemplateDO.class, o -> {
            o.setStatus(CommonStatusEnum.DISABLE.getStatus());
            o.setContent("验证码为{code}, 操作为{op}");
            o.setParams(Lists.newArrayList("code", "op"));
        });
        when(smsTemplateService.getSmsTemplateByCodeFromCache(eq(templateCode))).thenReturn(template);
        String content = randomString();
        when(smsTemplateService.formatSmsTemplateContent(eq(template.getContent()), eq(templateParams)))
                .thenReturn(content);
        // mock SmsLogService 的方法
        Long smsLogId = randomLongId();
        when(smsLogService.createSmsLog(eq(mobile), eq(userId), eq(userType), eq(Boolean.FALSE), eq(template),
                eq(content), eq(templateParams))).thenReturn(smsLogId);

        // 调用
        Long resultSmsLogId = smsService.sendSingleSms(mobile, userId, userType, templateCode, templateParams);
        // 断言
        assertEquals(smsLogId, resultSmsLogId);
        // 断言调用
        verify(smsProducer, times(0)).sendSmsSendMessage(anyLong(), anyString(),
                anyLong(), any(), anyList());
    }

    @Test
    public void testCheckSmsTemplateValid_notExists() {
        // 准备参数
        String templateCode = randomString();
        // mock 方法

        // 调用，并断言异常
        assertServiceException(() -> smsService.checkSmsTemplateValid(templateCode),
                SMS_TEMPLATE_NOT_EXISTS);
    }

    @Test
    public void testBuildTemplateParams_paramMiss() {
        // 准备参数
        SysSmsTemplateDO template = randomPojo(SysSmsTemplateDO.class,
                o -> o.setParams(Lists.newArrayList("code")));
        Map<String, Object> templateParams = new HashMap<>();
        // mock 方法

        // 调用，并断言异常
        assertServiceException(() -> smsService.buildTemplateParams(template, templateParams),
                SMS_SEND_MOBILE_TEMPLATE_PARAM_MISS, "code");
    }

    @Test
    public void testCheckMobile_notExists() {
        // 准备参数
        // mock 方法

        // 调用，并断言异常
        assertServiceException(() -> smsService.checkMobile(null),
                SMS_SEND_MOBILE_NOT_EXISTS);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testDoSendSms() {
        // 准备参数
        SysSmsSendMessage message = randomPojo(SysSmsSendMessage.class);
        // mock SmsClientFactory 的方法
        SmsClient smsClient = spy(SmsClient.class);
        when(smsClientFactory.getSmsClient(eq(message.getChannelId()))).thenReturn(smsClient);
        // mock SmsClient 的方法
        SmsCommonResult<SmsSendRespDTO> sendResult = randomPojo(SmsCommonResult.class, SmsSendRespDTO.class);
        when(smsClient.sendSms(eq(message.getLogId()), eq(message.getMobile()), eq(message.getApiTemplateId()),
                eq(message.getTemplateParams()))).thenReturn(sendResult);

        // 调用
        smsService.doSendSms(message);
        // 断言
        verify(smsLogService, times(1)).updateSmsSendResult(eq(message.getLogId()),
                eq(sendResult.getCode()), eq(sendResult.getMsg()), eq(sendResult.getApiCode()),
                eq(sendResult.getApiMsg()), eq(sendResult.getApiRequestId()), eq(sendResult.getData().getSerialNo()));
    }

    @Test
    public void testReceiveSmsStatus() throws Throwable {
        // 准备参数
        String channelCode = randomString();
        String text = randomString();
        // mock SmsClientFactory 的方法
        SmsClient smsClient = spy(SmsClient.class);
        when(smsClientFactory.getSmsClient(eq(channelCode))).thenReturn(smsClient);
        // mock SmsClient 的方法
        List<SmsReceiveRespDTO> receiveResults = randomPojoList(SmsReceiveRespDTO.class);

        // 调用
        smsService.receiveSmsStatus(channelCode, text);
        // 断言
        receiveResults.forEach(result -> {
            smsLogService.updateSmsReceiveResult(eq(result.getLogId()), eq(result.getSuccess()),
                    eq(result.getReceiveTime()), eq(result.getErrorCode()), eq(result.getErrorCode()));
        });
    }

}
