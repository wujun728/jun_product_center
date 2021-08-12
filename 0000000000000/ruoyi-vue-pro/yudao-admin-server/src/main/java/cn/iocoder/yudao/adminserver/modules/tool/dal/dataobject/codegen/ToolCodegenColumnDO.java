package cn.iocoder.yudao.adminserver.modules.tool.dal.dataobject.codegen;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import cn.iocoder.yudao.adminserver.modules.system.dal.dataobject.dict.SysDictTypeDO;
import cn.iocoder.yudao.adminserver.modules.tool.enums.codegen.ToolCodegenColumnHtmlTypeEnum;
import cn.iocoder.yudao.adminserver.modules.tool.enums.codegen.ToolCodegenColumnListConditionEnum;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 代码生成 column 字段定义
 *
 * @author 芋道源码
 */
@TableName(value = "tool_codegen_column", autoResultMap = true)
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class ToolCodegenColumnDO extends BaseDO {

    /**
     * ID 编号
     */
    @TableId
    private Long id;
    /**
     * 表编号
     *
     * 关联 {@link ToolCodegenTableDO#getId()}
     */
    private Long tableId;

    // ========== 表相关字段 ==========

    /**
     * 字段名
     */
    private String columnName;
    /**
     * 字段类型
     */
    private String columnType;
    /**
     * 字段描述
     */
    private String columnComment;
    /**
     * 是否允许为空
     */
    private Boolean nullable;
    /**
     * 是否主键
     */
    private Boolean primaryKey;
    /**
     * 是否自增
     */
    private Boolean autoIncrement;
    /**
     * 排序
     */
    private Integer ordinalPosition;

    // ========== Java 相关字段 ==========

    /**
     * Java 属性类型
     *
     * 例如说 String、Boolean 等等
     */
    private String javaType;
    /**
     * Java 属性名
     */
//    @NotBlank(message = "Java属性不能为空")
    private String javaField;
    /**
     * 字典类型
     *
     * 关联 {@link SysDictTypeDO#getType()}
     */
    private String dictType;
    /**
     * 数据示例，主要用于生成 Swagger 注解的 example 字段
     */
    private String example;

    // ========== CRUD 相关字段 ==========

    /**
     * 是否为 Create 创建操作的字段
     */
    private Boolean createOperation;
    /**
     * 是否为 Update 更新操作的字段
     */
    private Boolean updateOperation;
    /**
     * 是否为 List 查询操作的字段
     */
    private Boolean listOperation;
    /**
     * List 查询操作的条件类型
     *
     * 枚举 {@link ToolCodegenColumnListConditionEnum}
     */
    private String listOperationCondition;
    /**
     * 是否为 List 查询操作的返回字段
     */
    private Boolean listOperationResult;

    // ========== UI 相关字段 ==========

    /**
     * 显示类型
     *
     * 枚举 {@link ToolCodegenColumnHtmlTypeEnum}
     */
    private String htmlType;

}
