package com.ruoyi.mq.factory;

import com.ruoyi.mq.product.IAsyncTaskProduction;
import com.ruoyi.mq.enums.MqTypeEnum;
import com.ruoyi.mq.product.RabbitAsyncTaskProduction;
import com.ruoyi.tools.utils.bean.ApplicationContextHelper;

import java.util.concurrent.ConcurrentHashMap;

/**
 * <p> 异步任务服务工厂 </p>
 *
 * @Author wocurr.com
 */
public class MqAsyncServerFactory {

    private ConcurrentHashMap<String, Class<? extends IAsyncTaskProduction>> asyncProductServerMap = new ConcurrentHashMap<>();

    private MqAsyncServerFactory() {
        asyncProductServerMap.put(MqTypeEnum.RABBITMQ.getCode(), RabbitAsyncTaskProduction.class);
    }

    private static class InnerMqAsyncServerFactory {
        private static final MqAsyncServerFactory INSTANCE = new MqAsyncServerFactory();
    }

    public static MqAsyncServerFactory getInstance() {
        return InnerMqAsyncServerFactory.INSTANCE;
    }

    public IAsyncTaskProduction getProduction(String type) {
        Class<? extends IAsyncTaskProduction> clazz = asyncProductServerMap.get(type);
        if (clazz == null) {
            throw new RuntimeException("类型不支持！");
        }
        return ApplicationContextHelper.getBean(clazz);
    }
}
