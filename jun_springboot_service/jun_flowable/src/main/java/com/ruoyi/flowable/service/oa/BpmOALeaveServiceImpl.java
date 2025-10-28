package com.ruoyi.flowable.service.oa;

import cn.hutool.core.date.DateUtil;
import com.ruoyi.common.mybatis.pojo.PageResult;
import com.ruoyi.flowable.convert.oa.BpmOALeaveConvert;
import com.ruoyi.flowable.core.enums.task.BpmProcessInstanceResultEnum;
import com.ruoyi.flowable.domain.dto.task.BpmProcessInstanceCreateReqDTO;
import com.ruoyi.flowable.domain.entity.oa.BpmOALeaveDO;
import com.ruoyi.flowable.domain.vo.oa.BpmOALeaveCreateReqVO;
import com.ruoyi.flowable.domain.vo.oa.BpmOALeavePageReqVO;
import com.ruoyi.flowable.mapper.oa.BpmOALeaveMapper;
import com.ruoyi.flowable.service.definition.BpmProcessInstanceApi;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

import static com.ruoyi.common.exception.util.ServiceExceptionUtil.exception;
import static com.ruoyi.flowable.core.enums.ErrorCodeConstants.OA_LEAVE_NOT_EXISTS;

/**
 * OA 请假申请 Service 实现类
 *
 * @author jason
 * hasPermi
 */
@Service
@Validated
public class BpmOALeaveServiceImpl implements BpmOALeaveService {

    /**
     * OA 请假对应的流程定义 KEY
     */
    public static final String PROCESS_KEY = "oa_leave";

    @Resource
    private BpmOALeaveMapper leaveMapper;

    @Resource
    private BpmProcessInstanceApi processInstanceApi;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createLeave(Long userId, BpmOALeaveCreateReqVO createReqVO) {
        // 插入 OA 请假单
        long day = DateUtil.betweenDay(createReqVO.getStartTime(), createReqVO.getEndTime(), false);
        BpmOALeaveDO leave = BpmOALeaveConvert.INSTANCE.convert(createReqVO).setUserId(userId).setDay(day)
                .setResult(BpmProcessInstanceResultEnum.PROCESS.getResult());
        leaveMapper.insert(leave);

        // 发起 BPM 流程
        Map<String, Object> processInstanceVariables = new HashMap<>();
        processInstanceVariables.put("day", day);
        String processInstanceId = processInstanceApi.createProcessInstance(userId,
                new BpmProcessInstanceCreateReqDTO().setProcessDefinitionKey(PROCESS_KEY)
                        .setVariables(processInstanceVariables).setBusinessKey(String.valueOf(leave.getId())));

        // 将工作流的编号，更新到 OA 请假单中
        leaveMapper.updateById(new BpmOALeaveDO().setId(leave.getId()).setProcessInstanceId(processInstanceId));
        return leave.getId();
    }

    @Override
    public void updateLeaveResult(Long id, Integer result) {
        validateLeaveExists(id);
        leaveMapper.updateById(new BpmOALeaveDO().setId(id).setResult(result));
    }

    private void validateLeaveExists(Long id) {
        if (leaveMapper.selectById(id) == null) {
            throw exception(OA_LEAVE_NOT_EXISTS);
        }
    }

    @Override
    public BpmOALeaveDO getLeave(Long id) {
        return leaveMapper.selectById(id);
    }

    @Override
    public PageResult<BpmOALeaveDO> getLeavePage(Long userId, BpmOALeavePageReqVO pageReqVO) {
        return leaveMapper.selectPage(userId, pageReqVO);
    }

}
