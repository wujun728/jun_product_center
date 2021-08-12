package cc.mrbird.web.common.thymeleaf.config;

import cc.mrbird.web.common.thymeleaf.dict.DictDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义字典标签控制类
 *
 * @author lzx
 * @date 2018年11月26日20:26:42
 *
 */
@Configuration
public class DialectConfig {

	@Bean
    public DictDialect getDictDialect() {
        return new DictDialect();
    }
}
