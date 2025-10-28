package com.ruoyi;

import cn.hutool.extra.spring.SpringUtil;
import com.jun.plugin.qixing.qixing.QixingController;
import com.ruoyi.framework.datasource.DynamicDataSourceContextHolder;
import io.github.wujun728.db.record.Db;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.ObjectUtils;

import static io.github.wujun728.db.record.Db.main;

/**
 * 启动程序
 *
 * @author ruoyi
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class } )
@ComponentScan(value = {"com.ruoyi", "io.github.wujun728","com.jun.plugin"})
//@EnableScheduling
//@EnableWebSocketMessageBroker
public class QixingApplication
{
    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(QixingApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ   启动成功   ლ(´ڡ`ლ)ﾞ  \n");
        QixingController qx = SpringUtil.getBean(QixingController.class);
        if(!ObjectUtils.isEmpty(qx)){
            System.out.println(" qixing is not null ");
        }
    }

    @Primary
    @Bean
    public TaskExecutor primaryTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        return executor;
    }

}
