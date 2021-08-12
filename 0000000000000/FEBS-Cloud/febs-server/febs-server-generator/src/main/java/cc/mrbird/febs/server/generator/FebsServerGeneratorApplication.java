package cc.mrbird.febs.server.generator;

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
@MapperScan("cc.mrbird.febs.server.generator.mapper")
public class FebsServerGeneratorApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(FebsServerGeneratorApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }

}
