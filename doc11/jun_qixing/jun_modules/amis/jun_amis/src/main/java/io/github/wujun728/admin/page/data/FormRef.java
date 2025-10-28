package io.github.wujun728.admin.page.data;

import io.github.wujun728.admin.common.BaseData;
import io.github.wujun728.admin.common.annotations.OrderBy;
import lombok.Data;

import java.util.Objects;

@Data
@OrderBy
public class FormRef extends BaseData {
    //主表单
    private Long formId;
    //关联页面
    private String refPageCode;
    //关联字段
    private String refField;
    //序号
    private Integer seq;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        FormRef formRef = (FormRef) o;
        return Objects.equals(formId, formRef.formId) && Objects.equals(refPageCode, formRef.refPageCode) && Objects.equals(refField, formRef.refField) && Objects.equals(seq, formRef.seq);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), formId, refPageCode, refField, seq);
    }
}
