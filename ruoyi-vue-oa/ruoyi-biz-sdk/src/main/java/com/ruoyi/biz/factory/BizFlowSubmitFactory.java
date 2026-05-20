package com.ruoyi.biz.factory;

import com.ruoyi.biz.service.IBizFLowSubmitService;
import com.ruoyi.tools.utils.bean.ApplicationContextHelper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p> 业务按钮工厂 </p>
 *
 * @Author wocurr.com
 */
@Component
public class BizFlowSubmitFactory {

    private final Map<String, IBizFLowSubmitService> bizFlowSubmitMap = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        //从Spring容器获取所有的IBizFLowSubmitService实现类
        Map<String, IBizFLowSubmitService> bizFLowSubmitServiceMap = ApplicationContextHelper.getApplicationContext().getBeansOfType(IBizFLowSubmitService.class);
        bizFLowSubmitServiceMap.forEach((s, iBizFLowSubmitService) -> {
            bizFlowSubmitMap.put(iBizFLowSubmitService.getBizType(), iBizFLowSubmitService);
        });
    }

    public IBizFLowSubmitService getBizFLowSubmitImplByType(String bizType) {
        return bizFlowSubmitMap.get(bizType);
    }
}