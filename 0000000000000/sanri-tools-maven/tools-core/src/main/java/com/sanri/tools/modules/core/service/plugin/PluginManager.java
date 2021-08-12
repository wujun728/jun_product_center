package com.sanri.tools.modules.core.service.plugin;

import com.sanri.tools.modules.core.dtos.PluginDto;
import com.sanri.tools.modules.core.service.file.FileManager;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 插件就是以前的 tools
 * 将环境的判断提到了前端,需要前端获取插件后,根据前端 localStorage 保存的 env 去过滤工具列表
 */
@Service
@Slf4j
public class PluginManager {
    private List<EnhancePluginDto> enhancePlugins = new ArrayList<>();
    @Autowired
    private FileManager fileManager;

    // 上一次序列化的值
    private Map<String,SerializerPlugin> serializerPluginMap = new HashMap<>();

    /**
     * 注册插件,一个模块不一定只注册一个插件,可以注册多个的
     * 比如 : 数据库模块就可以注册代码生成,元数据查看,数据迁移 等插件更有利于前端的分离
     * @param pluginDto
     */
    public void register(PluginDto pluginDto){
        if (StringUtils.isBlank(pluginDto.getModule()) || StringUtils.isBlank(pluginDto.getName())){
            log.error("插件[{}:{}]注册失败,必填值为空 module name",pluginDto.getModule(),pluginDto.getName());
            return ;
        }
        log.info("注册插件[{}]",pluginDto);
        SerializerPlugin serializerPlugin = serializerPluginMap.get(pluginDto.key());
        if(serializerPlugin == null) {
            enhancePlugins.add(new EnhancePluginDto(pluginDto));
        }else{
            enhancePlugins.add(new EnhancePluginDto(pluginDto,serializerPlugin));
        }
    }

    /**
     * 获取插件名列表
     * @return
     */
    public Set<String> pluginNames() {
        Set<String> collect = enhancePlugins.stream()
                .map(EnhancePluginDto::getPluginDto)
                .map(PluginDto::key)
                .collect(Collectors.toSet());
        return collect;
    }

    /**
     * 排序一次插件列表
     * @return
     */
    public List<EnhancePluginDto> plugins() {
        Collections.sort(enhancePlugins);
        return enhancePlugins;
    }

    /**
     * 访问一次插件
     * @param key
     */
    public void visitedPlugin(String key){
        EnhancePluginDto enhancePluginDto = detail(key);
        if (enhancePluginDto != null){
            enhancePluginDto.setLastCallTime(System.currentTimeMillis());
            enhancePluginDto.setTotalCalls(enhancePluginDto.getTotalCalls() + 1);
        }
    }

    /**
     * 读取上次序列化的值
     */
    @PostConstruct
    public void init(){
        try {
            String serializerValues = fileManager.readConfig("plugin", "visited");
            if(StringUtils.isNotBlank(serializerValues)){
                String[] split = StringUtils.split(serializerValues, '\n');
                for (String line : split) {
                    String[] items = StringUtils.split(line, ',');
                    serializerPluginMap.put(items[0],new SerializerPlugin(items[0], NumberUtils.toInt(items[1]),NumberUtils.toLong(items[2])));
                }
            }
        } catch (Exception e) {
        }
    }

    @PreDestroy
    public void destory(){
        // 在即将销毁时序列化插件配置
        serializer();
    }

    /**
     * 定时将访问次数和上次访问时间序列化
     * 30 分钟序列化一次,100 秒后第一次序列化,不能和初始化的读取冲突
     */
    @Scheduled(fixedRate = 1800000,initialDelay = 100000)
    public void serializer(){
        // 准备数据
        List<String> lines = new ArrayList<>();
        for (EnhancePluginDto enhancePlugin : enhancePlugins) {
            PluginDto pluginDto = enhancePlugin.getPluginDto();
            String key = pluginDto.key();
            List<? extends Serializable> serializables = Arrays.asList(key, enhancePlugin.getTotalCalls(), enhancePlugin.getLastCallTime());
            lines.add(StringUtils.join(serializables,','));
        }
        String join = StringUtils.join(lines, '\n');
        try {
            fileManager.writeConfig("plugin","visited",join);
        } catch (IOException e) {
        }
    }

    /**
     * 插件详情
     * @param key
     * @return
     */
    public EnhancePluginDto detail(String key) {
       return  enhancePlugins.stream().filter(item -> item.getPluginDto().key().equals(key)).findFirst().get();
    }

    @Getter
    class SerializerPlugin{
        private String key;
        private int totalCalls;
        private long lastCallTime;

        public SerializerPlugin(String key, int totalCalls, long lastCallTime) {
            this.key = key;
            this.totalCalls = totalCalls;
            this.lastCallTime = lastCallTime;
        }
    }
}
