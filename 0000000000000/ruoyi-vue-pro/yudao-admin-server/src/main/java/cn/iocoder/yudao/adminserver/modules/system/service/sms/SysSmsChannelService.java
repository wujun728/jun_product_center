package cn.iocoder.yudao.adminserver.modules.system.service.sms;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.adminserver.modules.system.controller.sms.vo.channel.SysSmsChannelCreateReqVO;
import cn.iocoder.yudao.adminserver.modules.system.controller.sms.vo.channel.SysSmsChannelPageReqVO;
import cn.iocoder.yudao.adminserver.modules.system.controller.sms.vo.channel.SysSmsChannelUpdateReqVO;
import cn.iocoder.yudao.adminserver.modules.system.dal.dataobject.sms.SysSmsChannelDO;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

/**
 * 短信渠道Service接口
 *
 * @author zzf
 * @date 2021/1/25 9:24
 */
public interface SysSmsChannelService {

    /**
     * 初始化短信客户端
     */
    void initSmsClients();

    /**
     * 创建短信渠道
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createSmsChannel(@Valid SysSmsChannelCreateReqVO createReqVO);

    /**
     * 更新短信渠道
     *
     * @param updateReqVO 更新信息
     */
    void updateSmsChannel(@Valid SysSmsChannelUpdateReqVO updateReqVO);

    /**
     * 删除短信渠道
     *
     * @param id 编号
     */
    void deleteSmsChannel(Long id);

    /**
     * 获得短信渠道
     *
     * @param id 编号
     * @return 短信渠道
     */
    SysSmsChannelDO getSmsChannel(Long id);

    /**
     * 获得短信渠道列表
     *
     * @param ids 编号
     * @return 短信渠道列表
     */
    List<SysSmsChannelDO> getSmsChannelList(Collection<Long> ids);

    /**
     * 获得所有短信渠道列表
     *
     * @return 短信渠道列表
     */
    List<SysSmsChannelDO> getSmsChannelList();

    /**
     * 获得短信渠道分页
     *
     * @param pageReqVO 分页查询
     * @return 短信渠道分页
     */
    PageResult<SysSmsChannelDO> getSmsChannelPage(SysSmsChannelPageReqVO pageReqVO);

}
