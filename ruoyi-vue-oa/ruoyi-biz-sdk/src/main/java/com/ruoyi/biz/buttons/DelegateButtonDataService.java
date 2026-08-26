package com.ruoyi.biz.buttons;

import com.ruoyi.biz.domain.CommonButton;
import com.ruoyi.biz.enums.BizButtonEnum;
import com.ruoyi.biz.enums.PageTypeEnum;
import com.ruoyi.biz.service.abs.AbstractBizButtonData;
import org.springframework.stereotype.Component;

/**
 * <p> 委派按钮 </p>
 *
 * @Author wocurr.com
 */
@Component
public class DelegateButtonDataService extends AbstractBizButtonData {
    @Override
    public String getCode() {
        return BizButtonEnum.DELEGATE.getCode();
    }

    @Override
    public String getName() {
        return BizButtonEnum.DELEGATE.getName();
    }

    @Override
    public Integer getSort() {
        return 2;
    }

    @Override
    public Boolean isShow(CommonButton button) {
        if (PageTypeEnum.READ.getCode().equals(button.getPageType())) {
            return false;
        }
        return isShowButton(button);
    }
}
