package io.github.wujun728.admin.page.inputRender;

import io.github.wujun728.admin.page.data.InputField;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class InputFileRender  extends InputDefaultRender{
    @Override
    protected void extra(Map<String, Object> config, InputField field) {
        config.put("accept","*");
        config.put("receiver","/admin/upload");
        config.put("useChunk",false);
        if(StringUtils.isNotBlank(field.getFormat())){
            config.put("accept",field.getFormat());
        }
    }
}
