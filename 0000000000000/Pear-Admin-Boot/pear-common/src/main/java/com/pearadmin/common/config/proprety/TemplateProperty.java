package com.pearadmin.common.config.proprety;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * Describe: 文 件 上 传 配 置 类
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
@Data
@Component
@ConfigurationProperties("pear.upload")
public class TemplateProperty {

    /**
     *  windows 系统文件上传路径
     * */
    private String windowsPath;

    /**
     * linux 系统文件上传路径
     * */
    private String linuxPath;

    /**
     * 是否启用ftp服务器
     */
    private boolean ftpUse;

    /**
     * ftp服务器url
     */
    private String hostname;

    /**
     * ftp服务器端口号
     */
    private int port;

    /**
     * ftp服务器用户名
     */
    private String username;

    /**
     * ftp服务器密码
     */
    private String password;

    /**
     * upload path 根据系统环境获取上传路径
     * */
    public String getUploadPath(){
        return '\\' == File.separatorChar ? this.windowsPath : this.linuxPath;
    }

}
