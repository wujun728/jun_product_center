package com.ruoyi.biz.factory;

import com.ruoyi.biz.service.IBizButtonDataService;
import com.ruoyi.tools.utils.bean.ApplicationContextHelper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p> 业务按钮数据工厂 </p>
 *
 * @Author wocurr.com
 */
@Component
public class BizButtonDataFactory {

    private final Map<String, IBizButtonDataService> bizButtonDataMap = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        //从Spring容器获取所有的IBizButtonDataService实现类
        Map<String, IBizButtonDataService> bizButtonDataServiceMap =  ApplicationContextHelper.getApplicationContext().getBeansOfType(IBizButtonDataService.class);
        bizButtonDataServiceMap.forEach((s, iBizButtonDataService) -> {
            bizButtonDataMap.put(iBizButtonDataService.getCode(), iBizButtonDataService);
        });
    }

    public Map<String, IBizButtonDataService> getBizButtonDataMap() {
        return bizButtonDataMap;
    }
}