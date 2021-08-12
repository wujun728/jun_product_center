package com.pearadmin.system.domain;

import com.pearadmin.common.web.base.BaseDomain;
import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * Describe: 字典类型领域模型
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */

@Data
@Alias("SysDictType")
public class SysDictType extends BaseDomain {

    /**
     * 标识
     * */
    private String id;

    /**
     * 字典名称
     * */
    private String typeName;

    /**
     * 字典类型
     * */
    private String typeCode;

    /**
     * 字典描述
     * */
    private String description;

    /**
     * 字典可用状态
     * */
    private String enable;

}
