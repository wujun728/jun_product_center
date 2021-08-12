package com.shuogesha;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
//@ComponentScan("com.shuogesha") // 扫controller包 类似与传统的配置中 开启扫包模式那段xml配置
@EnableScheduling // 使能定时任务
@MapperScan("com.shuogesha.*.dao")
@ImportResource("quartz.xml")
@EnableCaching
//@EnableTransactionManagement(proxyTargetClass = true)
@EnableTransactionManagement // （单个业务启动事务管理）
public class Base2020Application {

	public static void main(String[] args) {
		SpringApplication.run(Base2020Application.class, args);
	}

}
