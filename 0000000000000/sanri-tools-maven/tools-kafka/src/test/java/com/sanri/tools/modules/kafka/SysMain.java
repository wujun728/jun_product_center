package com.sanri.tools.modules.kafka;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sanri.tools.modules.core.dtos.param.KafkaConnectParam;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class SysMain {
    @Test
    public void jsonLoadData() throws IOException {
        File file = new File("D:\\test\\config\\connect\\kafka/192.168.72.42_2181");
        String s = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        KafkaConnectParam kafkaProperties = JSON.parseObject(s, KafkaConnectParam.class);
        JSONObject jsonObject = JSON.parseObject(s);
        JSONObject jsonObject1 = jsonObject.getJSONObject("kafka").getJSONObject("consumer");
        KafkaProperties.Consumer consumer = JSON.parseObject(jsonObject1.toJSONString(), KafkaProperties.Consumer.class);
        System.out.println(consumer.getGroupId());
        System.out.println(kafkaProperties.getKafka().getConsumer().getGroupId());

        ObjectMapper objectMapper = new ObjectMapper();
        KafkaConnectParam kafkaConnectParam = objectMapper.readValue(s, KafkaConnectParam.class);
        System.out.println(kafkaConnectParam.getKafka().getConsumer().getGroupId());
    }
}
