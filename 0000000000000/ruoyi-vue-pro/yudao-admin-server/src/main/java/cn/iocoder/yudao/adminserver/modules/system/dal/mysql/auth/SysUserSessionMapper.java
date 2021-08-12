package cn.iocoder.yudao.adminserver.modules.system.dal.mysql.auth;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.QueryWrapperX;
import cn.iocoder.yudao.adminserver.modules.system.controller.auth.vo.session.SysUserSessionPageReqVO;
import cn.iocoder.yudao.adminserver.modules.system.dal.dataobject.auth.SysUserSessionDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Mapper
public interface SysUserSessionMapper extends BaseMapperX<SysUserSessionDO> {

    default PageResult<SysUserSessionDO> selectPage(SysUserSessionPageReqVO reqVO, Collection<Long> userIds) {
        return selectPage(reqVO, new QueryWrapperX<SysUserSessionDO>()
                .inIfPresent("user_id", userIds)
                .likeIfPresent("user_ip", reqVO.getUserIp()));
    }

    default List<SysUserSessionDO> selectListBySessionTimoutLt() {
        return selectList(new QueryWrapperX<SysUserSessionDO>().lt("session_timeout",new Date()));
    }
}
