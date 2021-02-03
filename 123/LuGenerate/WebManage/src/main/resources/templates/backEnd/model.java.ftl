package ${package.Entity};

import com.baomidou.mybatisplus.annotation.IdType;
<#list table.importPackages as pkg>
import ${pkg};
</#list>
<#if swagger2>
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
</#if>
<#if entityLombokModel>
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
</#if>

/**
 * @Project ${cfg.project}
 * @Author: ${author}
 * @Date: ${cfg.createTime}
 * @Description: ${table.comment!}
 */
<#if entityLombokModel>
@Data
@Accessors(chain = true)
</#if>
<#if table.convert>
@TableName("${table.name}")
</#if>
<#if swagger2>
@ApiModel(value="${entity}对象", description="${table.comment!}")
</#if>
<#if superEntityClass??>
public class ${entity} extends ${superEntityClass}<#if activeRecord><${entity}></#if> {
<#elseif activeRecord>
public class ${entity} extends Model<${entity}> {
<#else>
public class ${entity} implements Serializable {
</#if>

<#if entitySerialVersionUID>
    private static final long serialVersionUID = 1L;
</#if>
<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>
<#if field.keyFlag>
    <#assign keyPropertyName="${field.propertyName}"/>
</#if>

<#if field.comment!?length gt 0>
<#if swagger2>
    @ApiModelProperty(value = "${field.comment}")
<#else>
    //${field.comment}
</#if>
</#if>
<#-- 主键 -->
<#if field.keyFlag>
<#if field.keyIdentityFlag>
    @TableId(value = "${field.name}", type = IdType.AUTO)
<#elseif idType??>
    @TableId(value = "${field.name}", type = IdType.${idType})
<#elseif field.convert>
    @TableId(value = "${field.name}", type = IdType.ID_WORKER_STR)
</#if>
<#-- 普通字段 -->
<#elseif field.fill??>
<#-- -----   存在字段填充设置   ----->
<#if field.convert>
    @TableField(value = "${field.name}", fill = FieldFill.${field.fill})
<#else>
    @TableField(fill = FieldFill.${field.fill})
</#if>
<#elseif field.convert>
    @TableField("${field.name}")
</#if>
<#-- 乐观锁注解 -->
<#if (versionFieldName!"") == field.name>
    @Version
</#if>
<#-- 逻辑删除注解 -->
<#if (logicDeleteFieldName!"") == field.name>
    @TableLogic
</#if>
    private ${field.propertyType} ${field.propertyName};
</#list>

}
