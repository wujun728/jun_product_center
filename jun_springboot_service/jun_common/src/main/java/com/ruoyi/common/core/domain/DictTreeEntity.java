package com.ruoyi.common.core.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ruoyi.common.annotation.Excel;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Tree基类
 *
 * @author ruoyi
 */
@Data
public class DictTreeEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Excel(name = "主键")
    @TableId(value = "ID", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 名称
     */
    @Excel(name = "名称")
    private String label;

    /**
     * 编码
     */
    @Excel(name = "编码")
    private String code;

    /**
     * 备注
     */
    @Excel(name = "备注")
    private String remark;

    /**
     * 父节点ID
     */
    private String pid;

    /**
     * 显示顺序
     */
    private Integer orderNum;

    /**
     * 层次码
     * 说明：
     * 顶级层次码为0
     * 非顶级使用4位数字显示，起始位1000
     */
    private String levelCode;

    /**
     * 层次
     * 说明：
     * 结合层次码，计算树形结构的的层次
     */
    private Integer levelDepth;

    /**
     * 是否叶子节点
     * <p>
     * 说明：
     * 新建节点，默认为末级节点，并更新父节点为非末级节点
     */
    private String isLeaf;

    /**
     * 创建者
     */
    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新者
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 删除标志
     */
    @TableLogic
    private String delFlag;

    /**
     * 子节点
     */
    @TableField(exist = false)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<?> children = new ArrayList<>();
}
