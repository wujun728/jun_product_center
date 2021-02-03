package me.wuwenbin.noteblogv5.config.ssl;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * created by Wuwenbin on 2019-01-30 at 14:48
 *
 * @author wuwenbin
 */
@Configuration
public class SslConfig {

    private static final String SSL_ENABLED = "server.ssl.enabled";
    private static final String SERVER_HTTP_PORT = "server.http.port";
    private static final String SERVER_HTTPS_PORT = "server.port";
    private final Environment environment;


    @Autowired
    public SslConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public TomcatServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
            @Override
            protected void postProcessContext(Context context) {
                if (environment.getProperty(SSL_ENABLED, Boolean.class, Boolean.FALSE)) {
                    SecurityConstraint constraint = new SecurityConstraint();
                    constraint.setUserConstraint("CONFIDENTIAL");
                    SecurityCollection collection = new SecurityCollection();
                    collection.addPattern("/*");
                    constraint.addCollection(collection);
                    context.addConstraint(constraint);
                } else {
                    super.postProcessContext(context);
                }
            }
        };
        if (environment.getProperty(SSL_ENABLED, Boolean.class, Boolean.FALSE)) {
            tomcat.addAdditionalTomcatConnectors(httpConnector());
        }
        return tomcat;
    }


    @Bean
    public Connector httpConnector() {
        Connector connector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
        connector.setScheme("http");
        //Connector监听的http的端口号
        connector.setPort(environment.getProperty(SERVER_HTTP_PORT, Integer.class, 80));
        connector.setSecure(false);
        //监听到http的端口号后转向到的https的端口号
        connector.setRedirectPort(environment.getProperty(SERVER_HTTPS_PORT, Integer.class, 443));
        return connector;
    }
}
