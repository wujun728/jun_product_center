package com.ruoyi.biz.buttons;

import com.ruoyi.biz.domain.CommonButton;
import com.ruoyi.biz.enums.BizButtonEnum;
import com.ruoyi.biz.enums.PageTypeEnum;
import com.ruoyi.biz.service.abs.AbstractBizButtonData;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.flowable.domain.dto.FlowTaskDto;
import com.ruoyi.flowable.service.IFlowTaskService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * <p> 签收按钮 </p>
 *
 * @Author wocurr.com
 */
@Component
public class SignButtonDataService extends AbstractBizButtonData {

    @Autowired
    private IFlowTaskService flowTaskService;

    @Override
    public String getCode() {
        return BizButtonEnum.SIGN.getCode();
    }

    @Override
    public String getName() {
        return BizButtonEnum.SIGN.getName();
    }

    @Override
    public Integer getSort() {
        return 888;
    }

    @Override
    public Boolean isShow(CommonButton button) {
        if (PageTypeEnum.READ.getCode().equals(button.getPageType())) {
            return false;
        }
        if (!isShowButton(button)) {
            return false;
        }
        //多人审批情况
        List<FlowTaskDto> currentTaskList = flowTaskService.getTaskList(button.getProcInsId(), Collections.singletonList(button.getTaskId()));
        if (CollectionUtils.isEmpty(currentTaskList) || currentTaskList.size() > 1) {
            return false;
        }
        FlowTaskDto flowTaskDto = currentTaskList.get(0);
        if (StringUtils.isNotBlank(flowTaskDto.getAssignee())) {
            return flowTaskDto.getAssignee().split(Constants.COMMA).length > 1;
        }
        if (StringUtils.isNotBlank(flowTaskDto.getCandidate())) {
            return flowTaskDto.getCandidate().split(Constants.COMMA).length > 1;
        }
        return false;
    }
}
