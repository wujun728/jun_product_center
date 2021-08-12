package cn.iocoder.yudao.adminserver.modules.infra.service.job;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.adminserver.modules.infra.controller.job.vo.job.InfJobCreateReqVO;
import cn.iocoder.yudao.adminserver.modules.infra.controller.job.vo.job.InfJobExportReqVO;
import cn.iocoder.yudao.adminserver.modules.infra.controller.job.vo.job.InfJobPageReqVO;
import cn.iocoder.yudao.adminserver.modules.infra.controller.job.vo.job.InfJobUpdateReqVO;
import cn.iocoder.yudao.adminserver.modules.infra.dal.dataobject.job.InfJobDO;
import org.quartz.SchedulerException;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

/**
 * 定时任务 Service 接口
 *
 * @author 芋道源码
 */
public interface InfJobService {

    /**
     * 创建定时任务
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createJob(@Valid InfJobCreateReqVO createReqVO) throws SchedulerException;

    /**
     * 更新定时任务
     *
     * @param updateReqVO 更新信息
     */
    void updateJob(@Valid InfJobUpdateReqVO updateReqVO) throws SchedulerException;

    /**
     * 更新定时任务的状态
     *
     * @param id 任务编号
     * @param status 状态
     */
    void updateJobStatus(Long id, Integer status) throws SchedulerException;

    /**
     * 触发定时任务
     *
     * @param id 任务编号
     */
    void triggerJob(Long id) throws SchedulerException;

    /**
     * 删除定时任务
     *
     * @param id 编号
     */
    void deleteJob(Long id) throws SchedulerException;

    /**
     * 获得定时任务
     *
     * @param id 编号
     * @return 定时任务
     */
    InfJobDO getJob(Long id);

    /**
     * 获得定时任务列表
     *
     * @param ids 编号
     * @return 定时任务列表
     */
    List<InfJobDO> getJobList(Collection<Long> ids);

    /**
     * 获得定时任务分页
     *
     * @param pageReqVO 分页查询
     * @return 定时任务分页
     */
    PageResult<InfJobDO> getJobPage(InfJobPageReqVO pageReqVO);

    /**
     * 获得定时任务列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 定时任务分页
     */
    List<InfJobDO> getJobList(InfJobExportReqVO exportReqVO);

}
