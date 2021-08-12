package com.pearadmin.common.config;

import cn.hutool.extra.mail.MailAccount;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.pearadmin.common.constant.ConfigurationConstant;
import com.pearadmin.common.plugin.system.service.SysContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import javax.annotation.Resource;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class CoreConfig {

    @Resource
    private SysContext sysContext;

    @Bean
    public MailAccount mailAccount() {
        MailAccount mailAccount = new MailAccount();
        mailAccount.setHost(sysContext.getConfig(ConfigurationConstant.MAIN_HOST));
        mailAccount.setPort(sysContext.getConfig(ConfigurationConstant.MAIN_PORT)==""?0000: Integer.parseInt(sysContext.getConfig(ConfigurationConstant.MAIN_PORT)));
        mailAccount.setFrom(sysContext.getConfig(ConfigurationConstant.MAIN_FROM));
        mailAccount.setUser(sysContext.getConfig(ConfigurationConstant.MAIN_USER));
        mailAccount.setPass(sysContext.getConfig(ConfigurationConstant.MAIN_PASS));
        mailAccount.setCharset(StandardCharsets.UTF_8);
        mailAccount.setAuth(true);
        return mailAccount;
    }

    @Bean
    public RedisTemplate<String, Serializable>
    redisTemplate(LettuceConnectionFactory connectionFactory) {
        RedisTemplate<String, Serializable> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setConnectionFactory(connectionFactory);
        return redisTemplate;
    }

    @Bean
    public Module dateTime(){
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern("HH:mm:ss")));
        return javaTimeModule;
    }
}
