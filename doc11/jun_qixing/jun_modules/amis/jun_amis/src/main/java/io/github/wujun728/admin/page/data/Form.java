package io.github.wujun728.admin.page.data;

import io.github.wujun728.admin.common.BaseData;
import io.github.wujun728.admin.page.constants.Whether;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
public class Form extends BaseData {
    //编号
    private String code;
    //名称
    private String name;
    //主表
    private String tableName;

    //初始化接口
    private String initApi;
    //保存接口
    private String api;

    //字段列表
    private List<FormField> formFields = new ArrayList<>();

    //表单关联
    private List<FormRef> formRefs = new ArrayList<>();
    //表单按钮
    private List<FormButton> formButtons = new ArrayList<>();

    //弹出层大小
    private String size = "default";

    //字段宽度
    private Integer fieldWidth;

    //是否禁用
    private String disabled = Whether.NO;

    //初始化sql
    private String initSql = "";

    //前置接口
    private String beforeApi = "";
    //后置接口
    private String afterApi = "";

    //表单事件
    private String formEvent = "";

    //普通表单,向导表单,用向导表单时,分组字段为向导步骤
    private String formType;

    //自定义表单
    private String customForm;

    //扩展json
    private String extraJson;

    public String getInitSql() {
        return initSql == null ? "" : initSql;
    }

    public String getBeforeApi() {
        return beforeApi == null ? "" : beforeApi;
    }

    public String getAfterApi() {
        return afterApi == null ? "" : afterApi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Form form = (Form) o;
        return Objects.equals(code, form.code) && Objects.equals(name, form.name) && Objects.equals(tableName, form.tableName) && Objects.equals(initApi, form.initApi) && Objects.equals(api, form.api) && Objects.equals(size, form.size) && Objects.equals(fieldWidth, form.fieldWidth) && Objects.equals(disabled, form.disabled) && Objects.equals(initSql, form.initSql) && Objects.equals(beforeApi, form.beforeApi) && Objects.equals(afterApi, form.afterApi) && Objects.equals(formEvent, form.formEvent) && Objects.equals(formType, form.formType) && Objects.equals(customForm, form.customForm) && Objects.equals(extraJson, form.extraJson);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), code, name, tableName, initApi, api, size, fieldWidth, disabled, initSql, beforeApi, afterApi, formEvent, formType, customForm, extraJson);
    }
}
