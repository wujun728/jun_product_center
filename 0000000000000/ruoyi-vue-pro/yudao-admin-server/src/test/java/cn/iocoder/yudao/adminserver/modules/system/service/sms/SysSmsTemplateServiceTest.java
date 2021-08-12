package cn.iocoder.yudao.adminserver.modules.system.service.sms;

import cn.iocoder.yudao.adminserver.BaseDbUnitTest;
import cn.iocoder.yudao.framework.common.enums.CommonStatusEnum;
import cn.iocoder.yudao.framework.common.exception.enums.GlobalErrorCodeConstants;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.sms.core.client.SmsClient;
import cn.iocoder.yudao.framework.sms.core.client.SmsClientFactory;
import cn.iocoder.yudao.framework.sms.core.client.SmsCommonResult;
import cn.iocoder.yudao.framework.sms.core.client.dto.SmsTemplateRespDTO;
import cn.iocoder.yudao.adminserver.modules.system.controller.sms.vo.template.SysSmsTemplateCreateReqVO;
import cn.iocoder.yudao.adminserver.modules.system.controller.sms.vo.template.SysSmsTemplateExportReqVO;
import cn.iocoder.yudao.adminserver.modules.system.controller.sms.vo.template.SysSmsTemplatePageReqVO;
import cn.iocoder.yudao.adminserver.modules.system.controller.sms.vo.template.SysSmsTemplateUpdateReqVO;
import cn.iocoder.yudao.adminserver.modules.system.dal.dataobject.sms.SysSmsChannelDO;
import cn.iocoder.yudao.adminserver.modules.system.dal.dataobject.sms.SysSmsTemplateDO;
import cn.iocoder.yudao.adminserver.modules.system.dal.mysql.sms.SysSmsTemplateMapper;
import cn.iocoder.yudao.adminserver.modules.system.enums.sms.SysSmsTemplateTypeEnum;
import cn.iocoder.yudao.adminserver.modules.system.mq.producer.sms.SysSmsProducer;
import cn.iocoder.yudao.adminserver.modules.system.service.sms.impl.SysSmsTemplateServiceImpl;
import cn.iocoder.yudao.framework.common.util.collection.ArrayUtils;
import cn.iocoder.yudao.framework.common.util.object.ObjectUtils;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static cn.hutool.core.bean.BeanUtil.getFieldValue;
import static cn.hutool.core.util.RandomUtil.randomEle;
import static cn.iocoder.yudao.adminserver.modules.system.enums.SysErrorCodeConstants.*;
import static cn.iocoder.yudao.framework.test.core.util.AssertUtils.assertPojoEquals;
import static cn.iocoder.yudao.framework.test.core.util.AssertUtils.assertServiceException;
import static cn.iocoder.yudao.framework.common.util.date.DateUtils.buildTime;
import static cn.iocoder.yudao.framework.common.util.object.ObjectUtils.max;
import static cn.iocoder.yudao.framework.test.core.util.RandomUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

/**
* {@link SysSmsTemplateServiceImpl} 的单元测试类
*
* @author 芋道源码
*/
@Import(SysSmsTemplateServiceImpl.class)
public class SysSmsTemplateServiceTest extends BaseDbUnitTest {

    @Resource
    private SysSmsTemplateServiceImpl smsTemplateService;

    @Resource
    private SysSmsTemplateMapper smsTemplateMapper;

    @MockBean
    private SysSmsChannelService smsChannelService;
    @MockBean
    private SmsClientFactory smsClientFactory;
    @MockBean
    private SmsClient smsClient;
    @MockBean
    private SysSmsProducer smsProducer;

    @Test
    @SuppressWarnings("unchecked")
    void testInitLocalCache() {
        // mock 数据
        SysSmsTemplateDO smsTemplate01 = randomSmsTemplateDO();
        smsTemplateMapper.insert(smsTemplate01);
        SysSmsTemplateDO smsTemplate02 = randomSmsTemplateDO();
        smsTemplateMapper.insert(smsTemplate02);

        // 调用
        smsTemplateService.initLocalCache();
        // 断言 deptCache 缓存
        Map<String, SysSmsTemplateDO> smsTemplateCache = (Map<String, SysSmsTemplateDO>) getFieldValue(smsTemplateService, "smsTemplateCache");
        assertEquals(2, smsTemplateCache.size());
        assertPojoEquals(smsTemplate01, smsTemplateCache.get(smsTemplate01.getCode()));
        assertPojoEquals(smsTemplate02, smsTemplateCache.get(smsTemplate02.getCode()));
        // 断言 maxUpdateTime 缓存
        Date maxUpdateTime = (Date) getFieldValue(smsTemplateService, "maxUpdateTime");
        assertEquals(max(smsTemplate01.getUpdateTime(), smsTemplate02.getUpdateTime()), maxUpdateTime);
    }

    @Test
    public void testParseTemplateContentParams() {
        // 准备参数
        String content = "正在进行登录操作{operation}，您的验证码是{code}";
        // mock 方法

        // 调用
        List<String> params = smsTemplateService.parseTemplateContentParams(content);
        // 断言
        assertEquals(Lists.newArrayList("operation", "code"), params);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testCreateSmsTemplate_success() {
        // 准备参数
        SysSmsTemplateCreateReqVO reqVO = randomPojo(SysSmsTemplateCreateReqVO.class, o -> {
            o.setContent("正在进行登录操作{operation}，您的验证码是{code}");
            o.setStatus(randomEle(CommonStatusEnum.values()).getStatus()); // 保证 status 的范围
            o.setType(randomEle(SysSmsTemplateTypeEnum.values()).getType()); // 保证 type 的 范围
        });
        // mock Channel 的方法
        SysSmsChannelDO channelDO = randomPojo(SysSmsChannelDO.class, o -> {
            o.setId(reqVO.getChannelId());
            o.setStatus(CommonStatusEnum.ENABLE.getStatus()); // 保证 status 开启，创建必须处于这个状态
        });
        when(smsChannelService.getSmsChannel(eq(channelDO.getId()))).thenReturn(channelDO);
        // mock 获得 API 短信模板成功
        when(smsClientFactory.getSmsClient(eq(reqVO.getChannelId()))).thenReturn(smsClient);
        when(smsClient.getSmsTemplate(eq(reqVO.getApiTemplateId()))).thenReturn(randomPojo(SmsCommonResult.class, SmsTemplateRespDTO.class,
                o -> o.setCode(GlobalErrorCodeConstants.SUCCESS.getCode())));

        // 调用
        Long smsTemplateId = smsTemplateService.createSmsTemplate(reqVO);
        // 断言
        assertNotNull(smsTemplateId);
        // 校验记录的属性是否正确
        SysSmsTemplateDO smsTemplate = smsTemplateMapper.selectById(smsTemplateId);
        assertPojoEquals(reqVO, smsTemplate);
        assertEquals(Lists.newArrayList("operation", "code"), smsTemplate.getParams());
        assertEquals(channelDO.getCode(), smsTemplate.getChannelCode());
        // 校验调用
        verify(smsProducer, times(1)).sendSmsTemplateRefreshMessage();
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testUpdateSmsTemplate_success() {
        // mock 数据
        SysSmsTemplateDO dbSmsTemplate = randomSmsTemplateDO();
        smsTemplateMapper.insert(dbSmsTemplate);// @Sql: 先插入出一条存在的数据
        // 准备参数
        SysSmsTemplateUpdateReqVO reqVO = randomPojo(SysSmsTemplateUpdateReqVO.class, o -> {
            o.setId(dbSmsTemplate.getId()); // 设置更新的 ID
            o.setContent("正在进行登录操作{operation}，您的验证码是{code}");
            o.setStatus(randomEle(CommonStatusEnum.values()).getStatus()); // 保证 status 的范围
            o.setType(randomEle(SysSmsTemplateTypeEnum.values()).getType()); // 保证 type 的 范围
        });
        // mock 方法
        SysSmsChannelDO channelDO = randomPojo(SysSmsChannelDO.class, o -> {
            o.setId(reqVO.getChannelId());
            o.setStatus(CommonStatusEnum.ENABLE.getStatus()); // 保证 status 开启，创建必须处于这个状态
        });
        when(smsChannelService.getSmsChannel(eq(channelDO.getId()))).thenReturn(channelDO);
        // mock 获得 API 短信模板成功
        when(smsClientFactory.getSmsClient(eq(reqVO.getChannelId()))).thenReturn(smsClient);
        when(smsClient.getSmsTemplate(eq(reqVO.getApiTemplateId()))).thenReturn(randomPojo(SmsCommonResult.class, SmsTemplateRespDTO.class,
                o -> o.setCode(GlobalErrorCodeConstants.SUCCESS.getCode())));

        // 调用
        smsTemplateService.updateSmsTemplate(reqVO);
        // 校验是否更新正确
        SysSmsTemplateDO smsTemplate = smsTemplateMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, smsTemplate);
        assertEquals(Lists.newArrayList("operation", "code"), smsTemplate.getParams());
        assertEquals(channelDO.getCode(), smsTemplate.getChannelCode());
        // 校验调用
        verify(smsProducer, times(1)).sendSmsTemplateRefreshMessage();
    }

    @Test
    public void testUpdateSmsTemplate_notExists() {
        // 准备参数
        SysSmsTemplateUpdateReqVO reqVO = randomPojo(SysSmsTemplateUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> smsTemplateService.updateSmsTemplate(reqVO), SMS_TEMPLATE_NOT_EXISTS);
    }

    @Test
    public void testDeleteSmsTemplate_success() {
        // mock 数据
        SysSmsTemplateDO dbSmsTemplate = randomSmsTemplateDO();
        smsTemplateMapper.insert(dbSmsTemplate);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbSmsTemplate.getId();

        // 调用
        smsTemplateService.deleteSmsTemplate(id);
       // 校验数据不存在了
       assertNull(smsTemplateMapper.selectById(id));
        // 校验调用
        verify(smsProducer, times(1)).sendSmsTemplateRefreshMessage();
    }

    @Test
    public void testDeleteSmsTemplate_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> smsTemplateService.deleteSmsTemplate(id), SMS_TEMPLATE_NOT_EXISTS);
    }

    @Test
    public void testGetSmsTemplatePage() {
       // mock 数据
       SysSmsTemplateDO dbSmsTemplate = randomPojo(SysSmsTemplateDO.class, o -> { // 等会查询到
           o.setType(SysSmsTemplateTypeEnum.PROMOTION.getType());
           o.setStatus(CommonStatusEnum.ENABLE.getStatus());
           o.setCode("yudaoyuanma");
           o.setContent("芋道源码");
           o.setApiTemplateId("yunai");
           o.setChannelId(1L);
           o.setCreateTime(buildTime(2021, 11, 11));
       });
       smsTemplateMapper.insert(dbSmsTemplate);
       // 测试 type 不匹配
       smsTemplateMapper.insert(ObjectUtils.clone(dbSmsTemplate, o -> o.setType(SysSmsTemplateTypeEnum.VERIFICATION_CODE.getType())));
       // 测试 status 不匹配
       smsTemplateMapper.insert(ObjectUtils.clone(dbSmsTemplate, o -> o.setStatus(CommonStatusEnum.DISABLE.getStatus())));
       // 测试 code 不匹配
       smsTemplateMapper.insert(ObjectUtils.clone(dbSmsTemplate, o -> o.setCode("yuanma")));
       // 测试 content 不匹配
       smsTemplateMapper.insert(ObjectUtils.clone(dbSmsTemplate, o -> o.setContent("源码")));
       // 测试 apiTemplateId 不匹配
       smsTemplateMapper.insert(ObjectUtils.clone(dbSmsTemplate, o -> o.setApiTemplateId("nai")));
       // 测试 channelId 不匹配
       smsTemplateMapper.insert(ObjectUtils.clone(dbSmsTemplate, o -> o.setChannelId(2L)));
       // 测试 createTime 不匹配
       smsTemplateMapper.insert(ObjectUtils.clone(dbSmsTemplate, o -> o.setCreateTime(buildTime(2021, 12, 12))));
       // 准备参数
       SysSmsTemplatePageReqVO reqVO = new SysSmsTemplatePageReqVO();
       reqVO.setType(SysSmsTemplateTypeEnum.PROMOTION.getType());
       reqVO.setStatus(CommonStatusEnum.ENABLE.getStatus());
       reqVO.setCode("yudao");
       reqVO.setContent("芋道");
       reqVO.setApiTemplateId("yu");
       reqVO.setChannelId(1L);
       reqVO.setBeginCreateTime(buildTime(2021, 11, 1));
       reqVO.setEndCreateTime(buildTime(2021, 12, 1));

       // 调用
       PageResult<SysSmsTemplateDO> pageResult = smsTemplateService.getSmsTemplatePage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbSmsTemplate, pageResult.getList().get(0));
    }

    @Test
    public void testGetSmsTemplateList() {
        // mock 数据
        SysSmsTemplateDO dbSmsTemplate = randomPojo(SysSmsTemplateDO.class, o -> { // 等会查询到
            o.setType(SysSmsTemplateTypeEnum.PROMOTION.getType());
            o.setStatus(CommonStatusEnum.ENABLE.getStatus());
            o.setCode("yudaoyuanma");
            o.setContent("芋道源码");
            o.setApiTemplateId("yunai");
            o.setChannelId(1L);
            o.setCreateTime(buildTime(2021, 11, 11));
        });
        smsTemplateMapper.insert(dbSmsTemplate);
        // 测试 type 不匹配
        smsTemplateMapper.insert(ObjectUtils.clone(dbSmsTemplate, o -> o.setType(SysSmsTemplateTypeEnum.VERIFICATION_CODE.getType())));
        // 测试 status 不匹配
        smsTemplateMapper.insert(ObjectUtils.clone(dbSmsTemplate, o -> o.setStatus(CommonStatusEnum.DISABLE.getStatus())));
        // 测试 code 不匹配
        smsTemplateMapper.insert(ObjectUtils.clone(dbSmsTemplate, o -> o.setCode("yuanma")));
        // 测试 content 不匹配
        smsTemplateMapper.insert(ObjectUtils.clone(dbSmsTemplate, o -> o.setContent("源码")));
        // 测试 apiTemplateId 不匹配
        smsTemplateMapper.insert(ObjectUtils.clone(dbSmsTemplate, o -> o.setApiTemplateId("nai")));
        // 测试 channelId 不匹配
        smsTemplateMapper.insert(ObjectUtils.clone(dbSmsTemplate, o -> o.setChannelId(2L)));
        // 测试 createTime 不匹配
        smsTemplateMapper.insert(ObjectUtils.clone(dbSmsTemplate, o -> o.setCreateTime(buildTime(2021, 12, 12))));
        // 准备参数
        SysSmsTemplateExportReqVO reqVO = new SysSmsTemplateExportReqVO();
        reqVO.setType(SysSmsTemplateTypeEnum.PROMOTION.getType());
        reqVO.setStatus(CommonStatusEnum.ENABLE.getStatus());
        reqVO.setCode("yudao");
        reqVO.setContent("芋道");
        reqVO.setApiTemplateId("yu");
        reqVO.setChannelId(1L);
        reqVO.setBeginCreateTime(buildTime(2021, 11, 1));
        reqVO.setEndCreateTime(buildTime(2021, 12, 1));

       // 调用
       List<SysSmsTemplateDO> list = smsTemplateService.getSmsTemplateList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbSmsTemplate, list.get(0));
    }

    @Test
    public void testCheckSmsChannel_success() {
        // 准备参数
        Long channelId = randomLongId();
        // mock 方法
        SysSmsChannelDO channelDO = randomPojo(SysSmsChannelDO.class, o -> {
            o.setId(channelId);
            o.setStatus(CommonStatusEnum.ENABLE.getStatus()); // 保证 status 开启，创建必须处于这个状态
        });
        when(smsChannelService.getSmsChannel(eq(channelId))).thenReturn(channelDO);

        // 调用
        SysSmsChannelDO returnChannelDO = smsTemplateService.checkSmsChannel(channelId);
        // 断言
        assertPojoEquals(returnChannelDO, channelDO);
    }

    @Test
    public void testCheckSmsChannel_notExists() {
        // 准备参数
        Long channelId = randomLongId();

        // 调用，校验异常
        assertServiceException(() -> smsTemplateService.checkSmsChannel(channelId),
                SMS_CHANNEL_NOT_EXISTS);
    }

    @Test
    public void testCheckSmsChannel_disable() {
        // 准备参数
        Long channelId = randomLongId();
        // mock 方法
        SysSmsChannelDO channelDO = randomPojo(SysSmsChannelDO.class, o -> {
            o.setId(channelId);
            o.setStatus(CommonStatusEnum.DISABLE.getStatus()); // 保证 status 禁用，触发失败
        });
        when(smsChannelService.getSmsChannel(eq(channelId))).thenReturn(channelDO);

        // 调用，校验异常
        assertServiceException(() -> smsTemplateService.checkSmsChannel(channelId),
                SMS_CHANNEL_DISABLE);
    }

    @Test
    public void testCheckDictDataValueUnique_success() {
        // 调用，成功
        smsTemplateService.checkSmsTemplateCodeDuplicate(randomLongId(), randomString());
    }

    @Test
    public void testCheckSmsTemplateCodeDuplicate_valueDuplicateForCreate() {
        // 准备参数
        String code = randomString();
        // mock 数据
        smsTemplateMapper.insert(randomSmsTemplateDO(o -> o.setCode(code)));

        // 调用，校验异常
        assertServiceException(() -> smsTemplateService.checkSmsTemplateCodeDuplicate(null, code),
                SMS_TEMPLATE_CODE_DUPLICATE, code);
    }

    @Test
    public void testCheckDictDataValueUnique_valueDuplicateForUpdate() {
        // 准备参数
        Long id = randomLongId();
        String code = randomString();
        // mock 数据
        smsTemplateMapper.insert(randomSmsTemplateDO(o -> o.setCode(code)));

        // 调用，校验异常
        assertServiceException(() -> smsTemplateService.checkSmsTemplateCodeDuplicate(id, code),
                SMS_TEMPLATE_CODE_DUPLICATE, code);
    }

    // ========== 随机对象 ==========

    @SafeVarargs
    private static SysSmsTemplateDO randomSmsTemplateDO(Consumer<SysSmsTemplateDO>... consumers) {
        Consumer<SysSmsTemplateDO> consumer = (o) -> {
            o.setStatus(randomEle(CommonStatusEnum.values()).getStatus()); // 保证 status 的范围
            o.setType(randomEle(SysSmsTemplateTypeEnum.values()).getType()); // 保证 type 的 范围
        };
        return randomPojo(SysSmsTemplateDO.class, ArrayUtils.append(consumer, consumers));
    }

}
