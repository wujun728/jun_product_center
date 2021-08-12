package cc.mrbird.febs.monitor.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author MrBird
 */
@EnableAdminServer
@SpringBootApplication
public class FebsAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(FebsAdminApplication.class, args);
    }

}
