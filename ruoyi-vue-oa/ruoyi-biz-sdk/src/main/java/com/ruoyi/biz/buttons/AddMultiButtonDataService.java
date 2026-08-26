package com.ruoyi.biz.buttons;

import com.ruoyi.biz.domain.CommonButton;
import com.ruoyi.biz.enums.BizButtonEnum;
import com.ruoyi.biz.enums.PageTypeEnum;
import com.ruoyi.biz.service.abs.AbstractBizButtonData;
import org.springframework.stereotype.Component;

/**
 * <p> 加签按钮 </p>
 *
 * @Author wocurr.com
 */
@Component
public class AddMultiButtonDataService extends AbstractBizButtonData {
    @Override
    public String getCode() {
        return BizButtonEnum.ADD_MULTI.getCode();
    }

    @Override
    public String getName() {
        return BizButtonEnum.ADD_MULTI.getName();
    }

    @Override
    public Integer getSort() {
        return 4;
    }

    @Override
    public Boolean isShow(CommonButton button) {
        if (PageTypeEnum.READ.getCode().equals(button.getPageType())) {
            return false;
        }
        return isShowButton(button);
    }
}
