package io.github.wujun728.shiro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author wujun
 * @date 2021/3/19
 */
@Configuration
@ComponentScan(basePackages = "io.github.wujun728.shiro")
@MapperScan(basePackages = "io.github.wujun728.shiro.mapper")
@ServletComponentScan(basePackages = {"io.github.wujun728.shiro.compoent"})
public class ShiroAutoConfig {
}
