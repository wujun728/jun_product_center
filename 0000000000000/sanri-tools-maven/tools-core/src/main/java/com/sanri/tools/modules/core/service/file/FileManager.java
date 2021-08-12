package com.sanri.tools.modules.core.service.file;

import com.sanri.tools.modules.core.dtos.ConfigPath;
import com.sanri.tools.modules.core.utils.ZipUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.*;

@Service
@Slf4j
public class FileManager {
    private File configBase;
    private File tmpBase ;

    public FileManager(FileManagerProperties fileManagerProperties){
        configBase = fileManagerProperties.getConfig();
        tmpBase = fileManagerProperties.getTmp();
    }

    @PostConstruct
    public void init(){
        log.info("配置文件目录:{}",configBase);
        log.info("临时文件目录:{}",tmpBase);
        if(configBase != null){
            configBase.mkdirs();
        }
        if(tmpBase != null){
            tmpBase.mkdirs();
        }
    }

    /**
     * 返回所有模块
     * @return
     */
    public List<String> modules(){
        return Arrays.asList(configBase.list());
    }

    /**
     * 写入配置信息
     * @param module 模块路径
     * @param baseName 基础文件名称 可使用子路径 a/b
     * @param configs 配置信息
     */
    public void writeConfig(String module,String baseName,String content) throws IOException {
        //content 可能有编码操作，需要解码
        content = URLDecoder.decode(content,"utf-8");
        File moduleDir = new File(configBase, module);
        // check module exists
        if(!moduleDir.exists())moduleDir.mkdir();

        File configFile = new File(moduleDir, baseName);
        configFile.getParentFile().mkdirs();
        FileUtils.writeStringToFile(configFile,content);
    }

    /**
     * 简单配置名列表
     * @param module
     * @return
     */
    public List<String> simpleConfigNames(String module){
        List<ConfigPath> configPaths = configNames(module);
        List<String> names = new ArrayList<>();
        for (ConfigPath configPath : configPaths) {
            names.add(configPath.getPathName());
        }
        return names;
    }

    /**
     * 简单配置名列表
     * @param module
     * @return
     */
    public List<String> simpleConfigNames(String module, String baseName){
        List<ConfigPath> configPaths = configNames(module+"/"+baseName);
        List<String> names = new ArrayList<>();
        for (ConfigPath configPath : configPaths) {
            names.add(configPath.getPathName());
        }
        return names;
    }

    /**
     * 读取模块配置列表/顶层
     * @param module
     * @return
     */
    public List<ConfigPath> configNames(String module){
        File moduleDir = new File(configBase, module);
        // check module exists
        if(!moduleDir.exists())moduleDir.mkdir();

        List<ConfigPath> configPaths = convertDir2ConfigPaths(moduleDir);
        return configPaths;
    }

    /**
     * 一层一层来展示模块子项列表
     * @param module
     * @param baseName
     * @return
     */
    public List<ConfigPath> configChildNames(String module,String baseName){
        if(StringUtils.isBlank(baseName)){
            return configNames(module);
        }
        File moduleDir = new File(configBase, module);
        // check module exists
        if(!moduleDir.exists())moduleDir.mkdir();

        File targetDir = new File(moduleDir, baseName);
        List<ConfigPath> configPaths = convertDir2ConfigPaths(targetDir);
        return configPaths;
    }

    /**
     * 读取配置
     * @param modulee
     * @param baseName
     * @return
     */
    public String readConfig(String module, String baseName) throws IOException {
        if(StringUtils.isBlank(baseName))return "";
        File moduleDir = new File(configBase, module);
        // check module exists
        if(!moduleDir.exists())moduleDir.mkdir();
        File file = new File(moduleDir, baseName);
        if(!file.exists()){
            return null;
        }
        return FileUtils.readFileToString(file);
    }

    private List<ConfigPath> convertDir2ConfigPaths(File moduleDir) {
        List<ConfigPath> configPaths = new ArrayList<>();
        File[] files = moduleDir.listFiles();
        if (files != null) {
            for (File file : files) {
                String name = file.getName();
                boolean directory = file.isDirectory();
                configPaths.add(new ConfigPath(name, directory, file));
            }
        }

        // 配置列表排序, 按照访问时间排序, 上次访问的配置文件优先靠前排序 window 上这个排序无效
        if (CollectionUtils.isNotEmpty(configPaths)){
            Collections.sort(configPaths);
        }
        return configPaths;
    }

    /**
     * 获取相对于临时文件夹的资源
     * @param baseName
     * @return
     */
    public Resource relativeResource(String baseName) {
        File file = new File(tmpBase, baseName);
        if(!file.exists())return null;

        if(file.isFile()){
            return new FileSystemResource(file);
        }

        int length = file.listFiles().length;
        if(length == 0){
            log.warn("空文件夹[{}]",file);
            return null;
        }

        // 只有一个文件的情况
        if(length == 1 && file.listFiles()[0].isFile()) {
            String fileName = file.list()[0];
            return new FileSystemResource(new File(file, fileName));
        }

        File zip = ZipUtil.zip(file);
        return new FileSystemResource(zip);
    }

    /**
     * 创建临时目录;支持子目录
     * @param baseName
     */
    public File mkTmpDir(String baseName) {
        File file = new File(tmpBase, baseName);
        if(!file.exists()){
            file.mkdirs();
        }
        return file;
    }

    /**
     * 创建一个配置路径
     * @param baseName
     * @return
     */
    public File mkConfigDir(String baseName){
        File file = new File(configBase, baseName);
        if(!file.exists()){
            file.mkdirs();
        }
        return file;
    }

    /**
     * 删除配置文件
     * @param module
     * @param baseName
     */
    public void dropConfig(String module, String baseName) {
        File configFile = new File(configBase, module+"/"+baseName);
        if (configFile.exists()){
            FileUtils.deleteQuietly(configFile);
        }
    }

    /**
     * 删除模块
     * @param module
     * @throws IOException
     */
    public void dropConfig(String module) throws IOException {
        File configFile = new File(configBase, module);
        if (configFile.exists()){
            FileUtils.deleteQuietly(configFile);
        }
    }

    /**
     * 获取路径相对于基础路径的路径
     * @param path
     * @return
     */
    public Path relativePath(Path path) {
        Path relativize = tmpBase.toPath().relativize(path);
        return relativize;
    }
}
