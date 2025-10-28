package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.DictTreeEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author wangzongrun
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysTreeDictData  extends DictTreeEntity {

    private static final long serialVersionUID = 1L;


    /**
     * 树形类别
     */
    @Excel(name = "树形类别")
    private String treeDict;

    /**
     * 路径
     */
    @Excel(name = "路径")
    private String path;

    /**
     * 图标地址
     */
    @Excel(name = "图标地址")
    private String icon;


}
