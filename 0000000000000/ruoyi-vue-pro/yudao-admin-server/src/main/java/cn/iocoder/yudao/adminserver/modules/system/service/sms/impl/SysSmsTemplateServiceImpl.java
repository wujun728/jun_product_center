package cn.iocoder.yudao.adminserver.modules.system.service.sms.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.enums.CommonStatusEnum;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import cn.iocoder.yudao.framework.sms.core.client.SmsClient;
import cn.iocoder.yudao.framework.sms.core.client.SmsClientFactory;
import cn.iocoder.yudao.framework.sms.core.client.SmsCommonResult;
import cn.iocoder.yudao.framework.sms.core.client.dto.SmsTemplateRespDTO;
import cn.iocoder.yudao.adminserver.modules.system.controller.sms.vo.template.SysSmsTemplateCreateReqVO;
import cn.iocoder.yudao.adminserver.modules.system.controller.sms.vo.template.SysSmsTemplateExportReqVO;
import cn.iocoder.yudao.adminserver.modules.system.controller.sms.vo.template.SysSmsTemplatePageReqVO;
import cn.iocoder.yudao.adminserver.modules.system.controller.sms.vo.template.SysSmsTemplateUpdateReqVO;
import cn.iocoder.yudao.adminserver.modules.system.convert.sms.SysSmsTemplateConvert;
import cn.iocoder.yudao.adminserver.modules.system.dal.dataobject.sms.SysSmsChannelDO;
import cn.iocoder.yudao.adminserver.modules.system.dal.dataobject.sms.SysSmsTemplateDO;
import cn.iocoder.yudao.adminserver.modules.system.dal.mysql.sms.SysSmsTemplateMapper;
import cn.iocoder.yudao.adminserver.modules.system.mq.producer.sms.SysSmsProducer;
import cn.iocoder.yudao.adminserver.modules.system.service.sms.SysSmsChannelService;
import cn.iocoder.yudao.adminserver.modules.system.service.sms.SysSmsTemplateService;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.*;
import java.util.regex.Pattern;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.adminserver.modules.system.enums.SysErrorCodeConstants.*;

/**
 * 短信模板Service实现类
 *
 * @author zzf
 * @date 2021/1/25 9:25
 */
@Service
@Slf4j
public class SysSmsTemplateServiceImpl implements SysSmsTemplateService {

    /**
     * 正则表达式，匹配 {} 中的变量
     */
    private static final Pattern PATTERN_PARAMS = Pattern.compile("\\{(.*?)}");

    /**
     * 定时执行 {@link #schedulePeriodicRefresh()} 的周期
     * 因为已经通过 Redis Pub/Sub 机制，所以频率不需要高
     */
    private static final long SCHEDULER_PERIOD = 5 * 60 * 1000L;

    /**
     * 短信模板缓存
     * key：短信模板编码 {@link SysSmsTemplateDO#getCode()}
     *
     * 这里声明 volatile 修饰的原因是，每次刷新时，直接修改指向
     */
    private volatile Map<String, SysSmsTemplateDO> smsTemplateCache;
    /**
     * 缓存短信模板的最大更新时间，用于后续的增量轮询，判断是否有更新
     */
    private volatile Date maxUpdateTime;

    @Resource
    private SysSmsTemplateMapper smsTemplateMapper;

    @Resource
    private SysSmsChannelService smsChannelService;

    @Resource
    private SmsClientFactory smsClientFactory;

    @Resource
    private SysSmsProducer smsProducer;

    /**
     * 初始化 {@link #smsTemplateCache} 缓存
     */
    @Override
    @PostConstruct
    public void initLocalCache() {
        // 获取短信模板列表，如果有更新
        List<SysSmsTemplateDO> smsTemplateList = this.loadSmsTemplateIfUpdate(maxUpdateTime);
        if (CollUtil.isEmpty(smsTemplateList)) {
            return;
        }

        // 写入缓存
        ImmutableMap.Builder<String, SysSmsTemplateDO> builder = ImmutableMap.builder();
        smsTemplateList.forEach(sysSmsTemplateDO -> builder.put(sysSmsTemplateDO.getCode(), sysSmsTemplateDO));
        smsTemplateCache = builder.build();
        assert smsTemplateList.size() > 0; // 断言，避免告警
        maxUpdateTime = smsTemplateList.stream().max(Comparator.comparing(BaseDO::getUpdateTime)).get().getUpdateTime();
        log.info("[initLocalCache][初始化 SmsTemplate 数量为 {}]", smsTemplateList.size());
    }

    @Scheduled(fixedDelay = SCHEDULER_PERIOD, initialDelay = SCHEDULER_PERIOD)
    public void schedulePeriodicRefresh() {
        initLocalCache();
    }

    /**
     * 如果短信模板发生变化，从数据库中获取最新的全量短信模板。
     * 如果未发生变化，则返回空
     *
     * @param maxUpdateTime 当前短信模板的最大更新时间
     * @return 短信模板列表
     */
    private List<SysSmsTemplateDO> loadSmsTemplateIfUpdate(Date maxUpdateTime) {
        // 第一步，判断是否要更新。
        if (maxUpdateTime == null) { // 如果更新时间为空，说明 DB 一定有新数据
            log.info("[loadSmsTemplateIfUpdate][首次加载全量短信模板]");
        } else { // 判断数据库中是否有更新的短信模板
            if (smsTemplateMapper.selectExistsByUpdateTimeAfter(maxUpdateTime) == null) {
                return null;
            }
            log.info("[loadSmsTemplateIfUpdate][增量加载全量短信模板]");
        }
        // 第二步，如果有更新，则从数据库加载所有短信模板
        return smsTemplateMapper.selectList();
    }

    @Override
    public SysSmsTemplateDO getSmsTemplateByCode(String code) {
        return smsTemplateMapper.selectByCode(code);
    }

    @Override
    public SysSmsTemplateDO getSmsTemplateByCodeFromCache(String code) {
        return smsTemplateCache.get(code);
    }

    @Override
    public String formatSmsTemplateContent(String content, Map<String, Object> params) {
        return StrUtil.format(content, params);
    }

    @VisibleForTesting
    public List<String> parseTemplateContentParams(String content) {
        return ReUtil.findAllGroup1(PATTERN_PARAMS, content);
    }

    @Override
    public Long createSmsTemplate(SysSmsTemplateCreateReqVO createReqVO) {
        // 校验短信渠道
        SysSmsChannelDO channelDO = checkSmsChannel(createReqVO.getChannelId());
        // 校验短信编码是否重复
        checkSmsTemplateCodeDuplicate(null, createReqVO.getCode());
        // 校验短信模板
        checkApiTemplate(createReqVO.getChannelId(), createReqVO.getApiTemplateId());

        // 插入
        SysSmsTemplateDO template = SysSmsTemplateConvert.INSTANCE.convert(createReqVO);
        template.setParams(parseTemplateContentParams(template.getContent()));
        template.setChannelCode(channelDO.getCode());
        smsTemplateMapper.insert(template);
        // 发送刷新消息
        smsProducer.sendSmsTemplateRefreshMessage();
        // 返回
        return template.getId();
    }

    @Override
    public void updateSmsTemplate(SysSmsTemplateUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateSmsTemplateExists(updateReqVO.getId());
        // 校验短信渠道
        SysSmsChannelDO channelDO = checkSmsChannel(updateReqVO.getChannelId());
        // 校验短信编码是否重复
        checkSmsTemplateCodeDuplicate(updateReqVO.getId(), updateReqVO.getCode());
        // 校验短信模板
        checkApiTemplate(updateReqVO.getChannelId(), updateReqVO.getApiTemplateId());

        // 更新
        SysSmsTemplateDO updateObj = SysSmsTemplateConvert.INSTANCE.convert(updateReqVO);
        updateObj.setParams(parseTemplateContentParams(updateObj.getContent()));
        updateObj.setChannelCode(channelDO.getCode());
        smsTemplateMapper.updateById(updateObj);
        // 发送刷新消息
        smsProducer.sendSmsTemplateRefreshMessage();
    }

    @Override
    public void deleteSmsTemplate(Long id) {
        // 校验存在
        this.validateSmsTemplateExists(id);
        // 更新
        smsTemplateMapper.deleteById(id);
        // 发送刷新消息
        smsProducer.sendSmsTemplateRefreshMessage();
    }

    private void validateSmsTemplateExists(Long id) {
        if (smsTemplateMapper.selectById(id) == null) {
            throw exception(SMS_TEMPLATE_NOT_EXISTS);
        }
    }

    @Override
    public SysSmsTemplateDO getSmsTemplate(Long id) {
        return smsTemplateMapper.selectById(id);
    }

    @Override
    public List<SysSmsTemplateDO> getSmsTemplateList(Collection<Long> ids) {
        return smsTemplateMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<SysSmsTemplateDO> getSmsTemplatePage(SysSmsTemplatePageReqVO pageReqVO) {
        return smsTemplateMapper.selectPage(pageReqVO);
    }

    @Override
    public List<SysSmsTemplateDO> getSmsTemplateList(SysSmsTemplateExportReqVO exportReqVO) {
        return smsTemplateMapper.selectList(exportReqVO);
    }

    @Override
    public Integer countByChannelId(Long channelId) {
        return smsTemplateMapper.selectCountByChannelId(channelId);
    }

    @VisibleForTesting
    public SysSmsChannelDO checkSmsChannel(Long channelId) {
        SysSmsChannelDO channelDO = smsChannelService.getSmsChannel(channelId);
        if (channelDO == null) {
            throw exception(SMS_CHANNEL_NOT_EXISTS);
        }
        if (!Objects.equals(channelDO.getStatus(), CommonStatusEnum.ENABLE.getStatus())) {
            throw exception(SMS_CHANNEL_DISABLE);
        }
        return channelDO;
    }

    @VisibleForTesting
    public void checkSmsTemplateCodeDuplicate(Long id, String code) {
        SysSmsTemplateDO template = smsTemplateMapper.selectByCode(code);
        if (template == null) {
            return;
        }
        // 如果 id 为空，说明不用比较是否为相同 id 的字典类型
        if (id == null) {
            throw exception(SMS_TEMPLATE_CODE_DUPLICATE, code);
        }
        if (!template.getId().equals(id)) {
            throw exception(SMS_TEMPLATE_CODE_DUPLICATE, code);
        }
    }

    /**
     * 校验 API 短信平台的模板是否有效
     *
     * @param channelId 渠道编号
     * @param apiTemplateId API 模板编号
     */
    @VisibleForTesting
    public void checkApiTemplate(Long channelId, String apiTemplateId) {
        // 获得短信模板
        SmsClient smsClient = smsClientFactory.getSmsClient(channelId);
        Assert.notNull(smsClient, String.format("短信客户端(%d) 不存在", channelId));
        SmsCommonResult<SmsTemplateRespDTO> templateResult = smsClient.getSmsTemplate(apiTemplateId);
        // 校验短信模板是否正确
        templateResult.checkError();
    }

}
