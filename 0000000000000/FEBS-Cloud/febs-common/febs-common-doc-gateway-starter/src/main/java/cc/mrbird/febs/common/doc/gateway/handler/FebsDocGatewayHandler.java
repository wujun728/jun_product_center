package cc.mrbird.febs.common.doc.gateway.handler;

import cc.mrbird.febs.common.doc.gateway.properties.FebsDocGatewayProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import springfox.documentation.swagger.web.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author MrBird
 */
@RestController
public class FebsDocGatewayHandler {

    private SecurityConfiguration securityConfiguration;
    private UiConfiguration uiConfiguration;
    private SwaggerResourcesProvider swaggerResources;
    private FebsDocGatewayProperties properties;

    public void setSecurityConfiguration(SecurityConfiguration securityConfiguration) {
        this.securityConfiguration = securityConfiguration;
    }

    public void setUiConfiguration(UiConfiguration uiConfiguration) {
        this.uiConfiguration = uiConfiguration;
    }

    public void setSwaggerResources(SwaggerResourcesProvider swaggerResources) {
        this.swaggerResources = swaggerResources;
    }

    public void setProperties(FebsDocGatewayProperties properties) {
        this.properties = properties;
    }

    @GetMapping("/swagger-resources/configuration/security")
    public Mono<ResponseEntity<SecurityConfiguration>> securityConfiguration() {
        return Mono.just(new ResponseEntity<>(
                Optional.ofNullable(securityConfiguration).orElse(SecurityConfigurationBuilder.builder().build()), HttpStatus.OK));
    }

    @GetMapping("/swagger-resources/configuration/ui")
    public Mono<ResponseEntity<UiConfiguration>> uiConfiguration() {
        return Mono.just(new ResponseEntity<>(
                Optional.ofNullable(uiConfiguration).orElse(UiConfigurationBuilder.builder().build()), HttpStatus.OK));
    }

    @GetMapping("/swagger-resources")
    public Mono<ResponseEntity<List<SwaggerResource>>> swaggerResources() {
        List<SwaggerResource> swaggerResources = this.swaggerResources.get();
        List<SwaggerResource> filterList = new ArrayList<>();
        String resources = properties.getResources();
        String[] resourcesArray = StringUtils.splitByWholeSeparatorPreserveAllTokens(resources, ",");
        if (resourcesArray != null && resources.length() > 0) {
            boolean include = false;
            for (SwaggerResource resource : swaggerResources) {
                if (Arrays.stream(resourcesArray).anyMatch(r -> StringUtils.equalsIgnoreCase(r, resource.getName()))) {
                    include = true;
                }
                if (include) {
                    filterList.add(resource);
                }
            }
            return Mono.just((new ResponseEntity<>(filterList, HttpStatus.OK)));
        }
        return Mono.just((new ResponseEntity<>(swaggerResources, HttpStatus.OK)));
    }
}
