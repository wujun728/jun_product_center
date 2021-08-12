package cn.iocoder.yudao.adminserver.modules.system.service.logger;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.operatelog.core.service.OperateLogFrameworkService;
import cn.iocoder.yudao.adminserver.modules.system.controller.logger.vo.operatelog.SysOperateLogExportReqVO;
import cn.iocoder.yudao.adminserver.modules.system.controller.logger.vo.operatelog.SysOperateLogPageReqVO;
import cn.iocoder.yudao.adminserver.modules.system.dal.dataobject.logger.SysOperateLogDO;

import java.util.List;

/**
 * 操作日志 Service 接口
 */
public interface SysOperateLogService extends OperateLogFrameworkService {

    /**
     * 获得操作日志分页列表
     *
     * @param reqVO 分页条件
     * @return 操作日志分页列表
     */
    PageResult<SysOperateLogDO> getOperateLogPage(SysOperateLogPageReqVO reqVO);

    /**
     * 获得操作日志列表
     *
     * @param reqVO 列表条件
     * @return 日志列表
     */
    List<SysOperateLogDO> getOperateLogs(SysOperateLogExportReqVO reqVO);

}
