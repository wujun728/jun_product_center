package com.pearadmin.plugin.configure.document;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import springfox.documentation.service.Contact;

/**
 * Document Application Properties Mapper  -- [就眠仪式]
 */
@Data
@ConfigurationProperties("pear.plugin.document")
public class DocumentAutoProperties {

    /** 是 否 开 启 */
    private Boolean enable = true;

    /** 分 组 名 称 */
    private String groupName;

    /** 系 统 标 题 */
    private String title;

    /** 描 述 信 息 */
    private String describe;

    /** 版 本 信 息 */
    private String version;

    /** 扫 描 路 径 */
    private String scanPackage;

    /** 扩 展 信 息 */
    private Contact contact;

    /** 协 议 */
    private String licence;

    /** 协 议 链 接 */
    private String licenceUrl;

    /** 组 织 链 接 */
    private String termsOfServiceUrl;

}
