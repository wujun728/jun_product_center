package me.wuwenbin.noteblogv5.config.mvc;

import me.wuwenbin.noteblogv5.config.filter.AdminFilter;
import me.wuwenbin.noteblogv5.config.filter.InitFilter;
import me.wuwenbin.noteblogv5.config.filter.SessionFilter;
import me.wuwenbin.noteblogv5.config.filter.TokenFilter;
import me.wuwenbin.noteblogv5.constant.NBV5;
import me.wuwenbin.noteblogv5.utils.NbUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

/**
 * created by Wuwenbin on 2019/3/15 at 21:21
 *
 * @author wuwenbin
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final Environment env;

    @Autowired
    public WebMvcConfig(Environment env) {
        this.env = env;
    }

    /**
     * 添加一些虚拟路径的映射
     * 静态资源路径和上传文件的路径
     * 如果配置了七牛云上传，则上传路径无效
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations(env.getProperty("spring.resources.static-locations"));
        String uploadPath = env.getProperty(NBV5.APP_UPLOAD_PATH);
        if (StringUtils.isEmpty(uploadPath)) {
            uploadPath = NbUtils.rootPath().concat("/uploadv5/");
        }
        registry.addResourceHandler("/upfiles/**").addResourceLocations(uploadPath);
    }

    /**
     * 全局拦截器
     * 顺序：系统初始化/访问日志-->用户是否登录-->后台管理用户验证-->视图主题渲染
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> excludePaths = Arrays.asList("/static/**", "/error/**", "/init/**");
        registry.addInterceptor(new InitFilter()).addPathPatterns("/**").excludePathPatterns(excludePaths);
        registry.addInterceptor(new SessionFilter()).addPathPatterns("/**").excludePathPatterns(excludePaths);
        registry.addInterceptor(new TokenFilter()).addPathPatterns("/token/**", "/**/token/**");
        registry.addInterceptor(new AdminFilter()).addPathPatterns("/management/**");
    }
}
