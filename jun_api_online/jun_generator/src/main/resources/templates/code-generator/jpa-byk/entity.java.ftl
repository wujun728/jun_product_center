<#if isWithPackage?exists && isWithPackage==true>package ${packageName}.entity;</#if>

<#if isAutoImport?exists && isAutoImport==true>
<#if isLombok?exists && isLombok==true>import lombok.Data;</#if>
import java.util.Date;
import java.util.List;
import java.io.Serializable;
<#assign isSwagger=false />
<#if isSwagger?exists && isSwagger==true>
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;</#if>
import io.swagger.annotations.ApiModel;
import com.bjc.base.entity.LongEntity;
import lombok.EqualsAndHashCode;
import javax.persistence.*;
</#if>
/**
 * @description ${classInfo.classComment}
 * @author ${authorName}
 * @date ${.now?string('yyyy-MM-dd')}
 */
<#if isLombok?exists && isLombok==true>@Data</#if><#if isSwagger?exists && isSwagger==true>
@ApiModel("${classInfo.classComment}")</#if>
@EqualsAndHashCode(callSuper = false)
@Entity
@Table
@ApiModel(description = "${classInfo.classComment}")
public class ${classInfo.className}Entity  extends LongEntity  implements Serializable {

    private static final long serialVersionUID = 3724658580544666556L;

<#if classInfo.fieldList?exists && classInfo.fieldList?size gt 0>
<#list classInfo.fieldList as fieldItem >
    <#if isComment?exists && isComment==true>/**
    * ${fieldItem.fieldComment}
    */
    </#if>
    <#if isSwagger?exists && isSwagger==true>
    @ApiModelProperty("${fieldItem.fieldComment}")
    </#if>
    <#if fieldItem.isPrimaryKey==true>
    <#--@TableId(value = "${fieldItem.columnName}" ,type = IdType.AUTO )-->
    <#else>
    @Column(nullable = false)
    </#if>
    private ${fieldItem.fieldClass} ${fieldItem.fieldName};

    <#if isLombok?exists && isLombok==false>
    public ${fieldItem.fieldClass} get${fieldItem.fieldName?cap_first}() {
        return ${fieldItem.fieldName};
    }

    public void set${fieldItem.fieldName?cap_first}(${fieldItem.fieldClass} ${fieldItem.fieldName}) {
        this.${fieldItem.fieldName} = ${fieldItem.fieldName};
    }
</#if>
</#list>
</#if>

    @Override
    public void beforeCreate() {
        super.beforeCreate();
    }

    @Override
    public void beforeUpdate() {
        super.beforeUpdate();
    }

}
