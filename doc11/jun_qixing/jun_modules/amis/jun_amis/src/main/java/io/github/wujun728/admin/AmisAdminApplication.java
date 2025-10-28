//package io.github.wujun728.admin;
//
////import org.activiti.spring.boot.SecurityAutoConfiguration;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Primary;
//import org.springframework.core.task.TaskExecutor;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
//import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
//
//@SpringBootApplication(exclude = {/*SecurityAutoConfiguration.class,*/
//        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class},
//        scanBasePackages = {
//                "io.github.wujun728.*.**"/*,
//                "org.activiti.rest.diagram.services"*/
//        }
//)
////超时时间两小时
//@EnableScheduling
//@EnableWebSocketMessageBroker
//public class AmisAdminApplication {
//    public static void main(String[] args) {
//        SpringApplication.run(AmisAdminApplication.class, args);
//    }
//
//    @Primary
//    @Bean
//    public TaskExecutor primaryTaskExecutor() {
//        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//        return executor;
//    }
//
//}
