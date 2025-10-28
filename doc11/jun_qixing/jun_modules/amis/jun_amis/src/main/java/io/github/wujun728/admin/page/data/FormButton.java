package io.github.wujun728.admin.page.data;

import io.github.wujun728.admin.page.constants.Whether;
import lombok.Data;

import java.util.Objects;

@Data
public class FormButton extends BaseButton {
    //表单id
    private Long formId;
    //是否关闭
    private String close = Whether.NO;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        FormButton that = (FormButton) o;
        return Objects.equals(formId, that.formId) && Objects.equals(close, that.close);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), formId, close);
    }
}
