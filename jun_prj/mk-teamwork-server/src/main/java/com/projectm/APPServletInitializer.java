package com.projectm;

import cn.hutool.core.util.IdUtil;
import com.framework.common.core.lang.UUID;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * web容器中进行部署
 * 
 * @author gencya
 */
public class APPServletInitializer extends SpringBootServletInitializer
{
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application)
    {
        return application.sources(APPLauncher.class);
    }
}
