package com.sanri.tools.modules.rocketmq.service;

import com.sanri.tools.modules.rocketmq.service.client.MQAdminExtImpl;
import org.apache.rocketmq.tools.admin.MQAdminExt;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RocketmqService {

    private Map<String, MQAdminExt> clients = new ConcurrentHashMap<>();

    /**
     * 加载一个 rocketmq 连接
     * @param connName
     * @return
     */
    MQAdminExt loadClient(String connName){
        MQAdminExt mqAdminExt = new MQAdminExtImpl();

        return mqAdminExt;
    }
}
