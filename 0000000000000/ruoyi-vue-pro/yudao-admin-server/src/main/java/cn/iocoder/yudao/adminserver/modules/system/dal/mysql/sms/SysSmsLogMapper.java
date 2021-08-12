package cn.iocoder.yudao.adminserver.modules.system.dal.mysql.sms;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.QueryWrapperX;
import cn.iocoder.yudao.adminserver.modules.system.controller.sms.vo.log.SysSmsLogExportReqVO;
import cn.iocoder.yudao.adminserver.modules.system.controller.sms.vo.log.SysSmsLogPageReqVO;
import cn.iocoder.yudao.adminserver.modules.system.dal.dataobject.sms.SysSmsLogDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysSmsLogMapper extends BaseMapperX<SysSmsLogDO> {

    default PageResult<SysSmsLogDO> selectPage(SysSmsLogPageReqVO reqVO) {
        return selectPage(reqVO, new QueryWrapperX<SysSmsLogDO>()
                .eqIfPresent("channel_id", reqVO.getChannelId())
                .eqIfPresent("template_id", reqVO.getTemplateId())
                .likeIfPresent("mobile", reqVO.getMobile())
                .eqIfPresent("send_status", reqVO.getSendStatus())
                .betweenIfPresent("send_time", reqVO.getBeginSendTime(), reqVO.getEndSendTime())
                .eqIfPresent("receive_status", reqVO.getReceiveStatus())
                .betweenIfPresent("receive_time", reqVO.getBeginReceiveTime(), reqVO.getEndReceiveTime())
                .orderByDesc("id"));
    }

    default List<SysSmsLogDO> selectList(SysSmsLogExportReqVO reqVO) {
        return selectList(new QueryWrapperX<SysSmsLogDO>()
                .eqIfPresent("channel_id", reqVO.getChannelId())
                .eqIfPresent("template_id", reqVO.getTemplateId())
                .likeIfPresent("mobile", reqVO.getMobile())
                .eqIfPresent("send_status", reqVO.getSendStatus())
                .betweenIfPresent("send_time", reqVO.getBeginSendTime(), reqVO.getEndSendTime())
                .eqIfPresent("receive_status", reqVO.getReceiveStatus())
                .betweenIfPresent("receive_time", reqVO.getBeginReceiveTime(), reqVO.getEndReceiveTime())
                .orderByDesc("id"));
    }

}
