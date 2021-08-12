package com.pearadmin.system.domain;

import com.pearadmin.common.web.base.BaseDomain;
import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * Describe: 系 统 配 置
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */

@Data
@Alias("SysConfig")
public class SysConfig extends BaseDomain {

    /**
     * 配置标识
     * */
    private String configId;

    /**
     * 配置名称
     * */
    private String configName;

    /**
     * 配置类型
     * */
    private String configType;

    /**
     * 配置标识
     * */
    private String configCode;

    /**
     * 配置值
     * */
    private String configValue;

}
