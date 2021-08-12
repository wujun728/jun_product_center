package ${beanConfig.packageName};

<#list bean.imports as pkg>
import ${pkg};
</#list>
<#if beanConfig.swagger2>
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
</#if>
<#if beanConfig.lombok>
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
</#if>
<#if beanConfig.persistence>
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
</#if>

/**
* <p>
* ${table.actualTableName.catalog!} ${table.actualTableName.schema!} ${table.actualTableName.tableName}
* ${table.table.remark!}
* </p>
*
* @author ${author}
* @since ${date} ${time}
*/
<#if beanConfig.lombok>
@Data
</#if>
<#if beanConfig.persistence>
@Entity
@Table(name="${table.actualTableName.tableName}")
</#if>
<#if beanConfig.swagger2>
@ApiModel(value="${bean.className}", description="${table.table.remark!}")
</#if>
<#if beanConfig.supperClass??>
public class ${bean.className} extends ${beanConfig.supperClass} <#if beanConfig.serializer>implements Serializable</#if> {
<#else>
public class ${bean.className} <#if beanConfig.serializer>implements Serializable</#if> {
</#if>
<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list bean.fields as field>
    /*
    * <p>
    * ${field.comment!}
    * </p>
    */
    <#if beanConfig.persistence>
    @Column(name="${field.column.columnName}",length=${field.column.columnSize},precision=${field.column.decimalDigits})
    </#if>
    <#if beanConfig.swagger2>
    @ApiModelProperty(value = "${field.comment!}")
    </#if>
    <#if field.key>
    @Id
    </#if>
    private ${field.typeName} ${field.fieldName};

</#list>
<#------------  END 字段循环遍历  ---------->

<#----- 如果不是 lombok ,则需要生成 get set 方法 --->
<#if !beanConfig.lombok>
    <#list bean.fields as field>
        <#if field.typeName == "boolean">
            <#assign getprefix="is"/>
        <#else>
            <#assign getprefix="get"/>
        </#if>

    public ${field.typeName} ${getprefix}${field.capitalName}() {
        return ${field.fieldName};
    }

    public void set${field.capitalName}(${field.typeName} ${field.fieldName}) {
        this.${field.fieldName} = ${field.fieldName};
    }
    </#list>
</#if>

}