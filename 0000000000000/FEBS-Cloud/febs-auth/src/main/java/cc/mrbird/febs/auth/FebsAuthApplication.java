package cc.mrbird.febs.auth;

import cc.mrbird.febs.common.security.starter.annotation.EnableFebsCloudResourceServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author MrBird
 */
@SpringBootApplication
@EnableRedisHttpSession
@EnableFebsCloudResourceServer
@MapperScan("cc.mrbird.febs.auth.mapper")
public class FebsAuthApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(FebsAuthApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }
}
