package com.sanri.tools.modules.core.controller;

import com.sanri.tools.modules.core.service.plugin.EnhancePluginDto;
import com.sanri.tools.modules.core.dtos.PluginDto;
import com.sanri.tools.modules.core.service.plugin.PluginManager;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.io.*;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/plugin")
@Validated
public class PluginController {
    @Autowired
    private PluginManager pluginManager;

    /**
     * 获取所有的插件名
     * @return
     */
    @GetMapping("/names")
    public Set<String> names(){
        return pluginManager.pluginNames();
    }

    /**
     * 获取插件列表,经过排序的
     * @return
     */
    @GetMapping("/list")
    public List<EnhancePluginDto> plugins(){
        return pluginManager.plugins();
    }

    /**
     * 访问某个插件
     * @param key
     */
    @GetMapping("/visited")
    public void visited(@NotNull String key){
        pluginManager.visitedPlugin(key);
    }

    /**
     * 获取插件详情
     * @return
     */
    @GetMapping("/detail")
    public PluginDto detail(@NotNull String key) throws IOException {
        EnhancePluginDto detail = pluginManager.detail(key);
        if(detail != null) {
            PluginDto pluginDto = detail.getPluginDto();
            String help = pluginDto.getHelp();
            if (StringUtils.isNotBlank(help)){
                ClassPathResource classPathResource = new ClassPathResource(help);
                InputStream inputStream = null;
                try {
                   inputStream = classPathResource.getInputStream();
                   String content = IOUtils.toString(inputStream, "utf-8");
                   pluginDto.setHelpContent(content);
                } finally {
                    IOUtils.closeQuietly(inputStream);
                }
            }
            return pluginDto;
        }
        return null;
    }

    /**
     * 将当前访问量和访问次数进行序列化
     */
    @GetMapping("/serializer")
    public void serializer(){
        pluginManager.serializer();
    }
}
