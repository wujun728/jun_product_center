package io.github.wujun728.admin.page.service;

import io.github.wujun728.admin.common.BaseData;
import io.github.wujun728.admin.common.service.CacheService;
import io.github.wujun728.admin.page.data.BaseButton;
import io.github.wujun728.admin.page.data.Form;
import io.github.wujun728.admin.page.data.FormField;

import java.util.Map;

public interface FormService extends CacheService<Form> {
    void save(Form form);
    Form get(Long id);
    Form load(String code);
    void del(Form form);
    //重新生成关联数据
    void reload(Form form);

    Map<String,Object> getFormJson(String code, BaseButton button);
    Map<String,Object> getFormJson(Form form, BaseButton button);

    Map<String,Object> getPageJson(String code, BaseButton button);
    Map<String,Object> buildFormField(Form form, FormField formField);

    <T extends BaseData> T getObj(T t, String formCode);
}
