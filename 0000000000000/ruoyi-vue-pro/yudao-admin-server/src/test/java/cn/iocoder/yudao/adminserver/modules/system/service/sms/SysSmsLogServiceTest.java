package cn.iocoder.yudao.adminserver.modules.system.service.sms;

import cn.hutool.core.map.MapUtil;
import cn.iocoder.yudao.adminserver.BaseDbUnitTest;
import cn.iocoder.yudao.framework.common.enums.UserTypeEnum;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.adminserver.modules.system.controller.sms.vo.log.SysSmsLogExportReqVO;
import cn.iocoder.yudao.adminserver.modules.system.controller.sms.vo.log.SysSmsLogPageReqVO;
import cn.iocoder.yudao.adminserver.modules.system.dal.dataobject.sms.SysSmsLogDO;
import cn.iocoder.yudao.adminserver.modules.system.dal.dataobject.sms.SysSmsTemplateDO;
import cn.iocoder.yudao.adminserver.modules.system.dal.mysql.sms.SysSmsLogMapper;
import cn.iocoder.yudao.adminserver.modules.system.enums.sms.SysSmsReceiveStatusEnum;
import cn.iocoder.yudao.adminserver.modules.system.enums.sms.SysSmsSendStatusEnum;
import cn.iocoder.yudao.adminserver.modules.system.enums.sms.SysSmsTemplateTypeEnum;
import cn.iocoder.yudao.adminserver.modules.system.service.sms.impl.SysSmsLogServiceImpl;
import cn.iocoder.yudao.framework.common.util.collection.ArrayUtils;
import cn.iocoder.yudao.framework.common.util.object.ObjectUtils;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static cn.hutool.core.util.RandomUtil.randomBoolean;
import static cn.hutool.core.util.RandomUtil.randomEle;
import static cn.iocoder.yudao.framework.test.core.util.AssertUtils.assertPojoEquals;
import static cn.iocoder.yudao.framework.common.util.date.DateUtils.buildTime;
import static cn.iocoder.yudao.framework.test.core.util.RandomUtils.*;
import static org.junit.jupiter.api.Assertions.*;

/**
* {@link SysSmsLogServiceImpl} 的单元测试类
*
* @author 芋道源码
*/
@Import(SysSmsLogServiceImpl.class)
public class SysSmsLogServiceTest extends BaseDbUnitTest {

    @Resource
    private SysSmsLogServiceImpl smsLogService;

    @Resource
    private SysSmsLogMapper smsLogMapper;

    @Test
    public void testCreateSmsLog() {
        // 准备参数
        String mobile = randomString();
        Long userId = randomLongId();
        Integer userType = randomEle(UserTypeEnum.values()).getValue();
        Boolean isSend = randomBoolean();
        SysSmsTemplateDO templateDO = randomPojo(SysSmsTemplateDO.class,
                o -> o.setType(randomEle(SysSmsTemplateTypeEnum.values()).getType()));
        String templateContent = randomString();
        Map<String, Object> templateParams = randomTemplateParams();
        // mock 方法

        // 调用
        Long logId = smsLogService.createSmsLog(mobile, userId, userType, isSend,
                templateDO, templateContent, templateParams);
        // 断言
        SysSmsLogDO logDO = smsLogMapper.selectById(logId);
        assertEquals(isSend ? SysSmsSendStatusEnum.INIT.getStatus() : SysSmsSendStatusEnum.IGNORE.getStatus(),
                logDO.getSendStatus());
        assertEquals(mobile, logDO.getMobile());
        assertEquals(userType, logDO.getUserType());
        assertEquals(userId, logDO.getUserId());
        assertEquals(templateDO.getId(), logDO.getTemplateId());
        assertEquals(templateDO.getCode(), logDO.getTemplateCode());
        assertEquals(templateDO.getType(), logDO.getTemplateType());
        assertEquals(templateDO.getChannelId(), logDO.getChannelId());
        assertEquals(templateDO.getChannelCode(), logDO.getChannelCode());
        assertEquals(templateContent, logDO.getTemplateContent());
        assertEquals(templateParams, logDO.getTemplateParams());
        assertEquals(SysSmsReceiveStatusEnum.INIT.getStatus(), logDO.getReceiveStatus());
    }

    @Test
    public void testUpdateSmsSendResult() {
        // mock 数据
        SysSmsLogDO dbSmsLog = randomSmsLogDO(
                o -> o.setSendStatus(SysSmsSendStatusEnum.IGNORE.getStatus()));
        smsLogMapper.insert(dbSmsLog);
        // 准备参数
        Long id = dbSmsLog.getId();
        Integer sendCode = randomInteger();
        String sendMsg = randomString();
        String apiSendCode = randomString();
        String apiSendMsg = randomString();
        String apiRequestId = randomString();
        String apiSerialNo = randomString();

        // 调用
        smsLogService.updateSmsSendResult(id, sendCode, sendMsg,
                apiSendCode, apiSendMsg, apiRequestId, apiSerialNo);
        // 断言
        dbSmsLog = smsLogMapper.selectById(id);
        assertEquals(CommonResult.isSuccess(sendCode) ? SysSmsSendStatusEnum.SUCCESS.getStatus()
                : SysSmsSendStatusEnum.FAILURE.getStatus(), dbSmsLog.getSendStatus());
        assertNotNull(dbSmsLog.getSendTime());
        assertEquals(sendMsg, dbSmsLog.getSendMsg());
        assertEquals(apiSendCode, dbSmsLog.getApiSendCode());
        assertEquals(apiSendMsg, dbSmsLog.getApiSendMsg());
        assertEquals(apiRequestId, dbSmsLog.getApiRequestId());
        assertEquals(apiSerialNo, dbSmsLog.getApiSerialNo());
    }

    @Test
    public void testUpdateSmsReceiveResult() {
        // mock 数据
        SysSmsLogDO dbSmsLog = randomSmsLogDO(
                o -> o.setReceiveStatus(SysSmsReceiveStatusEnum.INIT.getStatus()));
        smsLogMapper.insert(dbSmsLog);
        // 准备参数
        Long id = dbSmsLog.getId();
        Boolean success = randomBoolean();
        Date receiveTime = randomDate();
        String apiReceiveCode = randomString();
        String apiReceiveMsg = randomString();

        // 调用
        smsLogService.updateSmsReceiveResult(id, success, receiveTime, apiReceiveCode, apiReceiveMsg);
        // 断言
        dbSmsLog = smsLogMapper.selectById(id);
        assertEquals(success ? SysSmsReceiveStatusEnum.SUCCESS.getStatus()
                : SysSmsReceiveStatusEnum.FAILURE.getStatus(), dbSmsLog.getReceiveStatus());
        assertEquals(receiveTime, dbSmsLog.getReceiveTime());
        assertEquals(apiReceiveCode, dbSmsLog.getApiReceiveCode());
        assertEquals(apiReceiveMsg, dbSmsLog.getApiReceiveMsg());
    }

    @Test
    public void testGetSmsLogPage() {
       // mock 数据
       SysSmsLogDO dbSmsLog = randomSmsLogDO(o -> { // 等会查询到
           o.setChannelId(1L);
           o.setTemplateId(10L);
           o.setMobile("15601691300");
           o.setSendStatus(SysSmsSendStatusEnum.INIT.getStatus());
           o.setSendTime(buildTime(2020, 11, 11));
           o.setReceiveStatus(SysSmsReceiveStatusEnum.INIT.getStatus());
           o.setReceiveTime(buildTime(2021, 11, 11));
       });
       smsLogMapper.insert(dbSmsLog);
       // 测试 channelId 不匹配
       smsLogMapper.insert(ObjectUtils.clone(dbSmsLog, o -> o.setChannelId(2L)));
       // 测试 templateId 不匹配
       smsLogMapper.insert(ObjectUtils.clone(dbSmsLog, o -> o.setTemplateId(20L)));
       // 测试 mobile 不匹配
       smsLogMapper.insert(ObjectUtils.clone(dbSmsLog, o -> o.setMobile("18818260999")));
       // 测试 sendStatus 不匹配
       smsLogMapper.insert(ObjectUtils.clone(dbSmsLog, o -> o.setSendStatus(SysSmsSendStatusEnum.IGNORE.getStatus())));
       // 测试 sendTime 不匹配
       smsLogMapper.insert(ObjectUtils.clone(dbSmsLog, o -> o.setSendTime(buildTime(2020, 12, 12))));
       // 测试 receiveStatus 不匹配
       smsLogMapper.insert(ObjectUtils.clone(dbSmsLog, o -> o.setReceiveStatus(SysSmsReceiveStatusEnum.SUCCESS.getStatus())));
       // 测试 receiveTime 不匹配
       smsLogMapper.insert(ObjectUtils.clone(dbSmsLog, o -> o.setReceiveTime(buildTime(2021, 12, 12))));
       // 准备参数
       SysSmsLogPageReqVO reqVO = new SysSmsLogPageReqVO();
       reqVO.setChannelId(1L);
       reqVO.setTemplateId(10L);
       reqVO.setMobile("156");
       reqVO.setSendStatus(SysSmsSendStatusEnum.INIT.getStatus());
       reqVO.setBeginSendTime(buildTime(2020, 11, 1));
       reqVO.setEndSendTime(buildTime(2020, 11, 30));
       reqVO.setReceiveStatus(SysSmsReceiveStatusEnum.INIT.getStatus());
       reqVO.setBeginReceiveTime(buildTime(2021, 11, 1));
       reqVO.setEndReceiveTime(buildTime(2021, 11, 30));

       // 调用
       PageResult<SysSmsLogDO> pageResult = smsLogService.getSmsLogPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbSmsLog, pageResult.getList().get(0));
    }

    @Test
    public void testGetSmsLogList() {
        // mock 数据
        SysSmsLogDO dbSmsLog = randomSmsLogDO(o -> { // 等会查询到
            o.setChannelId(1L);
            o.setTemplateId(10L);
            o.setMobile("15601691300");
            o.setSendStatus(SysSmsSendStatusEnum.INIT.getStatus());
            o.setSendTime(buildTime(2020, 11, 11));
            o.setReceiveStatus(SysSmsReceiveStatusEnum.INIT.getStatus());
            o.setReceiveTime(buildTime(2021, 11, 11));
        });
        smsLogMapper.insert(dbSmsLog);
        // 测试 channelId 不匹配
        smsLogMapper.insert(ObjectUtils.clone(dbSmsLog, o -> o.setChannelId(2L)));
        // 测试 templateId 不匹配
        smsLogMapper.insert(ObjectUtils.clone(dbSmsLog, o -> o.setTemplateId(20L)));
        // 测试 mobile 不匹配
        smsLogMapper.insert(ObjectUtils.clone(dbSmsLog, o -> o.setMobile("18818260999")));
        // 测试 sendStatus 不匹配
        smsLogMapper.insert(ObjectUtils.clone(dbSmsLog, o -> o.setSendStatus(SysSmsSendStatusEnum.IGNORE.getStatus())));
        // 测试 sendTime 不匹配
        smsLogMapper.insert(ObjectUtils.clone(dbSmsLog, o -> o.setSendTime(buildTime(2020, 12, 12))));
        // 测试 receiveStatus 不匹配
        smsLogMapper.insert(ObjectUtils.clone(dbSmsLog, o -> o.setReceiveStatus(SysSmsReceiveStatusEnum.SUCCESS.getStatus())));
        // 测试 receiveTime 不匹配
        smsLogMapper.insert(ObjectUtils.clone(dbSmsLog, o -> o.setReceiveTime(buildTime(2021, 12, 12))));
        // 准备参数
        SysSmsLogExportReqVO reqVO = new SysSmsLogExportReqVO();
        reqVO.setChannelId(1L);
        reqVO.setTemplateId(10L);
        reqVO.setMobile("156");
        reqVO.setSendStatus(SysSmsSendStatusEnum.INIT.getStatus());
        reqVO.setBeginSendTime(buildTime(2020, 11, 1));
        reqVO.setEndSendTime(buildTime(2020, 11, 30));
        reqVO.setReceiveStatus(SysSmsReceiveStatusEnum.INIT.getStatus());
        reqVO.setBeginReceiveTime(buildTime(2021, 11, 1));
        reqVO.setEndReceiveTime(buildTime(2021, 11, 30));

       // 调用
       List<SysSmsLogDO> list = smsLogService.getSmsLogList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbSmsLog, list.get(0));
    }

    // ========== 随机对象 ==========

    @SafeVarargs
    private static SysSmsLogDO randomSmsLogDO(Consumer<SysSmsLogDO>... consumers) {
        Consumer<SysSmsLogDO> consumer = (o) -> {
            o.setTemplateParams(randomTemplateParams());
            o.setTemplateType(randomEle(SysSmsTemplateTypeEnum.values()).getType()); // 保证 templateType 的范围
            o.setUserType(randomEle(UserTypeEnum.values()).getValue()); // 保证 userType 的范围
            o.setSendStatus(randomEle(SysSmsSendStatusEnum.values()).getStatus()); // 保证 sendStatus 的范围
            o.setReceiveStatus(randomEle(SysSmsReceiveStatusEnum.values()).getStatus()); // 保证 receiveStatus 的范围
        };
        return randomPojo(SysSmsLogDO.class, ArrayUtils.append(consumer, consumers));
    }


    private static Map<String, Object> randomTemplateParams() {
        return MapUtil.<String, Object>builder().put(randomString(), randomString())
                .put(randomString(), randomString()).build();
    }
}
