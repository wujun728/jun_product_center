package com.shuogesha;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableScheduling // 使能定时任务
@MapperScan("com.shuogesha.*.dao")
@EnableTransactionManagement // （单个业务启动事务管理）
public class ShuogeshaBootFastApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShuogeshaBootFastApplication.class, args);
	}

}
