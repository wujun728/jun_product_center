package me.wuwenbin.noteblogv5;

import me.wuwenbin.noteblogv5.annotation.MybatisMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author wuwenbin
 */
@SpringBootApplication
@MapperScan(basePackages = "me.wuwenbin.noteblogv5.mapper", annotationClass = MybatisMapper.class)
@EnableScheduling
@EnableCaching
public class Noteblogv5Application {

    public static void main(String[] args) {
        SpringApplication.run(Noteblogv5Application.class, args);
    }

}
