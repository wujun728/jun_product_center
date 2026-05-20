package com.ruoyi.biz.factory;

import com.ruoyi.biz.service.IBizFormDataService;
import com.ruoyi.tools.utils.bean.ApplicationContextHelper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p> 业务表单数据工厂 </p>
 *
 * @Author wocurr.com
 */
@Component
public class BizFormDataFactory {

    private final Map<String, IBizFormDataService> bizFormDataMap = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        //从Spring容器获取所有的IBizFormDataService实现类
        Map<String, IBizFormDataService> bizFormDataServiceMap = ApplicationContextHelper.getApplicationContext().getBeansOfType(IBizFormDataService.class);
        bizFormDataServiceMap.forEach((s, iBizFormDataService) -> {
            bizFormDataMap.put(iBizFormDataService.getBizType(), iBizFormDataService);
        });
    }

    public IBizFormDataService getBizFormDataImplByType(String bizType) {
        return bizFormDataMap.get(bizType);
    }
}