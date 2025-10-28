package io.github.wujun728.admin.page.inputRender;

import io.github.wujun728.admin.page.data.InputField;

import java.util.Map;

public class TreeSelectRender extends InputTreeRender{
    @Override
    protected void extra(Map<String, Object> config, InputField field) {
        super.extra(config,field);
        config.put("type","tree-select");
    }
}
