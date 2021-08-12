package cc.mrbird.febs.server.test;

import cc.mrbird.febs.common.security.starter.annotation.EnableFebsCloudResourceServer;
import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author MrBird
 */
@EnableFeignClients
@SpringBootApplication
@EnableFebsCloudResourceServer
@EnableTransactionManagement
@EnableDistributedTransaction
@MapperScan("cc.mrbird.febs.server.test.mapper")
public class FebsServerTestApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(FebsServerTestApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }

}
