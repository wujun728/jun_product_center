package com.ruoyi.biz.service.abs;

import com.ruoyi.biz.domain.CommonButton;
import com.ruoyi.biz.flow.ProcessVarService;
import com.ruoyi.biz.service.IBizButtonDataService;
import com.ruoyi.common.enums.WhetherStatus;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p> 业务按钮数据抽象类 </p>
 *
 * @Author wocurr.com
 */
public abstract class AbstractBizButtonData implements IBizButtonDataService {

    @Autowired
    private ProcessVarService processVarService;

    public Boolean isShowButton(CommonButton button) {
        if (button.getVariables() == null) {
            button.setVariables(processVarService.getTaskVariables(button.getTaskId()));
        }
        if (!button.getVariables().containsKey(getCode())) {
            return false;
        }
        String value = String.valueOf(button.getVariables().get(getCode()));
        return StringUtils.equals(WhetherStatus.YES.getCode(), value);
    }
}
