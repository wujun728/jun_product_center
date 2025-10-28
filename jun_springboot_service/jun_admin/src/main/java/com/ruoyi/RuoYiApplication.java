//package com.ruoyi;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
//import org.springframework.core.env.Environment;
//
///**
// * 启动程序
// *
// * @author ruoyi
// */
//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class},
//    scanBasePackages = {"com.ruoyi", "io.github.wujun728.online","com.jun.plugin"})
//@Slf4j
//public class RuoYiApplication {
//    public static void main(String[] args) {
//        SpringApplication app = new SpringApplication(RuoYiApplication.class);
//        Environment env = app.run(args).getEnvironment();
//        printServerStartupInfo(env);
//    }
//
//    private static void printServerStartupInfo(Environment env) {
//        String serverPort = env.getProperty("local.server.port");
//        System.out.println( " 启动成功 ");
//        log.info("{}启动成功，端口号：{}",  serverPort);
//    }
//}
