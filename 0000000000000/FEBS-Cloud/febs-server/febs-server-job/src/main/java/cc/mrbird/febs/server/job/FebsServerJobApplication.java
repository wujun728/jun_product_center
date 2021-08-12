package cc.mrbird.febs.server.job;

import cc.mrbird.febs.common.security.starter.annotation.EnableFebsCloudResourceServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author MrBird
 */
@SpringBootApplication
@EnableFebsCloudResourceServer
@MapperScan("cc.mrbird.febs.server.job.mapper")
public class FebsServerJobApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(FebsServerJobApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }

}
