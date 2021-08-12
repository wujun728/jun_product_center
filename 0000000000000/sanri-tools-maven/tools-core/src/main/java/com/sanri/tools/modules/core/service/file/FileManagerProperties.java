package com.sanri.tools.modules.core.service.file;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
@Data
@ConfigurationProperties(prefix = "data.path")
public class FileManagerProperties {
    private File config;
    private File tmp;
}
