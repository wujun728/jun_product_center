package io.github.wujun728.admin.page.data;

import io.github.wujun728.admin.common.annotations.OrderBy;
import lombok.Data;

import java.util.Objects;

@Data
@OrderBy
public class FormField extends InputField{
    //表单id
    private Long formId;
    //是否禁用
    private String disabled;
    //校验重复类型
    private String checkRepeatType;
    //校验重复配置
    private String checkRepeatConfig;
    //校验重复提示
    private String checkRepeatTip;
    //表单项校验
    private String validations;
    //分组名称
    private String groupName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        FormField formField = (FormField) o;
        return Objects.equals(formId, formField.formId) && Objects.equals(disabled, formField.disabled) && Objects.equals(checkRepeatType, formField.checkRepeatType) && Objects.equals(checkRepeatConfig, formField.checkRepeatConfig) && Objects.equals(checkRepeatTip, formField.checkRepeatTip) && Objects.equals(validations, formField.validations) && Objects.equals(groupName, formField.groupName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), formId, disabled, checkRepeatType, checkRepeatConfig, checkRepeatTip, validations, groupName);
    }
}
