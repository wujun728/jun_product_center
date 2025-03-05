package com.ruoyi;

import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.qixing.controller.QixingController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.util.ObjectUtils;

/**
 * 启动程序
 * 
 * @author ruoyi
 */
@SpringBootApplication(scanBasePackages = {"com.ruoyi"}, exclude = { DataSourceAutoConfiguration.class })
public class RuoYiApplication
{
    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(RuoYiApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  若依启动成功   ლ(´ڡ`ლ)ﾞ  \n");
        QixingController qx = SpringUtils.getBean(QixingController.class);
        if(!ObjectUtils.isEmpty(qx)){
            System.out.println(" qixing is not null ");
        }
    }
}
