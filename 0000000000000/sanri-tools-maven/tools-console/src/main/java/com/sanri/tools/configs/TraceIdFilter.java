package com.sanri.tools.configs;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.util.IdGenerator;
import org.springframework.util.SimpleIdGenerator;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.UUID;

@WebFilter(urlPatterns = "/*", filterName = "traceIdFilter")
public class TraceIdFilter implements Filter {
    private static final String UNIQUE_ID = "traceId";

    private static IdGenerator idGenerator = new SimpleIdGenerator();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        UUID uuid = idGenerator.generateId();
        String uniqueId = uuid.toString().replace("-", "");
        uniqueId = StringUtils.substring(uniqueId,-10);
        MDC.put(UNIQUE_ID, uniqueId);
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        }finally {
            MDC.remove(UNIQUE_ID);
        }
    }
}
