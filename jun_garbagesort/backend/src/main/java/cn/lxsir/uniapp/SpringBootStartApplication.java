package cn.lxsir.uniapp;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @创建人 luoxiang
 * @创建时间 2019/7/17  13:05
 * @描述
 */
public class SpringBootStartApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
// 注意这里要指向原先用main方法执行的Application启动类
        return builder.sources(UniappApiApplication.class);
    }
}
