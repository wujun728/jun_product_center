package cn.iocoder.yudao.adminserver.modules.system.service.sms.impl;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import cn.iocoder.yudao.framework.sms.core.client.SmsClientFactory;
import cn.iocoder.yudao.framework.sms.core.property.SmsChannelProperties;
import cn.iocoder.yudao.adminserver.modules.system.controller.sms.vo.channel.SysSmsChannelCreateReqVO;
import cn.iocoder.yudao.adminserver.modules.system.controller.sms.vo.channel.SysSmsChannelPageReqVO;
import cn.iocoder.yudao.adminserver.modules.system.controller.sms.vo.channel.SysSmsChannelUpdateReqVO;
import cn.iocoder.yudao.adminserver.modules.system.convert.sms.SysSmsChannelConvert;
import cn.iocoder.yudao.adminserver.modules.system.dal.dataobject.sms.SysSmsChannelDO;
import cn.iocoder.yudao.adminserver.modules.system.dal.mysql.sms.SysSmsChannelMapper;
import cn.iocoder.yudao.adminserver.modules.system.mq.producer.sms.SysSmsProducer;
import cn.iocoder.yudao.adminserver.modules.system.service.sms.SysSmsChannelService;
import cn.iocoder.yudao.adminserver.modules.system.service.sms.SysSmsTemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.adminserver.modules.system.enums.SysErrorCodeConstants.SMS_CHANNEL_HAS_CHILDREN;
import static cn.iocoder.yudao.adminserver.modules.system.enums.SysErrorCodeConstants.SMS_CHANNEL_NOT_EXISTS;

/**
 * 短信渠道Service实现类
 *
 * @author zzf
 * @date 2021/1/25 9:25
 */
@Service
@Slf4j
public class SysSmsChannelServiceImpl implements SysSmsChannelService {

    /**
     * 定时执行 {@link #schedulePeriodicRefresh()} 的周期
     * 因为已经通过 Redis Pub/Sub 机制，所以频率不需要高
     */
    private static final long SCHEDULER_PERIOD = 5 * 60 * 1000L;

    /**
     * 缓存菜单的最大更新时间，用于后续的增量轮询，判断是否有更新
     */
    private volatile Date maxUpdateTime;

    @Resource
    private SmsClientFactory smsClientFactory;

    @Resource
    private SysSmsChannelMapper smsChannelMapper;

    @Resource
    private SysSmsTemplateService smsTemplateService;

    @Resource
    private SysSmsProducer smsProducer;

    @Override
    @PostConstruct
    public void initSmsClients() {
        // 获取短信渠道，如果有更新
        List<SysSmsChannelDO> smsChannels = this.loadSmsChannelIfUpdate(maxUpdateTime);
        if (CollUtil.isEmpty(smsChannels)) {
            return;
        }

        // 创建或更新短信 Client
        List<SmsChannelProperties> propertiesList = SysSmsChannelConvert.INSTANCE.convertList02(smsChannels);
        propertiesList.forEach(properties -> smsClientFactory.createOrUpdateSmsClient(properties));

        // 写入缓存
        assert smsChannels.size() > 0; // 断言，避免告警
        maxUpdateTime = smsChannels.stream().max(Comparator.comparing(BaseDO::getUpdateTime)).get().getUpdateTime();
        log.info("[initSmsClients][初始化 SmsChannel 数量为 {}]", smsChannels.size());
    }

    @Scheduled(fixedDelay = SCHEDULER_PERIOD, initialDelay = SCHEDULER_PERIOD)
    public void schedulePeriodicRefresh() {
        initSmsClients();
    }

    /**
     * 如果短信渠道发生变化，从数据库中获取最新的全量短信渠道。
     * 如果未发生变化，则返回空
     *
     * @param maxUpdateTime 当前短信渠道的最大更新时间
     * @return 短信渠道列表
     */
    private List<SysSmsChannelDO> loadSmsChannelIfUpdate(Date maxUpdateTime) {
        // 第一步，判断是否要更新。
        if (maxUpdateTime == null) { // 如果更新时间为空，说明 DB 一定有新数据
            log.info("[loadSmsChannelIfUpdate][首次加载全量短信渠道]");
        } else { // 判断数据库中是否有更新的短信渠道
            if (smsChannelMapper.selectExistsByUpdateTimeAfter(maxUpdateTime) == null) {
                return null;
            }
            log.info("[loadSmsChannelIfUpdate][增量加载全量短信渠道]");
        }
        // 第二步，如果有更新，则从数据库加载所有短信渠道
        return smsChannelMapper.selectList();
    }

    @Override
    public Long createSmsChannel(SysSmsChannelCreateReqVO createReqVO) {
        // 插入
        SysSmsChannelDO smsChannel = SysSmsChannelConvert.INSTANCE.convert(createReqVO);
        smsChannelMapper.insert(smsChannel);
        // 发送刷新消息
        smsProducer.sendSmsChannelRefreshMessage();
        // 返回
        return smsChannel.getId();
    }

    @Override
    public void updateSmsChannel(SysSmsChannelUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateSmsChannelExists(updateReqVO.getId());
        // 更新
        SysSmsChannelDO updateObj = SysSmsChannelConvert.INSTANCE.convert(updateReqVO);
        smsChannelMapper.updateById(updateObj);
        // 发送刷新消息
        smsProducer.sendSmsChannelRefreshMessage();
    }

    @Override
    public void deleteSmsChannel(Long id) {
        // 校验存在
        this.validateSmsChannelExists(id);
        // 校验是否有字典数据
        if (smsTemplateService.countByChannelId(id) > 0) {
            throw exception(SMS_CHANNEL_HAS_CHILDREN);
        }
        // 删除
        smsChannelMapper.deleteById(id);
        // 发送刷新消息
        smsProducer.sendSmsChannelRefreshMessage();
    }

    private void validateSmsChannelExists(Long id) {
        if (smsChannelMapper.selectById(id) == null) {
            throw exception(SMS_CHANNEL_NOT_EXISTS);
        }
    }

    @Override
    public SysSmsChannelDO getSmsChannel(Long id) {
        return smsChannelMapper.selectById(id);
    }

    @Override
    public List<SysSmsChannelDO> getSmsChannelList(Collection<Long> ids) {
        return smsChannelMapper.selectBatchIds(ids);
    }

    @Override
    public List<SysSmsChannelDO> getSmsChannelList() {
        return smsChannelMapper.selectList();
    }

    @Override
    public PageResult<SysSmsChannelDO> getSmsChannelPage(SysSmsChannelPageReqVO pageReqVO) {
        return smsChannelMapper.selectPage(pageReqVO);
    }

}
