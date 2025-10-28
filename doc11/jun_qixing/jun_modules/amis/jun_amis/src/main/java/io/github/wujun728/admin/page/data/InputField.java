package io.github.wujun728.admin.page.data;

import io.github.wujun728.admin.common.BaseData;
import io.github.wujun728.admin.page.constants.Whether;
import io.github.wujun728.admin.rbac.service.InputParam;
import lombok.Data;

import java.util.Objects;

@Data
public class InputField extends BaseData implements InputParam {
    //字段
    private String field;
    //标签
    private String label;
    //参数值
    private String value;
    //是否隐藏
    private String hidden;
    //必填,目前必填的都是模板的值
    private String must = Whether.NO;
    //数据类型
    private String type;
    //格式化
    private String format;

    //宽度 1-12
    private Integer width;

    //日期表达式1
    private String dateExpress;

    //选项sql
    private String optionSql;

    //序号
    private int seq;

    //组件类型
    private String componentType;
    //是否多选
    private String multi;

    //标签提示
    private String labelRemark;

    //扩展json配置
    private String extraJson;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        InputField that = (InputField) o;
        return seq == that.seq && Objects.equals(field, that.field) && Objects.equals(label, that.label) && Objects.equals(value, that.value) && Objects.equals(hidden, that.hidden) && Objects.equals(must, that.must) && Objects.equals(type, that.type) && Objects.equals(format, that.format) && Objects.equals(width, that.width) && Objects.equals(dateExpress, that.dateExpress) && Objects.equals(optionSql, that.optionSql) && Objects.equals(componentType, that.componentType) && Objects.equals(multi, that.multi) && Objects.equals(labelRemark, that.labelRemark) && Objects.equals(extraJson, that.extraJson);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), field, label, value, hidden, must, type, format, width, dateExpress, optionSql, seq, componentType, multi, labelRemark, extraJson);
    }
}
