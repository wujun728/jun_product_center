package com.ruoyi.biz.service;

import com.ruoyi.biz.domain.BizButton;
import com.ruoyi.biz.domain.CommonButton;

import java.util.List;

/**
 * <p> 业务按钮接口 </p>
 *
 * @Author wocurr.com
 */
public interface IBizButtonService {

    List<BizButton> getButtons(CommonButton button);
}
