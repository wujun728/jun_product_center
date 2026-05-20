package com.ruoyi.biz.service.impl;

import com.ruoyi.biz.domain.BizButton;
import com.ruoyi.biz.domain.CommonButton;
import com.ruoyi.biz.factory.BizButtonDataFactory;
import com.ruoyi.biz.service.IBizButtonDataService;
import com.ruoyi.biz.service.IBizButtonService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * <p> 业务按钮接口实现类 </p>
 *
 * @Author wocurr.com
 */
@Service
public class BizButtonServiceImpl implements IBizButtonService {

    @Autowired
    private BizButtonDataFactory bizButtonDataFactory;

    @Override
    public List<BizButton> getButtons(CommonButton button) {
        List<BizButton> bizButtons = new ArrayList<>();
        Map<String, IBizButtonDataService> bizButtonDataMap = bizButtonDataFactory.getBizButtonDataMap();
        for (Map.Entry<String, IBizButtonDataService> entry : bizButtonDataMap.entrySet()) {
            IBizButtonDataService iBizButtonDataService = entry.getValue();
            if (iBizButtonDataService.isShow(button)) {
                BizButton bizButton = new BizButton();
                bizButton.setCode(iBizButtonDataService.getCode());
                bizButton.setName(iBizButtonDataService.getName());
                bizButton.setSort(iBizButtonDataService.getSort());
                bizButton.setShowFlag(true);
                bizButtons.add(bizButton);
            }
        }
        if (CollectionUtils.isNotEmpty(bizButtons)) {
            bizButtons.sort(Comparator.comparingInt(BizButton::getSort));
        }
        return bizButtons;
    }
}
