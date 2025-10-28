package io.github.wujun728.admin.page.data;

import io.github.wujun728.admin.common.BaseData;
import lombok.Data;

import java.util.Objects;

@Data
public class BaseButton extends BaseData {
    //按钮名称
    private String label;
    //操作类型  ajax/弹出表单/二次确认
    private String optionType;
    //ajax请求url/表单编号
    private String optionValue;
    //级别
    private String level;
    //二次确认提示
    private String confirmText;
    //序号
    private int seq;
    //规则
    private String jsRule;
    //编号,用于按钮权限
    private String code;
    //图标
    private String icon;
    //是否提示,默认提示
    private String whetherConfirm;
    //弹出前校验
    private String beforePopApi;
    //扩展json配置
    private String extraJson;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BaseButton that = (BaseButton) o;
        return seq == that.seq && Objects.equals(label, that.label) && Objects.equals(optionType, that.optionType) && Objects.equals(optionValue, that.optionValue) && Objects.equals(level, that.level) && Objects.equals(confirmText, that.confirmText) && Objects.equals(jsRule, that.jsRule) && Objects.equals(code, that.code) && Objects.equals(icon, that.icon) && Objects.equals(whetherConfirm, that.whetherConfirm)&& Objects.equals(beforePopApi, that.beforePopApi)&& Objects.equals(extraJson, that.extraJson);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), label, optionType, optionValue, level, confirmText, seq, jsRule, code, icon, whetherConfirm,beforePopApi,extraJson);
    }
}
