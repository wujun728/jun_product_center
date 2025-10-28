package io.github.wujun728.admin.page.inputRender;

import io.github.wujun728.admin.page.data.InputField;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class EditorRender extends InputDefaultRender{
    @Override
    protected void extra(Map<String, Object> config, InputField field) {
        if(StringUtils.isNotBlank(field.getFormat())){
            config.put("language",field.getFormat());
        }
        config.put("allowFullscreen",true);
    }
}
