package cc.mrbird.febs.gateway;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author MrBird
 */
@SpringBootApplication
public class FebsGatewayApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(FebsGatewayApplication.class)
                .web(WebApplicationType.REACTIVE)
                .run(args);
    }
}
